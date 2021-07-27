package org.wildstang.framework.io.inputs;

/**
 * First abstraction of Input representing "digital" Inputs
 * such as buttons and limit switches. 
 */
public abstract class DigitalInput extends Input {

    private boolean m_currentValue = false;
    private int m_cyclesOnCurrentValue = 0;
    private boolean m_debounced = false;
    private boolean m_lastValue;
    private int DEBOUNCE_CYCLES;

    /**
     * Constructor simply passes on name.
     * @param p_name Name of the Input.
     */
    public DigitalInput(String p_name) {
        super(p_name);
    }

    /**
     * Constructor with default value.
     * @param p_name Name of the Input.
     * @param p_debounced True if the Input should be "debounced". Used to clean up button presses.
     * @param p_debounceCycles Set the number of cycles value must be constant before determining no longer bouncing.
     */
    public DigitalInput(String p_name, boolean p_debounced, int p_debounceCycles) {
        super(p_name);
        m_debounced = p_debounced;
        DEBOUNCE_CYCLES = p_debounceCycles;
    }

    /**
     * Constructor with default value.
     * @param p_name Name of the Input.
     * @param p_default Default value of the Input.
     */
    public DigitalInput(String p_name, boolean p_default) {
        super(p_name);
        m_currentValue = p_default;
    }

    /**
     * Processes raw value read from Input's hardware.
     * Also counts bounces for debouncing.
     */
    @Override
    public void readDataFromInput() {
        boolean newValue = readRawValue();

        if (m_debounced) {
            if (newValue != m_lastValue) {
                // The value has changed - reset the counter
                m_cyclesOnCurrentValue = 0;
                m_lastValue = newValue;
            }
            else {
                // Otherwise, the value has held for longer - increment counter
                m_cyclesOnCurrentValue++;
            }

            // If the value has held long enough, set the new value
            if (m_cyclesOnCurrentValue >= DEBOUNCE_CYCLES) {
                setNewValue(newValue);
            }
        }
        else {
            setNewValue(newValue);
        }
    }

    /**
     * Takes a new value stores it and notifys listeners.
     * This is a public version of setNewValue() for manual value updating.
     * @param p_newValue New binary value read for the Input.
     */
    public void setValue(boolean p_newValue) {
        setNewValue(p_newValue);

        notifyListeners();
    }

    /**
     * Takes an ingested value, store it, and marks the value changed flag if it has.
     * @param p_newValue New binary value to store.
     */
    private void setNewValue(boolean p_newValue) {
        if (p_newValue != m_currentValue) {
            m_currentValue = p_newValue;
            setValueChanged(true);
        } else {
            setValueChanged(false);
        }
    }

    /**
     * Abstract function to request and return the raw value from hardware.
     * @return The latest binary value read from hardware.
     */
    protected abstract boolean readRawValue();

    /**
     * Returns the latest stored value from the Input.
     * @return Latest binary value store in the Input.
     */
    public boolean getValue() {
        return m_currentValue;
    }

}
