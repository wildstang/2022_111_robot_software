package org.wildstang.framework.io.inputs;

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * First abstraction of Input representing "analog" Inputs
 * such as joysticks and gyros. 
 */
public abstract class AnalogInput extends Input {

    private static Logger s_log = Logger.getLogger(AnalogInput.class.getName());
    private static final String s_className = "AnalogInput";

    private static final DecimalFormat s_format = new DecimalFormat("#.###");

    private double m_currentValue = 0.0d;

    /**
     * Constructor simply passes on name.
     * @param p_name Name of the Input.
     */
    public AnalogInput(String p_name) {
        super(p_name);
    }

    /**
     * Constructor with default value.
     * @param p_name Name of the Input.
     * @param p_default Default value of the Input.
     */
    public AnalogInput(String p_name, double p_default) {
        super(p_name);
        m_currentValue = p_default;
    }

    /**
     * Processes raw value read from Input's hardware.
     */
    @Override
    protected void readDataFromInput() {
        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "readDataFromInput");
        }

        double newValue = readRawValue();

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
    public void setValue(double p_newValue) {
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
     * Note: Analog input may change often due to their analog nature.
     * AnalogInputs could have a tolerance to reduce updates,
     * but this would create some latency for sensitive applications like driving.
     * @param p_newValue New value to store.
     */
    private void setNewValue(double p_newValue) {
        // Only update if the value has changed
        // NOTE: For analog inputs, it is possible to change often due to noise
        // or sensitive sensors. May want to implement a tolerance/sensitivity
        // on value changes
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
    protected abstract double readRawValue();

    /**
     * Returns the latest stored value from the Input.
     * @return Latest value stored in the Input.
     */
    public double getValue() {
        return m_currentValue;
    }

    /**
     * Logs the Input's state to the StateTracker.
     */
    @Override
    protected void logCurrentStateInternal() {
        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "logCurrentState");
        }

        getStateTracker().addState(getName(), getName(), s_format.format(getValue()));

        if (s_log.isLoggable(Level.FINER)) {
            s_log.exiting(s_className, "logCurrentState");
        }
    }

}
