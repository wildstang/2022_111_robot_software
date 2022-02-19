package org.wildstang.year2022.auto.steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.launcher.Launcher;
/**
 * Auto step that sets the desired flywheel velocity.
 * @author John
 */
public class AutoFlywheelSpeed extends AutoStep {
    private Launcher launcher;

    private double velocity;
    
    public AutoFlywheelSpeed() {
        this(Launcher.outputVelocityThresholdPercent*Launcher.maxOutputVelocity);
    }

    public AutoFlywheelSpeed(double velocity) {
        launcher = (Launcher) Core.getSubsystemManager().getSubsystem(WSSubsystems.LAUNCHER.getName());
    }

    @Override
    public void initialize() {
        launcher.setVelocity(velocity);
    }

    @Override
    public void update() {
        setFinished(true);
    }
    
    @Override
    public String toString() {
        return "Flywheel Speed";
    }
}