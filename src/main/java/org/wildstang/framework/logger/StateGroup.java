package org.wildstang.framework.logger;

import java.util.Date;
import java.util.HashMap;

/**
 * Tracks a group of StateInfo which occured at the same moment.
 * This is intended for logging many different states at once.
 */
public class StateGroup {

    private Date m_timestamp;
    private HashMap<String, StateInfo> m_stateList = new HashMap<>();

    /**
     * Construct a StateGroup at a given timestamp.
     * @param p_timestamp Timestamp of log.
     */
    public StateGroup(Date p_timestamp) {
        m_timestamp = p_timestamp;
    }

    /**
     * Adds a new entry to the StateGroup.
     * @param p_name Name of entry.
     * @param p_parent Name of entry's parent.
     * @param p_value Value to log.
     */
    public void addState(String p_name, String p_parent, Object p_value) {
        StateInfo state = new StateInfo(p_name, p_parent, p_value);
        m_stateList.put(p_name, state);
    }

    /**
     * Returns the timestamp associated with the StateGroup.
     * @return Timestamp associated with StateInfos.
     */
    public Date getTimestamp() {
        return m_timestamp;
    }

    /**
     * Returns the group's list of StateInfos.
     * @return Map of StateInfos and names.
     */
    public HashMap<String, StateInfo> getStateList() {
        return m_stateList;
    }

}
