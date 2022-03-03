package org.wildstang.year2022.auto.Programs;

import org.wildstang.framework.auto.AutoProgram;
import org.wildstang.year2022.auto.Steps.Inertia;

public class InertiaTest extends AutoProgram{

    @Override
    protected void defineSteps() {
        // TODO Auto-generated method stub
        addStep(new Inertia(0.5));
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Inertia Test";
    }
    
}
