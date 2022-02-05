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

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj2.command.PrintCommand;

public class Shooter implements Subsystem{
    //variables for inputs and outputs
        private AnalogInput ShootInput;

        private WsSparkMax shooterMotorOne;
        private WsSparkMax shooterMotorTwo;
        private WsSparkMax kickerMotor;

        private WsSolenoid SolenoidOpener;

    //Variables defining shooter speed -- percent output
        public static final double SAFE_SHOOTER_SPEED = .6;
            public static final double SAFE_SHOOTER_SPEED_RPM = 3;
        public static final double POINT_BLANK_SHOOTER_SPEED = .8;
            public static final double POINT_BLANK_SHOOTER_SPEED_RPM = 4;
        public static final double AIM_MODE_SHOOTER_SPEED = 1;
            public static final double AIM_MODE_SHOOTER_SPEED_RPM = 5;
        public static final double IDLE_SPEED = 0.6;
            public static final double IDLE_SPEED_RPM = 3;

    //Is Shooter On?
        private Timer ShooterTime;
        public Boolean ShooterOn;
        public String ShooterMode = "IDLE";
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
        
        if (ShooterTime.get() < 1){
            ShooterOn = true;
        }
        else {
            ShooterOn = false;
        }
        
        if (ShooterOn == true && ShooterOverride == false){
            kickerMotor.setSpeed(1);
            SolenoidOpener.setValue(true);

            switch(ShooterMode){
                case "IDLE":
                    if (IDLE_SPEED_RPM > shooterMotorOne.getVelocity()){
                        shooterMotorOne.setSpeed(IDLE_SPEED);
                    }
                    break;  
                case "AIM_MODE": 
                    if (AIM_MODE_SHOOTER_SPEED_RPM > shooterMotorOne.getVelocity()){
                        shooterMotorOne.setSpeed(AIM_MODE_SHOOTER_SPEED);
                    }
                    break;
                case "POINT_BLANK": 
                    if (POINT_BLANK_SHOOTER_SPEED_RPM > shooterMotorOne.getVelocity()){
                        shooterMotorOne.setSpeed(POINT_BLANK_SHOOTER_SPEED);
                    }
                    break;
                case "SAFE_SHOOTER": 
                    if (SAFE_SHOOTER_SPEED_RPM > shooterMotorOne.getVelocity()){
                        shooterMotorOne.setSpeed(SAFE_SHOOTER_SPEED);
                    }
                    break;
            }
        }
        else{
            if (ShooterOverride != true){
                //IDLE Shooter
                shooterMotorOne.setSpeed(IDLE_SPEED);
                kickerMotor.setSpeed(.5);
                SolenoidOpener.setValue(false);
            }
            shooterMotorOne.setSpeed(SHOOTER_OVERRIDE_SPEED);
            kickerMotor.setSpeed(KICKER_OVERRIDE_SPEED);
            SolenoidOpener.setValue(SOLENOID_OVERRIDE);
        }   
    }
    
    @Override
    public void resetState() {

    }

    public String getName() {
        return "Shooter";
    }
}