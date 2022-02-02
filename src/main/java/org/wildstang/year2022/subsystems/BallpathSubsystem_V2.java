package org.wildstang.year2022.subsystems;

import org.wildstang.framework.subsystems.Subsystem;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.kauailabs.navx.frc.AHRS;

import org.wildstang.framework.core.Core;
import com.revrobotics.CANSparkMax;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.logger.Log;
import org.wildstang.framework.pid.PIDConstants;
import org.wildstang.framework.subsystems.drive.Path;
import org.wildstang.framework.subsystems.drive.PathFollowingDrive;
import org.wildstang.framework.subsystems.drive.TankPath;
import org.wildstang.hardware.roborio.inputs.WsAnalogInput;
import org.wildstang.hardware.roborio.inputs.WsDigitalInput;
import org.wildstang.hardware.roborio.inputs.WsJoystickButton;
import org.wildstang.hardware.roborio.outputs.WsPhoenix;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;
import org.wildstang.hardware.roborio.outputs.WsSolenoid;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.I2C;

import org.wildstang.year2022.subsystems.swerve.SwerveDrive;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class BallpathSubsystem_V2 implements Subsystem{

    //Motors
    private WsSparkMax feedMotor, intakeMotor;

    //Motor Speeds
    private double feedMotorSpeed;
    private double intakeMotorSpeed;

    //Solenoids
    private WsSolenoid intakeSolenoid;

    //Solenoid Value
    private boolean intakeSolenoidValue;

    //Constants
    private final double FULL_SPEED = 1.0;
    private final double REVERSE_SPEED = -1.0;
    private final boolean OPEN = true; //intake deployed
    private final boolean CLOSE = !OPEN; //intake retracted 

    //Inputs
    private AnalogInput rightTrigger;
    private DigitalInput aButton;
    private DigitalInput yButton;
    private DigitalInput xButton;

    @Override
    public void inputUpdate(Input source) {

        //dynamicly controls hopper speed
        if (Math.abs(rightTrigger.getValue())>0.15){
            feedMotorSpeed = FULL_SPEED;
        } 
        
        //intake reverse 
        if (source == yButton) {
            if (yButton.getValue()) {
                intakeMotorSpeed = REVERSE_SPEED;
            } else {
                intakeMotorSpeed = 0;
            }
        } 

        //intake full speed - also opens intake
        if (source == aButton) {
            if (aButton.getValue()) {
                intakeSolenoidValue = OPEN;
                intakeMotorSpeed = FULL_SPEED;
                feedMotorSpeed = FULL_SPEED;
            } else {
                intakeSolenoidValue = CLOSE;
                intakeMotorSpeed = 0;
                feedMotorSpeed = 0;
            }

        }

        //feed & intake full speed
        if (source == xButton) {
            if (xButton.getValue()) {
                feedMotorSpeed = FULL_SPEED;
            } else {
                feedMotorSpeed = 0;
            }
        }
        
    }

    @Override
    public void init() {
        initInputs();
        initOutputs();
        resetState();
    }
    private void initInputs(){
        rightTrigger = (AnalogInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_RIGHT_TRIGGER);
        rightTrigger.addInputListener(this);
        xButton = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_FACE_LEFT);
        xButton.addInputListener(this);
        yButton = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_FACE_UP);
        yButton.addInputListener(this);
        aButton = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_FACE_DOWN);
        aButton.addInputListener(this);
    }

    private void initOutputs(){
        feedMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.BALLPATH_FEED);
        intakeMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.BALLPATH_INTAKE);
        intakeSolenoid = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.INTAKE_SOLENOID);
    }

    @Override
    public void selfTest() {
    }

    @Override
    public void update() {
        intakeSolenoid.setValue(intakeSolenoidValue);
        feedMotor.setSpeed(feedMotorSpeed);
        intakeMotor.setSpeed(intakeMotorSpeed);
    }

    @Override
    public void resetState() {
        feedMotorSpeed = 0.0;
        intakeMotorSpeed = 0.0;
        intakeSolenoidValue = CLOSE;
    }

    @Override
    public String getName() {
        return "Ballpath";
    }
    public void intakeDeploy(){
        intakeSolenoidValue = true;
    }
    public void turnOnIntake(){
        intakeMotorSpeed = FULL_SPEED;
    }
    public void turnOffIntake(){
        intakeMotorSpeed = 0;
    }
    public void turnOnFeed(){
        feedMotorSpeed = FULL_SPEED;
    }
    public void turnOffFeed(){
        feedMotorSpeed = 0.0;
    }
}