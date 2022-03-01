package org.wildstang.year2022.auto.programs;
import org.wildstang.framework.auto.AutoProgram;
import org.wildstang.year2022.subsystems.Hood.Hood;
import org.wildstang.year2022.subsystems.launcher.Launcher;
import org.wildstang.year2022.subsystems.ballpath.Ballpath;
import org.wildstang.year2022.subsystems.swerve.SwerveDrive;

public class FourBallProgram extends AutoProgram {
    private double launcherSpeed = 0;
    private double poopoopeepee;

    @Override
    protected void defineSteps() {
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