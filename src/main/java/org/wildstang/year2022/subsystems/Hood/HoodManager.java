package org.wildstang.year2022.subsystems.Hood; 
import org.wildstang.framework.core.Core;
import org.wildstang.hardware.roborio.inputs.WsAnalogInput;
import org.wildstang.hardware.roborio.inputs.WsDigitalInput;
import org.wildstang.hardware.roborio.outputs.WsPhoenix;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;
import org.wildstang.hardware.roborio.inputs.WsJoystickAxis;


//Goals bind controls to the asigned buttons. refrence aim helper 

public class HoodManager{
    //get inputs
    private WsJoystickAxis HoodMovement;
    private WsAnalogInput AutoAim;
    private WsSparkMax HoodEncoder;
    private LimeConsts LC;
    private AimHelper Aim;
    //presets
    private WsDigitalInput Preset1;
    private WsDigitalInput Preset2;
    private WsDigitalInput Preset3;
    private WsDigitalInput Preset4;

    //get outputs
    private WsPhoenix HoodMotor;


    //set inputs

    //@Override
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
    //@Override
    public void update() {
        //move hood with left joysick 
        //input range -1 to 1 
        //anything below like -0.75 is gonna be moving it down
        //anything above 0.75 is moving up
        
        int HoodMoveSpeed = 10; //positive is up
        int[] PresetIndex = {0,0,0,0};
        int encoderToAngle = 1; //number of encoder angle values per degrees


        


        //threads to do multiple things at once
        //i hope these dont just multiply every frame
        Thread moveHoodUp = new Thread(() -> {
            do {
                HoodMotor.setValue(HoodMoveSpeed); //positive is up (goal)
            } while (true);});
        
        Thread moveHoodDown = new Thread(() -> {
            do {
                HoodMotor.setValue(-HoodMoveSpeed); //negitive is down (goal)
            } while (true);});

        
        //rn you haveto hold the button for anything to happen this could be a toggle but thats for later
        if (AutoAim.getValue() >0.75){
            //intregrate jonahs stuff
            Double HoodAngle = (Double) Aim.getAngle();
            if (HoodAngle > (Double) (HoodEncoder.getValue() / encoderToAngle)){
                moveHoodUp.start();
                moveHoodDown.interrupt();
            }else if (HoodAngle < (Double) (HoodEncoder.getValue() / encoderToAngle)){
                moveHoodDown.start();
                moveHoodUp.interrupt();
            }
        }

        //preset stuff
        else if (Preset1.getValue()){
            if (HoodEncoder.getValue() > LC.Dists[PresetIndex[0]]){
                moveHoodUp.start();
                moveHoodDown.interrupt();
            }else if (HoodEncoder.getValue() < LC.Dists[PresetIndex[0]]){
                moveHoodDown.start();
                moveHoodUp.interrupt();
            }
        }
        else if (Preset2.getValue()){
            if (HoodEncoder.getValue() > LC.Dists[PresetIndex[1]]){
                moveHoodUp.start();
                moveHoodDown.interrupt();
            }else if (HoodEncoder.getValue() < LC.Dists[PresetIndex[1]]){
                moveHoodDown.start();
                moveHoodUp.interrupt();
            }
        }
        else if (Preset3.getValue()){
            if (HoodEncoder.getValue() > LC.Dists[PresetIndex[2]]){
                moveHoodUp.start();
                moveHoodDown.interrupt();
            }else if (HoodEncoder.getValue() < LC.Dists[PresetIndex[2]]){
                moveHoodDown.start();
                moveHoodUp.interrupt();
            }
        }
        else if (Preset4.getValue()){
            if (HoodEncoder.getValue() > LC.Dists[PresetIndex[3]]){
                moveHoodUp.start();
                moveHoodDown.interrupt();
            }else if (HoodEncoder.getValue() < LC.Dists[PresetIndex[3]]){
                moveHoodDown.start();
                moveHoodUp.interrupt();
            }
        } 
        //manual controls
        else if (HoodMovement.getValue() >0.75) {
            HoodMotor.setValue(HoodMoveSpeed);
        } else if (HoodMovement.getValue() <-0.75){
            HoodMotor.setValue(-HoodMoveSpeed);
        }else{//if there is no reason for the motor to move stop it
            moveHoodUp.interrupt();
            moveHoodDown.interrupt();
            HoodMotor.stop();
        }
    }
}
