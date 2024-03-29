package org.wildstang.year2022.subsystems.ballpath;

import org.wildstang.framework.subsystems.Subsystem;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.hardware.roborio.outputs.WsDigitalOutput;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;
import org.wildstang.hardware.roborio.outputs.WsSolenoid;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Ballpath implements Subsystem{

    //Motors
    private WsSparkMax feedMotor, intakeMotor;

    //Motor Speeds
    private double feedMotorSpeed;
    private double intakeMotorSpeed;

    //Solenoids
    private WsSolenoid intakeSolenoid;

    //Solenoid Value
    private boolean intakeSolenoidValue;

    private boolean shooting;

    //Constants
    private final double FULL_SPEED = 1.0;
    private final double REVERSE_SPEED = -1.0;
    private final boolean OPEN = true; //intake deployed
    private final boolean CLOSE = !OPEN; //intake retracted 

    //Inputs
    private AnalogInput rightTrigger;
    private AnalogInput driverAim;
    private DigitalInput aButton;
    private DigitalInput yButton;
    private DigitalInput xButton;
    private DigitalInput bButton;
    private DigitalInput driverIntake;
    private DigitalInput driverRightBumper;
    private AnalogInput driverShoot;
    private RoborioDIO cargoLow;
    private RoborioDIO cargoHigh;

    @Override
    public void inputUpdate(Input source) {

        //dynamicly controls hopper speed
        if (yButton.getValue()){
            feedMotorSpeed = REVERSE_SPEED;
            shooting = true;
        } else if (Math.abs(rightTrigger.getValue())>0.15 || (Math.abs(driverAim.getValue()) > 0.15 && driverRightBumper.getValue())){
            feedMotorSpeed = FULL_SPEED;
            shooting = true;
        } else if (aButton.getValue() || xButton.getValue() || driverIntake.getValue()){
            feedMotorSpeed = FULL_SPEED;
            shooting = false;
        } else {
            feedMotorSpeed = 0;
            shooting = false;
        }

        /**run intake and feed either forwards or backwards */
        if (aButton.getValue() || driverIntake.getValue()){// || driverLeftBumper.getValue()){
            intakeMotorSpeed = FULL_SPEED;
            intakeSolenoidValue = OPEN;
        }  else if (bButton.getValue()){
            intakeMotorSpeed = REVERSE_SPEED;
            intakeSolenoidValue = OPEN;
        }  else  {
            intakeSolenoidValue = CLOSE;
            intakeMotorSpeed = 0;
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
        bButton = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_FACE_RIGHT);
        bButton.addInputListener(this);
        driverShoot = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_TRIGGER);
        driverShoot.addInputListener(this);
        driverAim = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_TRIGGER);
        driverAim.addInputListener(this);
        driverIntake = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_SHOULDER);
        driverIntake.addInputListener(this);
        driverRightBumper = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_RIGHT_SHOULDER);
        driverRightBumper.addInputListener(this);
    }

    private void initOutputs(){
        feedMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.FEED);
        intakeMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.INTAKE);
        intakeSolenoid = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.INTAKE_SOLENOID);
        feedMotor.setCurrentLimit(30, 30, 0);
        intakeMotor.setCurrentLimit(25, 25, 0);
        cargoLow = new RoborioDIO(0);
        cargoHigh = new RoborioDIO(1);
    }

    @Override
    public void selfTest() {
    }

    @Override
    public void update() {
        if (!cargoLow.get() && !cargoHigh.get() && !shooting){
            feedMotor.setSpeed(0);
        } else {
            feedMotor.setSpeed(-feedMotorSpeed);
        }
        intakeSolenoid.setValue(intakeSolenoidValue);
        intakeMotor.setSpeed(intakeMotorSpeed);

        SmartDashboard.putBoolean("cargo low", cargoLow.get());
        SmartDashboard.putBoolean("cargo high", cargoHigh.get());
    }

    @Override
    public void resetState() {
        feedMotorSpeed = 0.0;
        intakeMotorSpeed = 0.0;
        intakeSolenoidValue = CLOSE;
        shooting = false;
    }

    @Override
    public String getName() {
        return "Ballpath";
    }
    public void intakeDeploy(){
        intakeSolenoidValue = OPEN;
    }
    public void intakeRetract(){
        intakeSolenoidValue = CLOSE;
    }
    public void turnOnIntake(){
        intakeMotorSpeed = FULL_SPEED;
    }
    public void turnOffIntake(){
        intakeMotorSpeed = 0;
    }
    public void reverse(double speed){
        intakeMotorSpeed = -speed;
        feedMotorSpeed = -FULL_SPEED;
        shooting = true;
    }
    public void turnOnFeed(){
        feedMotorSpeed = FULL_SPEED;
    }
    public void turnOffFeed(){
        feedMotorSpeed = 0.0;
    }
    public void setShooting(boolean isShooting){
        this.shooting = isShooting;
    }
}