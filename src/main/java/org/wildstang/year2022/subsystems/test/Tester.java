package org.wildstang.year2022.subsystems.test;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.hardware.roborio.outputs.WsSolenoid;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;
import org.wildstang.year2022.robot.WSOutputs;

import edu.wpi.first.wpilibj.XboxController;

import org.wildstang.year2022.robot.WSInputs;

public class Tester implements Subsystem{

    private DigitalInput aButton, xButton, yButton, bButton, leftbumper, rightBumper;
    private AnalogInput rightTrigger;
    
    private WsSparkMax intakeMotor, feedMotor, launcherMotor, kickerMotor, hoodMotor;

    private double feedSpeed, intakeSpeed, launcherSpeed, kickerSpeed, hoodSpeed;
    private double modifier;

    private final double LAUNCHER_INITIAL = 0.7;


    @Override
    public void inputUpdate(Input source) {
        if (aButton.getValue()){
            intakeSpeed = 1.0;
            feedSpeed = 1.0;
        } else if (yButton.getValue()){
            intakeSpeed = -0.5;
            feedSpeed = -0.5;
        } else {
            intakeSpeed = 0;
            feedSpeed = 0;
        }

        if (Math.abs(rightTrigger.getValue()) > 0.5){
            launcherSpeed = LAUNCHER_INITIAL + modifier;
            kickerSpeed = LAUNCHER_INITIAL + modifier;
        } else {
            launcherSpeed = 0;
            kickerSpeed = 0;
        }
        if (source == xButton && xButton.getValue()){
            modifier += 0.02;
        }
        if (source == bButton && bButton.getValue()){
            modifier -= 0.02;
        }

        if (rightBumper.getValue()){
            hoodSpeed = 0.2;
        } else if (leftbumper.getValue()){
            hoodSpeed = -0.2;
        } else {
            hoodSpeed = 0;
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

        intakeMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.INTAKE);
        feedMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.FEED);   
        launcherMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.LAUNCHER);    
        kickerMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.KICKER); 
        hoodMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.HOOD);
        intakeMotor.setCurrentLimit(25, 25, 0);
        feedMotor.setCurrentLimit(25, 25, 0);
        launcherMotor.setCurrentLimit(50, 50, 0);
        kickerMotor.setCurrentLimit(25, 25, 0);
        hoodMotor.setCurrentLimit(25, 25, 0);
    }

    @Override
    public void selfTest() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update() {
        launcherMotor.setSpeed(launcherSpeed);
        feedMotor.setSpeed(feedSpeed);
        intakeMotor.setSpeed(intakeSpeed);
        kickerMotor.setSpeed(kickerSpeed);
        hoodMotor.setSpeed(hoodSpeed);
    }

    @Override
    public void resetState() {
        feedSpeed = 0;
        intakeSpeed = 0;
        launcherSpeed = 0;
        modifier = 0;
        hoodSpeed = 0;
    }

    @Override
    public String getName() {
        return "Tester";
    }
    
}
