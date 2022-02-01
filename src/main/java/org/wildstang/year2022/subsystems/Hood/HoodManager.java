package org.wildstang.year2022.subsystems.Hood; 
import org.wildstang.framework.core.Core;
import org.wildstang.framework.subsystems.SubsystemManager;
import org.wildstang.hardware.roborio.inputs.WsAnalogInput;
import org.wildstang.hardware.roborio.inputs.WsDigitalInput;
import org.wildstang.hardware.roborio.outputs.WsPhoenix;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;
import org.wildstang.hardware.roborio.inputs.WsJoystickAxis;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.framework.io.inputs.Input;
import java.util.Arrays;

import com.google.common.util.concurrent.Service.State;

//Goals bind controls to the asigned buttons. refrence aim helper 

public class HoodManager implements Subsystem{
    //get inputs
    private WsJoystickAxis HoodMovement;
    private WsAnalogInput AutoAim;
    private LimeConsts LC;
    private AimHelper Aim;
    //presets
    private WsDigitalInput Preset1;
    private WsDigitalInput Preset2;
    private WsDigitalInput Preset3;
    private WsDigitalInput Preset4;
    
    //get outputs
    private WsSparkMax HoodMotor;

    //vars
    private int HoodMoveSpeed;
    private int[] PresetIndex = {0,0,0,0};
    private double encoderToAngle;
    public String State;
    
    //set inputs

    @Override
    public void init() {
        //analog
        HoodMovement = (WsJoystickAxis) Core.getInputManager().getInput(WSInputs.MANIPULATOR_LEFT_JOYSTICK_Y);
        AutoAim = (WsAnalogInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_LEFT_TRIGGER);

        //digital
        Preset1 = (WsDigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_DPAD_UP);
        Preset2 = (WsDigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_DPAD_LEFT);
        Preset3 = (WsDigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_DPAD_RIGHT);
        Preset4 = (WsDigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_DPAD_DOWN);

        //add the listners
        Preset1.addInputListener(this);
        Preset2.addInputListener(this);
        Preset3.addInputListener(this);
        Preset4.addInputListener(this);

        AutoAim.addInputListener(this);
        HoodMovement.addInputListener(this);

        //motors also an encoder
        HoodMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.HOOD_MOTOR);
        
        HoodMoveSpeed = 10; //positive is up
        encoderToAngle = 1; //number of encoder angle values per degrees
    }

    
    @Override
    public void update() {
        if (State == "Manual"){ //generaly defult
            if (HoodMovement.getValue() >0.75) {
                HoodMotor.setSpeed(HoodMoveSpeed);
            }
            if (HoodMovement.getValue() < -0.75){
                HoodMotor.setSpeed(-HoodMoveSpeed);
            }
        }
        if (State == "Auto"){
            //intregrate jonahs stuff
            Double HoodAngle = (Double) Aim.getAngle();
            if (HoodAngle > (Double) (HoodMotor.getValue() / encoderToAngle)){
                HoodMotor.setSpeed(HoodMoveSpeed);
            }else if (HoodAngle < (Double) (HoodMotor.getValue() / encoderToAngle)){
                HoodMotor.setSpeed(-HoodMoveSpeed);
            }
        }
        if (State == "P1"){
            if (HoodMotor.getValue() > LC.Dists[PresetIndex[0]]){//hope that the presets are in encoder values
                HoodMotor.setSpeed(HoodMoveSpeed);
                
            }else if (HoodMotor.getValue() < LC.Dists[PresetIndex[0]]){
                HoodMotor.setSpeed(-HoodMoveSpeed);
            }
        }
        if (State == "P2"){
            if (HoodMotor.getValue() > LC.Dists[PresetIndex[1]]){
                HoodMotor.setSpeed(HoodMoveSpeed);
                
            }else if (HoodMotor.getValue() < LC.Dists[PresetIndex[1]]){
                HoodMotor.setSpeed(-HoodMoveSpeed);
            }
        }
        if (State == "P3"){
            if (HoodMotor.getValue() > LC.Dists[PresetIndex[2]]){
                HoodMotor.setSpeed(HoodMoveSpeed);
            }else if (HoodMotor.getValue() < LC.Dists[PresetIndex[2]]){
                HoodMotor.setSpeed(-HoodMoveSpeed);
            }
        }
        if (State == "P4"){
            if (HoodMotor.getValue() > LC.Dists[PresetIndex[3]]){
                HoodMotor.setSpeed(HoodMoveSpeed);
                
            }else if (HoodMotor.getValue() < LC.Dists[PresetIndex[3]]){
                HoodMotor.setSpeed(-HoodMoveSpeed);
            }
        }
    }
    
    @Override
    public void inputUpdate(Input source) {
        if(source == AutoAim){
            State = "Auto";
        }
        //preset stuff
        if (source == Preset1){
            State = "P1";
        }
        if (source == Preset2){
            State = "P2";
        }
        if (source == Preset3){
            State = "P3";
        }
        if (source == Preset4){
            State = "P4";
        } 
        //manual controls
        if(source == HoodMovement) {
            State = "Manual";
        }
    }

    @Override
    public void selfTest() {

    }

    @Override
    public void resetState() {
        HoodMotor.setSpeed(0.0);
        HoodMotor.setValue(0.0);//encoder to neo encoder calc (encoder - (Ea/MaxGa)*range)
    }

    @Override
    public String getName() {
        return "HoodManager";
    }
}
