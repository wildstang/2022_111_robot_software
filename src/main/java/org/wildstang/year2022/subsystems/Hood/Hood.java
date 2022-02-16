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
import org.wildstang.year2022.subsystems.launcher.LauncherModes;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    DigitalInput leftBumper, rightBumper;

    double hood_position;
    double offset;
    private LauncherModes launchMode;
    private enum State {MANUALR, MANUALF, PRESET, AIMING}
    private State state;

    private final double CONVERSION = 54.0;//NEO rotations per 1V of MA3


    final double max_angle = 45;
    final double range_constant = max_angle/360.0;
    final double absLow = 0.0859;
    final double absHigh = 1.5117;
    final double absRange = absHigh - absLow;
    final double neoRange = 75.7;
    final double hoodSpeed = 0.2;
    
    AimHelper aim;
    
    @Override
    public void init() {
        hood_motor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.HOOD);
        hood_motor.initClosedLoop(1.0, 0.0, 0.0, 0.0);
        hood_motor.setCurrentLimit(15, 15, 0);
        hood_motor.setBrake();
        left_joystick_y = (WsJoystickAxis) Core.getInputManager().getInput(WSInputs.MANIPULATOR_LEFT_JOYSTICK_Y);
        left_joystick_y.addInputListener(this);
        left_trigger = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_TRIGGER);
        left_trigger.addInputListener(this);
        aim = new AimHelper();
        leftBumper = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_LEFT_SHOULDER);
        leftBumper.addInputListener(this);
        rightBumper = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_RIGHT_SHOULDER);
        rightBumper.addInputListener(this);
        resetState();
        
    }

    @Override
    public void update() {
        if (state == State.AIMING){
            hood_motor.setPosition(neoRange * (hood_position * range_constant - offset));
        }
        if (state == State.MANUALF){
            hood_motor.setSpeed(hoodSpeed);
        }
        if (state == State.MANUALR){
            hood_motor.setSpeed(-hoodSpeed);
        }
        if (state == State.PRESET){
            hood_motor.setPosition(CONVERSION * (getMA3() - launchMode.getHood()));
        }
        
        SmartDashboard.putNumber("hoodPosition", hood_motor.getPosition());
        SmartDashboard.putNumber("hood MA3", hood_motor.getController().getAnalog(Mode.kAbsolute).getVoltage());
    }

    @Override
    public void inputUpdate(Input source) {     
    if (left_trigger.getValue() > 0.5){
        hood_position = aim.getAngle() / max_angle;
        state = State.AIMING;
    }
    if (left_joystick_y.getValue() > .15 && getMA3() < absHigh){
        state = State.MANUALF;
    } else if (left_joystick_y.getValue() < -.15 && getMA3() > absLow){
        state = State.MANUALR;
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

    public double offset_from_initial(WsSparkMax motor){
        return (motor.getController().getAnalog(Mode.kAbsolute).getVoltage() - absLow) / absRange;
    }

    @Override
    public void selfTest() {

    }


    @Override
    public void resetState() {
        hood_motor.resetEncoder();
        state = State.MANUALF;
        offset = offset_from_initial(hood_motor);
        launchMode = LauncherModes.FENDER_SHOT;
    }
    private double getMA3(){
        return hood_motor.getController().getAnalog(Mode.kAbsolute).getVoltage();
    }

    @Override
    public String getName() {
        return "Hood";
    }
}