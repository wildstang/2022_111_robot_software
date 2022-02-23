package org.wildstang.year2022.auto.Steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.swerve.SwerveDrive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Inertia extends AutoStep{

    private double percentOutput;
    private SwerveDrive swerve;

    private double totalV;
    private double totalT;
    private double totalAdjustedPower;
    private double finalV;

    private final double timeStep = 0.02;
    private final double targetVelocity = 2000;

    public Inertia(double percent){
        
        this.percentOutput = percent;
        this.swerve = (SwerveDrive) Core.getSubsystemManager().getSubsystem(WSSubsystems.SWERVE_DRIVE);
    }
    
    @Override
    public void initialize() {
        swerve.setCharaDrive(percentOutput);
        totalV = 0;
        totalT = 0;
        finalV = 0;
    }

    @Override
    public void update() {
        totalT += timeStep;
        totalV += swerve.getVelocity() * 0.02;
        SmartDashboard.putNumber("Inertia time", totalT);
        SmartDashboard.putNumber("Inertia total V", totalV);
        SmartDashboard.putNumber("Inertia measured V", Math.abs(swerve.getVelocity()));
        SmartDashboard.putNumber("Inertia goal V", targetVelocity);
        if (targetVelocity < Math.abs(swerve.getVelocity())){
            finalV = Math.abs(swerve.getVelocity());
            SmartDashboard.putNumber("Inertia final velocity", finalV);
            setFinished();
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Inertia";
    }
    
}
