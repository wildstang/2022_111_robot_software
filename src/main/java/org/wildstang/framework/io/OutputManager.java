package org.wildstang.framework.io;

import java.util.HashMap;
import java.util.NoSuchElementException;

import org.wildstang.framework.CoreUtils;
import org.wildstang.framework.core.Outputs;
import org.wildstang.framework.io.outputs.Output;
import org.wildstang.framework.logger.Log;

/**
 * Contains and updates all Outputs.
 *
 * @author Steve
 *
 */
public class OutputManager {

    private HashMap<String, Output> m_outputs = new HashMap<>();
    private boolean s_initialised = false;

    /**
     * Initializes logger.
     */
    public void init() {
        if (!s_initialised) {
            s_initialised = true;
        }
    }

    /**
     * Updates all managed outputs.
     */
    public void update() {
        // Iterate over all outputs and update each one
        for (Output out : m_outputs.values()) {
            if (out.isEnabled()) {
                // Update the output - send value to output
                out.update();
            }
        }
    }

    /**
     * Adds an Output to the manager.
     * @param p_output Output object to add.
     */
    public void addOutput(Output p_output) {
        CoreUtils.checkNotNull(p_output, "p_output is null");

        if (!m_outputs.containsKey(p_output.getName())) {
            m_outputs.put(p_output.getName(), p_output);
        }
    }

    /**
     * Removes an Output to the manager.
     * @param p_output Output object to remove.
     */
    public void removeOutput(Output p_output) {
        CoreUtils.checkNotNull(p_output, "p_output is null");
        Log.info("Removing output " + p_output.getName());
        m_outputs.remove(p_output.getName());
    }

    /**
     * Returns an Output with a matching name from the manager.
     * @param p_output Output to fetch from the manager.
     * @return Output belonging to the manager.
     */
    public Output getOutput(Outputs p_output) {
        return getOutput(p_output.getName());
    }

    /**
     * Returns a named Output from the manager.
     * @param p_name Name of the Output to fetch.
     * @return Output belonging to the manager.
     */
    public Output getOutput(String p_name) {
        CoreUtils.checkNotNull(p_name, "p_name is null");
        Output output = null;

        if (!m_outputs.containsKey(p_name)) {
            throw new NoSuchElementException("No output with name '" + p_name + "' in OutputManager");
        }
        output = m_outputs.get(p_name);

        return output;
    }

    /**
     * Determines if the manager has an Output with a given name.
     * @param p_name Name of an Output to search for.
     * @return True if an Output exists with p_name.
     */
    public boolean contains(String p_name) {
        CoreUtils.checkNotNull(p_name, "p_name is null");
        return m_outputs.containsKey(p_name);
    }

    /**
     * Returns the total size of all managed outputs.
     * @return Size of all managed outputs.
     */
    public int size() {
        return m_outputs.size();
    }

    /**
     * Removes all Outputs from the manager.
     */
    public void removeAll() {
        m_outputs.clear();
    }

    /**
     * Produces a HashMap of all managed Outputs.
     * @return HashMap of all managed Outputs mapped to their names.
     */
    public HashMap<String, Output> getHashMap() {
        return new HashMap<>(m_outputs);
    }
}
