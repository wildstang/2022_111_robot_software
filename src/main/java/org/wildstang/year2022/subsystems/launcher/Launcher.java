package org.wildstang.year2022.subsystems.launcher;


import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;
import org.wildstang.hardware.roborio.outputs.WsSolenoid;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;

import org.wildstang.framework.core.Core;
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
    private DigitalInput launchButton;

    // outputs
    private WsSparkMax kickerMotor;
    private WsSparkMax flywheelMotor;
    private WsSolenoid latch;

    // variables
    private double speed = 0.0;
    private double maxSpeed = 1.0;
    public static double maxOutputVelocity = 240.0;
    private double outputVelocityThresholdPercent = 0.7;
    
    // initializes the subsystem
    public void init() {
        initInputs();
        initOutputs();
        resetState();
    }

    public void initInputs() {
        launchButton = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_RIGHT_TRIGGER.getName());
        launchButton.addInputListener(this);
    }

    public void initOutputs() {
        kickerMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.KICKER);
        flywheelMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.FLYWHEEL);
        latch = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.BALL_LATCH);

    }

    // update the subsystem everytime the framework updates (every ~0.02 seconds)
    public void update() {
        kickerMotor.setValue(speed*maxSpeed);
        if(flywheelMotor.getVelocity()< outputVelocityThresholdPercent*maxOutputVelocity) {
            flywheelMotor.setValue(maxSpeed);
        }
        else {
            flywheelMotor.setValue(outputVelocityThresholdPercent*maxSpeed);
        }
    }

    // respond to input updates
    public void inputUpdate(org.wildstang.framework.io.inputs.Input signal) {
        if (signal == launchButton){
            if (launchButton.getValue()){
                fire();
                
            }
            else {
                resetState();
            }
        }
        else {
            resetState();
        }
    }

    public void fire() {
        latch.setValue(false);
                speed = 1.0;
    }
    // used for testing
    public void selfTest() {}

    // helper methods for autonomous
    public void setLauncherSpeed(double i) {
        speed = i;
    }

    public void setVelocity(double velocity) {
        outputVelocityThresholdPercent = (velocity/maxOutputVelocity);
    }

    // resets all variables to the default state
    public void resetState() {
        speed = 0.0;
        latch.setValue(true);
    }

    // returns the unique name of the subsystem
    public String getName() {
        return "Launcher";
    }

   

 

    


}
