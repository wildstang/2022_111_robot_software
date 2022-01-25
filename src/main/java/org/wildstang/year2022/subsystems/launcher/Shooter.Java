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

public class Launcher implements Subsystem{
    private DigitalInput ShootInput;
        //AutoInput.Name = "MANIPULATOR_RIGHT_TRIGGER";

    private WsSparkMax shooterMotor;
        //shooterMotor.WsName = "shooterMotor";
    private CANSparkMax kickerMotor;
        //kickerMotor.WsName = "kickerMotor";
    //Add solenoid output

    public boolean IsShooterOn = false;

    public static final double UPPER_GOAL_DISTANCE_LIMIT = 0.0;
    public static final double REVS_PER_INCH = 1.0 / 2.0;
    public static final double TICKS_PER_REV = 1024.0;
    public static final double TICKS_PER_INCH = TICKS_PER_REV * REVS_PER_INCH;
    
    public enum driveType {TELEOP, AUTO, CROSS};
    public driveType driveState;
    
    public void inputUpdate(Input source) {
        if 
    }

    public void init(){

    }

    public void initInputs(){
        bumperRight =
        bumperLeft = 
        LeftButtonUp = 
        LeftButtonDown =
    }

    public void initOutputs(){

    }

    @Override
    public void selfTest(){
        
    }

    public void update() {

    }
    
    @Override
    public void resetState() {

    }

    public String getName() {
        // TODO Auto-generated method stub
        return "Launcher";
    }
    
}