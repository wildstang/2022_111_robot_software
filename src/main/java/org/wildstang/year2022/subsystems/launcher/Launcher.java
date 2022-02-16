package org.wildstang.year2022.subsystems.launcher;


import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;
import org.wildstang.hardware.roborio.outputs.WsSolenoid;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.subsystems.Subsystem;

/**
 * Class:       Launcher.java
 * Inputs:      1 DigitalInput (Right Trigger)
 * Outputs:     3 Neo 550
 * Description: Right Trigger to Shoot
 */
public class Launcher implements Subsystem {

    // inputs
    private AnalogInput launchButton;

    // outputs
    private WsSparkMax kickerMotor;
    private WsSparkMax flywheelMotor;
    private WsSolenoid latch;

    // variables
    private double speed = 0.0;
    private boolean latchValue;

    private final double maxPower = 1.0;
    private final double maxOutputVelocity = 240.0;
    private final double outputVelocityThresholdPercent = 0.9;
    private final double thresholdVelocity = maxOutputVelocity * outputVelocityThresholdPercent;
    private final double thresholdPower = 0.7;
    
    // initializes the subsystem
    public void init() {
        initInputs();
        initOutputs();
        resetState();
    }

    public void initInputs() {
        launchButton = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_RIGHT_TRIGGER.getName());
        launchButton.addInputListener(this);
    }

    public void initOutputs() {
        kickerMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.KICKER);
        flywheelMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.FLYWHEEL);
        latch = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.BALL_LATCH);

    }

    // update the subsystem everytime the framework updates (every ~0.02 seconds)
    public void update() {
        latch.setValue(latchValue);
        kickerMotor.setValue(speed*maxPower);
        if(flywheelMotor.getVelocity()< thresholdVelocity) {
            flywheelMotor.setSpeed(maxPower);
        } else if (flywheelMotor.getVelocity()>maxOutputVelocity){
            flywheelMotor.setSpeed(thresholdPower);
        } else {
            flywheelMotor.setSpeed(maxPower - (maxPower - thresholdPower) * (flywheelMotor.getVelocity()-thresholdVelocity)/(maxOutputVelocity - thresholdVelocity));
        }
    }

    // respond to input updates
    public void inputUpdate(org.wildstang.framework.io.inputs.Input signal) {
        if (signal == launchButton && Math.abs(launchButton.getValue()) > 0.5){
            latchValue = false;
            speed = 1.0;
        }
        else {
            resetState();
        }
    }

    // used for testing
    public void selfTest() {}

    // helper methods for autonomous
    public void setLauncherSpeed(double i) {
        speed = i;
    }

    // resets all variables to the default state
    public void resetState() {
        speed = 0.0;
        latchValue = true;
    }

    // returns the unique name of the subsystem
    public String getName() {
        return "Launcher";
    }

   

 

    


}
