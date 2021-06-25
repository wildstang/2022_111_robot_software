package org.wildstang.hardware.roborio.inputs.config;

import org.wildstang.framework.hardware.InputConfig;

/**
 * Contains configuration for remote digital inputs.
 */
public class WsRemoteDigitalInputConfig implements InputConfig {

    private String m_networktbl = "RemoteIO";

    /**
     * Construct the remote digital output config.
     * @param networktbl Network table name.
     */
    public WsRemoteDigitalInputConfig(String networktbl) {
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
     * Builds a JSON String describing the remote digital output config.
     * @return Network table name.
     */
    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("{\"networktbl\": ");
        buf.append(m_networktbl);
        buf.append("\"}");
        return buf.toString();
    }

}