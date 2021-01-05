package org.wildstang.year2021.auto.programs;

import org.wildstang.framework.auto.AutoProgram;
import org.wildstang.year2021.auto.steps.DelayStep;

/**
 * This is the framework of an Autonomous Program.
 * Autonomous programs control the robot without any driver/manipulator input.
 * These programs work by defining a series of steps in the "defineSteps" functions.
 * The "toString" function defines a name for the program.
 */
public class ExampleAutoProgram extends AutoProgram {

    @Override
    protected void defineSteps() {
        // define a series of steps
        addStep(new DelayStep(5));
    }

    @Override
    public String toString() {
        // give it a name
        return "ExampleAutoProgram";
    }

}