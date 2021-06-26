package org.wildstang.sample.robot;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.hardware.roborio.outputs.WsPhoenix;

public class SampleSubsystem implements Subsystem {

    WsPhoenix motor;

    @Override
    public void init() {
        motor = (WsPhoenix) Core.getOutputManager().getOutput(WSOutputs.TEST_MOTOR.getName());
    }

    @Override
    public void update() {
        motor.setValue(0.5);
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