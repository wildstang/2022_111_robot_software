package org.wildstang.year2022.subsystems.Hood; 
import org.wildstang.framework.core.Core;
import org.wildstang.framework.subsystems.SubsystemManager;
import org.wildstang.hardware.roborio.inputs.WsAnalogInput;
import org.wildstang.hardware.roborio.inputs.WsDigitalInput;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;
import org.wildstang.hardware.roborio.inputs.WsJoystickAxis;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.framework.io.inputs.Input;
import java.util.Arrays;
import com.revrobotics.SparkMaxAnalogSensor.Mode;


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
    private double angleToEncoder;
    private double AbsoluteToMotor;
    private String State;
    private double upperBound;
    private double lowerbound;
    
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

        //vars
        HoodMoveSpeed = 10; //positive is up
        angleToEncoder = 1; //number of encoder angle values per degrees
        AbsoluteToMotor = 10; // (Ea/MaxGa)
        upperBound = 100;
        lowerbound = 0;

        //motors also an encoder
        
        HoodMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.HOOD_MOTOR);
        HoodMotor.initClosedLoop(1.0,0.0,0.0,0.0);
       
    }

    
    @Override
    public void update() {
        if (State == "Manual"){ 
            if (HoodMovement.getValue() >0.75 && HoodMotor.getValue() <= upperBound) {
                HoodMotor.setSpeed(HoodMoveSpeed);
            }
            else if (HoodMovement.getValue() < -0.75 && HoodMotor.getValue() >= lowerbound){
                HoodMotor.setSpeed(-HoodMoveSpeed);
            }else {
                HoodMotor.stop();
            }
        }
        if (State == "Auto"){
            //intregrate jonahs stuff
            Double HoodAngle = (Double) Aim.getAngle();
            HoodMotor.setPosition(HoodAngle*angleToEncoder);
        }
        if (State == "P1"){
            HoodMotor.setPosition(PresetIndex[0]);
        }
        if (State == "P2"){
            HoodMotor.setPosition(PresetIndex[1]);
        }
        if (State == "P3"){
            HoodMotor.setPosition(PresetIndex[2]);
        }
        if (State == "P4"){
            HoodMotor.setPosition(PresetIndex[3]);
        }
        if (State == "Nutural"){ 
            HoodMotor.stop();
        }
    }
    
    @Override
    public void inputUpdate(Input source) {
        if(source == AutoAim && AutoAim.getValue() > 0.75){
            State = "Auto";
        }
        //preset stuff
        else if (source == Preset1 && Preset1.getValue()){ //digital inputs come in true or false only
            State = "P1";
        }
        else if (source == Preset2 && Preset2.getValue()){
            State = "P2";
        }
        else if (source == Preset3 && Preset3.getValue()){
            State = "P3";
        }
        else if (source == Preset4 && Preset4.getValue()){
            State = "P4";
        } 
        //manual controls
        else if(source == HoodMovement && Math.abs(HoodMovement.getValue()) > 0.75){
            State = "Manual"; 
        } 
        else{
            State = "Nutural";
        }
    }

    @Override
    public void selfTest() {

    }

    @Override
    public void resetState() {
        HoodMotor.setSpeed(0.0);
        HoodMotor.resetEncoder();
        HoodMotor.setValue(HoodMotor.getController().getAnalog(Mode.kAbsolute).getVoltage()*AbsoluteToMotor);// a number from 0.0 - 3.3 to angle 0-maxdeg
    }

    @Override
    public String getName() {
        return "HoodManager";
    }
}
