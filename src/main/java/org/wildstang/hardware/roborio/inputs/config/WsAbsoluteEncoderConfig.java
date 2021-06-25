package org.wildstang.hardware.roborio.inputs.config;

import org.wildstang.framework.hardware.InputConfig;

/**
 * Contains configuration for absolute encoders.
 */
public class WsAbsoluteEncoderConfig implements InputConfig {

    private int m_channel = 0;
    private int m_maxVoltage = 0;

    /**
     * Construct the absolute encoder config.
     * @param channel Encoder hardware port number.
     * @param p_maxVoltage Max voltage from the encoder.
     */
    public WsAbsoluteEncoderConfig(int channel, int p_maxVoltage) {
        m_channel = channel;
        m_maxVoltage = p_maxVoltage;
    }

    /**
     * Returns the hardware port number.
     * @return The hardware port number.
     */
    public int getChannel() {
        return m_channel;
    }

    /**
     * Returns the max voltage.
     * @return The max voltage.
     */
    public int getMaxVoltage() {
        return m_maxVoltage;
    }

    /**
     * Builds a JSON String describing the absolute encoder config.
     * @return Channel number and max voltage.
     */
    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("{\"channel\": ");
        buf.append(m_channel);
        buf.append(",\"maxVoltage\": ");
        buf.append(m_maxVoltage);
        buf.append("}");
        return buf.toString();
    }

}
