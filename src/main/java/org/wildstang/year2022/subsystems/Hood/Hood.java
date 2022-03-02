package org.wildstang.year2022.subsystems.Hood;


import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.hardware.roborio.inputs.WsJoystickAxis;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;
import org.wildstang.year2022.robot.WSSubsystems;
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
    AnalogInput right_bumper;
    DigitalInput leftBumper, rightBumper;

    double hood_position;
    double offset;
    private LauncherModes launchMode;
    private enum State {MANUALR, MANUALF, PRESET, AIMING, IDLE}
    private State state;

    private final double CONVERSION = -54.0;//NEO rotations per 1V of MA3


    private final double MAX_ANGLE = 45;
    private final double RANGE_CONSTANT = MAX_ANGLE/360.0;
    private final double ABS_LOW = 0.0859;
    private final double ABS_HIGH = 1.5117;
    private final double ABS_RANGE = ABS_HIGH - ABS_LOW;
    private final double NEO_RANGE = 75.7;
    private final double HOOD_SPEED = 0.2;
    
    AimHelper aim;
    
    @Override
    public void init() {
        hood_motor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.HOOD);
        hood_motor.initClosedLoop(0.05, 0.0, 1.0, 0.0);
        hood_motor.setCurrentLimit(10, 10, 0);
        hood_motor.setBrake();
        //hood_motor.getController().setInverted(true);
        left_joystick_y = (WsJoystickAxis) Core.getInputManager().getInput(WSInputs.MANIPULATOR_LEFT_JOYSTICK_Y);
        left_joystick_y.addInputListener(this);
        right_bumper = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_RIGHT_SHOULDER);
        right_bumper.addInputListener(this);
        aim = (AimHelper) Core.getSubsystemManager().getSubsystem(WSSubsystems.LIMELIGHT);
        leftBumper = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_LEFT_SHOULDER);
        leftBumper.addInputListener(this);
        rightBumper = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_RIGHT_SHOULDER);
        rightBumper.addInputListener(this);
        resetState();
        
    }

    @Override
    public void update() {
        if (state == State.AIMING){
            // hood_motor.setPosition(NEO_RANGE * (hood_position * RANGE_CONSTANT - offset));
            // setPosition(hood_motor.getPosition() + CONVERSION * (aim.getDistance() * 0.0058 + 0.4254));
            hood_motor.setPosition(hood_motor.getPosition() + CONVERSION * ((0.4254 + 0.0058 * aim.getDistance()) - getMA3()));
        }
        if (state == State.MANUALF){
            //hood_motor.setSpeed(HOOD_SPEED);
        }
        if (state == State.MANUALR){
            //hood_motor.setSpeed(-HOOD_SPEED);
        }
        if (state == State.PRESET){
            hood_motor.setPosition(hood_motor.getPosition()+ CONVERSION * (launchMode.getHood() - getMA3()));
            // setPosition(hood_motor.getPosition() + CONVERSION * (launchMode.getHood() - getMA3()));
        }
        if (state == State.IDLE){
            hood_motor.setSpeed(0);
        }
        
        SmartDashboard.putNumber("hoodPosition", hood_motor.getPosition());
        SmartDashboard.putNumber("hood MA3", getMA3());
        SmartDashboard.putNumber("hood target value", launchMode.getHood());
        SmartDashboard.putNumber("hood target", hood_motor.getPosition() + CONVERSION * (launchMode.getHood() - getMA3()));
        SmartDashboard.putString("hood mode", state.toString());
    }

    @Override
    public void inputUpdate(Input source) {    
    if (Math.abs(right_bumper.getValue()) > 0.5){
        hood_position = aim.getAngle() / MAX_ANGLE;
        state = State.AIMING;
    }
    else if (left_joystick_y.getValue() > .15 && getMA3() < ABS_HIGH){
        state = State.MANUALF;
    } else if (left_joystick_y.getValue() < -.15 && getMA3() > ABS_LOW){
        state = State.MANUALR;
    }
    else if (source == leftBumper && leftBumper.getValue()){
        if (rightBumper.getValue()){
            launchMode = LauncherModes.TARMAC_EDGE;
            state = State.PRESET;
        } else {
            launchMode = LauncherModes.FENDER_SHOT;
            state = State.PRESET;
        }
    }
     else if (source == rightBumper && rightBumper.getValue()){
        if (leftBumper.getValue()){
            launchMode = LauncherModes.TARMAC_EDGE;
            state = State.PRESET;
        } else {
            launchMode = LauncherModes.LAUNCH_PAD;
            state = State.PRESET;
        }
    } else if (state != State.PRESET){
        state = State.IDLE;
    }
     
    }

    public double offset_from_initial(){
        return (getMA3() - ABS_LOW) / ABS_RANGE;
    }

    @Override
    public void selfTest() {

    }


    @Override
    public void resetState() {
        hood_motor.resetEncoder();
        state = State.IDLE;
        offset = offset_from_initial();
        launchMode = LauncherModes.FENDER_SHOT;
    }
    private double getMA3(){
        return hood_motor.getController().getAnalog(Mode.kAbsolute).getVoltage();
    }

    @Override
    public String getName() {
        return "Hood";
    }

    public void setHood(LauncherModes modeToUse){
        state = State.PRESET;
        launchMode = modeToUse;
    }

    public void setPosition(double target){
        double pidSpeed = 0;
        if (target*.99 > getMA3() || target*1.01<getMA3()){
            pidSpeed = 0.8 * (target - getMA3()) * Math.abs(target - getMA3());
            pidSpeed += Math.signum(pidSpeed) * 0.024;
        }
        if ((pidSpeed > 0 && getMA3()>1.51) || (pidSpeed < 0 && getMA3() < 0.08)) hood_motor.setSpeed(0);
        else hood_motor.setSpeed(pidSpeed);
    }
}