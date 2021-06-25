package org.wildstang.hardware.roborio.inputs.config;

import org.wildstang.framework.hardware.InputConfig;

/**
 * Contains configurations for joystick axis.
 */
public class WsJSJoystickInputConfig implements InputConfig {

    private int m_port = 0;
    private int m_axis = 0;

    /**
     * Construct the joystick axis config
     * @param p_port Driver station controller port number.
     * @param p_axis Joystick axis index.
     */
    public WsJSJoystickInputConfig(int p_port, int p_axis) {
        m_port = p_port;
        m_axis = p_axis;
    }

    /**
     * Returns the controller port number from the driver station.
     * @return The controller port number.
     */
    public int getPort() {
        return m_port;
    }

    /**
     * Returns the joystick axis index.
     * @return The joystick axis index.
     */
    public int getAxis() {
        return m_axis;
    }

    /**
     * Builds a JSON String describing the joystick axis config.
     * @return Port number and axis number.
     */
    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("{\"port\": ");
        buf.append(m_port);
        buf.append(",\"axis\": ");
        buf.append(m_axis);
        buf.append("}");
        return buf.toString();
    }

}
