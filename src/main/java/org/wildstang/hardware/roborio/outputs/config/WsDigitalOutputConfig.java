package org.wildstang.hardware.roborio.outputs.config;

import org.wildstang.framework.hardware.OutputConfig;

/**
 * Contains configurations for digital outputs.
 */
public class WsDigitalOutputConfig implements OutputConfig {

    private int m_channel = 0;
    private boolean m_default;

    /**
     * Construct the digital output config.
     * @param channel Digital hardware port number.
     * @param p_default Default output value.
     */
    public WsDigitalOutputConfig(int channel, boolean p_default) {
        m_channel = channel;
        m_default = p_default;
    }

    /**
     * Returns the hardware port number.
     * @return The hardware port number.
     */
    public int getChannel() {
        return m_channel;
    }

    /**
     * Returns the default output value.
     * @return The default value.
     */
    public boolean getDefault() {
        return m_default;
    }

    /**
     * Builds a JSON String describing the digital output config.
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
