package org.wildstang.framework.io;

import java.util.List;


/**
 * Defines an input. Inputs allow Listeners to register for value changes.
 *
 * @author Steve
 *
 */
public interface Input {

    /**
     * Returns the name of the input.
     *
     * @return the name of the input
     */
    public String getName();

    /**
     * Adds a new listener to the list of registered listeners.
     *
     * @param listener
     *            the listener to add
     */
    public void addInputListener(InputListener listener);

    /**
     * Removes the listener from the list of registered listeners.
     *
     * @param listener
     *            the listener to remove
     */
    public void removeInputListener(InputListener listener);

    /**
     * Gets the listener from the list of registered listeners.
     *
     * 
     *           
     */
    public List<InputListener> getInputListeners();

    /**
     * Removes all listeners from the list of registered listeners.
     */
    public void removeAllListeners();

    public void update();

    public void enable();

    public void disable();

    public boolean isEnabled();
}