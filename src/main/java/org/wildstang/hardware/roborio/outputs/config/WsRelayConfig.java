package org.wildstang.hardware.roborio.outputs.config;

import org.wildstang.framework.hardware.OutputConfig;
import org.wildstang.hardware.roborio.outputs.WsRelayState;

/**
 * Contains configurations for relays.
 */
public class WsRelayConfig implements OutputConfig {

    private int m_channel = 0;
    private WsRelayState m_default;

    /**
     * Construct the relay config.
     * @param channel Hardware port number.
     * @param p_default Default relay state.
     */
    public WsRelayConfig(int channel, WsRelayState p_default) {
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
     * Returns the default relay state.
     * @return The default state.
     */
    public WsRelayState getDefault() {
        return m_default;
    }

    /**
     * Builds a JSON String describing the relay config.
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
