package org.wildstang.hardware.roborio.inputs.config;

import org.wildstang.framework.hardware.InputConfig;

/**
 * Contains configuration for cameras.
 */
public class WsCameraInputConfig implements InputConfig {

    private int m_channel = 0;

    /**
     * Construct the camera config.
     * @param channel Camera channel number.
     */
    public WsCameraInputConfig(int channel) {
        m_channel = channel;
    }

    /**
     * Returns the channel number.
     * @return The channel number.
     */
    public int getChannel() {
        return m_channel;
    }
}
