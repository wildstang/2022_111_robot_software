package org.wildstang.hardware.roborio.inputs;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * Reads an analog input.
 */
public class WsAnalogInput extends org.wildstang.framework.io.inputs.AnalogInput {

    AnalogInput input;

    /**
     * Construct the analog input.
     * @param p_name Descriptive name for the input.
     * @param channel Hardware channel used for input.
     */
    public WsAnalogInput(String p_name, int channel) {
        super(p_name);

        input = new AnalogInput(channel);
    }

    /**
     * Reads the value from the analog input.
     * @return Raw value from input.
     */
    @Override
    public double readRawValue() {
        return input.getAverageVoltage();
    }
}
