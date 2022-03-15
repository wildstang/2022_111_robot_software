package org.wildstang.year2022.subsystems.launcher;


import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.wildstang.hardware.roborio.outputs.WsSolenoid;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.subsystems.Subsystem;

/**
 * Class:       Launcher.java
 * Inputs:      1 DigitalInput (Right Trigger)
 * Outputs:     3 Neo 550
 * Description: Right Trigger to Shoot
 */
public class Launcher implements Subsystem {

    // inputs
    private AnalogInput launchButton, speedButton;
    private DigitalInput leftBumper, rightBumper, yButton;

    // outputs
    private WsSparkMax kickerMotor;
    private WsSparkMax flywheelMotor;
    private WsSolenoid latch;

    // variables
    private LauncherModes launchMode;
<<<<<<< Updated upstream
    private boolean isRunning;
=======
    public boolean LAUNCHER_RUNNING;
    private boolean isAiming;
>>>>>>> Stashed changes
    private boolean latchValue;

    private final double threshold = 0.7;
    
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
    }

    public void initOutputs() {
        kickerMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.KICKER);
        flywheelMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.LAUNCHER);
        latch = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.LAUNCHER_SOLENOID);
        kickerMotor.setCurrentLimit(25, 25, 0);
        flywheelMotor.setCurrentLimit(50, 50, 0);

    }

    // update the subsystem everytime the framework updates (every ~0.02 seconds)
    public void update() {
        latch.setValue(latchValue);
<<<<<<< Updated upstream
        if (!isRunning){
            flywheelMotor.setSpeed(0);
            kickerMotor.setSpeed(0);
        } else {
            if (Math.abs(flywheelMotor.getVelocity()) < threshold*launchMode.getRPM()){
=======
        if (isAiming){
            double distance = aimHelper.getDistance();
            aimDistance = REG_A * distance*distance + REG_B * distance + REG_C;
            kickerMotor.setSpeed(1.0);
            if (Math.abs(flywheelMotor.getVelocity()) < threshold*aimDistance*CONVERSION){
                flywheelMotor.setSpeed(-1.0);
            } else {
                flywheelMotor.setSpeed(-aimDistance - 0.00001*(aimDistance*CONVERSION-Math.abs(flywheelMotor.getVelocity())));
            }
        } else if (LAUNCHER_RUNNING) {
            if (Math.abs(flywheelMotor.getVelocity()) < threshold*launchMode.getSpeed()*CONVERSION){
>>>>>>> Stashed changes
                flywheelMotor.setSpeed(-1.0);
                kickerMotor.setSpeed(1.0);
            } else {
                flywheelMotor.setSpeed(-launchMode.getSpeed());
                kickerMotor.setSpeed(1.0);
            }
<<<<<<< Updated upstream
=======
        } else if (isLow){
            flywheelMotor.setSpeed(-0.23);
            kickerMotor.setSpeed(1.0);
        } else if (!LAUNCHER_RUNNING){
            flywheelMotor.setSpeed(0);
            kickerMotor.setSpeed(0);
>>>>>>> Stashed changes
        }

        
        SmartDashboard.putNumber("kicker output current", kickerMotor.getController().getOutputCurrent());
        SmartDashboard.putNumber("Flywheel velocity", -flywheelMotor.getVelocity());
        SmartDashboard.putNumber("Flywheel percent output", -launchMode.getSpeed());
    }

    // respond to input updates
    public void inputUpdate(Input source) {
        if (Math.abs(launchButton.getValue()) > 0.5 || yButton.getValue()){
            latchValue = false;
        } else {
            latchValue = true;
        }
        if (Math.abs(speedButton.getValue()) > 0.5){
            LAUNCHER_RUNNING = true;
        } else {
            LAUNCHER_RUNNING = false;
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
    }

    // used for testing
    public void selfTest() {}

    // resets all variables to the default state
    public void resetState() {
        launchMode = LauncherModes.FENDER_SHOT;
<<<<<<< Updated upstream
        isRunning = false;
=======
        LAUNCHER_RUNNING = false;
        isAiming = false;
>>>>>>> Stashed changes
        latchValue = true;
    }

    // returns the unique name of the subsystem
    public String getName() {
        return "Launcher";
    }

    public void setLauncher(LauncherModes modeToUse){
        launchMode = modeToUse;
        LAUNCHER_RUNNING = true;
    }
    public void stopLauncher(){
        LAUNCHER_RUNNING = false;
        launchMode = LauncherModes.ZERO;
    }
    /**
     * @param firing true to fire, false for not
     */
    public void fire(boolean firing){
        latchValue = !firing;
    }

}
