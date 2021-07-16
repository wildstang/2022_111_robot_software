package org.wildstang.framework.io.outputs;

/**
 * First abstraction of Output representing I2C Outputs.
 * Currently there are no implementations of this type.
 */
public abstract class I2COutput extends Output {

    private byte[] m_value;

    /**
     * Constructor simply passes on name.
     * @param p_name Name of the Output.
     */
    public I2COutput(String p_name) {
        super(p_name);
    }

    /**
     * Returns the latest commanded value for the Output.
     * @return Latest value stored in the Output.
     */
    public byte[] getValue() {
        return m_value;
    }

    /**
     * Sets the output value, doesn't command the hardware.
     * @param p_value New value for the Output.
     */
    public void setValue(byte[] p_value) {
        m_value = p_value;
    }

    /**
     * Doesn't log anything right now.
     */
    @Override
    protected void logCurrentState() {}

}
