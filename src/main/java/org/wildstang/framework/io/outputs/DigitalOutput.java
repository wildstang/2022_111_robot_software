package org.wildstang.framework.io.outputs;

/**
 * First abstraction of Output representing "analog" Outputs
 * such as solenoids and LEDs. 
 */
public abstract class DigitalOutput extends Output {

    private boolean m_value;

    /**
     * Constructor simply passes on name.
     * @param p_name Name of the Output.
     */
    public DigitalOutput(String p_name) {
        super(p_name);
    }

    /**
     * Constructor with default value.
     * @param p_name Name of the Output.
     * @param p_default Default value of the Output.
     */
    public DigitalOutput(String p_name, boolean p_default) {
        super(p_name);
        m_value = p_default;
    }

    /**
     * Returns the latest commanded value for the Output.
     * @return Latest binary value stored in the Output.
     */
    public boolean getValue() {
        return m_value;
    }

    /**
     * Sets the output value, doesn't command the hardware.
     * @param p_value New binary value for the Output.
     */
    public void setValue(boolean p_value) {
        m_value = p_value;
    }

}
