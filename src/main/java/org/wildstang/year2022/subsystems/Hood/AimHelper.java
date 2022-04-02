package org.wildstang.year2022.subsystems.Hood; 
// ton of imports
import org.wildstang.framework.subsystems.Subsystem;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.kauailabs.navx.frc.AHRS;

import org.wildstang.framework.core.Core;
import com.revrobotics.CANSparkMax;

import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.logger.Log;
import org.wildstang.framework.pid.PIDConstants;
import org.wildstang.framework.subsystems.drive.Path;
import org.wildstang.framework.subsystems.drive.PathFollowingDrive;
import org.wildstang.framework.subsystems.drive.TankPath;
import org.wildstang.hardware.roborio.inputs.WsAnalogInput;
import org.wildstang.hardware.roborio.inputs.WsDigitalInput;
import org.wildstang.hardware.roborio.inputs.WsJoystickButton;
import org.wildstang.hardware.roborio.outputs.WsPhoenix;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;
import org.wildstang.year2022.robot.WSSubsystems;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.I2C;

import org.wildstang.year2022.subsystems.swerve.DriveConstants;
import org.wildstang.year2022.subsystems.swerve.SwerveDrive;
import org.wildstang.year2022.subsystems.swerve.WSSwerveHelper;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import org.wildstang.year2022.subsystems.Hood.LimeConsts;

import java.util.Arrays;

public class AimHelper implements Subsystem{
    
    private NetworkTable LimeTable;
    private NetworkTableEntry ty; //y angle
    private NetworkTableEntry tx; //x angle
    private NetworkTableEntry tv;
    private NetworkTableEntry ledModeEntry;
    private NetworkTableEntry llModeEntry;

    //private SwerveDrive swerve;
    private WSSwerveHelper helper;
    
    public double x;
    public double y;

    private double modifier;

    public boolean TargetInView;

    private double TargetDistance;
    private double Angle;
    private double xSpeed, ySpeed;

    private double perpFactor, parFactor;

    private DigitalInput rightBumper, dup, ddown;
    private AnalogInput leftStickX, leftStickY;

    private LimeConsts LC;

    private double gyroValue;

    private double distanceFactor = 10;
    private double angleFactor = 20;

    ShuffleboardTab tab = Shuffleboard.getTab("Tab");
    SimpleWidget distance = tab.add("SWM distance", 60);
    SimpleWidget angle = tab.add("SWM angle", 15);


    public void calcTargetCoords(){ //update target coords. 
        if(tv.getDouble(0) == 1){
            x = tx.getDouble(0);
            y = ty.getDouble(0);
            TargetInView = true;
        }
        else{
            x = 0; //no target case
            y = 0;
            TargetInView = false;
        }
        getMovingCoords();
    }

    public void getMovingCoords(){
        double robotAngle = (getGyroAngle()+180)%360;
        double movementAngle = helper.getDirection(xSpeed, ySpeed);
        //double movementMagnitude = helper.getMagnitude(xSpeed, ySpeed);
        //double movementMagnitude = 1;
        if (Math.abs(xSpeed) < 0.1 && Math.abs(ySpeed) < 0.1){
            parFactor = 0;
            perpFactor = 0;
        } else {
            // if (Math.sin(Math.toRadians(-robotAngle + movementAngle)) > 0){
            //     parFactor = 15;
            // } else {
            //     parFactor = -15;
            // }
            perpFactor = distanceFactor * Math.cos(Math.toRadians(-robotAngle + movementAngle));
            parFactor = angleFactor * Math.sin(Math.toRadians(-robotAngle + movementAngle));
        }
        
        //double tofFactor = 0.8 + 0.2*(((modifier*12) + 48 + LC.TARGET_HEIGHT / Math.tan(Math.toRadians(ty.getDouble(0) + LC.CAMERA_ANGLE_OFFSET)))-115)/60;
        if (!TargetInView){
            parFactor *= -0.2;
        }
        //perpFactor *= tofFactor;
        //parFactor *= tofFactor;
    }
    private double getGyroAngle(){
        return gyroValue;
    }
    public void setGyroValue(double toSet){
        gyroValue = toSet;
    }

    public double getDistance(){
        calcTargetCoords();
        TargetDistance = (modifier*12) + 48 + LC.TARGET_HEIGHT / Math.tan(Math.toRadians(ty.getDouble(0) + LC.CAMERA_ANGLE_OFFSET));
        //return TargetDistance;
        return TargetDistance - perpFactor;
    }
    
    public double getRotPID(){
        calcTargetCoords();
        //return tx.getDouble(0) * -0.015;
        return (tx.getDouble(0) - parFactor) * -0.015;
    }

    public void turnOnLED(boolean onState){
        if (onState) {
            ledModeEntry.setNumber(0);//turn led on
            llModeEntry.setNumber(0);//turn camera to vision tracking
        } else {
            ledModeEntry.setNumber(1);//turn led off
            llModeEntry.setNumber(1);//turn camera to normal color mode
        }
    }
    @Override
    public void inputUpdate(Input source) {
        turnOnLED(rightBumper.getValue());
        if (source == dup && dup.getValue()){
            modifier++;
        }
        if (source == ddown && ddown.getValue()){
            modifier--;
        }
        xSpeed = leftStickX.getValue();
        ySpeed = leftStickY.getValue();
        if (Math.abs(leftStickX.getValue()) < DriveConstants.DEADBAND) xSpeed = 0;
        if (Math.abs(leftStickY.getValue()) < DriveConstants.DEADBAND) ySpeed = 0;
        
    }
    @Override
    public void init() {
        LC = new LimeConsts();
        x = 0;  //x and y angular offsets from limelight. Only updated when calcTargetCoords is called.
        y = 0;
        TargetInView = false; //is the target in view? only updated when calcTargetCoords is called.
        TargetDistance = 0; //distance to target in feet. Only updated when calcTargetCoords is called.


        LimeTable  = NetworkTableInstance.getDefault().getTable("limelight");

        ty = LimeTable.getEntry("ty");
        tx = LimeTable.getEntry("tx");
        tv = LimeTable.getEntry("tv");
        ledModeEntry = LimeTable.getEntry("ledMode");
        llModeEntry = LimeTable.getEntry("camMode");

        helper = new WSSwerveHelper();

        rightBumper = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_RIGHT_SHOULDER);
        rightBumper.addInputListener(this);
        leftStickX = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_JOYSTICK_X);
        leftStickX.addInputListener(this);
        leftStickY = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_JOYSTICK_Y);
        leftStickY.addInputListener(this);
        dup = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_DPAD_UP);
        dup.addInputListener(this);
        ddown = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_DPAD_DOWN);
        ddown.addInputListener(this);
        //swerve = (SwerveDrive) Core.getSubsystemManager().getSubsystem(WSSubsystems.SWERVE_DRIVE);
        resetState();
        
    }
    @Override
    public void selfTest() {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void update() {
        calcTargetCoords();
        distanceFactor = distance.getEntry().getDouble(0);
        angleFactor = angle.getEntry().getDouble(0);
        SmartDashboard.putNumber("limelight distance", getDistance());    
        SmartDashboard.putNumber("limelight tx", tx.getDouble(0));
        SmartDashboard.putNumber("limelight ty", ty.getDouble(0));  
        SmartDashboard.putBoolean("limelight target in view", tv.getDouble(0)==1);  
        SmartDashboard.putNumber("Distance Modifier", modifier);
        SmartDashboard.putNumber("SWM parFactor", parFactor);
        SmartDashboard.putNumber("SWM perpFactor", perpFactor);
    }

    @Override
    public void resetState() {
        turnOnLED(false);
        modifier = 0;
        xSpeed = 0;
        ySpeed = 0;
        perpFactor = 0;
        parFactor = 0;
        gyroValue = 0;
        
    }
    @Override
    public String getName() {
        return "Limelight";
    }

    

}