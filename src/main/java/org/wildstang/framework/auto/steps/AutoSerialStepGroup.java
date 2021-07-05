package org.wildstang.framework.auto.steps;

import java.util.ArrayList;
import java.util.List;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.logger.Log;

/**
 * Serial groups execute all contained steps sequentially.
 * @author coder65535
 */
public class AutoSerialStepGroup extends AutoStep {

    final List<AutoStep> steps = new ArrayList<>();
    int currentStep = 0;
    boolean initialized = false;
    String name = "";
    private boolean finishedPreviousStep;

    /**
     * Allow empty constructors since groups contain steps anyway.
     */
    public AutoSerialStepGroup() {
        this("");
    }

    /**
     * Constructor takes a name since there may be multiple groups.
     * @param name Name for the step group.
     */
    public AutoSerialStepGroup(String name) {
        this.name = name;
    }

    /**
     * Initializes each step in the group.
     */
    @Override
    public void initialize() {
        finishedPreviousStep = false;
        currentStep = 0;
        if (!steps.isEmpty()) {
            steps.get(currentStep).initialize();
            Log.info("Starting step " + steps.get(currentStep).toString());
        }
        initialized = true;
    }

    /**
     * Updates the current step in the group until it is finished.
     * Then moves on to the next step. Group is finished when last step is finished.
     */
    @Override
    public void update() {
        if (isFinished()) {
            return;
        }
        if (finishedPreviousStep) {
            finishedPreviousStep = false;
            currentStep++;
            if (currentStep >= steps.size()) {
                // We have reached the end of our list of steps, we're finished
                setFinished(true);
                return;
            }
            else {
                steps.get(currentStep).initialize();
                Log.info("Starting step " + steps.get(currentStep).toString());
            }
        }
        AutoStep step = steps.get(currentStep);
        step.update();
        if (step.isFinished()) {
            finishedPreviousStep = true;
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
     * Group name is used in conjuction with "Serial step group: "
     * @return Name of the group.
     */
    @Override
    public String toString() {
        return "Serial step group: " + name;
    }

    /**
     * Gets the current executing AutoStep.
     * @return The current AutoStep.
     */
    public AutoStep getCurrentStep() {
        return steps.get(currentStep);
    }

    /**
     * Gets the next AutoStep to execute.
     * @return The next AutoStep to execute of null if none left.
     */
    public AutoStep getNextStep() {
        if (currentStep + 1 < steps.size()) {
            return steps.get(currentStep + 1);
        } else {
            return null;
        }
    }
}
