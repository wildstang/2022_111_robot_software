package org.wildstang.framework.auto.steps.control;

import org.wildstang.framework.auto.AutoStep;

/**
 * Do nothing. This step does nothing, and never finishes, effectively
 * halting autonomous operations.
 * Note: If included in a parallel step group, it only halts operations
 * after all other steps in the group finish.
 * @author coder65535
 */
public class AutoStepStopAutonomous extends AutoStep {

    /**
     * Does nothing on initialization.
     */
    @Override
    public void initialize() {
        // Do nothing.
    }

    /**
     * Does nothing forever, never finishes.
     */
    @Override
    public void update() {
        // Do nothing.
    }

    /**
     * Returns the name of the AutoStep, used to uniquely identify the step.
     * @return Name of the AutoStep, "Stop auto-op".
     */
    @Override
    public String toString() {
        return "Stop auto-op";
    }
}
