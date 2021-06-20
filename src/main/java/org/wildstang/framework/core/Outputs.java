package org.wildstang.framework.core;

import org.wildstang.framework.hardware.OutputConfig;
import org.wildstang.framework.io.outputs.OutputType;

/**
 * Used in implementations to enumerate Outputs.
 * We would like to have a super class for this structure, however,
 * Java does not support enums extending classes.
 */
public interface Outputs {

    /**
     * Returns the name mapped to the Output.
     * @return Name mapped to the Output.
     */
    public String getName();

    /**
     * Returns the type of Output for the enumeration.
     * @return OutputType of enumeration.
     */
    public OutputType getType();

    /**
     * Returns the config of Output for the enumeration.
     * @return OutputConfig of enumeration.
     */
    public OutputConfig getConfig();

    /**
     * Returns true if the Logger should track the Output's state.
     * @return True if of tracking state.
     */
    public boolean isTrackingState();
}