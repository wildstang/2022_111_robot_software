package org.wildstang.year2022.subsystems;

import com.revrobotics.AnalogInput;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.hardware.roborio.inputs.WsJoystickAxis;
import org.wildstang.hardware.roborio.inputs.WsJoystickButton;
import org.wildstang.hardware.roborio.outputs.WsPhoenix;
import org.wildstang.hardware.roborio.outputs.WsSolenoid;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;

/**
 * Sample Subsystem that controls a motor with a joystick.
 * @author Ethan Jensen
 */
public class shooter implements Subsystem {

    
    WsSparkMax wheelMotor1;
    WsSparkMax wheelMotor2;
    WsSparkMax shooterFlywheel;
    WsSolenoid feedSolenoid;
    WsJoystickAxis shooterTrigger;


    double triggerValue = shooterTrigger.getValue();
    double motorSpeed = 0;
    boolean solenoidVal = false; 


    @Override
    public void init() {
        
        wheelMotor1 = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.SHOOTER_WHEEL1);
        wheelMotor2 = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.SHOOTER_WHEEL2);
        wheelMotor1 = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.SHOOTER_FLYWHEEL);
        feedSolenoid = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.FEED_SOLENOID);
        shooterTrigger = (WsJoystickAxis) Core.getInputManager().getInput(WSInputs.MANIPULATOR_RIGHT_TRIGGER);
        shooterTrigger.addInputListener(this);

        resetState();
    }

    @Override
    public void update() {
        
        wheelMotor1.setValue(motorSpeed);
        wheelMotor2.setValue(motorSpeed);
        feedSolenoid.setValue(solenoidVal);
        shooterFlywheel.setValue(motorSpeed);
    }

    @Override
    public void inputUpdate(Input source) {
        if(shooterTrigger.getValue() > 0){
            motorSpeed = 1;
            solenoidVal = true;

        }
    }

    @Override
    public void selfTest() {

    }

    @Override
    public void resetState() {
        motorSpeed = 0;
        solenoidVal = false;
    }

    @Override
    public String getName() {
        return "shooter";
    }
}