package org.wildstang.framework.subsystems.drive;

import java.util.ArrayList;

import com.ctre.phoenix.motion.TrajectoryPoint;

/**
 * Represents a list of CTRE TrajectoryPoints for a cumulative drive path.
 */
public class Trajectory {

    // this array is indexed as [time][3]
    // each row is [rotation, velocity, time]
    private ArrayList<TrajectoryPoint> m_points;

    /**
     * Set the TrajectoryPoints.
     * @param p_points ArrayList of TrajectoryPoints.
     */
    public void setTalonPoints(ArrayList<TrajectoryPoint> p_points) {
        m_points = p_points;
    }

    /**
     * Returns the TrajectoryPoints.
     * @return ArrayList of TrajectoryPoints.
     */
    public ArrayList<TrajectoryPoint> getTalonPoints() {
        return m_points;
    }

}