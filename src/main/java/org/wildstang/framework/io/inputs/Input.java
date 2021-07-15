package org.wildstang.framework.io.inputs;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.wildstang.framework.CoreUtils;
import org.wildstang.framework.io.InputListener;
import org.wildstang.framework.logger.StateTracker;

/**
 * Core input functions, primarily handling InputListeners.
 * Note: Input's update() handles InputListener triggering,
 * latency could be increase if abstracted versions call
 * readDataFromInput() then notifyListners().
 * @author Steve
 */
public abstract class Input {

    private static Logger s_log = Logger.getLogger(Input.class.getName());
    private static final String s_className = "AbstractInput";

    private ArrayList<InputListener> m_listeners = new ArrayList<>(5);
    private String m_name = "Undefined";
    private boolean m_valueChanged = false;
    private boolean m_enabled = true;

    private StateTracker m_stateTracker;

    /**
     * Constructor sets the name of the Input.
     * @param p_name New name of the Input.
     */
    public Input(String p_name) {
        CoreUtils.checkNotNull(p_name, "p_name is null");

        m_name = p_name;
    }

    /**
     * Attaches an InputListener to the Input.
     * @param p_listener New InputListener to attach.
     */
    public void addInputListener(InputListener p_listener) {
        CoreUtils.checkNotNull(p_listener, "p_listener is null");

        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "addInputListener");
        }

        // Only add the listener if it does not exist in the list already
        if (!m_listeners.contains(p_listener)) {
            m_listeners.add(p_listener);
        }

        if (s_log.isLoggable(Level.FINER)) {
            s_log.exiting(s_className, "addInputListener");
        }
    }

    /**
     * Detaches a given InputListener from the Input.
     * @param p_listener InputListner to detach.
     */
    public void removeInputListener(InputListener p_listener) {
        CoreUtils.checkNotNull(p_listener, "p_listener is null");

        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "removeInputListener");
        }

        for (int i = 0; i < m_listeners.size(); i++) {
            if (m_listeners.get(i).equals(p_listener)) {
                m_listeners.remove(i);
            }
        }

        if (s_log.isLoggable(Level.FINER)) {
            s_log.exiting(s_className, "removeInputListener");
        }
    }

    /**
     * Returns a copy of the Array of all attached InputListeners.
     * @return Copy of the Array of InputListeners.
     */
    public List<InputListener> getInputListeners() {
        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "getInputListeners");
        }

        // Make a copy of the list so that callers are not working on the internal list
        ArrayList<InputListener> copy = new ArrayList<>(m_listeners);

        if (s_log.isLoggable(Level.FINER)) {
            s_log.exiting(s_className, "getInputListeners");
        }

        return copy;
    }

    /**
     * Determines if the value has changed,
     * then notifys all attached listeners that a state change has occured.
     */
    protected void notifyListeners() {
        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "notifyListeners");
        }

        // If the value changed, notify listeners
        if (hasValueChanged()) {
            if (s_log.isLoggable(Level.FINEST)) {
                s_log.finest("Input " + getName() + ": value has changed - notifying listeners");
            }

            for (InputListener listener : m_listeners) {
                if (s_log.isLoggable(Level.FINER)) {
                    s_log.finer("Notifying input listener: " + listener);
                }
                listener.inputUpdate(this);
            }
        } else {
            if (s_log.isLoggable(Level.FINEST)) {
                s_log.finest("Input value has not changed - not notifying");
            }
        }

        if (s_log.isLoggable(Level.FINER)) {
            s_log.exiting(s_className, "notifyListeners");
        }
    }

    /**
     * Reads the data from the hardware Input and notifys listeners appropriately.
     */
    public void update() {
        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "update");
        }

        // Read the raw input state
        if (s_log.isLoggable(Level.FINER)) {
            s_log.finer("Reading data from input");
        }
        readDataFromInput();

        logCurrentState();

        // Notify any listeners
        notifyListeners();

        if (s_log.isLoggable(Level.FINER)) {
            s_log.exiting(s_className, "update");
        }
    }

    /**
     * Logs the current state of the Input.
     */
    protected void logCurrentState() {
        // Log any state information
        if (m_stateTracker != null) {
            if (s_log.isLoggable(Level.FINER)) {
                s_log.finer("Logging input state");
            }
            logCurrentStateInternal();
        }
    }

    /**
     * Returns the name of the Input.
     * @return Name of the Input.
     */
    public String getName() {
        return m_name;
    }

    /**
     * Mark that the value of the Input has changed.
     * @param p_changed New state of the valueChanged.
     */
    protected void setValueChanged(boolean p_changed) {
        m_valueChanged = p_changed;
    }

    /**
     * Returns true if the value of the Input has changed.
     * @return True if the value of the Input has changed.
     */
    protected boolean hasValueChanged() {
        return m_valueChanged;
    }

    /**
     * Abstract function to log the hardware Input's state to the StateTracker.
     */
    protected abstract void logCurrentStateInternal();

    /**
     * Abstract function to read the hardware Input and store its value.
     */
    protected abstract void readDataFromInput();

    /**
     * Detaches all InputListeners associated with the Input.
     */
    public void removeAllListeners() {
        m_listeners.clear();
    }

    /**
     * Associates a StateTracker with the Input.
     * @param p_tracker New StateTracker to associate with Input.
     */
    public void setStateTracker(StateTracker p_tracker) {
        m_stateTracker = p_tracker;
    }

    /**
     * Returns the StateTracker associated with the Input.
     * @return Input's StateTracker.
     */
    public StateTracker getStateTracker() {
        return m_stateTracker;
    }

    /**
     * Enables the Input, does nothing at the Input level.
     */
    public void enable() {
        m_enabled = true;
    }

    /**
     * Disables the Input, does nothing at the Input level.
     */
    public void disable() {
        m_enabled = false;
    }

    /**
     * Returns true if the Input is enabled, does nothing at this level.
     * @return True if the Input is enabled.
     */
    public boolean isEnabled() {
        return m_enabled;
    }

}
