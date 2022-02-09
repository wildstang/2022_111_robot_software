package org.wildstang.year2022.subsystems.Hood;


import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.hardware.roborio.inputs.WsJoystickAxis;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;

import org.wildstang.hardware.roborio.outputs.WsSparkMax;
import org.wildstang.year2022.subsystems.Hood.AimHelper;
import com.revrobotics.SparkMaxAnalogSensor.Mode;

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
    double range_constant;
    double offset;

    final double zero_position = .5; // to be changed
    final double preset1 = 1;
    final double preset2 = .75;
    final double preset3 = .5;
    final double preset4 = .25;

    final double max_angle = 45;
    final double position_change = .02;
    
    AimHelper aim;
    
    @Override
    public void init() {
        hood_motor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.HOOD_MOTOR);
        hood_motor.initClosedLoop(1.0, 0.0, 0.0, 0.0);
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
        aim = new AimHelper();
        resetState();
        
    }

    @Override
    public void update() {
        hood_motor.setPosition(hood_position * range_constant + offset);
    }
// zero = .5
// at 0 = .6
// offset = -.1
// 0 + -.1 = .5

    @Override
    public void inputUpdate(Input source) {
    
    if (source == dpad_up){
        hood_position = preset1;
    }
    if (source == dpad_down){
        hood_position = preset2;
    }
    if (source == dpad_left){
        hood_position = preset3;
    }
    if (source == dpad_right){
        hood_position = preset4;
    }     
    if (left_trigger.getValue() > 0.5){
            hood_position = aim.getAngle() / max_angle;
         }
     
     else if (left_joystick_y.getValue() > .15 && hood_position < 1){
         hood_position += position_change;
     }else if (left_joystick_y.getValue() < -.15 && hood_position > 0){
         hood_position += position_change * -1;
     }
     
    }

    public double offset_from_initial(WsSparkMax motor,double initial){
        return initial - motor.getController().getAnalog(Mode.kAbsolute).getVoltage() / 3.3;
    }

    @Override
    public void selfTest() {

    }


    @Override
    public void resetState() {
        hood_position = 0.0;
        hood_motor.resetEncoder();
        offset = offset_from_initial(hood_motor,zero_position);
        range_constant = max_angle / 360.0;
    }

    @Override
    public String getName() {
        return "Hood";
    }
}