package org.wildstang.year2022.subsystems;


import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.hardware.roborio.inputs.WsJoystickAxis;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;

import org.wildstang.hardware.roborio.outputs.WsSparkMax;
//  import org.wildstang.year2022.subsystems.Hood.AimHelper;

/**
 * Hood system
 * Left Joystick Y: controls hood angle
 * dpad: preset angle
 * Left Trigger: Auto Aim
 * @author Bernard
 */
public class Hood implements Subsystem {

    WsSparkMax hood_motor;
    WsJoystickAxis left_joystick_y;
    AnalogInput left_trigger;
    DigitalInput dpad_up;
    DigitalInput dpad_down;
    DigitalInput dpad_left;
    DigitalInput dpad_right;

    double hood_position;
    


    // AimHelper aim;
    
    @Override
    public void init() {
        hood_motor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.HOOD_MOTOR);
        left_joystick_y = (WsJoystickAxis) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_JOYSTICK_Y);
        left_trigger = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_TRIGGER);
        dpad_up = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_DPAD_UP);
        dpad_up.addInputListener(this);
        dpad_down = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_DPAD_DOWN);
        dpad_down.addInputListener(this);
        dpad_left = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_DPAD_LEFT);
        dpad_left.addInputListener(this);
        dpad_right = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_DPAD_RIGHT);
        dpad_right.addInputListener(this);
        hood_position = 0.0;
        //aim = new AimHelper();
    }

    @Override
    public void update() {
        hood_motor.setPosition(hood_position);
    }

    @Override
    public void inputUpdate(Input source) {
    
    if (source == dpad_up){
        hood_position = 1;
    }
    if (source == dpad_down){
        hood_position = .75;
    }
    if (source == dpad_left){
        hood_position = .5;
    }
    if (source == dpad_right){
        hood_position = .25;
    }     
    if (left_trigger.getValue() > 0.5){
            //hood_position = aim.getAngle();
         }
     
     else if (left_joystick_y.getValue() > 0){
         hood_position = left_joystick_y.getValue();
     }
     
    }

    @Override
    public void selfTest() {

    }

    @Override
    public void resetState() {
     hood_position = 0.0;
    }

    @Override
    public String getName() {
        return "Hood";
    }
}