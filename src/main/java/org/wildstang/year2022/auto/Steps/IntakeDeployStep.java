package org.wildstang.year2022.auto.Steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.ballpath.Ballpath;

/**
 * Deploys intake and runs feed and intake motors
 * @author Ethan Jensen
 */
public class IntakeDeployStep extends AutoStep {

    private Ballpath intake;
    private boolean modifier;

    /**Deploy the intake and run the intake and feed
     * 
     * @param deploy true to deploy and run wheels, false to retract and stop wheels
     */
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