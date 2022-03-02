package org.wildstang.year2022.auto.steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.subsystems.ballpath.Ballpath;
import org.wildstang.year2022.subsystems.launcher.Launcher;
import org.wildstang.year2022.subsystems.launcher.LauncherModes;

public class Fire extends AutoStep{

    Ballpath ballpath;
    Launcher launcher;
    LauncherModes modeToUse;

    public Fire(LauncherModes mode){
        ballpath = (Ballpath) Core.getSubsystemManager().getSubsystem("Ballpath");
        launcher = (Launcher) Core.getSubsystemManager().getSubsystem("Launcher");
        modeToUse = mode;
    }

    @Override
    public void initialize() {
        ballpath.turnOnFeed();
        launcher.fire(true);
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
