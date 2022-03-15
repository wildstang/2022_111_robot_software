package org.wildstang.year2022.auto.Programs;

import org.wildstang.framework.auto.AutoProgram;
import org.wildstang.framework.auto.steps.control.AutoStepDelay;
import org.wildstang.year2022.auto.Steps.Fire;
import org.wildstang.year2022.auto.Steps.StartFlywheel;
import org.wildstang.year2022.subsystems.launcher.LauncherModes;

public class Shoot extends AutoProgram{

    @Override
    protected void defineSteps() {
        addStep(new StartFlywheel(LauncherModes.FENDER_SHOT));
        addStep(new AutoStepDelay(1000));
        addStep(new Fire(true));
    }

    @Override
    public String toString() {
        return "Shoot";
    }
    
}
