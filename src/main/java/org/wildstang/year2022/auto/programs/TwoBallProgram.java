package org.wildstang.year2022.auto.programs;

import org.wildstang.framework.auto.AutoProgram;
import org.wildstang.framework.auto.steps.SwervePathFollowerStep;
import org.wildstang.framework.auto.steps.control.AutoStepDelay;
import org.wildstang.year2022.auto.steps.Fire;
import org.wildstang.year2022.subsystems.launcher.LauncherModes;
import org.wildstang.year2022.auto.steps.StartFlywheel;
import org.wildstang.year2022.auto.steps.IntakeDeployStep;
//import org.wildstang.year2022.auto.steps.OpenIntake;
import org.wildstang.year2022.subsystems.ballpath.Ballpath;
import org.wildstang.year2022.subsystems.swerve.SwerveDrive;
import frc.TwoBallPath.TwoBallPath;


public class TwoBallProgram extends AutoProgram {

    SwerveDrive swerveDrive;

    @Override
    protected void defineSteps() {
        addStep(new IntakeDeployStep(true));
        addStep(new StartFlywheel(LauncherModes.TARMAC_EDGE));
        addStep(new SwervePathFollowerStep(new TwoBallPath().getPath(), swerveDrive));  // back to the ball 
        addStep(new AutoStepDelay(1000));
        addStep(new Fire(LauncherModes.TARMAC_EDGE));
        
        
        
    }

    @Override
    public String toString() {
        //give it a name
        return "TwoBallPath";
    }

}
