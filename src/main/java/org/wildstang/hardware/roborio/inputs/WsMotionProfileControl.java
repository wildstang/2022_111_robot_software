package org.wildstang.hardware.roborio.inputs;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.wildstang.framework.io.inputs.DigitalInput;

/**
 * Reads a motion profile.
 */
public class WsMotionProfileControl extends DigitalInput {

    private static Logger s_log = Logger.getLogger(DigitalInput.class.getName());
    private static final String s_className = "WsMotionProfileInput";
    private boolean profileEnabled = false;
    private boolean resetKinematics = false;

    /**
     * Constructs the motion profile.
     * @param p_name Descriptive name ofthe motion profile.
     */
    public WsMotionProfileControl(String p_name) {
        super(p_name);
        profileEnabled = false;
        resetKinematics = false;
    }

    /**
     * Motion profile does not return raw values.
     * @return False.
     */
    @Override
    protected boolean readRawValue() {
        return false;
    }

    /**
     * Motion profile does not read any data.
     */
    @Override
    public void readDataFromInput() { }

    /**
     * Enables the motion profile.
     * @param p_newValue True to enable profile.
     */
    public void setProfileEnabled(boolean p_newValue) {
        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "setProfileEnabled");
        }

        profileEnabled = p_newValue;

        logCurrentState();

        notifyListeners();

        if (s_log.isLoggable(Level.FINER)) {
            s_log.exiting(s_className, "setProfileEnabled");
        }
    }

    /**
     * Sets to kinematics to reset.
     * @param p_newValue True if kinematics should reset.
     */
    public void setResetKinematics(boolean p_newValue) {
        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "setResetKinematics");
        }

        resetKinematics = p_newValue;

        logCurrentState();

        notifyListeners();

        if (s_log.isLoggable(Level.FINER)) {
            s_log.exiting(s_className, "setResetKinematics");
        }
    }

    /**
     * Returns true if the motion profile is enabled.
     * @return True if the motion profile is enabled.
     */
    public boolean getProfileEnabled() {
        return profileEnabled;
    }

    /**
     * Returns true if the kinematics is set to reset.
     * @return True if the kinematics is set to reset.
     */
    public boolean getResetKinematics() {
        return resetKinematics;
    }
}
