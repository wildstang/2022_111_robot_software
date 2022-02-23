package org.wildstang.year2022.auto.Steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.swerve.SwerveDrive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Momentum extends AutoStep{

    private double percentOutput;
    private SwerveDrive swerve;

    public Momentum(double percent){
        this.percentOutput = percent;
        this.swerve = (SwerveDrive) Core.getSubsystemManager().getSubsystem(WSSubsystems.SWERVE_DRIVE);
    }

    @Override
    public void initialize() {
        swerve.setCharaDrive(percentOutput);
    }

    @Override
    public void update() {
        SmartDashboard.putNumber("Momentum percent", percentOutput);
        SmartDashboard.putNumber("Momentum Velocity", swerve.getVelocity());
    }

    @Override
    public String toString() {
        return "Momentum";
    }
    
}
