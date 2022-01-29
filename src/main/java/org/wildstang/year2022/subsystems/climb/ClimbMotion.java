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
import org.wildstang.hardware.roborio.outputs.WsSolenoid;
import org.wildstang.hardware.roborio.outputs.WsDoubleSolenoidState;
import org.wildstang.hardware.roborio.outputs.WsDoubleSolenoid;
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

    private WsDoubleSolenoid RightSol;
    private WsDoubleSolenoid LeftSol;

    private ClimbConstants constant = new ClimbConstants();
    public boolean IsExtended;
    public boolean IsRotated;

    
    private boolean AutoClimbing;
    
    private double climberSpeed;

    private double ClimberTracker; //keep track of climber motor position
    
    public void ClimbMotion() {

        RightSol = (WsDoubleSolenoid) Core.getOutputManager().getOutput(WSOutputs.CLIMB_RIGHT_SOLENOID);
        LeftSol = (WsDoubleSolenoid) Core.getOutputManager().getOutput(WSOutputs.CLIMB_LEFT_SOLENOID);

        RightClimber = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.RIGHT_CLIMB);
        LeftClimber = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.LEFT_CLIMB);
        
        
        resetState();
    }


    public void update() {
        //handle motor output
        RightClimber.setSpeed(climberSpeed);
        LeftClimber.setSpeed(climberSpeed);
        if(IsRotated){
            RightSol.setValue(WsDoubleSolenoidState.REVERSE.ordinal()); //because initialized to .FORWARD, and i'm assuming starts not rotated.
        }
        else{ 
        
            LeftSol.setValue(WsDoubleSolenoidState.FORWARD.ordinal());
        }

        // update ClimberTracker
        if(RightClimber.getPosition()>(int) (constant.TICKS_PER_ROTATION/2) && !IsExtended){  //if moved more than half rotation since last reset and extending
            ClimberTracker += RightClimber.getPosition();
            RightClimber.resetEncoder(); //so that encoder tracks change and never exeeds maximum
        } 
        if(RightClimber.getPosition()<(int) (constant.TICKS_PER_ROTATION/2) && IsExtended){ //if moved more than half rotation since reset and retracting
            ClimberTracker -= RightClimber.getPosition();
            RightClimber.resetEncoder(); //so that encoder tracks change and never exeeds maximum
        }
        

        // update climb state (is it done extending/retracting????)
        if(IsExtended && (ClimberTracker - (constant.TICKS_PER_ROTATION-RightClimber.getPosition()))<=constant.RETRACTED_POS){
            IsExtended = false; 
            climberSpeed = 0;
            if (AutoClimbing){ //if auto climbing, tilt and stop autoclimbing. autoclimb must be pressed again to climb another bar.
                Tilt();
                AutoClimbing = false;
            }
        }
        else if (!IsExtended && (ClimberTracker + (RightClimber.getPosition()))>=constant.EXTENDED_POS){
            IsExtended = true; 
            climberSpeed = 0;
            if(AutoClimbing){ //if auto climbing, it is time to start retracting.
                Retract();
            }
        }

        //SmartDashboard
        SmartDashboard.putNumber("Climb Motor Speed", climberSpeed);
        SmartDashboard.putBoolean("Is Rotated", IsRotated);
        SmartDashboard.putBoolean("Is Extended", IsExtended);
        SmartDashboard.putBoolean("Is AutoClimbing", AutoClimbing);

        

    }

    //commands
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
        Extend(); //first step to climb any bar is to extend.
    }
    public void StopAutoClimb(){
        AutoClimbing = false;
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
