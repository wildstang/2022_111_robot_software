package org.wildstang.year2022.subsystems.test;

import com.revrobotics.SparkMaxAnalogSensor.Mode;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.hardware.roborio.outputs.WsDoubleSolenoid;
import org.wildstang.hardware.roborio.outputs.WsDoubleSolenoidState;
import org.wildstang.hardware.roborio.outputs.WsSolenoid;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;
import org.wildstang.year2022.robot.WSOutputs;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.wildstang.year2022.robot.WSInputs;

public class Tester implements Subsystem{

    //a press runs intake/feed, x toggles intake deploy, y and b increase/decrease flywheel speed
    //RT runs the flywheel and its solenoid, left and right bumper move the hood
    //start switches the kicker from mirroring the flywheel and just 100%, select toggles tilt solenoids
    //right stick y axis moves the climb up and down, 20% min speed
    private DigitalInput aButton, xButton, yButton, bButton, leftbumper, rightBumper, startButton, selectButton;
    private AnalogInput rightTrigger, rightStickY;
    
    private WsSparkMax feedMotor, launcherMotor, kickerMotor, hoodMotor, intakeMotor, climbMotor;
    private WsSolenoid launcherSolenoid, intakeSolenoid;
    private WsDoubleSolenoid tiltSolenoid1, tiltSolenoid2;

    private double feedSpeed, launcherSpeed, kickerSpeed, hoodSpeed, intakeSpeed, climbSpeed;
    private double modifier;
    private boolean launcherSolenoidState, intakeSolenoidState, tiltState;

    private final double LAUNCHER_INITIAL = 0.7;
    private final double modifyAmount = 0.02;
    private final double feedMaxSpeed = 1.0;
    private final double intakeMaxSpeed = 1.0;
    private final double hoodMaxSpeed = 0.2;


    @Override
    public void inputUpdate(Input source) {
        if (aButton.getValue()){
            intakeSpeed = intakeMaxSpeed;
            feedSpeed = feedMaxSpeed;
        } else {
            intakeSpeed = 0;
            feedSpeed = 0;
        }

        if (source == xButton && xButton.getValue()){
            intakeSolenoidState = !intakeSolenoidState;
        }

        if (Math.abs(rightTrigger.getValue()) > 0.5){
            launcherSolenoidState = false;
            launcherSpeed = LAUNCHER_INITIAL + modifier;
            kickerSpeed = 1.0;
        } else {
            launcherSolenoidState = true;
            launcherSpeed = 0;
            kickerSpeed = 0;
        }
        if (source == yButton && yButton.getValue()){
            modifier += modifyAmount;
        }
        if (source == bButton && bButton.getValue()){
            modifier -= modifyAmount;
        }

        if (rightBumper.getValue()){
            hoodSpeed = hoodMaxSpeed;
        } else if (leftbumper.getValue()){
            hoodSpeed = -hoodMaxSpeed;
        } else {
            hoodSpeed = 0;
        }

        if (source == selectButton && selectButton.getValue()){
            tiltState = !tiltState;
        }  
        if (Math.abs(rightStickY.getValue()) > 0.2){
            climbSpeed = -rightStickY.getValue();
        } else {
            climbSpeed = 0;
        }
    }

    @Override
    public void init() {
        rightTrigger = (AnalogInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_RIGHT_TRIGGER);
        rightTrigger.addInputListener(this);
        aButton = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_FACE_DOWN);
        aButton.addInputListener(this);
        xButton = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_FACE_LEFT);
        xButton.addInputListener(this);
        yButton = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_FACE_UP);
        yButton.addInputListener(this);
        bButton = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_FACE_RIGHT);
        bButton.addInputListener(this);
        leftbumper = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_LEFT_SHOULDER);
        leftbumper.addInputListener(this);
        rightBumper = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_RIGHT_SHOULDER);
        rightBumper.addInputListener(this);
        startButton = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_START);
        startButton.addInputListener(this);
        selectButton = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_SELECT);
        selectButton.addInputListener(this);
        rightStickY = (AnalogInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_RIGHT_JOYSTICK_Y);
        rightStickY.addInputListener(this);

        feedMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.FEED);   
        launcherMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.LAUNCHER);    
        kickerMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.KICKER); 
        hoodMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.HOOD);
        intakeMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.INTAKE);
        climbMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.CLIMB);
        feedMotor.setCurrentLimit(35, 35, 0);
        launcherMotor.setCurrentLimit(50, 50, 0);
        kickerMotor.setCurrentLimit(30, 30, 0);
        hoodMotor.setCurrentLimit(25, 25, 0);
        intakeMotor.setCurrentLimit(25, 25, 0);
        climbMotor.setCurrentLimit(50, 50, 0);
        climbMotor.setBrake();

        launcherSolenoid = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.LAUNCHER_SOLENOID);
        tiltSolenoid1 = (WsDoubleSolenoid) Core.getOutputManager().getOutput(WSOutputs.CLIMB_SOLENOID_1);
        tiltSolenoid2 = (WsDoubleSolenoid) Core.getOutputManager().getOutput(WSOutputs.CLIMB_SOLENOID_2);
        intakeSolenoid = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.INTAKE_SOLENOID);

        resetState();
    }

    @Override
    public void selfTest() {
    }

    @Override
    public void update() {
        launcherMotor.setSpeed(-launcherSpeed);
        feedMotor.setSpeed(-feedSpeed);
        kickerMotor.setSpeed(kickerSpeed);
        hoodMotor.setSpeed(hoodSpeed);
        intakeMotor.setSpeed(intakeSpeed);
        if ((climbSpeed > 0 && Math.abs(climbMotor.getPosition()) < 88.5) || (climbSpeed < 0 && Math.abs(climbMotor.getPosition()) >= 0)){
            climbMotor.setSpeed(climbSpeed);
        } else {
            climbMotor.setSpeed(0);
        }
        

        launcherSolenoid.setValue(launcherSolenoidState);
        intakeSolenoid.setValue(intakeSolenoidState);
        if (!tiltState){
            tiltSolenoid1.setValue(WsDoubleSolenoidState.FORWARD.ordinal());
            tiltSolenoid2.setValue(WsDoubleSolenoidState.FORWARD.ordinal());
        } else {
            tiltSolenoid1.setValue(WsDoubleSolenoidState.REVERSE.ordinal());
            tiltSolenoid2.setValue(WsDoubleSolenoidState.REVERSE.ordinal());
        }

        SmartDashboard.putNumber("Flywheel velocity", -launcherMotor.getVelocity());
        SmartDashboard.putNumber("kicker percent output", kickerSpeed);
        SmartDashboard.putNumber("kicker output current", kickerMotor.getController().getOutputCurrent());
        SmartDashboard.putNumber("Flywheel percent output", LAUNCHER_INITIAL+modifier);
        SmartDashboard.putNumber("hoodPosition", hoodMotor.getPosition());
        SmartDashboard.putNumber("hood MA3", hoodMotor.getController().getAnalog(Mode.kAbsolute).getVoltage());
        SmartDashboard.putNumber("climb encoder value", climbMotor.getPosition());
        SmartDashboard.putNumber("climb percent output", climbSpeed);
        SmartDashboard.putBoolean("solenoid launcher", launcherSolenoidState);
        SmartDashboard.putBoolean("solenoid intake", intakeSolenoidState);
        SmartDashboard.putBoolean("solenoid tilt", !tiltState);
    }

    @Override
    public void resetState() {
        feedSpeed = 0;
        launcherSpeed = 0;
        modifier = 0;
        hoodSpeed = 0;
        intakeSpeed = 0;
        climbSpeed = 0;
        intakeSolenoidState = false;
        launcherSolenoidState = true;
        tiltState = true;
    }

    @Override
    public String getName() {
        return "Tester";
    }
}