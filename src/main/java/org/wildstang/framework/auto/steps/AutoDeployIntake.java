package org.wildstang.framework.auto.steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.BallpathSubsystem_V2;

public class AutoDeployIntake extends AutoStep {

    private BallpathSubsystem_V2 intake;

    private boolean deploy;

    public AutoDeployIntake(boolean deploy){
        this.deploy = deploy;
        intake = (BallpathSubsystem_V2) Core.getSubsystemManager().getSubsystem(WSSubsystems.BALLPATH_INTAKE.getName());
    }

    public AutoDeployIntake() {
        this(true);
    }

    @Override
    public void initialize() {
        if (deploy){
            intake.intakeDeploy();
            intake.turnOnIntake();
        }else{
            intake.resetState();
        }
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
