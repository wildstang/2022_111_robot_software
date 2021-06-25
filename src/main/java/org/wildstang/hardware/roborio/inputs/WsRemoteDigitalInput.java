package org.wildstang.hardware.roborio.inputs;

import org.wildstang.framework.io.inputs.DigitalInput;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Reads a remote digital input.
 */
public class WsRemoteDigitalInput extends DigitalInput {

    NetworkTable remoteIOTable;

    /**
     * Construct the remote input.
     * @param p_name Name of the input.
     * @param p_networkTbl Network table name.
     */
    public WsRemoteDigitalInput(String p_name, String p_networkTbl) {
        super(p_name);
        remoteIOTable = NetworkTableInstance.getDefault().getTable(p_networkTbl);
    }

    /**
     * Reads the value from the remote digital input.
     * @return Raw value from input.
     */
    @Override
    public boolean readRawValue() {
        return remoteIOTable.getEntry(getName()).getBoolean(false);
    }
}