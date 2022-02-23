package org.wildstang.year2022.auto.Programs;

import org.wildstang.framework.auto.AutoProgram;
import org.wildstang.year2022.auto.Steps.Friction;

public class FrictionTest extends AutoProgram{

    @Override
    protected void defineSteps() {
        addStep(new Friction(0.8, 0.1));
    }

    @Override
    public String toString() {
        return "Friction Test";
    }

    
    
}
