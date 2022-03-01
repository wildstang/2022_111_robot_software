//package org.wildstang.year2022.auto;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.ballpath.Ballpath;

/**
 * Deploys intake and runs feed and intake motors
 * @author Ethan Jensen
 */
public class OpenIntake extends AutoStep {

    private Ballpath intake;
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
        intake = (Ballpath) Core.getSubsystemManager().getSubsystem(WSSubsystems.BALLPATH);
    }
}