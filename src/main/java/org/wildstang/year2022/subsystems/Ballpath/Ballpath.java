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

    //Booleans
    private boolean toggleBallpath = true;

    //Constants
    private final double FULL_SPEED = 1.0;
    private final double KICKER_MOTOR_CONSTANT = 0.4;
    private final double REVERSE_SPEED = -1.0;

    //Inputs
    private AnalogInput rightTrigger;
    private DigitalInput aButton;
    private DigitalInput yButton;
    private DigitalInput xButton;

    @Override
    public void inputUpdate(Input source) {
        //set feed and hopper motor speeds
        if (Math.abs(rightTrigger.getValue())>0.75){
            feedMotorSpeed = FULL_SPEED;
        } 
        //set intake motor speed
        if (source == yButton) {
            if (yButton.getValue()) {
                intakeMotorSpeed = REVERSE_SPEED;
            } else {
                intakeMotorSpeed = 0;
            }
        } 
        //intake solenoid toggle
        if (source == aButton) {
            if (aButton.getValue()) {
                if (intakeMotorSpeed != 0) {
                    intakeMotorSpeed = 0;
                    intakeSolenoidValue = false;
                } else {
                    intakeMotorSpeed = FULL_SPEED;
                    intakeSolenoidValue = true;
                }
            }
        }
        //toggle ballpath run
        if (source == xButton && xButton.getValue()) {
            if (toggleBallpath = true) {
                toggleBallpath = false;
                intakeMotorSpeed = 0;
                feedMotorSpeed = 0;
            } else {
                toggleBallpath = true;
                feedMotorSpeed = FULL_SPEED;
                if (intakeSolenoidValue) {
                intakeMotorSpeed = FULL_SPEED;
                }
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
        intakeSolenoid = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.INTAKE_DEPLOY_SOLENOID);
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
        intakeSolenoidValue = false;
    }

    @Override
    public String getName() {
        return "Ballpath";
    }
}