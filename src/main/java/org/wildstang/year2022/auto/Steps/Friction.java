package org.wildstang.year2022.auto.Steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.swerve.SwerveDrive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Friction extends AutoStep{

    private double initial;
    private double changeValue;
    private SwerveDrive swerve;
    private Timer timer;
    private double lastPos;


    public Friction(double newInitial, double newChangeValue){
        this.initial = newInitial;
        this.changeValue = newChangeValue;
        this.swerve = (SwerveDrive) Core.getSubsystemManager().getSubsystem(WSSubsystems.SWERVE_DRIVE);
        timer = new Timer();
        lastPos = 0;
    }

    public void initialize() {
        swerve.setCharaDrive(initial);
        timer.start();
    }

    public void update() {
        SmartDashboard.putNumber("Friction current Value", initial);
        SmartDashboard.putNumber("Friction Current time state", timer.get());
        SmartDashboard.putNumber("Friction drive value", Math.abs(swerve.getCharaPos()));
        SmartDashboard.putNumber("Friction last pos", lastPos);
        if (timer.get()>2.0){
            if (Math.abs(swerve.getCharaPos()) < lastPos+0.1){
                SmartDashboard.putNumber("current Value", initial);
                swerve.setCharaDrive(0);
                setFinished();
            } else {
                timer.reset();
                initial -= changeValue;
                swerve.setCharaDrive(initial);
                lastPos = Math.abs(swerve.getCharaPos());
            }
        }
    }

    public String toString() {
        return "Friction Test";
    }
    
}
