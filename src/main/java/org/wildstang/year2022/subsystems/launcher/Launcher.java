package org.wildstang.year2022.subsystems.launcher;


import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;
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
    private DigitalInput leftBumper, rightBumper;

    // outputs
    private WsSparkMax kickerMotor;
    private WsSparkMax flywheelMotor;
    private WsSolenoid latch;

    // variables
    private LauncherModes launchMode;
    private boolean isRunning;
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
    }

    public void initOutputs() {
        kickerMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.KICKER);
        flywheelMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.LAUNCHER);
        latch = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.LAUNCHER_SOLENOID);

    }

    // update the subsystem everytime the framework updates (every ~0.02 seconds)
    public void update() {
        latch.setValue(latchValue);
        if (!isRunning){
            kickerMotor.setSpeed(0);
            flywheelMotor.setSpeed(0);
        } else {
            if (flywheelMotor.getVelocity() < threshold*launchMode.getRPM()){
                flywheelMotor.setSpeed(-1.0);
            } else {
                flywheelMotor.setSpeed(-launchMode.getSpeed());
            }
        }
    }

    // respond to input updates
    public void inputUpdate(Input source) {
        if (Math.abs(launchButton.getValue()) > 0.5){
            latchValue = false;
        } else {
            latchValue = true;
        }
        if (Math.abs(speedButton.getValue()) > 0.5){
            isRunning = true;
        } else {
            isRunning = false;
        }
        if (source == leftBumper && leftBumper.getValue()){
            if (rightBumper.getValue()){
                launchMode = LauncherModes.LAUNCH_PAD;
            } else {
                launchMode = LauncherModes.FENDER_SHOT;
            }
        }
        if (source == rightBumper && rightBumper.getValue()){
            if (leftBumper.getValue()){
                launchMode = LauncherModes.LAUNCH_PAD;
            } else {
                launchMode = LauncherModes.TARMAC_EDGE;
            }
        }
    }

    // used for testing
    public void selfTest() {}

    // resets all variables to the default state
    public void resetState() {
        launchMode = LauncherModes.ZERO;
        isRunning = false;
        latchValue = true;
    }

    // returns the unique name of the subsystem
    public String getName() {
        return "Launcher";
    }

   

 

    


}
