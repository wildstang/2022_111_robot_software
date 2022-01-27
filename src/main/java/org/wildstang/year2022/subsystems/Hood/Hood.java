package org.wildstang.year2022.subsystems;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.hardware.roborio.inputs.WsJoystickAxis;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;

import org.wildstang.hardware.roborio.outputs.WsSparkMax;

/**
 * Hood system
 * Left Joystick Y: controls hood angle 
 * dpad: preset angle 
 * Left Trigger: Auto Aim
 * @author Bernard
 */
public class SampleSubsystem implements Subsystem {

    WsSparkMax hood_motor;
    WsJoystickAxis js;
    
    double hood_position
    

    @Override
    public void init() {
        hood_motor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.HOOD_MOTOR);
        left_joystick_y = (WsJoystickAxis) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_JOYSTICK_Y);

        hood_position = 0.0;
    }

    @Override
    public void update() {
        hood_motor.setPosition(hood_position);
    }

    @Override
    public void inputUpdate(Input source) {
     if (left_joystick_y.getValue() > 0){
         hood_position = (left_joystick_y.getValue());
     }
    }

    @Override
    public void selfTest() {

    }

    @Override
    public void resetState() {

    }

    @Override
    public String getName() {
        return "Sample";
    }
}