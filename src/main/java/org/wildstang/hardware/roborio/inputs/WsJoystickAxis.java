package org.wildstang.hardware.roborio.inputs;

import org.wildstang.framework.io.inputs.AnalogInput;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Reads a controller joystick axis.
 */
public class WsJoystickAxis extends AnalogInput {

    Joystick m_joystick;
    int m_axisIndex;

    /**
     * Construct the axis.
     * @param p_name Descriptive name of the axis.
     * @param p_port Controller port as defined in the driver station.
     * @param p_axisIndex Controller axis index.
     */
    public WsJoystickAxis(String p_name, int p_port, int p_axisIndex) {
        super(p_name);
        m_joystick = new Joystick(p_port);
        m_axisIndex = p_axisIndex;
    }

    /**
     * Reads the value from the axis.
     * @return Raw axis value.
     */
    @Override
    protected double readRawValue() {
        double value;

        // Invert the vertical axes so that full up is 1
        if (m_axisIndex % 2 == 0) {
            value = m_joystick.getRawAxis(m_axisIndex);
        } else {
            value = m_joystick.getRawAxis(m_axisIndex) * -1;
        }

        return value;
    }

}
