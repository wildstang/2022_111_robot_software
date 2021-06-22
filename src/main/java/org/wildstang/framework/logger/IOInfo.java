package org.wildstang.framework.logger;

/**
 * Stores a description of an available input/output that may be logged with StateInfo.
 */
public class IOInfo {

    private String m_name;
    private String m_type;
    private String m_direction;
    private Object m_port;

    /**
     * Constructs an IOInfo entry for describing an IO state.
     * @param p_name Specific name of state.
     * @param p_type Type of IO, normally subsystem.
     * @param p_direction IO direction (Input/Output).
     * @param p_port IO port or null if not applicable.
     */
    public IOInfo(String p_name, String p_type, String p_direction, Object p_port) {
        m_name = p_name;
        m_type = p_type;
        m_direction = p_direction;
        m_port = p_port;
    }

    /**
     * Returns the name of the state.
     * @return Name of the state.
     */
    public String getName() {
        return m_name;
    }

    /**
     * Returns the type of IO for the state.
     * @return Type of IO.
     */
    public String getType() {
        return m_type;
    }

    /**
     * Returns the port for the state's IO.
     * @return Port of IO.
     */
    public Object getPort() {
        return m_port;
    }

    /**
     * Returns the IO direction (input/output).
     * @return Direction of IO.
     */
    public String getDirection() {
        return m_direction;
    }

}
