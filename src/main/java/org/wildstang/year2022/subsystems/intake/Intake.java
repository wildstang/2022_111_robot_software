package org.wildstang.year2022.subsystems.intake;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.hardware.roborio.inputs.WsJoystickAxis;
import org.wildstang.hardware.roborio.outputs.WsPhoenix;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;

public class Intake {
    
    private WsSparkMax intakeWheel;
    WsJoystickAxis js;

    @Override
    public void init() {
        intakeWheel = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.INTAKE);
        js = (WsJoystickAxis) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_JOYSTICK_Y);
    }

    @Override
    public void update() {
        intakeWheel.Value(js.getValue());
    }

    @Override
    public void inputUpdate(Input source) {

    }

    @Override
    public void selfTest() {

    }

    @Override
    public void resetState() {

    }

    @Override
    public String getName() {
        return "Sample";
    }
}
