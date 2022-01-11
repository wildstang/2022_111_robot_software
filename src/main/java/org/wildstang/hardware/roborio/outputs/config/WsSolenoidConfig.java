package org.wildstang.hardware.roborio.outputs.config;

import org.wildstang.framework.hardware.OutputConfig;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

/**
 * Contains configurations for single solenoids.
 */
public class WsSolenoidConfig implements OutputConfig {

    private PneumaticsModuleType m_module;
    private int m_channel;
    private boolean m_default;

    /**
     * Construct the solenoid config.
     * @param module Module number.
     * @param channel Channel number.
     * @param p_default Default state.
     */
    public WsSolenoidConfig(PneumaticsModuleType module, int channel, boolean p_default) {
        m_module = module;
        m_channel = channel;
        m_default = p_default;
    }

    /**
     * Return the channel number.
     * @return The channel number.
     */
    public int getChannel() {
        return m_channel;
    }

    /**
     * Return the default solenoid state.
     * @return The default solenoid state.
     */
    public boolean getDefault() {
        return m_default;
    }

    /**
     * Return the module number.
     * @return The module number.
     */
    public PneumaticsModuleType getModule() {
        return m_module;
    }

    /**
     * Builds a JSON String describing the solenoid config.
     * @return Module number and channel number.
     */
    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("{\"module\": ");
        buf.append(m_module);
        buf.append(", \"channel\": ");
        buf.append(m_channel);
        buf.append("}");
        return buf.toString();
    }

}
