package org.wildstang.year2022.auto;

import org.wildstang.framework.auto.AutoProgram;
import org.wildstang.framework.auto.steps.SwervePathFollowerStep;
import org.wildstang.framework.core.Core;
import org.wildstang.sample.subsystems.swerve.SwerveDrive;
import org.wildstang.year2022.robot.WSSubsystems;
import frc.paths.*;

/**
 * Sample auto program that just waits 10 seconds before finishing.
 * @author Liam
 */
public class SampleAutoProgram extends AutoProgram {

    @Override
    protected void defineSteps() {
        addStep(new SwervePathFollowerStep(new Test().getPath(), (SwerveDrive) Core.getSubsystemManager().getSubsystem(WSSubsystems.SWERVE_DRIVE)));
    }

    @Override
    public String toString() {
        return "Sample";
    }
    
}