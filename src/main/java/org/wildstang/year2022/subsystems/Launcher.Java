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
    //variables for inputs and outputs
        private DigitalInput ShootInput;
            ShootInput.WsName = "MANIPULATOR_RIGHT_TRIGGER";

        private WsSparkMax shooterMotorOne;
            shooterMotorOne.WsName = "LAUNCHER1";
        private WsSparkMax shooterMotorTwo;
            shooterMotorTwo.WsName = "LAUNCHER1";
        private WsSparkMax kickerMotor;
            kickerMotor.WsName = "KICKER";
        private WsSolenoid SolenoidOpener;
            SolenoidOpenor.WsName = "LAUNCHBAR";

    //Variables defining shooter speed
        public static final double SAFE_SHOOTER_SPEED = 0*(3750 * TICKS_PER_REV) / 600.0;//dropped to 25600 from 34133
        public static final double POINT_BLANK_SHOOTER_SPEED = 26000;
        public static final double AIM_MODE_SHOOTER_SPEED = 38000;//4*(6750 * TICKS_PER_REV) / 600.0;//51200
        public static final double IDLE_SPEED = 0.6;//idle percent output

    //Is Shooter On?
        private Timer ShooterTime;
        public Boolean ShooterOn;
            ShooterOn.Override = Boolean false;
        protected final double delay;

    //What Mode Are We In?
        public enum driveType {TELEOP, AUTO, CROSS};
        public driveType driveState;
    
    public void inputUpdate(Input source) {
        if (source == ShootInput){
            if (ShootInput.getValue()) {
                private TheInput = ShooterInput.getValue();
                if (TheInput == 1) {
                    ShooterTime.reset ();
                }
            }
        }

    }

    public void init(){
        ShooterTime.start ();
    }

    public void initInputs(){
        ShootInput = (DigitalInput) Core.getInputManager().getInput(WSInputs.[ShootInput.WsName]);
        ShootInput.addInputListener(this);
    }

    public void initOutputs(){
        shooterMotorOne = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs[shooterMotorOne.WsName]);
        shooterMotorTwo = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs[shooterMotorTwo.WsName]);
        kickerMotor = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs[KickerMotor.WsName]);

        SolenoidOpener = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs[SolenoidOpenor.WsName]);
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
        if (ShooterOn.Override == true){
            
            if (ShooterOn == true){

            }
        else 
        }
        if (ShooterOn == true && ShooterOn.Override == false){
             
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