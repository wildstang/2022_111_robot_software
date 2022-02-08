package org.wildstang.year2022.auto.steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.launcher.Launcher;

public class AutoFlywheelSpeed extends AutoStep {
    private Launcher launcher;

    public AutoFlywheelSpeed() {
        this(1.0);
    }

    public AutoFlywheelSpeed(double velocity) {
        launcher = (Launcher) Core.getSubsystemManager().getSubsystem(WSSubsystems.LAUNCHER.getName());
        launcher.setVelocity(velocity);
    }

    public void initialize() {
    }

    public void update() {
    }
    
    public String toString() {
        return "AutoFlywheelSpeed";
    }
}