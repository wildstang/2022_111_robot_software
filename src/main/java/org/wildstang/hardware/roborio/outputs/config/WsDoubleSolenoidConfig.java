package org.wildstang.hardware.roborio.outputs.config;

import org.wildstang.framework.hardware.OutputConfig;
import org.wildstang.hardware.roborio.outputs.WsDoubleSolenoidState;

/**
 * Contains configurations for double solenoids.
 */
public class WsDoubleSolenoidConfig implements OutputConfig {

    private int m_module;
    private int m_channel1;
    private int m_channel2;
    private WsDoubleSolenoidState m_default;

    /**
     * Construct the double solenoid config.
     * @param module Module number.
     * @param channel1 Forward channel number.
     * @param channel2 Reverse channel number.
     * @param p_default Default state.
     */
    public WsDoubleSolenoidConfig(int module, int channel1, int channel2,
                                  WsDoubleSolenoidState p_default) {
        m_module = module;
        m_channel1 = channel1;
        m_channel2 = channel2;
        m_default = p_default;
    }

    /**
     * Return the forward channel number.
     * @return The forward channel number.
     */
    public int getChannel1() {
        return m_channel1;
    }

    /**
     * Return the reverse channel number.
     * @return The reverse channel number.
     */
    public int getChannel2() {
        return m_channel2;
    }
    
    /**
     * Return the default solenoid state.
     * @return The default solenoid state.
     */
    public WsDoubleSolenoidState getDefault() {
        return m_default;
    }

    /**
     * Return the module number.
     * @return The module number.
     */
    public int getModule() {
        return m_module;
    }

    /**
     * Builds a JSON String describing the solenoid config.
     * @return Module number and channel numbers.
     */
    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("{\"module\": ");
        buf.append(m_module);
        buf.append(", \"channel1\": ");
        buf.append(m_channel1);
        buf.append(",\"channel2\": ");
        buf.append(m_channel2);
        buf.append("}");
        return buf.toString();
    }

}
