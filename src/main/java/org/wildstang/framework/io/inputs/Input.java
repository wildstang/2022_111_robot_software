package org.wildstang.framework.io.inputs;

import java.util.ArrayList;
import java.util.List;

import org.wildstang.framework.CoreUtils;
import org.wildstang.framework.io.InputListener;
import org.wildstang.year2022.subsystems.Catapault.CatapaultMain;

/**
 * Core input functions, primarily handling InputListeners.
 * Note: Input's update() handles InputListener triggering,
 * latency could be increase if abstracted versions call
 * readDataFromInput() then notifyListners().
 * @author Steve
 */
public abstract class Input {

    private ArrayList<InputListener> m_listeners = new ArrayList<>(5);
    private String m_name = "Undefined";
    private boolean m_valueChanged = false;
    private boolean m_enabled = true;

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
     * @param catapaultMain New InputListener to attach.
     */
    public void addInputListener(CatapaultMain catapaultMain) {
        CoreUtils.checkNotNull(catapaultMain, "p_listener is null");

        // Only add the listener if it does not exist in the list already
        if (!m_listeners.contains(catapaultMain)) {
            m_listeners.add(catapaultMain);
        }
    }

    /**
     * Detaches a given InputListener from the Input.
     * @param p_listener InputListner to detach.
     */
    public void removeInputListener(InputListener p_listener) {
        CoreUtils.checkNotNull(p_listener, "p_listener is null");

        for (int i = 0; i < m_listeners.size(); i++) {
            if (m_listeners.get(i).equals(p_listener)) {
                m_listeners.remove(i);
            }
        }
    }

    /**
     * Returns a copy of the Array of all attached InputListeners.
     * @return Copy of the Array of InputListeners.
     */
    public List<InputListener> getInputListeners() {
        // Make a copy of the list so that callers are not working on the internal list
        return new ArrayList<>(m_listeners);
    }

    /**
     * Determines if the value has changed,
     * then notifys all attached listeners that a state change has occured.
     */
    protected void notifyListeners() {
        // If the value changed, notify listeners
        if (hasValueChanged()) {
            for (InputListener listener : m_listeners) {
                listener.inputUpdate(this);
            }
        }
    }

    /**
     * Reads the data from the hardware Input and notifys listeners appropriately.
     */
    public void update() {
        // Read the raw input state
        readDataFromInput();

        // Notify any listeners
        notifyListeners();
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
