package org.wildstang.framework.auto.program;

import org.wildstang.framework.auto.AutoProgram;
import org.wildstang.framework.auto.steps.control.AutoStepStopAutonomous;

/**
 * A default AutoProgram that does nothing an never completes.
 * This is used in AutoManager as a final program (program 0) which
 * does nothing but keeps a program running when all programs complete.
 * @author coder65535
 */
public class Sleeper extends AutoProgram {

    /**
     * Defines an AutoProgram's steps, executed in order added.
     * Sleeper uses only a single step: AutoStepStopAutonomous, which
     * does nothing and never finishes.
     */
    @Override
    public void defineSteps() {
        addStep(new AutoStepStopAutonomous());
    }

    /**
     * Returns the AutoProgram's name.
     * @return The name of the AutoProgram.
     */
    @Override
    public String toString() {
        return "Sleeper";
    }
}
