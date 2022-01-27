import org.wildstang.framework.subsystems.Subsystem;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.kauailabs.navx.frc.AHRS;

import org.wildstang.framework.core.Core;
import com.revrobotics.CANSparkMax;
import org.wildstang.framework.io.inputs.Input;
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

    //Solenoid Value
    private boolean intakeSolenoidValue;

    //Shuffleboard entries
    private ShuffleboardTab driveTab;
    private NetworkTableEntry maxDriveInputEntry;

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
        if (Math.abs(rightTrigger.getValue())>0.75){ //MARK : When is it wanted at full speed/Right Trigger purpose
            feedMotorSpeed = FULL_SPEED;
        } 
        if (source == xButton){
            if (xButton.getValue()) {
                if (feedMotorSpeed != 0) {
                    feedMotorSpeed = 0;
                } else {
                    feedMotorSpeed = FULL_SPEED;
                }
            }
        }
        //set intake motor speed
        if (source == yButton) {
            if (yButton.getValue()) {
                intakeMotorSpeed = REVERSE_SPEED;
            } else {
                intakeMotorSpeed = FULL_SPEED;
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
    }

    @Override
    public void init() {
        initInputs();
        initOutputs();
        resetState();
    }
    private void initInputs(){
        rightTrigger = (AnalogInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_TRIGGER_RIGHT.getName());
        rightTrigger.addInputListener(this);
        xButton = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_FACE_LEFT.getName());
        xButton.addInputListener(this);
        yButton = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_FACE_UP.getName());
        yButton.addInputListener(this);
        aButton = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_FACE_DOWN.getName());
        aButton.addInputListener(this);
        driveTab = Shuffleboard.getTab("Drive");
        maxDriveInputEntry = driveTab.add("Max Input", 1).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0, "max", 1)).getEntry();
    }

    private void initOutputs(){
        feedMotor = new (WsSparkMax) Core.getOutputManager().getOutput(WSOutput.BALLPATH_FEED);
        intakeMotor = new (WsSparkMax) Core.getOutputManager().getOutput(WSOutput.BALLPATH_INTAKE);
        intakeSolenoid = new (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.INTAKE_DEPLOY_SOLENOID);
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
    }

    @Override
    public String getName() {
        return "Ballpath";
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