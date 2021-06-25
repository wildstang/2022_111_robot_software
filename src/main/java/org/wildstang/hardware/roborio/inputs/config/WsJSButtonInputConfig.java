package org.wildstang.hardware.roborio.inputs.config;

import org.wildstang.framework.hardware.InputConfig;

/**
 * Contains configurations for joystick buttons.
 */
public class WsJSButtonInputConfig implements InputConfig {

    private int m_port = 0;
    private int m_button = 0;

    /**
     * Construct the joystick button config
     * @param p_port Driver station controller port number.
     * @param p_button Joystick button index.
     */
    public WsJSButtonInputConfig(int p_port, int p_button) {
        m_port = p_port;
        m_button = p_button;
    }

    /**
     * Returns the controller port number from the driver station.
     * @return The controller port number.
     */
    public int getPort() {
        return m_port;
    }

    /**
     * Returns the joystick button index.
     * @return The joystick button index.
     */
    public int getButton() {
        return m_button;
    }

    /**
     * Builds a JSON String describing the joystick button config.
     * @return Port number and button number.
     */
    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("{\"port\": ");
        buf.append(m_port);
        buf.append(",\"button\": ");
        buf.append(m_button);
        buf.append("}");
        return buf.toString();
    }

}
