package org.wildstang.framework.core;

import org.wildstang.framework.hardware.InputConfig;
import org.wildstang.framework.io.inputs.InputType;

/**
 * Used in implementations to enumerate Inputs.
 * We would like to have a super class for this structure, however,
 * Java does not support enums extending classes.
 */
public interface Inputs {

    /**
     * Returns the name mapped to the Input.
     * @return Name mapped to the Input.
     */
    public String getName();

    /**
     * Returns the type of Input for the enumeration.
     * @return InputType of enumeration.
     */
    public InputType getType();

    /**
     * Returns the config of Input for the enumeration.
     * @return InputConfig of enumeration.
     */
    public InputConfig getConfig();

    /**
     * Returns true if the Logger should track the Input's state.
     * @return True if of tracking state.
     */
    public boolean isTrackingState();
}