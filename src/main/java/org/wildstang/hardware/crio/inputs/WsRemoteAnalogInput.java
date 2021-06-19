package org.wildstang.hardware.crio.inputs;

import org.wildstang.framework.io.inputs.AnalogInput;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 *
 */
public class WsRemoteAnalogInput extends AnalogInput {

    NetworkTable remoteIOTable;

    public WsRemoteAnalogInput(String p_name, String p_networkTbl) {
        super(p_name);
        remoteIOTable = NetworkTableInstance.getDefault().getTable(p_networkTbl);
    }

    @Override
    public double readRawValue() {
        return remoteIOTable.getEntry(getName()).getDouble(0);
    }

}