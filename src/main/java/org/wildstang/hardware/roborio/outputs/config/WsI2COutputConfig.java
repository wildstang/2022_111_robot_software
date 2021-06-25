package org.wildstang.hardware.roborio.outputs.config;

import org.wildstang.framework.hardware.OutputConfig;

import edu.wpi.first.wpilibj.I2C;

/**
 * Contains configurations for I2C Outputs.
 */
public class WsI2COutputConfig implements OutputConfig {

    private I2C.Port m_port = null;
    private int m_address = 0;

    /**
     * Construct the I2C config.
     * @param p_port I2C hardware port number.
     * @param p_address I2C address, normally static by device.
     */
    public WsI2COutputConfig(I2C.Port p_port, int p_address) {
        m_port = p_port;
        m_address = p_address;
    }

    /**
     * Return the I2C hardware port number, kOnboard or kMXP.
     * @return The I2C port number.
     */
    public I2C.Port getPort() {
        return m_port;
    }

    /**
     * Return the I2C address.
     * @return The I2C address.
     */
    public int getAddress() {
        return m_address;
    }

    /**
     * Builds a JSON String describing the I2C config.
     * @return Port number and address.
     */
    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("{\"port\": \"");
        buf.append(m_port);
        buf.append("\",\"address\": ");
        buf.append(Integer.toHexString(m_address));
        buf.append("}");
        return buf.toString();
    }

}
