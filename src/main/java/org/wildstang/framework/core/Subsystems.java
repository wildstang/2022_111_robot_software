package org.wildstang.framework.core;

/**
 * Used in implementations to enumerate Subsystems.
 * We would like to have a super class for this structure, however,
 * Java does not support enums extending classes.
 */
public interface Subsystems {

    /**
     * Returns the name mapped to the subsystem.
     * @return Name mapped to the subsystem.
     */
    public String getName();

    /**
     * Returns subsystem's class.
     * @return Subsystem's class.
     */
    public Class<?> getSubsystemClass();
}
