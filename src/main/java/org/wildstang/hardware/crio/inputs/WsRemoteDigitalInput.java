package org.wildstang.hardware.crio.inputs;

import org.wildstang.framework.io.inputs.DigitalInput;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class WsRemoteDigitalInput extends DigitalInput {

    NetworkTable remoteIOTable;

    public WsRemoteDigitalInput(String p_name, String p_networkTbl) {
        super(p_name);
        remoteIOTable = NetworkTableInstance.getDefault().getTable(p_networkTbl);

    }

    @Override
    public boolean readRawValue() {
        return remoteIOTable.getEntry(getName()).getBoolean(false);
    }
}