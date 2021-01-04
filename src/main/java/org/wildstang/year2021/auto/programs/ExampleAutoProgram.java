package org.wildstang.year2021.auto.programs;

import org.wildstang.framework.auto.AutoProgram;

public class ExampleAutoProgram extends AutoProgram {

    @Override
    protected void defineSteps() {
        //addStep(new DelayStep());
    }

    @Override
    public String toString() {
        //give it a name
        return "ExampleAutoProgram";
    }

}