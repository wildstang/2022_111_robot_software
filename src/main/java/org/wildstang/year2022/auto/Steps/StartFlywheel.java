package org.wildstang.year2022.auto.Steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.subsystems.Hood.Hood;
import org.wildstang.year2022.subsystems.launcher.Launcher;
import org.wildstang.year2022.subsystems.launcher.LauncherModes;

public class StartFlywheel extends AutoStep{

    LauncherModes modeToUse;
    Launcher launcher;
    Hood hood;

    public StartFlywheel(LauncherModes mode){
        modeToUse = mode;
        launcher = (Launcher) Core.getSubsystemManager().getSubsystem("Launcher");
        hood = (Hood) Core.getSubsystemManager().getSubsystem("Hood");
    }
    @Override
    public void initialize() {
        launcher.setLauncher(modeToUse);
        hood.setHood(modeToUse);
    }

    @Override
    public void update() {
        setFinished(true);
    }

    @Override
    public String toString() {
        return "Start Flywheel";
    }
    
}
