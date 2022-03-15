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
import org.wildstang.year2022.auto.Steps.StartFlywheel;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.launcher.LauncherModes;
import org.wildstang.year2022.subsystems.swerve.SwerveDrive;

import frc.paths.Scoot;
import frc.paths.TwoBall;

public class Two_Ball extends AutoProgram{

    private SwerveDrive swerve;

    @Override
    protected void defineSteps() {
        
        SwerveDrive swerve = (SwerveDrive) Core.getSubsystemManager().getSubsystem(WSSubsystems.SWERVE_DRIVE);

        AutoParallelStepGroup group1 = new AutoParallelStepGroup();
        group1.addStep(new IntakeDeployStep(true));
        group1.addStep(new StartFlywheel(LauncherModes.AUTO));
        group1.addStep(new SetGyroStep(201.7, swerve));
        group1.addStep(new PathHeadingStep(212.5, swerve));
        group1.addStep(new AutoStepDelay(2000));
        addStep(group1);

        addStep(new SwervePathFollowerStep(new TwoBall().getPath(), swerve));

        AutoParallelStepGroup group2 = new AutoParallelStepGroup();
        group2.addStep(new Fire(true));
        //group2.addStep(new LimelightStep(true));
        group2.addStep(new AutoStepDelay(2500));
        addStep(group2);

        addStep(new SwervePathFollowerStep(new Scoot().getPath(), swerve));



    }

    public String toString(){
        return "Two Ball";
    }
}
