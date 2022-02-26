package org.wildstang.year2022.subsystems.LimeLight; 
// ton of imports
import org.wildstang.framework.subsystems.Subsystem;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.kauailabs.navx.frc.AHRS;

import org.wildstang.framework.core.Core;
import com.revrobotics.CANSparkMax;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.logger.Log;
import org.wildstang.framework.pid.PIDConstants;
import org.wildstang.framework.subsystems.drive.Path;
import org.wildstang.framework.subsystems.drive.PathFollowingDrive;
import org.wildstang.framework.subsystems.drive.TankPath;
import org.wildstang.hardware.roborio.inputs.WsAnalogInput;
import org.wildstang.hardware.roborio.inputs.WsDigitalInput;
import org.wildstang.hardware.roborio.inputs.WsJoystickButton;
import org.wildstang.hardware.roborio.outputs.WsPhoenix;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.I2C;

import org.wildstang.year2022.subsystems.swerve.SwerveDrive;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import org.wildstang.year2022.subsystems.LimeLight.LimeConsts;

import java.util.Arrays;

import org.wildstang.year2022.robot.WSSubsystems;

public class AimHelper implements Subsystem{
    
    private NetworkTable LimeTable;
    private NetworkTableEntry ty; //y angle
    private NetworkTableEntry tx; //x angle
    private NetworkTableEntry tv;
    
    public double x;
    public double y;

    public boolean TargetInView;

    private double TargetDistance;
    private double Angle;

    private LimeConsts LC;

    public double AngleError; // an error value for the x. NOT the displacement, but will indicate direction the robot should turn and something like a magnitude.

    private double RobotAngle;
    private double RobotSpeed;

    private SwerveDrive swerve;
    public AimHelper(){ //initialize. Call before use.
        LC = new LimeConsts();

        LimeTable  = NetworkTableInstance.getDefault().getTable("limelight-stang");

        ty = LimeTable.getEntry("ty");
        tx = LimeTable.getEntry("tx");
        tv = LimeTable.getEntry("tv");
        resetState();
    }
    @Override
    public void init(){ // copy/paste of constructor 
        LC = new LimeConsts();

        LimeTable  = NetworkTableInstance.getDefault().getTable("limelight-stang");

        ty = LimeTable.getEntry("ty");
        tx = LimeTable.getEntry("tx");
        tv = LimeTable.getEntry("tv");
        resetState();
        swerve = WSSubsystems("Swerve Drive",SwerveDrive);
    }

    @Override
    public void update(){
        calcTargetCoords(); //get values from the limelight
    }

    @Override
    public void inputUpdate(Input source) {
        // not sure if this should do anything

    }

    @Override
    public void selfTest(){
        
    }
    @Override
    public void resetState() {
        x = 0;  //x and y angular offsets from limelight. Only updated when calcTargetCoords is called.
        y = 0;
        TargetInView = false; //is the target in view? only updated when calcTargetCoords is called.
        TargetDistance = 0; //distance to target in feet. Only updated when calcTargetCoords is called.
        AngleError = 0;
        RobotAngle = 0;
        RobotSpeed = 0;
    }
    @Override
    public String getName() {
        return "LimeLight";
    }
    public void calcTargetCoords(){ //update target coords. 
        if(tv.getDouble(0) == 1){
            x = tx.getDouble(0);
            y = ty.getDouble(0);
            TargetInView = true;
        }
        else{
            x = 0; //no target case
            y = 0;
            TargetInView = false;
        }
        RobotSpeed = Math.sqrt(Math.pow(swerve.xSpeed,2)+Math.pow(swerve.ySpeed,2));
        RobotAngle = Math.tan(swerve.ySpeed/swerve.xSpeed)-swerve.gyroValue;

    }

    private void getDistance(){ //update target dist. for internal use. Distance is in feet.
        calcTargetCoords();
        //h = lsin(0), d = lcos(0)
        // l = h/sin(0) = d/cos(0)
        // d = cos(0)*h/sin(0) = h/tan(0)
        TargetDistance = LC.TARGET_HEIGHT/Math.tan(y+(Math.PI*LC.CAMERA_ANGLE_OFFSET/180));
        
    }

    public double getAngle(){ //get hood angle for autoaim
        getDistance();
        if(RobotSpeed < LC.DEADBAND){
            return ApproximateAngle(TargetDistance);
        }
        else{
            double EffectiveDist = TargetDistance*Math.sin(Math.abs(RobotAngle)-Math.abs(x))/Math.sin(Math.PI/Math.abs(RobotAngle));

            double Tang = EffectiveDist*Math.sin(Math.abs(RobotAngle)-Math.abs(x))/Math.sin(Math.abs(x));
            double aproxang = ApproximateAngle(EffectiveDist);
            double vertvel = LC.BALL_VELOCITY*Math.sin(aproxang);
            double Tdist = (vertvel+Math.sqrt(Math.pow(vertvel,2)-(LC.TARGET_HEIGHT*LC.GRAVITY/2)))/LC.GRAVITY;

            AngleError = 1-(Tdist/Tang); //this is a really bad meusure of error. Only will work ok for small errors.
            return aproxang;
        }
        
    }
    public double ApproximateAngle(double dist){ //linear interlopation
        double[] dists = LC.Dists;
        int max = dists.length-1;
        int min = 0;
        int c = 0;
        boolean done = false;
        boolean exact = false;
        while(done == false){
            if(max-min <= 1){
                done = true;
                c = max;
            }
            else{
                c = (int) ((max-min)/2)+min;
                
                if(dists[c]>dist){
                    max = c;
                }
                else if(dists[c] < dist){
                    min = c;
                }
                else{
                    exact = true;
                    done = true;
                }
                
            }

            
        }
        double out = 0;
        // now c is index of nearest value (rounded up)
        if(exact){
            out = LC.Angles[c];
        }
        else{
            if(c-1 >= min){
                double interval = dists[c]-dists[c-1];
                double range = LC.Angles[c]-LC.Angles[c-1];
                out = (((dist-dists[c-1])/interval)*range)+dists[c-1];
            }
            else{ //outside of domain
                out = LC.Angles[c];
            }


        }
        return out;
    
    }

    

}