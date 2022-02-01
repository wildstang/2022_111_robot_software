package org.wildstang.year2022.subsystems;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.hardware.roborio.inputs.WsJoystickAxis;
import org.wildstang.hardware.roborio.outputs.WsPhoenix;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;

/**
 * Sample Subsystem that controls a motor with a joystick.
 * @author Liam
 */
public class manipulatorSubsystem implements Subsystem {

    WsSparkMax motor;
    
    WsJoystickButton forwardTrigger;
    WsJoystickButton backwardTrigger;
    
    WsJoystickButton toggleTrigger;

    WsSolenoid solenoid;

    @Override
    public void init() {
        motor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.INTAKE_MOTOR);
        
        forwardTrigger = (WsJoystickButton) Core.getInputManager().getInput(WSInputs.DRIVER_FACE_DOWN);
        forewardTrigger.addInputListener(this);
        backwardTrigger = (WsJoystickButton) Core.getInputManager().getInput(WSInputs.DRIVER_FACE_UP);
        backwardTrigger.addInputListener(this);

        solenoid = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.PISTON_SOLENOID);

        toggleTrigger = (WsJoystickButton) Core.getInputManager().getInput(WSInputs.DRIVER_FACE_RIGHT);
        toggleTrigger.addInputListener(this);

    }

    @Override
    public void update() {
        
    }

    @Override
    public void inputUpdate(Input source) {
        if (source == forwardTrigger || source == backwardTrigger) {

            if (forwardTrigger.getValue()) {
                motor.setValue(1);

            } else if (backwardTrigger.getValue()) {
                motor.setValue(-1);

            }
        } else { 
            motor.setValue(0);
        }
        if (source == toggleTrigger) {
            solenoid.setValue(!solenoid.getValue());
        }
    }

    @Override
    public void selfTest() {

    }

    @Override
    public void resetState() {
        motor.setValue(0);
    }

    @Override
    public String getName() {
        return "manipulatorSubsystem";
    }
}