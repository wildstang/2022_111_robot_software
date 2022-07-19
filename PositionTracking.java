package org.wildstang.year2022.subsystems.Hood;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;

import com.kauailabs.navx.frc.AHRS;

import org.wildstang.year2022.subsystems.swerve.SwerveDrive;

public class PositionTracking implements Subsystem{
    public double x;
    public double y;
    private double[] accel;
    private double[] vel;
    AHRS gyro;
    SwerveDrive swerve;

    @Override
    public void init() {
        swerve = (SwerveDrive) Core.getSubsystemManager().getSubsystem(WSSubsystems.SWERVE_DRIVE);
        gyro = swerve.getGyro();
        resetState();
    }

    @Override
    public void resetState(){
        x = 0;
        y = 0;
    }

    @Override
    public void update(){
        double delta_t = 0.05; //FFFFFFFIIIIIIIXXXXXXXXX THIS ADD TIMER TO GET delta_t!!!!
        vel = swerve.getVelocity();
        //x += vel[0]*delta_t;
        //y += vel[1]*delta_t;
        accel[0] = gyro.getWorldLinearAccelX();
        accel[1] = gyro.getWorldLinearAccelY();
    }

    public double[] GetPredictedCoordinates(double dt){
       dx =  vel[0]*dt + (accel[0]*Math.pow(dt,2));
       
    }
    @Override
    public void inputUpdate(Input source){

    }

    @Override
    public void selfTest(){

    }
    @Override
    public String getName() {
        return "Position Tracking";
    }
}
