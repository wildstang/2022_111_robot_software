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

    //a toggles intake forward, y toggles feed, x and b increase/decrease flywheel speed
    //RT runs the flywheel and its solenoid, left and right bumper move the hood
    //start switches the kicker from mirroring the flywheel and just 100%, select toggles tilt solenoids
    private DigitalInput aButton, xButton, yButton, bButton, leftbumper, rightBumper, startButton, selectButton;
    private AnalogInput rightTrigger;
    
    private WsSparkMax feedMotor, launcherMotor, kickerMotor, hoodMotor, intakeMotor;
    private WsSolenoid launcherSolenoid, intakeSolenoid;
    private WsDoubleSolenoid tiltSolenoid1, tiltSolenoid2;

    private double feedSpeed, launcherSpeed, kickerSpeed, hoodSpeed, intakeSpeed;
    private double modifier;
    private boolean kickerState, launcherSolenoidState, intakeSolenoidState, tiltState;

    private final double LAUNCHER_INITIAL = 0.7;
    private final double modifyAmount = 0.02;
    private final double feedMaxSpeed = 1.0;
    private final double intakeMaxSpeed = 1.0;
    private final double hoodMaxSpeed = 0.2;


    @Override
    public void inputUpdate(Input source) {
        if (aButton.getValue() && source == aButton){
            intakeSpeed = intakeMaxSpeed;
            intakeSolenoidState = false;
        } else if (source == aButton && !aButton.getValue()) {
            intakeSpeed = 0;
            intakeSolenoidState = false;
        }

        if (yButton.getValue() && source == yButton){
            feedSpeed = feedMaxSpeed;
        } else if (yButton.getValue() && source == yButton){
            feedSpeed = 0;
        }
        

        if (source == startButton && startButton.getValue()) kickerState = !kickerState;

        if (Math.abs(rightTrigger.getValue()) > 0.5){
            launcherSolenoidState = false;
            launcherSpeed = LAUNCHER_INITIAL + modifier;
            kickerSpeed = kickerState ? LAUNCHER_INITIAL + modifier : 1.0;
        } else {
            launcherSolenoidState = true;
            launcherSpeed = 0;
            kickerSpeed = 0;
        }
        if (source == xButton && xButton.getValue()){
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

        feedMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.FEED);   
        launcherMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.LAUNCHER);    
        kickerMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.KICKER); 
        hoodMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.HOOD);
        intakeMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.INTAKE);
        feedMotor.setCurrentLimit(25, 25, 0);
        launcherMotor.setCurrentLimit(50, 50, 0);
        kickerMotor.setCurrentLimit(25, 25, 0);
        hoodMotor.setCurrentLimit(25, 25, 0);
        intakeMotor.setCurrentLimit(25, 25, 0);

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
        launcherMotor.setSpeed(launcherSpeed);
        feedMotor.setSpeed(feedSpeed);
        kickerMotor.setSpeed(kickerSpeed);
        hoodMotor.setSpeed(hoodSpeed);
        intakeMotor.setSpeed(intakeSpeed);

        launcherSolenoid.setValue(launcherSolenoidState);
        if (tiltState){
            tiltSolenoid1.setValue(WsDoubleSolenoidState.FORWARD.ordinal());
            tiltSolenoid2.setValue(WsDoubleSolenoidState.FORWARD.ordinal());
        } else {
            tiltSolenoid1.setValue(WsDoubleSolenoidState.REVERSE.ordinal());
            tiltSolenoid2.setValue(WsDoubleSolenoidState.REVERSE.ordinal());
        }

        SmartDashboard.putNumber("Flywheel velocity", launcherMotor.getVelocity());
        SmartDashboard.putNumber("Flywheel percent output", LAUNCHER_INITIAL+modifier);
        SmartDashboard.putNumber("hoodPosition", hoodMotor.getPosition());
        SmartDashboard.putNumber("hood MA3", hoodMotor.getController().getAnalog(Mode.kAbsolute).getVoltage());
        SmartDashboard.putNumber("Hood MA3 inbuilt position", hoodMotor.getController().getAnalog(Mode.kAbsolute).getPosition());
        SmartDashboard.putBoolean("is kicker mirroring flywheel", kickerState);
    }

    @Override
    public void resetState() {
        feedSpeed = 0;
        launcherSpeed = 0;
        modifier = 0;
        hoodSpeed = 0;
        intakeSpeed = 0;
        kickerState = true;
        intakeSolenoidState = false;
        launcherSolenoidState = true;
        tiltState = true;
    }

    @Override
    public String getName() {
        return "Tester";
    }
}