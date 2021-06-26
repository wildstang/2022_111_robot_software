package org.wildstang.hardware.roborio.outputs.config;

import org.wildstang.framework.hardware.OutputConfig;

/**
 * Contains configurations for Talon motor controllers.
 * @deprecated Since we use CAN, use TalonSRX instead.
 */
@Deprecated
public class WsTalonConfig implements OutputConfig {

    private int m_channel = 0;
    private double m_default;

    /**
     * Construct the Talon config.
     * @param channel Hardware port number.
     * @param p_default Default output value.
     */
    public WsTalonConfig(int channel, double p_default) {
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
    public double getDefault() {
        return m_default;
    }

    /**
     * Builds a JSON String describing the Talon config.
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
