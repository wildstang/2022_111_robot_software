package org.wildstang.framework.io.inputs;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * First abstraction of Input representing I2C Inputs.
 * Currently there are no implementations of this type.
 */
public abstract class I2CInput extends Input {

    private static Logger s_log = Logger.getLogger(I2CInput.class.getName());
    private static final String s_className = "I2CInput";

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
        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "readDataFromInput");
        }

        byte[] newValue = readRawValue();

        setNewValue(newValue);

        if (s_log.isLoggable(Level.FINER)) {
            s_log.exiting(s_className, "readDataFromInput");
        }
    }

    /**
     * Takes a new value stores it and notifys listeners.
     * This is a public version of setNewValue() for manual value updating.
     * @param p_newValue New value read for the Input.
     */
    public void setValue(byte[] p_newValue) {
        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "setValue");
        }

        setNewValue(p_newValue);

        logCurrentState();

        notifyListeners();

        if (s_log.isLoggable(Level.FINER)) {
            s_log.exiting(s_className, "setValue");
        }
    }

    /**
     * Takes an ingested value, store it, and marks the value changed flag if it has.
     * @param p_newValue New value to store.
     */
    private void setNewValue(byte[] p_newValue) {
        if (s_log.isLoggable(Level.FINEST)) {
            s_log.finest("Current value = " + m_currentValue + " : New value = " + p_newValue);
        }

        if (p_newValue != m_currentValue) {
            m_currentValue = p_newValue;
            setValueChanged(true);
        } else {
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

    /**
     * Doesn't log anything right now.
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
