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

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.I2C;

import org.wildstang.year2022.subsystems.swerve.SwerveDrive;

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
    
    public double x;
    public double y;

    private double modifier;

    public boolean TargetInView;

    private double TargetDistance;
    private double Angle;

    private DigitalInput rightBumper, dup, ddown;

    private LimeConsts LC;

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
    }

    public double getDistance(){ //update target dist. for internal use. Distance is in feet.
        calcTargetCoords();
        //h = lsin(0), d = lcos(0)
        // l = h/sin(0) = d/cos(0)
        // d = cos(0)*h/sin(0) = h/tan(0)
        // TargetDistance = LC.TARGET_HEIGHT/Math.tan(ty.getDouble(0)+(Math.PI*LC.CAMERA_ANGLE_OFFSET/180));
        //double distance = (75.5 / Math.sin(Math.toRadians(20 + getTYValue()))) / 12.0;
        TargetDistance = (modifier*12) + 48 + LC.TARGET_HEIGHT / Math.tan(Math.toRadians(ty.getDouble(0) + LC.CAMERA_ANGLE_OFFSET));
        return TargetDistance;
    }
    
    public double getRotPID(){
        calcTargetCoords();
        return tx.getDouble(0) * -0.015;
    }

    public double getAngle(){ //get hood angle for autoaim
        getDistance();
        return ApproximateAngle(TargetDistance);
    }
    public double ApproximateAngle(double dist){ //linear interlopation
        double[] dists = LC.Dists;
        int max = dists.length-1;
        int min = 0;
        int c = 0;
        boolean done = false;
        boolean exact = false;
        while(done == false){
            if(max-min <= 1){
                done = true;
                c = max;
            }
            else{
                c = (int) ((max-min)/2)+min;
                
                if(dists[c]>dist){
                    max = c;
                }
                else if(dists[c] < dist){
                    min = c;
                }
                else{
                    exact = true;
                    done = true;
                }
                
            }

            
        }
        double out = 0;
        // now c is index of nearest value (rounded up)
        if(exact){
            out = LC.Angles[c];
        }
        else{
            if(c-1 >= min){
                double interval = dists[c]-dists[c-1];
                double range = LC.Angles[c]-LC.Angles[c-1];
                out = (((dist-dists[c-1])/interval)*range)+dists[c-1];
            }
            else{ //outside of domain
                out = LC.Angles[c];
            }


        }
        return out;
    
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

        rightBumper = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_RIGHT_SHOULDER);
        rightBumper.addInputListener(this);
        dup = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_DPAD_UP);
        dup.addInputListener(this);
        ddown = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_DPAD_DOWN);
        ddown.addInputListener(this);
        resetState();
        
    }
    @Override
    public void selfTest() {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void update() {
        calcTargetCoords();
        SmartDashboard.putNumber("limelight distance", getDistance());    
        SmartDashboard.putNumber("limelight tx", tx.getDouble(0));
        SmartDashboard.putNumber("limelight ty", ty.getDouble(0));  
        SmartDashboard.putBoolean("limelight target in view", tv.getDouble(0)==1);  
        SmartDashboard.putNumber("Distance Modifier", modifier);
    }

    @Override
    public void resetState() {
        turnOnLED(false);
        modifier = 0;
        
    }
    @Override
    public String getName() {
        return "Limelight";
    }

    

}