package org.wildstang.year2022.auto.Steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.Hood.AimHelper;
import org.wildstang.year2022.subsystems.Hood.Hood;
import org.wildstang.year2022.subsystems.launcher.Launcher;
import org.wildstang.year2022.subsystems.swerve.SwerveDrive;

public class LimelightStep extends AutoStep {

    private Launcher launcher;
    private Hood hood;
    private SwerveDrive swerve;
    private AimHelper limelight;
    private boolean isAiming;


    public LimelightStep(boolean toAim){
        launcher = (Launcher) Core.getSubsystemManager().getSubsystem(WSSubsystems.LAUNCHER);
        hood = (Hood) Core.getSubsystemManager().getSubsystem(WSSubsystems.HOOD);
        swerve = (SwerveDrive) Core.getSubsystemManager().getSubsystem(WSSubsystems.SWERVE_DRIVE);
        limelight = (AimHelper) Core.getSubsystemManager().getSubsystem(WSSubsystems.LIMELIGHT);
        isAiming = toAim;
    }

    @Override
    public void initialize() {
        launcher.setAiming(isAiming);
        hood.setAiming(isAiming);
        limelight.turnOnLED(isAiming);
        if (isAiming){
            swerve.setAiming();
        }
    }

    @Override
    public void update() {
        setFinished();
    }

    @Override
    public String toString() {
        return "Limelight step";
    }
    
}
