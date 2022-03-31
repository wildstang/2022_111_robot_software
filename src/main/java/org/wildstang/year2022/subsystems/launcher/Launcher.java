package org.wildstang.year2022.subsystems.launcher;


import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.Hood.AimHelper;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.wildstang.hardware.roborio.outputs.WsSolenoid;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.subsystems.Subsystem;

import org.wildstang.year2022.subsystems.swerve.SwerveDrive;

/**
 * Class:       Launcher.java
 * Inputs:      1 DigitalInput (Right Trigger)
 * Outputs:     3 Neo 550
 * Description: Right Trigger to Shoot
 */
public class Launcher implements Subsystem {

    // inputs
    private AnalogInput launchButton, speedButton, driverShoot;
    private DigitalInput leftBumper, rightBumper, yButton, driverAim;


    // outputs
    private WsSparkMax kickerMotor;
    private WsSparkMax flywheelMotor;
    private WsSolenoid latch;

    // variables
    private LauncherModes launchMode;
    private boolean isRunning;
    private boolean isAiming;
    private boolean latchValue;
    private boolean isLow;
    private double aimDistance;

    private final double threshold = 0.01;
    private final double CONVERSION = 5500;
    private final double REG_A = -0.000001488;//0.000015406;//-0.0000040441;//0.0000001497
    private final double REG_B = 0.002265;//-0.002584;//0.003158;//0.002085;
    private final double REG_C = 0.149;//.48595;//0.08335;//0.1497;


    private SwerveDrive swerveDrive;

    private AimHelper aimHelper;

    private double findDistanceWithVelocity(double fedDistance){
        double CurrentOffset = Math.toRadians(aimHelper.getRotPID()/-.0015);
        double WheelOffset_ABS = swerveDrive.getGyroAngle() - CurrentOffset;

        //Find robot translational speed towards hub and tangent to hub
        double TAN_Speed = swerveDrive.xSpeed/Math.cos(CurrentOffset) * swerveDrive.MotorVelocityParam * Math.cos(WheelOffset_ABS);
        double Direct_Speed = swerveDrive.xSpeed/Math.cos(CurrentOffset) * swerveDrive.MotorVelocityParam * Math.sin(WheelOffset_ABS); //Lower left wheel

        //Find current limelight offset and theta of offset (shooting triangle)
        double AimOffset = (aimHelper.getDistance()*swerveDrive.AimOffsetParam) * TAN_Speed;
        double AimOffset_THETA = Math.atan(AimOffset/(aimHelper.getDistance()+Direct_Speed*swerveDrive.RobotVelocityParam));

        //Takes distance + d(position)/dt *  VelocityParameter then multiplies by Inv_Sin of Shooting Triangle
        double newDistance = (fedDistance + Direct_Speed * swerveDrive.RobotVelocityParam)/Math.sin(AimOffset_THETA);

        return newDistance;
    }

    
    // initializes the subsystem
    public void init() {
        initInputs();
        initOutputs();
        resetState();
    }

    public void initInputs() {
        launchButton = (AnalogInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_RIGHT_TRIGGER);
        launchButton.addInputListener(this);
        speedButton = (AnalogInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_LEFT_TRIGGER);
        speedButton.addInputListener(this);
        leftBumper = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_LEFT_SHOULDER);
        leftBumper.addInputListener(this);
        rightBumper = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_RIGHT_SHOULDER);
        rightBumper.addInputListener(this);
        yButton = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_FACE_UP);
        yButton.addInputListener(this);
        driverShoot = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_TRIGGER);
        driverShoot.addInputListener(this);
        driverAim = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_RIGHT_SHOULDER);
        driverAim.addInputListener(this);
    }

    public void initOutputs() {
        kickerMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.KICKER);
        flywheelMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.LAUNCHER);
        latch = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.LAUNCHER_SOLENOID);
        kickerMotor.setCurrentLimit(25, 25, 0);
        flywheelMotor.setCurrentLimit(50, 50, 0);

        aimHelper = (AimHelper) Core.getSubsystemManager().getSubsystem(WSSubsystems.LIMELIGHT);
        swerveDrive = (SwerveDrive) Core.getSubsystemManager().getSubsystem(WSSubsystems.SWERVE_DRIVE);
    }

    // update the subsystem everytime the framework updates (every ~0.02 seconds)
    public void update() {
        latch.setValue(latchValue);
        if (isAiming){
            double distance = aimHelper.getDistance();
            distance = findDistanceWithVelocity(distance);
            aimDistance = REG_A * distance*distance + REG_B * distance + REG_C;
            kickerMotor.setSpeed(1.0);
            if (Math.abs(flywheelMotor.getVelocity()) < threshold*aimDistance*CONVERSION){
                flywheelMotor.setSpeed(-1.0);
            } else {
                flywheelMotor.setSpeed(-aimDistance - 0.00001*(aimDistance*CONVERSION-Math.abs(flywheelMotor.getVelocity())));
            }
        } else if (isRunning) {
            if (Math.abs(flywheelMotor.getVelocity()) < threshold*launchMode.getSpeed()*CONVERSION){
                flywheelMotor.setSpeed(-1.0);
                kickerMotor.setSpeed(1.0);
            } else {
                flywheelMotor.setSpeed( -launchMode.getSpeed() - 0.0001*(launchMode.getSpeed()*CONVERSION-Math.abs(flywheelMotor.getVelocity())));
                kickerMotor.setSpeed(1.0);
            }
        } else if (isLow){
            flywheelMotor.setSpeed(-0.23);
            kickerMotor.setSpeed(1.0);
        } else if (!isRunning){
            flywheelMotor.setSpeed(0);
            kickerMotor.setSpeed(0);
        }
        
        SmartDashboard.putNumber("kicker output current", kickerMotor.getController().getOutputCurrent());
        SmartDashboard.putNumber("Flywheel velocity", -flywheelMotor.getVelocity());
        SmartDashboard.putNumber("Flywheel percent output", -launchMode.getSpeed());
        SmartDashboard.putNumber("Flywheel limelight power", aimDistance);
        SmartDashboard.putBoolean("flywheel is aiming", isAiming);
    }

    // respond to input updates
    public void inputUpdate(Input source) {
        
        if (Math.abs(launchButton.getValue()) > 0.5 || yButton.getValue()){
            latchValue = false;
        } else {
            latchValue = true;
        }
        if (Math.abs(speedButton.getValue()) > 0.5){
            isRunning = true;
        } else {
            isRunning = false;
        }
        if (Math.abs(launchButton.getValue())>0.15){
            isLow = true;
        } else {
            isLow = false;
        }
        if (source == leftBumper && leftBumper.getValue()){
            if (rightBumper.getValue()){
                launchMode = LauncherModes.TARMAC_EDGE;
            } else {
                launchMode = LauncherModes.FENDER_SHOT;
            }
        }
        if (source == rightBumper && rightBumper.getValue()){
            if (leftBumper.getValue()){
                launchMode = LauncherModes.TARMAC_EDGE;
            } else {
                launchMode = LauncherModes.LAUNCH_PAD;
            }
        }
        if (driverAim.getValue()){
            isAiming = true;
        } else {
            isAiming = false;
        }
    }

    // used for testing
    public void selfTest() {}

    // resets all variables to the default state
    public void resetState() {
        launchMode = LauncherModes.FENDER_SHOT;
        isRunning = false;
        isAiming = false;
        latchValue = true;
        aimDistance = 0;
    }

    // returns the unique name of the subsystem
    public String getName() {
        return "Launcher";
    }

    public void setLauncher(LauncherModes modeToUse){
        launchMode = modeToUse;
        isRunning = true;
    }
    public void setAiming(boolean toAim){
        isAiming = toAim;
    }
    public void stopLauncher(){
        isRunning = false;
        launchMode = LauncherModes.ZERO;
    }
    /**
     * @param firing true to fire, false for not
     */
    public void fire(boolean firing){
        latchValue = !firing;
    }

}
