package org.wildstang.sample.auto;

import org.wildstang.framework.auto.AutoProgram;
import org.wildstang.framework.auto.steps.PathFollowerStep;
import org.wildstang.framework.core.Core;
import org.wildstang.sample.robot.WSSubsystems;
import org.wildstang.sample.subsystems.Drive;

/**
 * Sample auto program that just waits 10 seconds before finishing.
 * @author Liam
 */
public class SampleAutoProgram extends AutoProgram {

    @Override
    protected void defineSteps() {
        addStep(new PathFollowerStep("test", (Drive) Core.getSubsystemManager().getSubsystem(WSSubsystems.DRIVE), true, true, 100));
    }

    @Override
    public String toString() {
        return "Sample";
    }
    
}