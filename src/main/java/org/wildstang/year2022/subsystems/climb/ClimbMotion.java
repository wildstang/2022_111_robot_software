package org.wildstang.year2022.subsystems.climb;

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

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.wildstang.year2022.subsystems.climb.ClimbConstants;

public class ClimbMotion {

    private WsSparkMax RightClimber;
    private WsSparkMax LeftClimber;

    private ClimbConstants constant = new ClimbConstants();
    private boolean IsExtended;
    private boolean IsRotated;

    private double climberSpeed;
    private boolean AutoClimbing;
    
    public void init() {
        IsExtended = false;
        IsRotated = false;
        climberSpeed = 0;
        AutoCliming = false;


    }


    public void update() {
        RightClimber.setSpeed(climberSpeed);
        LeftClimber.setSpeed(climberSpeed);
        

        

    }




    public void selfTest() {

    }


    public void resetState() {

    }


    public String getName() {
        return "Climb Motion";
    }
}
