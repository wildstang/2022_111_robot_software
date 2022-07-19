package org.wildstang.year2022.subsystems.Hood;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.year2022.subsystems.Hood.AimHelper;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Timer;
import org.wildstang.year2022.subsystems.Hood.LimeConsts;

import org.wildstang.year2022.subsystems.swerve.SwerveDrive;

public class PositionTracking implements Subsystem{
    /* For advanced aim while moving- implementation of Calvin's algorithim to account for firing delay and also estimating target position when target is out of veiw 
    
    ONLY FOR FEILD-CENTRIC DRIVE MODE.

     only uses 2nd order taylor seires, so just acceleration and velocity. I doubt more will be needed for good performance */


    private double x; //target-centric tangential coord
    private double y;  //target-centric centrifugal coord
    private double rot; //Rotation between target and robot
    private double[] accel; //feild-centric ac. vector
    private double[] vel; //feild-centric vel. vector
    private boolean IsAiming;

    private double lastAngle;
    AHRS gyro;
    AimHelper aim;
    SwerveDrive swerve;
    private Timer timer;
    private LimeConsts LC;

    private double flightTime;
    private double vLaunchVel; //vertical launch velocity squared

    public double launchVel; //target launch velocity
    public double targetOffsetGoal; //goal for target offset 
    public double launchAngle; //vertical angle 

    @Override
    public void init() {
        swerve = (SwerveDrive) Core.getSubsystemManager().getSubsystem(WSSubsystems.SWERVE_DRIVE);
        aim = (AimHelper) Core.getSubsystemManager().getSubsystem(WSSubsystems.LIMELIGHT);
        gyro = swerve.getGyro();
        resetState();
        timer = new Timer();
        timer.reset();
    }

    @Override
    public void resetState(){
        flightTime = Math.sqrt(2*LC.SHOT_HEIGHT/LC.GRAVITY)+Math.sqrt(2*(LC.SHOT_HEIGHT-LC.TARGET_HEIGHT)/LC.GRAVITY);
        vLaunchVel = Math.pow(LC.SHOT_HEIGHT/Math.sqrt(2*LC.SHOT_HEIGHT/LC.GRAVITY),2);
        x = 0;
        y = 0;
        rot = 0;
        lastAngle = 0;
        launchVel = 0;
        targetOffsetGoal = 0;
        launchAngle = 0;
        IsAiming = false;
    }

    @Override
    public void update(){
        if(AimHelper.TargetInView()){ //if LL can see target, fix coordinates
            y = AimHelper.getDistance();
            x = 0;
            rot = Math.toRadians(AimHelper.x);
        }

        //get raw velocity and acceleration
        vel = swerve.getVelocity();
        accel[0] = gyro.getWorldLinearAccelX();
        accel[1] = gyro.getWorldLinearAccelY(); 

        double angle = Math.toRadians(swerve.getGyroAngle());  //change accel and vel to correct coordinate system
        accel[0] = (accel[0]*Math.cos(rot+angle))+(accel[1]*Math.sin(rot+angle)); //if this isn't working try flipping the expressions, idk wether gyro 0 is along x or y
        accel[1] = (accel[1]*Math.cos(rot+angle))+(accel[0]*Math.sin(rot+angle));
        vel[0] = (vel[0]*Math.cos(rot+angle))+(vel[1]*Math.sin(rot+angle));
        vel[1] = (vel[1]*Math.cos(rot+angle))+(vel[0]*Math.sin(rot+angle));

        double delta_t = timer.get();
        timer.reset();
        timer.start();
        x += vel[0]*delta_t +(accel[0]*Math.pow(delta_t,2)); // update coordinates 
        y += vel[1]*delta_t +(accel[1]*Math.pow(delta_t,2)); //if this is not working well enough estimate velocity at half-intervals by keeping last recorded velocity 
        rot -= (angle-lastAngle);
        lastAngle = angle;

        if (!(y==0)){ //update coordinate system. idk how y would = 0, but just in case.
        rot += Math.tan(x/y) 
        }
        y = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        x = 0;

        SmartDashboard.putNumber("TangentialSpeed", vel[0]); //smartdashboard
        SmartDashboard.putNumber("CentriSpeed",vel[1]);
        SmartDashboard.putNumber("dt",delta_t);
        SmartDashboard.putNumber("AngleToTarget",Math.toDegrees(rot));

        if(IsAiming){ //only update launch vars when necessary
            UpdateLaunchVariables();
        }
    }

    public double[] GetPredictedCoordinates(double dt){
       dx =  vel[0]*dt + (accel[0]*Math.pow(dt,2));
       dy = vel[1]*dt+(accel[1]*Math.pow(dt,2));
       return new double[] {dx,dy};
    }

    public void UpdateLaunchVariables(){ //stuff that doesn't always need to be updated. Launch angles and velocity.
        double[] delta = GetPredictedCoordinates(LC.FIRE_TIME); //predicted change in x and y
        double[] predVelocity = {vel[0]+(accel[0]*Math.pow(LC.FIRE_TIME,2)/2),vel[1]+(accel[1]*Math.pow(LC.FIRE_TIME,2)/2)}; 
        targetOffsetGoal = Math.atan((delta[0]-(predVelocity[0]*flightTime))/(y+delta[1]-(predVelocity[1]*flightTime))); 
        double EffectiveDist = Math.sqrt(Math.pow(delta[0]-(predVelocity[0]*flightTime,2))+Math.pow(y+delta[1]-(predVelocity[0]*flightTime,2),2));
        double hLaunchVel = EffectiveDist/flightTime;
        launchVel = Math.sqrt(Math.pow(hLaunchVel,2)+Math.pow(vLaunchVel,2));
        launchAngle = Math.atan(vLaunchVel/hLaunchVel);

        targetOffsetGoal = Math.toDegrees(targetOffsetGoal); //switch to degrees
        launchAngle = Math.toDegrees(targetOffsetGoal);
    }

    public void setAiming(boolean aim){
        IsAiming = aim;
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
