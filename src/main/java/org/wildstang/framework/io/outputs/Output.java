package org.wildstang.framework.io.outputs;

import org.wildstang.framework.CoreUtils;
import org.wildstang.framework.logger.StateTracker;

/**
 * Core output functions.
 */
public abstract class Output {

    private String m_name = "Undefined";
    private StateTracker m_stateTracker;
    private boolean m_enabled = true;

    /**
     * Constructor sets the name of the Output.
     * @param p_name New name of the Output.
     */
    public Output(String p_name) {
        CoreUtils.checkNotNull(p_name, "p_name is null");

        m_name = p_name;
    }

    /**
     * Logs the current state to the StateTracker, if one exists.
     * Then sends that state to the hardware O;utput.
     */
    public void update() {
        // Log the output current state
        if (m_stateTracker != null) {
            logCurrentState();
        }

        sendDataToOutput();
    }

    /**
     * Returns the name of the Output.
     * @return Name of the Output.
     */
    public String getName() {
        return m_name;
    }

    /**
     * Abstract function to log the Output's state to the StateTracker.
     */
    protected abstract void logCurrentState();

    /**
     * Abstract function to write the current value to hardware.
     */
    protected abstract void sendDataToOutput();

    /**
     * Produces a hashcode based off the Output's name.
     * @return Newly generated hashcode.
     */
    @Override
    public int hashCode() {
        return getName().hashCode();
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
     * Enables the Output, does nothing at the Input level.
     */
    public void enable() {
        m_enabled = true;
    }

    /**
     * Disables the Output, does nothing at the Input level.
     */
    public void disable() {
        m_enabled = false;
    }

    /**
     * Returns true if the Output is enabled, does nothing at this level.
     * @return True if the Output is enabled.
     */
    public boolean isEnabled() {
        return m_enabled;
    }

}
