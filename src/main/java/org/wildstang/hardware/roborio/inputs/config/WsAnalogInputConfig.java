package org.wildstang.hardware.roborio.inputs.config;

import org.wildstang.framework.hardware.InputConfig;

/**
 * Contains configuration for analog inputs.
 */
public class WsAnalogInputConfig implements InputConfig {

    private int m_channel = 0;

    /**
     * Construct the analog output config.
     * @param channel Analog hardware port number.
     */
    public WsAnalogInputConfig(int channel) {
        m_channel = channel;
    }

    /**
     * Returns the hardware port number.
     * @return The hardware port number.
     */
    public int getChannel() {
        return m_channel;
    }

    /**
     * Builds a JSON String describing the analog output config.
     * @return Channel number.
     */
    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("{\"channel\": ");
        buf.append(m_channel);
        buf.append("}");
        return buf.toString();
    }
}
