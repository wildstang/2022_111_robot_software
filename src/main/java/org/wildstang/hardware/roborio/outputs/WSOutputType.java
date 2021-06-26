package org.wildstang.hardware.roborio.outputs;

import org.wildstang.framework.io.outputs.OutputType;

/**
 * Enum of provided OutputType.
 */
public enum WSOutputType implements OutputType {

    DIGITAL_OUTPUT("Digital"),
    SERVO("Servo"),
    SOLENOID_SINGLE("Solenoid"),
    SOLENOID_DOUBLE("Double solenoid"),
    PHOENIX("Phoenix"),
    VICTOR("Victor"),
    TALON("Talon"),
    RELAY("Relay"),
    I2C("I2C"),
    REMOTE_ANALOG("Remote Analog"),
    REMOTE_DIGITAL("Remote Digital"),
    NULL("Null");

    private String m_typeStr;

    /**
     * Construct the OutputType with a name.
     * @param p_typeStr Name of the output type.
     */
    WSOutputType(String p_typeStr) {
        m_typeStr = p_typeStr;
    }

    /**
     * Returns the name of the OutputType.
     * @return Name of the OutputType.
     */
    @Override
    public String toString() {
        return m_typeStr;
    }

}
