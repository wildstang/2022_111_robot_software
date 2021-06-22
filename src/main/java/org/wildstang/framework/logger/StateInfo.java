package org.wildstang.framework.logger;

/**
 * Tracks a single key, value pair for logging a robot state.
 */
public class StateInfo {

    private String m_name;
    private String m_parent;
    private Object m_value;

    /**
     * Construct a new StateInfo log.
     * @param p_name Name of entry.
     * @param p_parent Name of entry's parent.
     * @param p_value Value of entry.
     */
    public StateInfo(String p_name, String p_parent, Object p_value) {
        m_name = p_name;
        m_parent = p_parent;
        m_value = p_value;
    }

    /**
     * Returns the name of StateInfo entry.
     * @return Name of entry.
     */
    public String getName() {
        return m_name;
    }

    /**
     * Returns the name of the entry's parent.
     * @return Name of the entry's parent.
     */
    public String getParent() {
        return m_parent;
    }

    /**
     * Returns the value logged for the entry.
     * @return Value logged for the entry.
     */
    public Object getValue() {
        return m_value;
    }

}
