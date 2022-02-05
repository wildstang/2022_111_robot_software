package org.wildstang.year2022.subsystems.Hood;



import com.revrobotics.SparkMaxAnalogSensor.Mode;

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

/**
 * Hood system
 * Left Joystick Y: controls hood angle
 * dpad: preset angle
 * Left Trigger: Auto Aim
 * @author Bernard
 */
public class Hood implements Subsystem {

    //Motors
    WsSparkMax hood_Motor;

    //Inputs
    WsJoystickAxis left_Joystick_y;
    AnalogInput left_Trigger;
    DigitalInput dpad_Up;
    DigitalInput dpad_Down;
    DigitalInput dpad_Left;
    DigitalInput dpad_Right;

    //Variables
    private double hood_Position;
    private double range_constant;
    
    //Constants
    private final double PRESET1 = 1;
    private final double PRESET2 = .75;
    private final double PRESET3 = .5;
    private final double PRESET4 = .25;

    private final double MAX_ANGLE = 45;
    private final double POSITION_CHANGE = .02;
    
    AimHelper aim;
    
    @Override
    public void init() {
        hood_Motor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.HOOD_MOTOR);
        hood_Motor.initClosedLoop(1.0, 0.0, 0.0, 0.0);
        left_Joystick_y = (WsJoystickAxis) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_JOYSTICK_Y);
        left_Trigger = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_TRIGGER);
        dpad_Up = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_DPAD_UP);
        dpad_Up.addInputListener(this);
        dpad_Down = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_DPAD_DOWN);
        dpad_Down.addInputListener(this);
        dpad_Left = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_DPAD_LEFT);
        dpad_Left.addInputListener(this);
        dpad_Right = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_DPAD_RIGHT);
        dpad_Right.addInputListener(this);
        aim = new AimHelper();
        
        resetState();
    }

    @Override
    public void update() {
        hood_Motor.setPosition(hood_Position * range_constant);
    }

    @Override
    public void inputUpdate(Input source) {
    if (source == dpad_Up){
        hood_Position = PRESET1;
    }
    if (source == dpad_Down){
        hood_Position = PRESET2;
    }
    if (source == dpad_Left){
        hood_Position = PRESET3;
    }
    if (source == dpad_Right){
        hood_Position = PRESET4;
    }     
    if (left_Trigger.getValue() > 0.5){
        hood_Position = aim.getAngle() / MAX_ANGLE;
    }
     
    else if (left_Joystick_y.getValue() > .15 && hood_Position < 1){
        hood_Position += POSITION_CHANGE;
    }else if (left_Joystick_y.getValue() < -.15 && hood_Position > 0){
        hood_Position += POSITION_CHANGE * -1;
     }
     
    }

    @Override
    public void selfTest() {

    }

    @Override
    public void resetState() {
        hood_Position = (hood_Motor.getController().getAnalog(Mode.kAbsolute).getVoltage() /3.3);
        range_constant = MAX_ANGLE / 360.0;
    }

    @Override
    public String getName() {
        return "Hood";
    }
}