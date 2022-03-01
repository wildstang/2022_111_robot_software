package org.wildstang.year2022.auto.programs;

import org.wildstang.framework.auto.AutoProgram;
import org.wildstang.framework.auto.steps.control.AutoStepDelay;
import org.wildstang.year2022.auto.steps.Fire;
import org.wildstang.year2022.subsystems.launcher.LauncherModes;
import org.wildstang.year2022.auto.steps.StartFlywheel;
import org.wildstang.year2022.auto.steps.IntakeDeployStep;
//import org.wildstang.year2022.auto.steps.OpenIntake;
import org.wildstang.year2022.subsystems.ballpath.Ballpath;
import org.wildstang.year2022.subsystems.swerve.SwerveDrive;

public class TwoBallProgram extends AutoProgram {
    @Override
    protected void defineSteps() {
        addStep(new IntakeDeployStep(true));
        addStep(new StartFlywheel(LauncherModes.FENDER_SHOT));
        //addStep(new DriveStep(Path));         #Drives -> back to the ball -> then up to finder
        addStep(new AutoStepDelay(1000));
        addStep(new Fire(LauncherModes.FENDER_SHOT));
        addStep(new AutoStepDelay(250));
        //addStep(new DriveStep(Path));         #Drives -> back (maybe runs into enemy ball)
        
    }

    @Override
    public String toString() {
        //give it a name
        return "TwoBallPath";
    }

}
