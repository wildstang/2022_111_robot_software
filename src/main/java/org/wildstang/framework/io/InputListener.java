package org.wildstang.framework.io;

import org.wildstang.framework.io.inputs.Input;

/**
 * Interface for handling input updates. I.e. reacting to input changes.
 */
public interface InputListener {

    /**
     * Notifies the listener that an input event has occurred.
     *
     * @param source The Input that has updated.
     */
    public void inputUpdate(Input source);
}
