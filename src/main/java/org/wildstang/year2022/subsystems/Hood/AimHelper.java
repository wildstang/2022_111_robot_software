package org.wildstang.year2022.subsystems.Hood.AimHelper; 
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

import LimeConsts;

public class AimHelper{
    
    private NetworkTable LimeTable;
    private NetworkTableEntry ty; //y position
    private NetworkTableEntry tx; //x position
    
    public double x;
    public double y;

    private double TargetDistance;
    private double Angle;


    public void init(){
        x,y,Angle,TargetDistance = 0;


        LimeTable  = NetworkTableInstance.getDefault().getTable("limelight-stang");

        ty = LimeTable.getEntry("ty");
        tx = LimeTable.getEntry("tx");
    }
    public void calcTargetCoords(){
        x = tx.getDouble(0);
        y = ty.getDouble(0);
    }
    public double getVertAngleFromCamera(){
        calcTargetCoords();
        Angle = ((y/LimeConsts.CAMERA_VERT_DIVISIONS)+1)*0.5*LimeConsts.CAMERA_VEIW_ANGLE;
    }
    public double getDistance(){
        getVertAngleFromCamera();
        //h = lsin(0), d = lcos(0)
        // l = h/sin(0) = d/cos(0)
        // d = cos(0)*h/sin(0) = h/tan(0)
        TargetDistance = LimeConsts.TARGET_HEIGHT/Math.tan(Math.PI*Angle/180);

    }

}