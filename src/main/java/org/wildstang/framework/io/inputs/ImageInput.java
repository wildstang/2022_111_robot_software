package org.wildstang.framework.io.inputs;

/**
 * First abstraction of Input representing "image" Inputs for vision purposes.
 * There are currently no implementations of this type.
 */
public abstract class ImageInput extends Input {

    private Object m_currentValue = null;

    /**
     * Constructor simply passes on name.
     * @param p_name Name of the Input.
     */
    public ImageInput(String p_name) {
        super(p_name);
    }

    /**
     * Processes raw value read from Input's hardware.
     */
    @Override
    public void readDataFromInput() {
        Object newValue = readRawValue();

        // Only update if the value has changed
        if (!newValue.equals(m_currentValue)) {
            setCurrentValue(newValue);
            setValueChanged(true);
        }
    }

    /**
     * Stores a given Object as the current value.
     * @param p_value New Object to store.
     */
    protected void setCurrentValue(Object p_value) {
        m_currentValue = p_value;
    }

    /**
     * Abstract function to request and return the raw value from hardware.
     * @return The latest value read from hardware.
     */
    protected abstract Object readRawValue();

    /**
     * Returns the latest stored value from the Input.
     * @return Latest value store in the Input.
     */
    public Object getValue() {
        return m_currentValue;
    }

    /**
     * Logs the Input's state to the StateTracker.
     * Currently logs nothing as images are too complex for complete logs.
     */
    @Override
    protected void logCurrentStateInternal() {}

}
