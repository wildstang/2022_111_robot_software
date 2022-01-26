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

public class AimHelper{
    
    private NetworkTable LimeTable;
    private NetworkTableEntry ty; //y position
    private NetworkTableEntry tx; //x position
    
    public double x;
    public double y;

    private double TargetDistance;
    private double Angle;

    private LimeConsts LC;

    public void init(){ //initialize. Call before use.
        LC = new LimeConsts();
        x = 0;
        y = 0;
        Angle = 0;
        TargetDistance = 0;


        LimeTable  = NetworkTableInstance.getDefault().getTable("limelight-stang");

        ty = LimeTable.getEntry("ty");
        tx = LimeTable.getEntry("tx");
    }
    public void calcTargetCoords(){ //update target coords. For internal use
        x = tx.getDouble(0);
        y = ty.getDouble(0);
    }
    public double getVertAngleFromCamera(){ //Get target angle from camera. For internal use.
        calcTargetCoords();
        Angle = ((y/LC.CAMERA_VERTICAL_DIVISIONS)+1)*0.5*LC.CAMERA_VEIW_ANGLE;
        return Angle;
    }
    public double getHorzAngle(){ //Get target horizontal angle from camera.
        calcTargetCoords();
        double out = ((x/LC.CAMERA_HORIZONTAL_DIVISIONS)+1)*0.5*LC.CAMERA_VEIW_ANGLE;
        return out;
    }
    public double getDistance(){ //Get target dist. for internal use.
        double nonevar = getVertAngleFromCamera();
        //h = lsin(0), d = lcos(0)
        // l = h/sin(0) = d/cos(0)
        // d = cos(0)*h/sin(0) = h/tan(0)
        TargetDistance = LC.TARGET_HEIGHT/Math.tan(Math.PI*Angle/180);
        return TargetDistance;
    }

    public double getAngle(){ //get hood angle for autoaim
        double nonevar = getDistance();
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