package org.wildstang.hardware.roborio.inputs;

import org.wildstang.framework.io.inputs.DigitalInput;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Reads a controller button.
 */
public class WsJoystickButton extends DigitalInput {

    Joystick m_joystick;
    int m_buttonIndex;

    /**
     * Construct the button.
     * @param p_name Descriptive name of the button.
     * @param p_port Controller port as defined in the driver station.
     * @param p_buttonIndex Controller button index.
     */
    public WsJoystickButton(String p_name, int p_port, int p_buttonIndex) {
        super(p_name);
        m_joystick = new Joystick(p_port);
        m_buttonIndex = p_buttonIndex + 1;
    }

    /**
     * Reads the value from the button.
     * @return Raw button value.
     */
    @Override
    protected boolean readRawValue() {
        return m_joystick.getRawButton(m_buttonIndex);
    }

}
