package org.wildstang.year2022.auto.Programs;
import org.wildstang.framework.auto.AutoProgram;
import org.wildstang.framework.auto.steps.PathHeadingStep;
import org.wildstang.framework.auto.steps.SwervePathFollowerStep;
import org.wildstang.framework.auto.steps.control.AutoStepDelay;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.swerve.SwerveDrive;

import frc.paths.*;
public class TestHeading extends AutoProgram{

    @Override
    protected void defineSteps() {
        // TODO Auto-generated method stub
        SwerveDrive swerve = (SwerveDrive) Core.getSubsystemManager().getSubsystem(WSSubsystems.SWERVE_DRIVE);
        //addStep(new SwervePathFollowerStep(new Circle().getPath(), swerve));
        addStep(new PathHeadingStep(270, swerve));
         addStep(new SwervePathFollowerStep(new Straight5ft().getPath(), swerve));
         addStep(new AutoStepDelay(2000));
        // addStep(new SwervePathFollowerStep(new Straight10ft().getPath(), swerve));
        addStep(new PathHeadingStep(00, swerve));
        addStep(new SwervePathFollowerStep(new Straight5ft().getPath(), swerve));
         //addStep(new SwervePathFollowerStep(new Test().getPath(), swerve));
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Test Heading";
    }
    
}
