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
import org.wildstang.hardware.roborio.inputs.WsJoystickAxis;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.I2C;

import org.wildstang.year2022.subsystems.swerve.SwerveDrive;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import org.wildstang.year2022.subsystems.Hood.LimeConsts;

import java.util.Arrays;
import org.wildstang.year2022.subsystems.Hood.AimHelper;;


//Goals bind controls to the asigned buttons. refrence aim helper 

public class HoodManager{
    //get inputs
    WsJoystickAxis HoodMovement;
    WsAnalogInput AutoAim;

    //presets
    WsDigitalInput Preset1;
    WsDigitalInput Preset2;
    WsDigitalInput Preset3;
    WsDigitalInput Preset4;

    //get outputs
    WsPhoenix HoodMotor;


    //set inputs
    
    //@Overide
    public void init() {
        //analog
        HoodMovement = (WsJoystickAxis) Core.getInputManager().getInput(WSInputs.MANIPULATOR_LEFT_JOYSTICK_Y);
        AutoAim = (WsAnalogInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_LEFT_TRIGGER);

        //digital
        Preset1 = (WsDigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_DPAD_UP);
        Preset2 = (WsDigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_DPAD_LEFT);
        Preset3 = (WsDigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_DPAD_RIGHT);
        Preset4 = (WsDigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_DPAD_DOWN);

        //motors
        HoodMotor = (WsPhoenix) Core.getOutputManager().getOutput(WSOutputs.HOOD_MOTOR); //no real motor yet replace wen added
    }

    // it was annoying me on overides
    //@Overide
    public void update() {
        //move hood with left joysick 
        //input range -1 to 1 
        //anything below like -0.75 is gonna be moving it down
        //anything above 0.75 is moving up
        
        int HoodMoveSpeed = 10; //positive is up

        if (HoodMovement.getValue() >0.75) {
            HoodMotor.setValue(HoodMoveSpeed);
        } else if (HoodMovement.getValue() <-0.75){
            HoodMotor.setValue(-HoodMoveSpeed);
        }

        if (AutoAim.getValue() >0.75){
            //intregrate jonahs stuff
        }
    }

}
