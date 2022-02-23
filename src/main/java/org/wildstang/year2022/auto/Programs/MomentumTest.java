package org.wildstang.year2022.auto.Programs;

import org.wildstang.framework.auto.AutoProgram;
import org.wildstang.year2022.auto.Steps.Momentum;

public class MomentumTest extends AutoProgram{

    @Override
    protected void defineSteps() {
        // TODO Auto-generated method stub
        addStep(new Momentum(0.3));
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Momentum Test";
    }
    
}
