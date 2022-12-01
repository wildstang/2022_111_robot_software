package org.wildstang.framework.auto.demo;

import org.wildstang.framework.auto.AutoManager;
import org.wildstang.framework.auto.AutoProgram;
import org.wildstang.framework.auto.demo.Calculations;
import org.wildstang.framework.core.Core;
import org.wildstang.sample.subsystems.swerve.SwerveDrive;
import org.wildstang.framework.auto.demo.StepLoop;

import org.wildstang.year2022.robot.WSSubsystems;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import org.wildstang.year2022.subsystems.swerve.WSSwerveHelper;

public class Routine {
    //the Routine
    private int bounds = 4;
    private double facingAngle;
    private double moveDirection;
    private double speedx;
    private double speedy;
    private int loopCount; // in frames should be 50times per sec
    private final AHRS gyro = new AHRS(SerialPort.Port.kUSB);
    private WSSwerveHelper swerveHelper = new WSSwerveHelper();
    private Calculations calcs = new Calculations();
    private AutoManager autoMan; //remove??
    private boolean Started;
    private static SwerveDrive swerve;
    
    public Routine(){
        gyro.reset();
        autoMan.addProgram(new StepLoop());
        Started = false;
        swerve = (SwerveDrive) Core.getSubsystemManager().getSubsystem(WSSubsystems.SWERVE_DRIVE);
    }


    public void update(){
        // things to check every frame
            //all posable encoders
            //gyro
            //if its outside the boundries
            
        //stealing speed from auto dont worry about it
        speedx = swerve.autoTempX;
        speedy = swerve.autoTempY;
        
        moveDirection = 0;
        facingAngle = gyro.getAngle();
        if (calcs.Calc() && !Started){
            autoMan.startCurrentProgram();
            Started = true;
        }
        else if(!calcs.Calc()){
            returnToCenter();
        }
        calcs.update(swerveHelper.getDirection(speedx, speedy),0.4);

    }
    
    public void returnToCenter(){
        //return robot to center
        //once activated
    }

    
    
}
