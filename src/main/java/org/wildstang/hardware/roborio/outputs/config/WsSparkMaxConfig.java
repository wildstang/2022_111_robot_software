package org.wildstang.hardware.roborio.outputs.config;

import org.wildstang.framework.hardware.OutputConfig;

/**
 * Contains configurations for Spark Max motor controllers.
 */
public class WsSparkMaxConfig implements OutputConfig {

    private int m_channel = 0;
    private double m_default;
    private boolean brushless;
    private boolean invert;

    /**
     * Construct the Phoenix config.
     * @param channel Hardware port number.
     * @param brushless True if the motor is brushless, false if brushed.
     * @param p_default Default output value.
     */
    public WsSparkMaxConfig(int channel, boolean brushless, double p_default) {
        this(channel, brushless, p_default, false);
    }

    /**
     * Construct the Phoenix config.
     * @param channel Controller CAN constant.
     * @param brushless True if the motor is brushless, false if brushed.
     * @param p_default Default output value.
     * @param invert True if motor output should be inverted.
     */
    public WsSparkMaxConfig(int channel, boolean brushless, double p_default, boolean invert) {
        m_channel = channel;
        m_default = p_default;
        this.brushless = brushless;
        this.invert = invert;
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
     * Returns true if the motor is brushless.
     * @return True if the motor is brushless, false if brushed.
     */
    public boolean isBrushless() {
        return brushless;
    }

    /**
     * Returns if the controller should be inverted.
     * @return True if inverted.
     */
    public boolean isInverted() {
        return invert;
    }

    /**
     * Builds a JSON String describing the Spark Max config.
     * @return Channel number and is talon.
     */
    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("{\"channel\": ");
        buf.append(m_channel);
        buf.append(", \"brushless\": ");
        buf.append(brushless);
        buf.append("}");
        return buf.toString();
    }

}
