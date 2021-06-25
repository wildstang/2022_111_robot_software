package org.wildstang.hardware.roborio.inputs;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Reads a digital input.
 */
public class WsDigitalInput extends org.wildstang.framework.io.inputs.DigitalInput {

    DigitalInput input;
    private boolean m_pullup = false;

    /**
     * Construct the digital input.
     * @param p_name Descriptive name for the input.
     * @param channel Hardware channel used for input.
     * @param p_pullup Whether pull up more should be used.
     */
    public WsDigitalInput(String p_name, int channel, boolean p_pullup) {
        super(p_name);
        m_pullup = p_pullup;

        this.input = new DigitalInput(channel);
    }

    /**
     * Reads the value from the digital input.
     * Note: if pull up mode is enabled input is reversed.
     * @return Raw value from input.
     */
    @Override
    public boolean readRawValue() {
        if (m_pullup) {
            return !input.get();
        } else {
            return input.get();
        }
    }
}
