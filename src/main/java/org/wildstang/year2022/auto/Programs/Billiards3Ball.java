package org.wildstang.year2022.auto.Programs;

import org.wildstang.framework.auto.AutoProgram;
import org.wildstang.framework.auto.steps.AutoParallelStepGroup;
import org.wildstang.framework.auto.steps.PathHeadingStep;
import org.wildstang.framework.auto.steps.SetGyroStep;
import org.wildstang.framework.auto.steps.SwervePathFollowerStep;
import org.wildstang.framework.auto.steps.control.AutoStepDelay;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.auto.Steps.Fire;
import org.wildstang.year2022.auto.Steps.IntakeDeployStep;
import org.wildstang.year2022.auto.Steps.LimelightStep;
import org.wildstang.year2022.auto.Steps.ReverseIntakeStep;
import org.wildstang.year2022.auto.Steps.StartFlywheel;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.launcher.LauncherModes;
import org.wildstang.year2022.subsystems.swerve.SwerveDrive;

import frc.paths.Scoot;
import frc.paths.Steal1;
import frc.paths.StealNew;
import frc.paths.StealNew2;
import frc.paths.StealNew3;
import frc.paths.StealNew4;
import frc.paths.TwoBall;

public class Billiards3Ball extends AutoProgram{

    private SwerveDrive swerve;

    @Override
    protected void defineSteps() {
        
        SwerveDrive swerve = (SwerveDrive) Core.getSubsystemManager().getSubsystem(WSSubsystems.SWERVE_DRIVE);

        AutoParallelStepGroup group1 = new AutoParallelStepGroup();
        group1.addStep(new IntakeDeployStep(true));
        group1.addStep(new StartFlywheel(LauncherModes.AUTO));
        group1.addStep(new SetGyroStep(201.7, swerve));
        group1.addStep(new PathHeadingStep(212.5, swerve));
        group1.addStep(new AutoStepDelay(500));
        addStep(group1);

        addStep(new SwervePathFollowerStep(new TwoBall().getPath(), swerve));

        addStep(new LimelightStep(true));
        addStep(new AutoStepDelay(500));

        AutoParallelStepGroup group2 = new AutoParallelStepGroup();
        group2.addStep(new Fire(true));
        group2.addStep(new AutoStepDelay(2500));
        addStep(group2);

        AutoParallelStepGroup group3 = new AutoParallelStepGroup();
        group3.addStep(new Fire(false));
        group3.addStep(new LimelightStep(false));
        group3.addStep(new SwervePathFollowerStep(new StealNew().getPath(), swerve));
        group3.addStep(new PathHeadingStep(270, swerve));
        addStep(group3);

        AutoParallelStepGroup group4 = new AutoParallelStepGroup();
        group4.addStep(new PathHeadingStep(297.4-14.4, swerve));
        group4.addStep(new SwervePathFollowerStep(new StealNew2().getPath(), swerve));
        addStep(group4);
        
        addStep(new ReverseIntakeStep(1.0));
        addStep(new AutoStepDelay(1500));

        AutoParallelStepGroup group5 = new AutoParallelStepGroup();
        group5.addStep(new PathHeadingStep(270, swerve));
        group5.addStep(new SwervePathFollowerStep(new StealNew3().getPath(), swerve));
        group5.addStep(new IntakeDeployStep(true));
        addStep(group5);

        AutoParallelStepGroup group6 = new AutoParallelStepGroup();
        group6.addStep(new PathHeadingStep(212.5-12.5, swerve));
        group6.addStep(new SwervePathFollowerStep(new StealNew4().getPath(), swerve));
        addStep(group6);

        addStep(new LimelightStep(true));
        addStep(new AutoStepDelay(500));
        addStep(new Fire(true));





    }

    public String toString(){
        return "Two Ball and another";
    }
}
