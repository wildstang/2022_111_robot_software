package org.wildstang.hardware.roborio.inputs;

import org.wildstang.framework.io.inputs.DigitalInput;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Reads a toggle joystick button.
 * A joystick button whose output TOGGLES between true and false with each button press.
 */
public class WsJoystickToggleButton extends DigitalInput {

    private Joystick m_joystick;
    private int m_buttonIndex;
    private boolean m_lastPressed;
    private boolean m_initialState;
    private boolean m_state;

    /** Construct the axis
     * @param p_name Descriptive name of the button.
     * @param p_port Controller port as defined in the driver station.
     * @param p_buttonIndex Controller button index.
     * @param p_initialState Initial state the toggle should start in.
     */
    public WsJoystickToggleButton(String p_name, int p_port, int p_buttonIndex, boolean p_initialState) {
        super(p_name);
        m_joystick = new Joystick(p_port);
        m_buttonIndex = p_buttonIndex + 1;
        m_lastPressed = false;
        m_state = m_initialState = p_initialState;
    }

    /**
     * Returns a toggled value each time the button is pressed.
     * @return Toggled button value.
     */
    @Override
    protected boolean readRawValue() {
        boolean pressed = m_joystick.getRawButton(m_buttonIndex);
        if (pressed && !m_lastPressed) {
            m_state = !m_state;
        }
        m_lastPressed = pressed;

        return m_state;
    }

    /**
     * Sets the toggle state back to the start state.
     */
    public void resetState() {
        m_state = m_initialState;
    }
}
