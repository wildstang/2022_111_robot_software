package org.wildstang.year2022.subsystems.launcher;

import org.wildstang.year2022.robot.CANConstants;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;
import org.wildstang.year2022.robot.WSSubsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.pid.PIDConstants;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.hardware.roborio.inputs.WsAnalogInput;
import org.wildstang.hardware.roborio.outputs.WsSolenoid;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;
import org.wildstang.hardware.roborio.outputs.config.WsServoConfig;

public final class Launcher implements Subsystem{

    private AnalogInput FIRE_TRIGGER;

    private WsSparkMax leftFlywheelMotor, rightFlywheelMotor, kickerMotor;

    private WsSolenoid ballLatch;

    private double speed, kickerSpeed;

    private double maxSpeed = 342.0;
    //This is the max speed a motor can go without burning out

    private int reverseVariable = -1;
    //We don't know which motor is going to be in the negative direction, so we will worry about this later

    @Override
    public void init() {
        leftFlywheelMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.LAUNCHER_LEFT);
        rightFlywheelMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.LAUNCHER_RIGHT);
        kickerMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.KICKER);

        ballLatch = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.LAUNCHER_SOLENOID);

        FIRE_TRIGGER = (WsAnalogInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_RIGHT_TRIGGER);
        resetState();
    }

    @Override
    public void update(){
        if (FIRE_TRIGGER.getValue() > 0.5){
            leftFlywheelMotor.setValue(speed/* * reverseVariable*/);
            rightFlywheelMotor.setValue(speed/* * reverseVariable*/);
                //we will uncomment whichever we are using backwards later
            try {
                Thread.sleep(3000/*startup delay */);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();;
            }
            ballLatch.setValue(false);
        }else{
            resetState();
        }
    }

    @Override
    public void inputUpdate(Input source) {
        if (source == FIRE_TRIGGER){
            speed = maxSpeed;
        }else{
            resetState();
        }
    }

    @Override
    public void selfTest() {
       
    }

    @Override
    public void resetState() {
        speed = 0.0;
        kickerSpeed = maxSpeed;
        ballLatch.setValue(true);
    }

    @Override
    public String getName() {
        return "Launcher";
    }

}