package org.wildstang.framework.auto.demo;

import org.wildstang.framework.auto.demo.Calculations;
import org.wildstang.framework.core.Core;
import org.wildstang.sample.subsystems.swerve.SwerveDrive;

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
    
    public Routine(){
        gyro.reset();
    }

    public void loop(int LC){
        //Look on to do for order ig

        //LC is the time in frames
        //50 fps


        //step one
        //convert to seconds
        if (LC < 100 && LC >=0){ //2 seconds

        }else if (LC >= 100 && LC < 240){ // 2 seconds

        }
    }

    public void update(){
        // things to check every frame
            //all posable encoders
            //gyro
            //if its outside the boundries
        
        moveDirection = 0;
        facingAngle = gyro.getAngle();
        if (calcs.Calc()){
            loop(loopCount);
            loopCount ++;
        }
        else {
            returnToCenter();
            loopCount = 0;
        }
        calcs.update(swerveHelper.getDirection(speedx, speedy),0.4);

    }
    
    public void returnToCenter(){
        //return robot to center
    }

    
    
}
