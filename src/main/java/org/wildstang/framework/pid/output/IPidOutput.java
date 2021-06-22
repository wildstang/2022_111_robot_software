package org.wildstang.framework.pid.output;

/**
 * Interface for wrapping an Output to PID.
 * @author Nathan
 */
public interface IPidOutput {

    /**
     * Writes a value to an Output.
     * @param output Value to write to Output.
     */
    public void pidWrite(double output);
}
