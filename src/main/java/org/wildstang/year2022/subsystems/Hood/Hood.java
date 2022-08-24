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
    AnalogInput left_trigger;//driver left trigger
    DigitalInput leftBumper, rightBumper;//manipulator right bumper


    double hood_position;
    double offset;
    private LauncherModes launchMode;
    private enum State {PRESET, AIMING, IDLE}
    private State state;

    private final double CONVERSION = -54.0;//NEO rotations per 1V of MA3



    private final double ABS_LOW = 0.0859;
    private final double ABS_HIGH = 1.5117;
    private final double ABS_RANGE = ABS_HIGH - ABS_LOW;

    private final double REG_A = -0.00000588;//0.00013116;//-0.000034595;
    private final double REG_B = 0.00832;//-0.03091;//0.0153707;
    private final double REG_C = 0.2338;//2.9316;//-0.19354;
    
    AimHelper aim;

    private double DistanceRegression(double input){
        return REG_A * (input*input) + REG_B * input + REG_C;
    }
    
    @Override
    public void init() {
        hood_motor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.HOOD);
        hood_motor.initClosedLoop(0.05, 0.0, 1.0, 0.0);
        hood_motor.setCurrentLimit(10, 10, 0);
        hood_motor.setBrake();
        //hood_motor.getController().setInverted(true);
        left_joystick_y = (WsJoystickAxis) Core.getInputManager().getInput(WSInputs.MANIPULATOR_LEFT_JOYSTICK_Y);
        left_joystick_y.addInputListener(this);
        left_trigger = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_TRIGGER);
        left_trigger.addInputListener(this);
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
            double distance = aim.getDistance();

            if (distance < AimHelper.FenderDistance){
                setPosition(Math.max(ABS_LOW, DistanceRegression(distance))); //Linear EQ & Hood Position Floor
            }
            else {
                setPosition(Math.min(ABS_HIGH, DistanceRegression(distance))); //Hood Position Ceiling
            }

        }
        if (state == State.PRESET){
            setPosition(launchMode.getHood());
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

        
    if (Math.abs(left_trigger.getValue()) > 0.15){
        //hood_position = aim.getAngle() / MAX_ANGLE;
        state = State.AIMING;
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

    public void setAiming(boolean toAim){
        if (toAim){
            state = State.AIMING;
        } else {
            state = State.PRESET;
        }
    }

    public void setPosition(double target){
        double pidSpeed = 0;
        if (target*.99 > getMA3() || target*1.01<getMA3()){
            pidSpeed = -2.8 * (target - getMA3());
            pidSpeed += Math.signum(pidSpeed) * 0.024;
        }
        if ((pidSpeed < 0 && getMA3()>1.51) || (pidSpeed > 0 && getMA3() < 0.08)) hood_motor.setSpeed(0);
        else hood_motor.setSpeed(pidSpeed);
    }
}