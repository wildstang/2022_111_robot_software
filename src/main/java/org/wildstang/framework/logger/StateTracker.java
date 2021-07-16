package org.wildstang.framework.logger;

import java.util.ArrayList;
import java.util.Date;

/**
 * Used to track the various logs created before being written to log file.
 * There is one StateTracker that the framework's Core owns.
 * Additional StateTrackers may be made and given to new StateLoggers,
 * but the intended use case is one StateLogger belonging to the year's Robot.
 * StateTrackers track both descriptions of available IO as IOInfo grouped in IOSets,
 * as well as, StateInfo logs grouped in StateGroups.
 */
public class StateTracker {

    private boolean s_initialised = false;

    private StateGroup m_currentState = null;
    private ArrayList<StateGroup> m_stateList = new ArrayList<>();
    private IOSet m_ioSet = new IOSet();

    private boolean m_trackingState = true;

    private Object m_stateListLock = new Object();

    /**
     * Sets the tracker as initialised.
     */
    public void init() {
        if (!s_initialised) {
            s_initialised = true;
        }
    }

    /**
     * If tracking state, creates a new StateGroup at the given timestamp.
     * @param p_timestamp Timestamp to create StateGroup at.
     */
    public void beginCycle(Date p_timestamp) {
        if (m_trackingState) {
            if (m_currentState != null) {
                // Error - still in a cycle
                throw new IllegalStateException(
                        "Cannot being a new state cycle while already in a cycle.");
            }
            else {

                m_currentState = new StateGroup(p_timestamp);
            }
        }
    }

    /**
     * Creates a new StateInfo and adds it to the current StateGroup if it exists.
     * @param p_name Name of entry.
     * @param p_parent Name of entry's parent.
     * @param p_value Value of entry.
     */
    public void addState(String p_name, String p_parent, Object p_value) {
        if (m_trackingState) {
            if (m_currentState == null) {
                // Error - not in a cycle
                throw new IllegalStateException(
                        "Cannot add state information while not in a cycle.");
            }
            else {
                m_currentState.addState(p_name, p_parent, p_value);
            }
        }
    }

    /**
     * Adds the current StateGroup to the StateTracker's set, then clears it.
     */
    public void endCycle() {
        if (m_trackingState) {
            if (m_currentState == null) {
                // Error not in a cycle
                throw new IllegalStateException("Cannot end a cycle while not in a cycle.");
            }
            else {
                // Synchronise access to the list to avoid clashes with having the list swapped
                synchronized (m_stateListLock) {
                    m_stateList.add(m_currentState);
                    m_currentState = null;
                }
            }
        }
    }

    /**
     * Creates a new IOInfo and adds it to the current IOSet if it exists.
     * @param p_name Specific name of state.
     * @param p_type Type of IO, normally subsystem.
     * @param p_direction IO direction (Input/Output).
     * @param p_port IO port or null if not applicable.
     */
    public void addIOInfo(String p_name, String p_type, String p_direction, Object p_port) {
        m_ioSet.addIOInfo(p_name, p_type, p_direction, p_port);
    }

    /**
     * Returns the current StateGroup, it may be null.
     * @return The current StateGroup.
     */
    protected StateGroup getCurrentState() {
        return m_currentState;
    }

    /**
     * Returns the current IOSet, it may be null.
     * @return The current IOSet.
     */
    public IOSet getIoSet() {
        return m_ioSet;
    }

    /**
     * Returns the current set of StateGroups.
     * @return Current set of StateGroups.
     */
    public ArrayList<StateGroup> getStateList() {
        ArrayList<StateGroup> prevList;

        // Get the current list and swap it for a new list
        // This avoids write clashes when read from the logger possibly on a separate
        // thread
        synchronized (m_stateListLock) {
            prevList = m_stateList;
            m_stateList = new ArrayList<>();
        }

        return prevList;
    }

    /**
     * Resumes state tracking.
     */
    public void startTrackingState() {
        m_trackingState = true;
    }

    /**
     * Pauses state tracking.
     */
    public void stopTrackingState() {
        m_trackingState = false;
    }

    /**
     * Returns true if tracking state.
     * @return True if tracking state.
     */
    public boolean isTrackingState() {
        return m_trackingState;
    }

    /**
     * Clears out all stored info and resumes state tracking.
     */
    public void reset() {
        m_ioSet = new IOSet();
        m_stateList.clear();
        m_currentState = null;
        startTrackingState();
    }
}
