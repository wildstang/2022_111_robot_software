package org.wildstang.framework.io.outputs;

import org.wildstang.framework.CoreUtils;

/**
 * Core output functions.
 */
public abstract class Output {

    private String m_name = "Undefined";
    private boolean m_enabled = true;

    /**
     * Constructor sets the name of the Output.
     * @param p_name New name of the Output.
     */
    public Output(String p_name) {
        CoreUtils.checkNotNull(p_name, "p_name is null");

        m_name = p_name;
    }

    /**.
     * Sends that state to the hardware Output.
     */
    public void update() {
        sendDataToOutput();
    }

    /**
     * Returns the name of the Output.
     * @return Name of the Output.
     */
    public String getName() {
        return m_name;
    }

    /**
     * Abstract function to write the current value to hardware.
     */
    protected abstract void sendDataToOutput();

    /**
     * Produces a hashcode based off the Output's name.
     * @return Newly generated hashcode.
     */
    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    /**
     * Enables the Output, does nothing at the Input level.
     */
    public void enable() {
        m_enabled = true;
    }

    /**
     * Disables the Output, does nothing at the Input level.
     */
    public void disable() {
        m_enabled = false;
    }

    /**
     * Returns true if the Output is enabled, does nothing at this level.
     * @return True if the Output is enabled.
     */
    public boolean isEnabled() {
        return m_enabled;
    }

}
