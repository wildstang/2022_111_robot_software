package org.wildstang.hardware.roborio.inputs.config;

import org.wildstang.framework.hardware.InputConfig;

/**
 * Contains configuration for digital inputs.
 */
public class WsDigitalInputConfig implements InputConfig {

    private int m_channel = 0;
    private boolean m_pullup = false;

    /**
     * Construct the digital output config.
     * @param channel Digital hardware port number.
     * @param p_pullup Enables pull up mode on digital input.
     */
    public WsDigitalInputConfig(int channel, boolean p_pullup) {
        m_channel = channel;
        m_pullup = p_pullup;
    }

    /**
     * Returns the hardware port number.
     * @return The hardware port number.
     */
    public int getChannel() {
        return m_channel;
    }

    /**
     * Returns the pull up state.
     * @return True if pull up is enabled.
     */
    public boolean getPullup() {
        return m_pullup;
    }

    /**
     * Builds a JSON String describing the digital output config.
     * @return Channel number and pull up state.
     */
    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("{\"channel\": ");
        buf.append(m_channel);
        buf.append(",\"pullup\": \"");
        buf.append(m_pullup);
        buf.append("\"}");
        return buf.toString();
    }

}
