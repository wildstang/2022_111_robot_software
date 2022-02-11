package org.wildstang.year2022.auto;

import org.wildstang.framework.auto.AutoProgram;
import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.auto.steps.SwervePathFollowerStep;
import org.wildstang.framework.core.Core;
import org.wildstang.sample.subsystems.swerve.SwerveDrive;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.BallpathSubsystem_V2;
import org.wildstang.year2022.subsystems.swerve.*;

import frc.paths.*;

/**
 * Deploys intake and runs feed and intake motors
 * @author Ethan Jensen
 */
public class IntakeDeployStep extends AutoStep {

    private BallpathSubsystem_V2 intake;
    private boolean modifier;

    public IntakeDeployStep(boolean deploy){
        this.modifier = deploy;
    }

    public void update(){
        if (modifier){
            intake.intakeDeploy();
            intake.turnOnFeed();
            intake.turnOnIntake();
        }else{
            intake.intakeRetract();
            intake.turnOffIntake();
            intake.turnOffFeed();
        }
        this.setFinished(true);
    }
    
    @Override
    public String toString() {
        return "Intake Deploy";
    }
    
    public void initialize(){
        intake = (BallpathSubsystem_V2) Core.getSubsystemManager().getSubsystem(WSSubsystems.BALLPATH);
    }
}