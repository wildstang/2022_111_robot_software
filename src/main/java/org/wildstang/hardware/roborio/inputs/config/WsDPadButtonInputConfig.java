package org.wildstang.hardware.roborio.inputs.config;

/**
 * Wrapper class to identify d-pad buttons separate from joystick buttons.
 */
public class WsDPadButtonInputConfig extends WsJSButtonInputConfig {

    /**
     * Wrapper constructor.
     * @param p_port Driver station controller port number.
     * @param p_button Joystick button index.
     */
    public WsDPadButtonInputConfig(int p_port, int p_button) {
        super(p_port, p_button);
    }
}