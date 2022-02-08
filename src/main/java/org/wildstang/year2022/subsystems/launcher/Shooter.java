package org.wildstang.year2022.subsystems.launcher;

import javax.lang.model.util.ElementScanner14;

import com.ctre.phoenix.sensors.CANCoder;
import com.kauailabs.navx.frc.AHRS;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.year2022.robot.CANConstants;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;
import org.wildstang.hardware.roborio.outputs.WsSolenoid;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj2.command.PrintCommand;

public class Shooter implements Subsystem{
    //variables for inputs and outputs
        private AnalogInput ShootInput;

        private WsSparkMax shooterMotorOne;
        private WsSparkMax kickerMotor;

        private WsSolenoid SolenoidOpener;

    //Variables defining shooter speed -- percent output
    public enum SHOOTER_MODES{
        SAFE_SHOOTER (.5,3),
        POINT_BLANK (.8,4),
        AIM_MODE(1,5),
        IDLE_SPEED(.5,3);

        private final double SPEED;
        private final double RPM;

        SHOOTER_MODES(double SPEED, double RPM) {
            this.SPEED = SPEED;
            this.RPM = RPM;
        }
        private double getSpeed(){ return SPEED;}
        private double getRPM(){return RPM;}
        }
        public SHOOTER_MODES currentMode;

    //Is Shooter On?
        private Timer ShooterTime;
        public Boolean ShooterOn;
        protected final double delay = 0;

    //Override Speeds
        public Boolean ShooterOverride = false;
        public double SHOOTER_OVERRIDE_SPEED = 0;
        public double KICKER_OVERRIDE_SPEED = .5;
        public Boolean SOLENOID_OVERRIDE = false;

    //What Mode Are We In?
        public enum driveType {TELEOP, AUTO, CROSS};
        public driveType driveState;
            
    //For SmartDashboard
        ShuffleboardTab OVERRIDE_TURRET = Shuffleboard.getTab("OVERRIDE_TURRET");
        
        NetworkTableEntry SmartDashboardOverride = OVERRIDE_TURRET.add("Turn On Override?",boolean SmartDashboardOverride_Val);
            SmartDashboardOverride.setBoolean(false);
        NetworkTableEntry SmartDashboardShooter = OVERRIDE_TURRET.add ("Shooter (RPM)",double SmartDashboardShooter_Val);
            SmartDashboardShooter.setDouble(0);
        NetworkTableEntry SmartDashboardKicker = OVERRIDE_TURRET.add ("Kicker (RPM)",double SmartDashboardKicker_Val);
            SmartDashboardKicker.setDouble(0);
        NetworkTableEntry SmartDashboardSolenoid = OVERRIDE_TURRET.add ("Solenoid (ON)", boolean SmartDashboardSolenoid_Val);
            SmartDashboardSolenoid.setBoolean(false);

    public void inputUpdate(Input source) {
        if (source == ShootInput){
            if (ShootInput.getValue() >.5 ) {
                ShooterTime.reset ();
            }
        }
    }


    public void init(){
        ShooterTime.start ();
    }

    public void initInputs(){
        ShootInput = (AnalogInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_RIGHT_TRIGGER);
        ShootInput.addInputListener(this);
    }

    public void initOutputs(){
        shooterMotorOne = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.LAUNCHER1);
        kickerMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.KICKER);

        SolenoidOpener = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.LAUNCHBAR);
    }

    @Override
    public void selfTest(){
        
    }

    public void update() {
        SmartDashboard.putNumber("Shooter Speed (RPM)", shooterMotorOne.getVelocity());
        SmartDashboard.putNumber("Kicker Speed (RPM)", kickerMotor.getVelocity());
        SmartDashboard.putBoolean("SolenoidOpener On (Bool)", SolenoidOpener.getValue());
        
        if (SmartDashboardOverride.getBoolean(SmartDashboardOverride_Val)){
            ShooterOverride = true;
        }
        else{
            ShooterOverride = false;
        }

        if (ShooterTime.get() < 1){
            ShooterOn = true;
        }
        else {
            ShooterOn = false;
        }
        
        if (ShooterOn == true && ShooterOverride == false){
            double DESIRED_RPM;
            double DESIRED_SPEED;

            DESIRED_RPM = currentMode.getRPM();
            DESIRED_SPEED = currentMode.getSpeed();
            //currentMode = SHOOTER_MODES.SAFE_SHOOTER;
        }
        else{
            if (ShooterOverride != true){
                //IDLE Shooter
                shooterMotorOne.setSpeed(IDLE_SPEED);
                kickerMotor.setSpeed(.5);
                SolenoidOpener.setValue(false);
            }
            shooterMotorOne.setSpeed(SmartDashboardShooter.getDouble(SmartDashboardShooter_Val));
            kickerMotor.setSpeed(SmartDashboardKicker.getDouble(SmartDashboardKicker_Val));
            SolenoidOpener.setValue(SmartDashboardSolenoid.getBoolean(SmartDashboardSolenoid_Val));
        }   
    }
    
    @Override
    public void resetState() {

    }

    public String getName() {
        return "Shooter";
    }
}