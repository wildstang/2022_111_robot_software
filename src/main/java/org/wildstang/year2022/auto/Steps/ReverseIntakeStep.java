package org.wildstang.year2022.auto.Steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.ballpath.Ballpath;

/**
 * Deploys intake and runs feed and intake motors
 * @author Ethan Jensen
 */
public class ReverseIntakeStep extends AutoStep {

    private Ballpath intake;

    /**Reverse the Intake
     * 
     */
    public ReverseIntakeStep(){
    }

    public void update(){
        intake.intakeRetract();
        intake.reverse();
        this.setFinished(true);
    }
    
    @Override
    public String toString() {
        return "Reverse Intake";
    }
    
    public void initialize(){
        intake = (Ballpath) Core.getSubsystemManager().getSubsystem(WSSubsystems.BALLPATH);
    }
}