package org.wildstang.framework.hardware;

/**
 * This interface could be deprecated as it provides no functionality
 * other than categoriztion. However, being able to distinguish both
 * OutputConfigs and InputConfigs can prevent at least the wrong type
 * of Config from being used in Outputs and Inputs implementations,
 * where we can't be specific enough to confirm the correct Config
 * is paired with the matching Input/Output.
 */
public interface OutputConfig {}
