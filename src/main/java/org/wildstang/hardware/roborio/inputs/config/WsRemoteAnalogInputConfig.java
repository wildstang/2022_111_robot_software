package org.wildstang.hardware.roborio.inputs.config;

import org.wildstang.framework.hardware.InputConfig;

/**
 * Contains configuration for remote analog inputs.
 */
public class WsRemoteAnalogInputConfig implements InputConfig {

    private String m_networktbl = "remoteIO";

    /**
     * Construct the remote analog output config.
     * @param networktbl Network table name.
     */
    public WsRemoteAnalogInputConfig(String networktbl) {
        m_networktbl = networktbl;
    }

    /**
     * Returns the network table name.
     * @return Network table name.
     */
    public String getTableName() {
        return m_networktbl;
    }

    /**
     * Builds a JSON String describing the remote analog output config.
     * @return Network table name.
     */
    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("{\"networktbl\": ");
        buf.append(m_networktbl);
        buf.append("}");
        return buf.toString();
    }
}