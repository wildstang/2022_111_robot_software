package org.wildstang.framework.pid.controller;

/**
 * Represents PID states
 * @author Nathan
 */
public enum PidStateType {

    PID_DISABLED_STATE("PID_DISABLED_STATE"),
    PID_INITIALIZE_STATE("PID_INITIALIZE_STATE"),
    PID_BELOW_TARGET_STATE("PID_BELOW_TARGET_STATE"),
    PID_ON_TARGET_STATE("PID_ON_TARGET_STATE"),
    PID_STABILIZED_STATE("PID_STABILIZED_STATE"),
    PID_ABOVE_TARGET_STATE("PID_ABOVE_TARGET_STATE");

    private String title;

    /**
     * Constructs with human readable name.
     * @param name Human readable name for state.
     */
    private PidStateType(String name) {
        this.title = name;
    }

    /**
     * Returns the name of the state.
     * @return Name of the state.
     */
    @Override
    public String toString() {
        return title;
    }
}
