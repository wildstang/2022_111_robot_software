package org.wildstang.hardware.roborio.outputs;

import org.wildstang.framework.io.outputs.DigitalOutput;

/**
 * Controls a generic digital output.
 */
public class WsDigitalOutput extends DigitalOutput {

    edu.wpi.first.wpilibj.DigitalOutput output;

    /**
     * Constructs the output from config.
     * @param name Descriptive name of the output.
     * @param channel Hardware port number the output is connected to.
     * @param p_default Default state.
     */
    public WsDigitalOutput(String name, int channel, boolean p_default) {
        super(name, p_default);
        output = new edu.wpi.first.wpilibj.DigitalOutput(channel);
    }

    /**
     * Sets output state to current value.
     */
    @Override
    protected void sendDataToOutput() {
        this.output.set(getValue());
    }

    /**
     * Does nothing, config values only affects start state.
     */
    public void notifyConfigChange() { }

}
