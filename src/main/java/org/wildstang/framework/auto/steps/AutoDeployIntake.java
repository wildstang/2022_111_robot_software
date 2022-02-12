package org.wildstang.framework.auto.steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.BallpathSubsystem_V2;

public class AutoDeployIntake extends AutoStep {

    private BallpathSubsystem_V2 intake;

    public AutoDeployIntake(){
        this(true);
    }

    public AutoDeployIntake(boolean deploy) {
        intake = (BallpathSubsystem_V2) Core.getSubsystemManager().getSubsystem(WSSubsystems.BALLPATH_INTAKE.getName());
        if (deploy){
            intake.intakeDeploy();
            intake.turnOnIntake();
        }else{
            intake.resetState();
        }
        //this if else statement should be in initialize but the build fails any way I do this so it is here instead
    }

    @Override
    public void initialize() {

    }

    @Override
    public void update() {
        setFinished(true);
        
    }

    @Override
    public String toString() {
        return "AutoDeployIntake";
    }
}
