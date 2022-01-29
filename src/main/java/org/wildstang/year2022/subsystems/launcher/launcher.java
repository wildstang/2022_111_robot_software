package org.wildstang.year2022.subsystems.launcher;

import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.Input;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.subsystems.Subsystem;

/**
 * Class:       launcher.java
 * Inputs:      1 DigitalInput (Right Trigger)
 * Outputs:     3 Neo 550
 * Description: Shoot
 */
public class launcher implements Subsystem {

    // inputs
    private DigitalInput launchButton;

    // outputs
    private WsSparkMax motor;

    // variables
    private double speed = 0.0;
    private double maxSpeed = 1.0;
    
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
        motor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.SHOOTER1);
    }

    // update the subsystem everytime the framework updates (every ~0.02 seconds)
    public void update() {
        motor.setValue(speed*maxSpeed);
    }

    // respond to input updates
    public void inputUpdate(Input signal) {
        if (signal == launchButton){
            if (launchButton.getValue()){
                speed = 1.0;
            }
            else {
                resetState();
            }
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
    }

    // returns the unique name of the subsystem
    public String getName() {
        return "Launcher";
    }

	@Override
	public void inputUpdate(org.wildstang.framework.io.inputs.Input source) {
		// TODO Auto-generated method stub
		
	}
}