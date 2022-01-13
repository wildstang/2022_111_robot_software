package org.wildstang.year2022.auto;

import org.wildstang.framework.auto.AutoProgram;
import org.wildstang.framework.auto.steps.PathFollowerStep;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.drive.Drive;
import frc.paths.*;

/**
 * Sample auto program that just waits 10 seconds before finishing.
 * @author Liam
 */
public class SampleAutoProgram extends AutoProgram {

    @Override
    protected void defineSteps() {
        addStep(new PathFollowerStep(new Test().getPath(),
                (Drive) Core.getSubsystemManager().getSubsystem(WSSubsystems.DRIVE)));
    }

    @Override
    public String toString() {
        return "Sample";
    }
    
}