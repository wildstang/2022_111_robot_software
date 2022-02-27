package org.wildstang.year2022.auto.Steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.subsystems.ballpath.Ballpath;
import org.wildstang.year2022.subsystems.launcher.Launcher;

public class Fire extends AutoStep{

    Ballpath ballpath;
    Launcher launcher;
    Boolean turnOn;

    public Fire(boolean turnOn){
        ballpath = (Ballpath) Core.getSubsystemManager().getSubsystem("Ballpath");
        launcher = (Launcher) Core.getSubsystemManager().getSubsystem("Launcher");
        this.turnOn = turnOn;
    }

    @Override
    public void initialize() {
        ballpath.turnOnFeed();
        launcher.fire(turnOn);
    }

    @Override
    public void update() {
        setFinished(true);
    }

    @Override
    public String toString() {
        return "Fire";
    }
    
}
