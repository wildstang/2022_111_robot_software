package org.wildstang.framework.auto.steps;

import java.util.ArrayList;
import java.util.List;

import org.wildstang.framework.auto.AutoStep;

/**
 * Parallel groups execute all contained steps in the same frame. Be careful!
 * Note: a finished step is immediately removed from the list. update() is
 * not called on any step that finishes.
 * @author coder65535
 */
public class AutoParallelStepGroup extends AutoStep {

    final List<AutoStep> steps = new ArrayList<>();
    boolean initialized = false;
    String name = "";

    /**
     * Allow empty constructors since groups contain steps anyway.
     */
    public AutoParallelStepGroup() {
        this("");
    }

    /**
     * Constructor takes a name since there may be multiple groups.
     * @param name Name for the step group.
     */
    public AutoParallelStepGroup(String name) {
        this.name = name;
    }

    /**
     * Initializes each step in the group.
     */
    @Override
    public void initialize() {
        for (AutoStep step : steps) {
            step.initialize();
        }
        initialized = true;
    }

    /**
     * Updates all steps in the group until each is finished.
     * Removes each step as it completes.
     */
    @Override
    public void update() {
        List<AutoStep> toRemove = new ArrayList<>();
        for (AutoStep step : steps) {
            step.update();
            if (step.isFinished()) {
                toRemove.add(step);
            }
        }

        for (AutoStep removeStep : toRemove) {
            steps.remove(removeStep);
        }

        if (steps.isEmpty()) {
            setFinished(true);
        }
    }

    /**
     * Add a step to the group if it is not initialized.
     * @param step Step to add to the group.
     */
    public void addStep(AutoStep step) {
        if (!initialized) {
            steps.add(step);
        }
    }

    /**
     * Group name is used in conjuction with "Parrallel step group: "
     * @return Name of the group.
     */
    @Override
    public String toString() {
        return "Parallel step group: " + name;
    }
}
