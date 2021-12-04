package org.wildstang.framework.io.inputs;

/**
 * First abstraction of Input representing I2C Inputs.
 * Currently there are no implementations of this type.
 */
public abstract class I2CInput extends Input {

    private byte[] m_currentValue;

    /**
     * Constructor simply passes on name.
     * @param p_name Name of the Input.
     */
    public I2CInput(String p_name) {
        super(p_name);
    }

    /**
     * Processes raw value read from Input's hardware.
     */
    @Override
    protected void readDataFromInput() {
        setNewValue(readRawValue());
    }

    /**
     * Takes a new value stores it and notifys listeners.
     * This is a public version of setNewValue() for manual value updating.
     * @param p_newValue New value read for the Input.
     */
    public void setValue(byte[] p_newValue) {
        setNewValue(p_newValue);

        notifyListeners();
    }

    /**
     * Takes an ingested value, store it, and marks the value changed flag if it has.
     * @param p_newValue New value to store.
     */
    private void setNewValue(byte[] p_newValue) {
        if (p_newValue != m_currentValue) {
            m_currentValue = p_newValue;
            setValueChanged(true);
        }
        else {
            setValueChanged(false);
        }
    }

    /**
     * Abstract function to request and return the raw value from hardware.
     * @return The latest value read from hardware.
     */
    protected abstract byte[] readRawValue();

    /**
     * Returns the latest stored value from the Input.
     * @return Latest value store in the Input.
     */
    public byte[] getValue() {
        return m_currentValue;
    }
    
}
