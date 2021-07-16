package org.wildstang.framework.config;

import java.io.BufferedReader;

/**
 * This class is the public interface to accessing and working with the
 * configuration.
 *
 * This class is responsible for reading the configuration from the file into
 * the Config object, and providing access to the Config object.
 *
 * This class is a singleton.
 * 
 * Note: This class previously handled ConfigListeners but they were removed
 * because they were only implemented once and weren't actually used.
 *
 * @author Steve
 *
 */
public class ConfigManager {

    private boolean m_initialised = false;

    private Config m_config;

    /**
     * Create a new Config, belonging to the manager.
     */
    public void init() {
        if (!m_initialised) {
            m_config = new Config();
            m_initialised = true;
        }
    }

    /**
     * Returns the manager's Config.
     * @return The manager's Config if initialized, otherwise null.
     */
    public Config getConfig() {
        return m_config;
    }

    /**
     * Loads in a Config from file, using a BufferedReader.
     * @param p_reader BufferedReader opened on a file.
     */
    public void loadConfig(BufferedReader p_reader) {
        m_config.load(p_reader);
    }

}
