package org.wildstang.framework.pid.input;

/**
 * Interface for wrapping an Input to PID.
 */
public interface IPidInput {

    /**
     * Gets a value from an Input.
     * @return Value read from an Input.
     */
    public double pidRead();
}
