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
import org.wildstang.year2022.robot.WsOutputs.WsSolenoid;
import org.wildstang.year2022.subsystems.swerve.DriveConstants;
import org.wildstang.year2022.subsystems.swerve.SwerveSignal;
import org.wildstang.year2022.subsystems.swerve.WSSwerveHelper;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.wildstang.year2022.subsystems.climb.ClimbConstants;

//Not a subsystem- motor control class for ClimbControl.
public class ClimbMotion {

    private WsSparkMax RightClimber;
    private WsSparkMax LeftClimber;

    private WsSolenoid RightSol;
    private WsSolenoid LeftSol;

    private ClimbConstants constant = new ClimbConstants();
    public boolean IsExtended;
    public boolean IsRotated;

    private double climberSpeed;
    private boolean AutoClimbing;
    

    private double ClimberTracker; //keep track of climber position
    public void init() {

        RightSol = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.CLIMB_RIGHT_SOLENOID);
        LeftSol = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.CLIMB_LEFT_SOLENOID);

        RightClimber = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.RIGHT_CLIMB);
        LeftClimber = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.LEFT_CLIMB);
        
        
        resetState();
    }


    public void update() {
        RightClimber.setSpeed(climberSpeed);
        LeftClimber.setSpeed(climberSpeed);
        if(IsRotated){
            RightSol.setValue(WsSolenoidState.REVERSE); //because initialized to .FORWARD, and i'm assuming starts not rotated.
        }
        else{ 
        
            LeftSol.setValue(WsSolenoidState.FORWARD);
        }

        // update ClimberTracker
        if(RightClimber.getPosition()>(int) (constant.TICKS_PER_ROTATION/2) && !IsExtended){
            ClimberTracker += RightClimber.getPosition();
            RightClimber.resetEncoder();
        }
        if(RightClimber.getPosition()<(int) (constant.TICKS_PER_ROTATION/2) && IsExtended){
            ClimberTracker -= RightClimber.getPosition();
            RightClimber.resetEncoder();
        }
        
        // update climb state (is it done extending/retracting????)
        if(IsExtended && (ClimberTracker - (constant.TICKS_PER_ROTATION-RightClimber.getPosition()))<=constant.RETRACTED_POS){
            IsExtended = false;
            climberSpeed = 0;
            if (AutoClimbing){
                Tilt();
                AutoClimbing = false;
            }
        }
        else if (IsExtended && (ClimberTracker + (RightClimber.getPosition()))>=constant.EXTENDED_POS){
            IsExtended = true;
            climberSpeed = 0;
            if(AutoClimbing){
                Retract();
            }
        }

        
        

    }


    public void Extend(){
        climberSpeed = constant.CLIMB_SPEED;
    }
    public void Retract(){
        climberSpeed = -1*constant.CLIMB_SPEED;
    }
    public void Tilt(){
        IsRotated = true;
    }
    public void UnTilt(){
        IsRotated = false;
    }

    public void AutoClimb(){
        AutoClimbing = true;
        Extend();
    }
    public void selfTest() {

    }


    public void resetState() {
        RightClimber.resetEncoder();
    }


    public String getName() {
        return "Climb Motion";
    }
}
