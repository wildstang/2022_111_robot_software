package org.wildstang.year2022.auto.programs;

import org.wildstang.framework.auto.AutoProgram;
import org.wildstang.framework.auto.steps.control.AutoStepDelay;
import org.wildstang.year2022.auto.steps.Fire;
import org.wildstang.year2022.subsystems.launcher.LauncherModes;
import org.wildstang.year2022.auto.steps.StartFlywheel;
import org.wildstang.year2022.auto.steps.IntakeDeployStep;

public class FourBallProgram extends AutoProgram {

        //Important >>> I dont know what LAUNCH_PAD means

    @Override
    protected void defineSteps() {
        addStep(new IntakeDeployStep(true));
        addStep(new StartFlywheel(LauncherModes.TARMAC_EDGE));
        //addStep(new DriveStep(Path));         #Drives -> back to the ball 
        addStep(new AutoStepDelay(1000));
        addStep(new Fire(LauncherModes.TARMAC_EDGE));
        addStep(new AutoStepDelay(250));
        addStep(new StartFlywheel(LauncherModes.LAUNCH_PAD));  
        //addStep(new DriveStep(Path));         #Drives -> drives back to human player
        //                                      #Gets ball infront & human player ball
        addStep(new AutoStepDelay(1500));
        addStep(new Fire(LauncherModes.LAUNCH_PAD));
        

    }

    @Override
    public String toString() {
        //give it a name
        return "FourBallPath";
    }

}

//addStep(new WaitStep());
//addStep(new StopStep());