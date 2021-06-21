package org.wildstang.framework.auto.steps;

import org.wildstang.framework.auto.AutoStep;

/**
 * AutoParallelStepGroup where on completion of any child steps the whole group finishes.
 * @author Joey
 */
public class AutoParallelFinishedOnAnyStepGroup extends AutoParallelStepGroup {

    /**
     * Updates every step in the group, until one is finished, then finishes.
     */
    @Override
    public void update() {
        for (AutoStep step : steps) {
            step.update();
            if (step.isFinished()) {
                steps.clear();
                break;
            }
        }
        if (steps.isEmpty()) {
            setFinished(true);
        }
    }

    /**
     * Returns the name of the AutoStep, used to uniquely identify the step.
     * @return Name of the AutoStep.
     */
    @Override
    public String toString() {
        return "Parallel finished on any step group: " + name;
    }
}
