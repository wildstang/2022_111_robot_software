package org.wildstang.hardware.roborio.inputs;

import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.hardware.JoystickConstants;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Reads the d-pad on playstation controllers.
 */
public class WsDPadButton extends DigitalInput {

    Joystick m_joystick;
    int m_buttonIndex;

    /**
     * Construct the d-pad button.
     * @param p_name Descriptive name of the button.
     * @param p_port Controller port as defined in the driver station.
     * @param p_buttonIndex Button index of the d-pad.
     */
    public WsDPadButton(String p_name, int p_port, int p_buttonIndex) {
        super(p_name, true, 3);
        m_joystick = new Joystick(p_port);
        m_buttonIndex = p_buttonIndex;
    }

    /**
     * Reads the value from the d-pad and appropriately sets button press.
     * @return True if pressed.
     */
    @Override
    protected boolean readRawValue() {
        double value;
        boolean boolValue = false;

        switch (m_buttonIndex) {
        case JoystickConstants.DPAD_X_LEFT:
            value = m_joystick.getPOV();
            boolValue = (value == 270);
        break;
        case JoystickConstants.DPAD_X_RIGHT:
            value = m_joystick.getPOV();
            boolValue = (value == 90);
        break;
        case JoystickConstants.DPAD_Y_UP:
            value = m_joystick.getPOV();
            boolValue = (value == 0);
        break;
        case JoystickConstants.DPAD_Y_DOWN:
            value = m_joystick.getPOV();
            boolValue = (value == 180);
        break;
        default:
            // Don't care about any other buttons.
        }

        return boolValue;
    }

}
