package org.wildstang.year2022.auto.Programs;

import org.wildstang.framework.auto.AutoProgram;
import org.wildstang.framework.auto.steps.AutoParallelStepGroup;
import org.wildstang.framework.auto.steps.PathHeadingStep;
import org.wildstang.framework.auto.steps.SetGyroStep;
import org.wildstang.framework.auto.steps.SwervePathFollowerStep;
import org.wildstang.framework.auto.steps.control.AutoStepDelay;
import org.wildstang.year2022.auto.Steps.Fire;
import org.wildstang.year2022.auto.Steps.IntakeDeployStep;
import org.wildstang.year2022.auto.Steps.LimelightStep;
import org.wildstang.year2022.auto.Steps.StartFlywheel;
import org.wildstang.year2022.subsystems.ballpath.Ballpath;
import org.wildstang.year2022.subsystems.launcher.LauncherModes;
import org.wildstang.year2022.subsystems.swerve.SwerveDrive;

import frc.paths.Fiver;
import frc.paths.FiverA;
import frc.paths.FiverB;
import frc.paths.FiverC;

public class Offset5Ball extends AutoProgram{

    private SwerveDrive swerve;

    @Override
    protected void defineSteps() {

        AutoParallelStepGroup group1 = new AutoParallelStepGroup();
        group1.addStep(new IntakeDeployStep(true));
        group1.addStep(new StartFlywheel(LauncherModes.AUTO));
        group1.addStep(new SetGyroStep(100.15, swerve));
        group1.addStep(new PathHeadingStep(100.15, swerve));
        group1.addStep(new SwervePathFollowerStep(new Fiver().getPath(), swerve));
        addStep(group1);

        AutoParallelStepGroup group2 = new AutoParallelStepGroup();
        group2.addStep(new Fire(true));
        group2.addStep(new LimelightStep(true));
        group2.addStep(new AutoStepDelay(1500));
        addStep(group2);

        AutoParallelStepGroup group3 = new AutoParallelStepGroup();
        group3.addStep(new Fire(false));
        group3.addStep(new LimelightStep(false));
        group3.addStep(new SwervePathFollowerStep(new FiverA().getPath(), swerve));
        group3.addStep(new PathHeadingStep(144.8, swerve));
        addStep(group3);

        AutoParallelStepGroup group4 = new AutoParallelStepGroup();
        group4.addStep(new Fire(true));
        group4.addStep(new LimelightStep(true));
        group4.addStep(new AutoStepDelay(1000));
        addStep(group4);

        AutoParallelStepGroup group5 = new AutoParallelStepGroup();
        group5.addStep(new Fire(false));
        group5.addStep(new LimelightStep(false));
        group5.addStep(new SwervePathFollowerStep(new FiverB().getPath(), swerve));
        addStep(group5);

        addStep(new AutoStepDelay(1000));

        AutoParallelStepGroup group6 = new AutoParallelStepGroup();
        group6.addStep(new IntakeDeployStep(false));
        group6.addStep(new SwervePathFollowerStep(new FiverC().getPath(), swerve));
        addStep(group6);

        AutoParallelStepGroup group7 = new AutoParallelStepGroup();
        group7.addStep(new IntakeDeployStep(true));
        group7.addStep(new Fire(true));
        group7.addStep(new LimelightStep(true));
        addStep(group7);
        
    }

    @Override
    public String toString() {
        return "Offset 5 Ball";
    }
    
}
