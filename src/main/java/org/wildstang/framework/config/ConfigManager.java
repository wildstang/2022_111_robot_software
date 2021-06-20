package org.wildstang.framework.config;

import java.io.BufferedReader;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private static Logger s_log = Logger.getLogger(ConfigManager.class.getName());
    private static final String s_className = "ConfigManager";

    private boolean m_initialised = false;

    private Config m_config;

    /**
     * Create a new Config, belonging to the manager.
     */
    public void init() {
        s_log.entering(s_className, "init");

        if (!m_initialised) {
            m_config = new Config();
            m_initialised = true;
        }

        s_log.exiting(s_className, "init");
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
        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "loadConfig");
        }

        m_config.load(p_reader);

        if (s_log.isLoggable(Level.FINER)) {
            s_log.exiting(s_className, "load");
        }
    }

}
