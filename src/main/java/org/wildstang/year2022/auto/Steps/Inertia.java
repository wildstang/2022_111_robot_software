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
    private double ii;
    private double iff;

    private final double timeStep = 0.02;
    private final double targetVelocity = 2800;
    private final double timeInitial = 1.0;
    private final double timeFinal = 2.0;

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
        ii = 0;
        iff = 0;
    }

    @Override
    public void update() {
        totalT += timeStep;
            if (totalT >= 1.0 && totalT <= 2.0){
            totalV += swerve.getVelocity() * 0.02;
        }
        if (totalT == 1.0){
            ii = swerve.getVelocity();
        }
        if (totalT == 2.0){
            iff = swerve.getVelocity();
        }
        SmartDashboard.putNumber("Inertia time", totalT);
        SmartDashboard.putNumber("Inertia total Vel", totalV);
        SmartDashboard.putNumber("Inertia measured Vel", Math.abs(swerve.getVelocity()));
        SmartDashboard.putNumber("Inertia intial vel", ii);
        SmartDashboard.putNumber("Inertia goal Vel", targetVelocity);
        SmartDashboard.putNumber("Inertia final value", iff);
        if (targetVelocity < Math.abs(swerve.getVelocity())){
            finalV = Math.abs(swerve.getVelocity());
            SmartDashboard.putNumber("Inertia final velocity", finalV);
            swerve.setCharaDrive(0);
            setFinished();
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Inertia";
    }
    
}
