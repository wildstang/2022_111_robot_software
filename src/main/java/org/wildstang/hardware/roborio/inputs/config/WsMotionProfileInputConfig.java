package org.wildstang.hardware.roborio.inputs.config;

import org.wildstang.framework.hardware.InputConfig;

/**
 * Used only for identifying motion profiles.
 */
public class WsMotionProfileInputConfig implements InputConfig {

    /**
     * Construct the motion profile config.
     */
    public WsMotionProfileInputConfig() {}

    /**
     * Builds a JSON String describing the motion profile config.
     * @return Empty JSON object.
     */
    @Override
    public String toString() {
        return "{}";
    }

}
