package org.wildstang.hardware.roborio.inputs;

import org.wildstang.framework.io.inputs.DigitalInput;

/**
 * Reads a motion profile.
 */
public class WsMotionProfileControl extends DigitalInput {

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
        profileEnabled = p_newValue;

        notifyListeners();
    }

    /**
     * Sets to kinematics to reset.
     * @param p_newValue True if kinematics should reset.
     */
    public void setResetKinematics(boolean p_newValue) {
        resetKinematics = p_newValue;

        notifyListeners();
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
