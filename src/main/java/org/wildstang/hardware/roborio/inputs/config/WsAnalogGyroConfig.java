package org.wildstang.hardware.roborio.inputs.config;

import org.wildstang.framework.hardware.InputConfig;

/**
 * Contains configuration for analog gyros.
 */
public class WsAnalogGyroConfig implements InputConfig {

    private int m_channel = 0;
    private boolean m_compensate = true;

    /**
     * Construct the analog gyro config.
     * @param channel Encoder hardware port number.
     * @param driftCompensate Drift compensation factor.
     */
    public WsAnalogGyroConfig(int channel, boolean driftCompensate) {
        m_channel = channel;
        m_compensate = driftCompensate;
    }

    /**
     * Returns the hardware port number.
     * @return The hardware port number.
     */
    public int getChannel() {
        return m_channel;
    }

    /**
     * Returns the drift compensation factor.
     * @return The drift compensation factor.
     */
    public boolean getCompensate() {
        return m_compensate;
    }

    /**
     * Builds a JSON String describing the absolute encoder config.
     * @return Channel number and drift compensation factor.
     */
    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("{\"channel\": ");
        buf.append(m_channel);
        buf.append(",\"driftCompensation\": ");
        buf.append(m_compensate);
        buf.append("}");
        return buf.toString();
    }
}
