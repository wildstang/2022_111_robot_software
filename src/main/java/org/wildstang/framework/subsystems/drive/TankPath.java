package org.wildstang.framework.subsystems.drive;

/**
 * A path implementation which represents tank drive.
 * It contains 2 sets of trajectorys, one for each side of the drivebase.
 */
public class TankPath implements Path {

    private Trajectory m_left;
    private Trajectory m_right;

    /**
     * Returns the left side Trajectory.
     * @return Trajectory for left motors.
     */
    public Trajectory getLeft() {
        return m_left;
    }

    /**
     * Sets the left side Trajectory.
     * @param p_left New Trajectory for left motors.
     */
    public void setLeft(Trajectory p_left) {
        m_left = p_left;
    }

    /**
     * Returns the right side Trajectory.
     * @return Trajectory for right motors.
     */
    public Trajectory getRight() {
        return m_right;
    }

    /**
     * Sets the right side Trajectory.
     * @param p_right New Trajectory for right motors.
     */
    public void setRight(Trajectory p_right) {
        m_right = p_right;
    }

}