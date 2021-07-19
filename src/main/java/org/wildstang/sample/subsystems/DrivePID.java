package org.wildstang.sample.subsystems;

import org.wildstang.framework.pid.PIDConstants;

/**
 * Used for configuring drive PID constants.
 * Note: only four slots are available on the Talon.
 */
public enum DrivePID {
    
    PATH(0, new PIDConstants(0.0652,1.00,0.0,0.0)),
    BASE_LOCK(1, new PIDConstants(0.0, .8, 0.001, 10)),
    MM_QUICK(2, new PIDConstants(0.55, .8, 0.001, 10)),
    MM_DRIVE(3, new PIDConstants(0.0, .2, 0.001, 2));

    private PIDConstants k;
    private int slot;

    DrivePID(int slot, PIDConstants pid) {
        this.slot = slot;
        this.k = pid;
    }

    public PIDConstants getConstants() {
        return k;
    }

    public int getSlot() {
        return slot;
    }
}