package org.wildstang.framework.io.outputs;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.wildstang.framework.CoreUtils;
import org.wildstang.framework.logger.StateTracker;

public abstract class Output {

    private static Logger s_log = Logger.getLogger(Output.class.getName());
    private static final String s_className = "AbstractOutput";

    private String m_name = "Undefined";
    private StateTracker m_stateTracker;
    private boolean m_enabled = true;

    public Output(String p_name) {
        CoreUtils.checkNotNull(p_name, "p_name is null");

        m_name = p_name;
    }

    public void update() {
        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "update");
        }

        // Log the output current state
        if (m_stateTracker != null) {
            if (s_log.isLoggable(Level.FINER)) {
                s_log.finer("Logging current state");
            }
            logCurrentState();
        }

        if (s_log.isLoggable(Level.FINER)) {
            s_log.finer("Sending data to output");
        }
        sendDataToOutput();

        if (s_log.isLoggable(Level.FINER)) {
            s_log.exiting(s_className, "update");
        }
    }

    public String getName() {
        return m_name;
    }

    protected abstract void logCurrentState();

    protected abstract void sendDataToOutput();

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    public void setStateTracker(StateTracker p_tracker) {
        m_stateTracker = p_tracker;
    }

    public StateTracker getStateTracker() {
        return m_stateTracker;
    }

    public void enable() {
        m_enabled = true;
    }

    public void disable() {
        m_enabled = false;
    }

    public boolean isEnabled() {
        return m_enabled;
    }

}
