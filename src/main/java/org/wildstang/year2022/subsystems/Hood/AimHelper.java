package org.wildstang.year2022.subsystems.Hood; 
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

import org.wildstang.year2022.subsystems.Hood.LimeConsts;

import java.util.Arrays;

public class AimHelper implements Subsystem{
    
    private NetworkTable LimeTable;
    private NetworkTableEntry ty; //y angle
    private NetworkTableEntry tx; //x angle
    private NetworkTableEntry tv;
    
    public double LimeLight_Analog_X;
    public double LimeLight_Analog_Y;

    public boolean TargetInView;

    private double TargetDistance;
    private double Angle;

    private LimeConsts LC;


    public void init(){ //initialize. Call before use.
        LC = new LimeConsts();
        LimeLight_Analog_X = 0;  //x and y angular offsets from limelight. Only updated when calcTargetCoords is called.
        LimeLight_Analog_Y = 0;
        TargetInView = false; //is the target in view? only updated when calcTargetCoords is called.
        TargetDistance = 0; //distance to target in feet. Only updated when calcTargetCoords is called.


        LimeTable  = NetworkTableInstance.getDefault().getTable("limelight-stang");

        calcTargetCoords();
    }
    public void calcTargetCoords(){ //update target coords. 
        ty = LimeTable.getEntry("ty");
        tx = LimeTable.getEntry("tx");
        tv = LimeTable.getEntry("tv");

        if(tv.getDouble(0) == 1){
            LimeLight_Analog_X = tx.getDouble(0);
            LimeLight_Analog_Y = ty.getDouble(0);
            TargetInView = true;
        }
        else{
            LimeLight_Analog_X = 0; //no target case
            LimeLight_Analog_Y = 0;
            TargetInView = false;
        }
    }

    public void update(){
        calcTargetCoords();
    }

    public void selfTest(){

    }

    public void inputUpdate(Input theinput){
        
    }

    public void resetState(){
        LimeTable  = NetworkTableInstance.getDefault().getTable("limelight-stang");
        calcTargetCoords();
    }

    public String getName(){
        return "AimHelper";
    }   

    private void getDistance(){ //update target dist. for internal use. Distance is in feet.
        calcTargetCoords();
        //h = lsin(0), d = lcos(0)
        // l = h/sin(0) = d/cos(0)
        // d = cos(0)*h/sin(0) = h/tan(0)
        TargetDistance = LC.TARGET_HEIGHT/Math.tan(LimeLight_Analog_Y+(Math.PI*LC.CAMERA_ANGLE_OFFSET/180));
        
    }

    public double getAngle(){ //get hood angle for autoaim
        getDistance();
        return ApproximateAngle(TargetDistance);
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