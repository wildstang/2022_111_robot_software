package org.wildstang.framework.auto.demo;

import org.wildstang.framework.auto.AutoProgram;

import org.wildstang.framework.auto.steps.SwervePathFollowerStep;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.swerve.SwerveDrive;
import org.wildstang.framework.auto.steps.AutoParallelStepGroup;
import org.wildstang.framework.auto.steps.PathHeadingStep;
import org.wildstang.framework.auto.steps.SetGyroStep;
import org.wildstang.framework.auto.steps.SwervePathFollowerStep;
import org.wildstang.framework.auto.steps.control.AutoStepDelay;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.auto.Programs.TestStraight5;
import org.wildstang.year2022.auto.Steps.Fire;
import org.wildstang.year2022.auto.Steps.IntakeDeployStep;
import org.wildstang.year2022.auto.Steps.LimelightStep;
import org.wildstang.year2022.auto.Steps.ReverseIntakeStep;
import org.wildstang.year2022.auto.Steps.StartFlywheel;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.launcher.LauncherModes;
import org.wildstang.year2022.subsystems.swerve.SwerveDrive;

import frc.paths.*;

public class StepLoop extends AutoProgram{

    @Override
    protected void defineSteps() {
        // TODO Auto-generated method stub
        //run through a loop
        SwerveDrive swerve = (SwerveDrive) Core.getSubsystemManager().getSubsystem(WSSubsystems.SWERVE_DRIVE);

        //intake out
        addStep(new IntakeDeployStep(true));
        //spin
        addStep(new PathHeadingStep(0,swerve));
        addStep(new PathHeadingStep(180,swerve));
        addStep(new PathHeadingStep(0,swerve));
        //intake in
        addStep(new IntakeDeployStep(false));
        //fly on
        addStep(new StartFlywheel(LauncherModes.AUTO));
        //move forward
        addStep(new SwervePathFollowerStep(new Straight5ft().getPath(), swerve));
        //fly off
        addStep(new StartFlywheel(LauncherModes.ZERO));
        //spin
        addStep(new PathHeadingStep(180,swerve));
        addStep(new PathHeadingStep(0,swerve));
        addStep(new PathHeadingStep(180,swerve));
        //moveback
        addStep(new SwervePathFollowerStep(new Straight5ft().getPath(), swerve));
        //minor delay
        addStep(new AutoStepDelay(500));
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Step Loop";
    }

}
