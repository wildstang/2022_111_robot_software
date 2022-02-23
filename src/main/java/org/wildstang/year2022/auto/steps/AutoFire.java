package org.wildstang.year2022.auto.steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.launcher.Launcher;
/**
 * Auto step that fires the launcher.
 * @author John
 */
public class AutoFire extends AutoStep {
    private Launcher launcher;

    private boolean fire;

    public AutoFire() {
        this(true);
    }

    public AutoFire(boolean f) {
        launcher = (Launcher) Core.getSubsystemManager().getSubsystem(WSSubsystems.LAUNCHER.getName());
        fire = f;
        
    }

    
    public void initialize() {
        if (fire){
            launcher.fire();
        } else {
            launcher.resetState();
        }
    }

    
    public void update() {
        setFinished(true);
    }
    
    
    public String toString() {
        return "Fire";
    }
}