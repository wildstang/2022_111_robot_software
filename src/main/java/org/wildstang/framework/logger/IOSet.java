package org.wildstang.framework.logger;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of IOInfo descriptions as a dictionary of sorts for inputs or outputs belonging to a subsystem or group.
 */
public class IOSet {

    private ArrayList<IOInfo> m_infoList = new ArrayList<>();

    /**
     * Creates a new IOInfo and adds it to the set.
     * @param p_name Specific ame of state.
     * @param p_type Type of IO, normally subsystem.
     * @param p_direction IO direction (Input/Output).
     * @param p_port IO port or null if not applicable.
     */
    public void addIOInfo(String p_name, String p_type, String p_direction, Object p_port) {
        IOInfo info = new IOInfo(p_name, p_type, p_direction, p_port);
        m_infoList.add(info);
    }

    /**
     * Returns the List of IOInfo.
     * @return List of IOInfos.
     */
    public List<IOInfo> getInfoList() {
        return new ArrayList<>(m_infoList);
    }

}
