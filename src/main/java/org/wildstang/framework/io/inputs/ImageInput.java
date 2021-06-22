package org.wildstang.framework.io.inputs;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * First abstraction of Input representing "image" Inputs for vision purposes.
 * There are currently no implementations of this type.
 */
public abstract class ImageInput extends Input {

    private static Logger s_log = Logger.getLogger(ImageInput.class.getName());
    private static final String s_className = "ImageInput";

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
        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "readDataFromInput");
        }

        Object newValue = readRawValue();

        // Only update if the value has changed
        if (s_log.isLoggable(Level.FINEST)) {
            s_log.finest("Current value = " + m_currentValue + " : New value = " + newValue);
        }

        if (!newValue.equals(m_currentValue)) {
            setCurrentValue(newValue);
            setValueChanged(true);
        }

        if (s_log.isLoggable(Level.FINER)) {
            s_log.exiting(s_className, "readDataFromInput");
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
    protected void logCurrentStateInternal() {
        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "logCurrentState");
        }

        if (s_log.isLoggable(Level.FINER)) {
            s_log.exiting(s_className, "logCurrentState");
        }
    }

}
