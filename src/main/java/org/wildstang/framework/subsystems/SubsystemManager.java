package org.wildstang.framework.subsystems;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.wildstang.framework.CoreUtils;
import org.wildstang.framework.core.Core;
import org.wildstang.framework.core.Subsystems;

/**
 * This class in the manager for all outputs.
 *
 * @author Steve
 */
public class SubsystemManager {

    private static Logger s_log = Logger.getLogger(SubsystemManager.class.getName());
    private static final String s_className = "SubsystemManager";

    private ArrayList<Subsystem> m_subsystems = new ArrayList<>();
    private boolean s_initialised = false;

    /**
     * Initialize all subsystems registered with the manager.
     */
    public void init() {
        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "init");
        }

        if (!s_initialised) {
            s_initialised = true;
        }

        for (Subsystem sub : m_subsystems) {
            if (s_log.isLoggable(Level.FINEST)) {
                s_log.finest("Initializing Subsystem: " + sub.getName());
            }

            // Init all subsystems.
            sub.init();
        }

        if (s_log.isLoggable(Level.FINER)) {
            s_log.exiting(s_className, "init");
        }
    }

    /**
     * Updates all subsystems registered with the manager.
     */
    public void update() {
        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "update");
        }

        // Iterate over all outputs and update each one
        for (Subsystem sub : m_subsystems) {
            if (s_log.isLoggable(Level.FINEST)) {
                s_log.finest("Updating Subsystem: " + sub.getName());
            }

            // Update the output - send value to output
            sub.update();
        }

        if (s_log.isLoggable(Level.FINER)) {
            s_log.exiting(s_className, "update");
        }
    }

    /**
     * Resets states of all subsystems registered with the manager.
     */
    public void resetState() {
        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "resetState");
        }

        // Iterate over all outputs and update each one
        for (Subsystem sub : m_subsystems) {
            if (s_log.isLoggable(Level.FINEST)) {
                s_log.finest("Resetting Subsystem: " + sub.getName());
            }

            // Update the output - send value to output
            sub.resetState();
        }

        if (s_log.isLoggable(Level.FINER)) {
            s_log.exiting(s_className, "resetState");
        }
    }

    /**
     * Registers a given subsystem with the manager.
     * @param p_subsystem Subsystem to register.
     */
    public void addSubsystem(Subsystem p_subsystem) {
        CoreUtils.checkNotNull(p_subsystem, "p_subsystem is null");

        if (!m_subsystems.contains(p_subsystem)) {
            m_subsystems.add(p_subsystem);
        }
    }

    /**
     * Deregisters a given subsystem from the manager.
     * @param p_subsystem Subsystem to deregister.
     */
    public void removeSubsystem(Subsystem p_subsystem) {
        CoreUtils.checkNotNull(p_subsystem, "p_subsystem is null");

        if (s_log.isLoggable(Level.WARNING)) {
            s_log.warning("Removing Subsystem " + p_subsystem.getName());
        }
        m_subsystems.remove(p_subsystem);
    }

    /**
     * Gets a given subsystem by name from the manager.
     * @param p_subsystem Name of subsystem to get.
     * @return The registered system requested.
     */
    public Subsystem getSubsystem(String p_name) {
        CoreUtils.checkNotNull(p_name, "p_name is null");

        Subsystem result = null;

        for (Subsystem sub : m_subsystems) {
            if (sub.getName().equals(p_name)) {
                result = sub;
            }
        }

        return result;
    }

    /**
     * Gets a given subsystem from the manager.
     * @param p_subsystem Subsystem to get.
     * @return The registered system requested.
     */
    public Subsystem getSubsystem(Subsystems desiredSubsystem) {
        return getSubsystem(desiredSubsystem.getName());
    }

    /**
     * Gets the liset of registered subsystems.
     * @return List of registered subsystems.
     */
    public List<Subsystem> getSubsystems() {
        return m_subsystems;
    }

    /**
     * Tests all subsystems registered with the manager.
     */
    public void selfTestAll() {
        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "selfTestAll");
        }

        // Turn off state tracking
        Core.getStateTracker().stopTrackingState();

        for (Subsystem s : m_subsystems) {
            s.selfTest();
        }

        // Turn back on state tracking
        Core.getStateTracker().startTrackingState();

        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "selfTestAll");
        }
    }

    /**
     * Deregisters all subsystems from the manager.
     */
    public void removeAll() {
        m_subsystems.clear();
    }

    /**
     * Gets the size of all subsystems registered with the manager.
     * @return Size of subsystems array.
     */
    public int size() {
        return m_subsystems.size();
    }
}
