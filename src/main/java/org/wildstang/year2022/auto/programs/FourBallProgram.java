package org.wildstang.year2022.auto.programs;

import org.wildstang.framework.auto.AutoProgram;
import org.wildstang.framework.auto.steps.control.AutoStepDelay;
import org.wildstang.year2022.auto.steps.Fire;
import org.wildstang.year2022.subsystems.launcher.LauncherModes;
import org.wildstang.year2022.auto.steps.StartFlywheel;
import org.wildstang.year2022.auto.steps.IntakeDeployStep;

public class FourBallProgram extends AutoProgram {


    @Override
    protected void defineSteps() {
        addStep(new IntakeDeployStep(true));
        addStep(new StartFlywheel(LauncherModes.FENDER_SHOT));
        //addStep(new DriveStep(Path));         #Drives -> back to the ball -> then up to finder
        addStep(new AutoStepDelay(1000));
        addStep(new Fire(LauncherModes.FENDER_SHOT));
        addStep(new AutoStepDelay(250));
        //addStep(new DriveStep(Path));         #Drives -> back (maybe runs into enemy ball)
        //addStep(new IntakeDeployStep());
        //addStep(new LauncherSpeedStep());
        //addStep(new HopperPresetStep(?));
        // addStep(new PathFollowerStep());
        
        //addStep(new ShootStep(?));
        //addStep(new WaitStep(?));
        //addStep(new Step(?));
        //addStep(new Step());

    }

    @Override
    public String toString() {
        //give it a name
        return "TwoBallPath";
    }

}

//addStep(new WaitStep());
//addStep(new StopStep());