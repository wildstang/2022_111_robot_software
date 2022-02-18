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

    private WsSparkMax RightClimber; //left is follower

    private WsDoubleSolenoid RightSol;
    private WsDoubleSolenoid LeftSol;

    private ClimbConstants constant = new ClimbConstants();
    public boolean IsExtended;
    public boolean IsRotated;
    public boolean isClimbing;
    
    private double climberSpeed;

    private enum AutoState {IDLE, DEPLOY, RETRACT, GRAB}
    private AutoState autoState;
    
    public ClimbMotion() {

        RightSol = (WsDoubleSolenoid) Core.getOutputManager().getOutput(WSOutputs.CLIMB_SOLENOID_1);
        LeftSol = (WsDoubleSolenoid) Core.getOutputManager().getOutput(WSOutputs.CLIMB_SOLENOID_2);

        RightClimber = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.CLIMB);
        
        
        resetState();
    }


    public void update() {
        //handle motor output
        if (climberSpeed < 0 && Math.abs(RightClimber.getPosition()) >= constant.RETRACTED_POS){
            RightClimber.setSpeed(climberSpeed);
        } else if (climberSpeed > 0 && Math.abs(RightClimber.getPosition()) < constant.EXTENDED_POS){
            if (autoState == AutoState.DEPLOY && RightClimber.getPosition() >= constant.DEPLOY_POS){
                RightClimber.setSpeed(0);
            } else {
                RightClimber.setSpeed(climberSpeed);
            }
        } else {
            RightClimber.setSpeed(0);
        }
        if(IsRotated){
            RightSol.setValue(WsDoubleSolenoidState.REVERSE.ordinal()); //because initialized to .FORWARD, and i'm assuming starts not rotated.
            LeftSol.setValue(WsDoubleSolenoidState.REVERSE.ordinal());
        }
        else{ 
            RightSol.setValue(WsDoubleSolenoidState.FORWARD.ordinal());
            LeftSol.setValue(WsDoubleSolenoidState.FORWARD.ordinal());
        }
        
        if (autoState == AutoState.IDLE){
        } else if (autoState == AutoState.DEPLOY){
            Extend();
            if (RightClimber.getPosition() >= constant.DEPLOY_POS){
                autoState = AutoState.IDLE;
            }
        } else if (autoState == AutoState.GRAB){
            if (RightClimber.getPosition() < constant.DEPLOY_POS){
                Tilt();
            } else {
                UnTilt();
            }
            Extend();
            if (RightClimber.getPosition() >= constant.EXTENDED_POS){
                autoState = AutoState.IDLE;
            }
        } else if (autoState == AutoState.RETRACT){
            Retract();
            if (RightClimber.getPosition() < constant.RETRACTED_POS){
                autoState = AutoState.IDLE;
            }
        }

        //SmartDashboard
        SmartDashboard.putNumber("Climb Motor Speed", climberSpeed);
        SmartDashboard.putBoolean("Is Rotated", IsRotated);
        SmartDashboard.putBoolean("Is Extended", IsExtended);
        SmartDashboard.putBoolean("Is AutoClimbing", autoState != AutoState.IDLE);

        

    }

    //commands
    public void Extend(){
        if (isClimbing) climberSpeed = constant.DEPLOY_PERCENT_SPEED; 
    }
    public void Retract(){
        if (isClimbing) climberSpeed = -constant.RETRACT_PERCENT_SPEED;
    }
    public void Tilt(){
        if (isClimbing) IsRotated = true;
    }
    public void UnTilt(){
        if (isClimbing) IsRotated = false;
    }

    public void AutoClimb(){
        if (!isClimbing){
            autoState = AutoState.DEPLOY;
            isClimbing = true;
        } else if (autoState == AutoState.IDLE){
            if (RightClimber.getPosition() >= constant.DEPLOY_POS){
                autoState = AutoState.RETRACT;
            } else {
                autoState = AutoState.GRAB;
            }
        }
        
        Extend(); //first step to climb any bar is to extend.
    }
    public void StopAutoClimb(){
        autoState = AutoState.IDLE;
    }

    
    public void selfTest() {
    }

    public void resetState() {
        IsExtended = false;
        IsRotated = false;
        isClimbing = false;
        autoState = AutoState.IDLE;
        climberSpeed = 0;

        RightClimber.resetEncoder();
    }

    public String getName() {
        return "Climb Motion";
    }
}
