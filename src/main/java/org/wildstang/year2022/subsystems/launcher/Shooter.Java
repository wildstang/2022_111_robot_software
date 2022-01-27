package org.wildstang.year2022.subsystems.launcher;

import com.ctre.phoenix.sensors.CANCoder;
import com.kauailabs.navx.frc.AHRS;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.framework.subsystems.swerve.SwerveDriveTemplate;
import org.wildstang.year2022.robot.CANConstants;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;
import org.wildstang.year2022.subsystems.swerve.DriveConstants;
import org.wildstang.year2022.subsystems.swerve.SwerveSignal;
import org.wildstang.year2022.subsystems.swerve.WSSwerveHelper;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;
import org.wildstang.hardware.roborio.outputs.WsSolenoid;

import edu.wpi.first.wpilibj.Timer;


public class Launcher implements Subsystem{
    private DigitalInput ShootInput;
        ShootInput.WsName = "MANIPULATOR_RIGHT_TRIGGER";

    private WsSparkMax shooterMotorOne;
        //shooterMotor.WsName = "shooterMotor";
    private WsSparkMax shooterMotorTwo;
    private WsSparkMax kickerMotor;
        //kickerMotor.WsName = "kickerMotor";

    private WsSolenoid SolenoidOpener;

    private Timer ShooterTime;
    public Boolean ShooterOn;
           ShooterOn.Override = Boolean false;
    protected final double delay;

    public enum driveType {TELEOP, AUTO, CROSS};
    public driveType driveState;
    
    public void inputUpdate(Input source) {
        if (source == ShootInput){
            if (ShootInput.getValue()) {
                private TheInput = ShooterInput.getValue();
                if (TheInput == 1) {
                    ShooterTime.reset ()
                }
            }
        }

    }

    public void init(){
        ShooterTime.start ();
    }

    public void initInputs(){
        ShootInput = (DigitalInput) Core.getInputManager().getInput(WSInputs.["MANIPULATOR_RIGHT_TRIGGER"]);
        ShootInput.addInputListener(this);
    }

    public void initOutputs(){
        shooterMotorOne = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.LAUNCHER1);
        shooterMotorTwo = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.LAUNCHER2);
        kickerMotor = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.KICKER);

        SolenoidOpener = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.KickerMotor);
    }

    @Override
    public void selfTest(){
        
    }

    public void update() {
        if (ShooterTime.get() < 1){
            ShooterOn = true;
        }
        else {
            ShooterOn = false;
        }
        if (ShooterOn = true && ShooterOn.Override = false){

        }
    }
    
    @Override
    public void resetState() {

    }

    public String getName() {
        // TODO Auto-generated method stub
        return "Launcher";
    }
    
}