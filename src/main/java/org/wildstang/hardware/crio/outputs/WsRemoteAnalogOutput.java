package org.wildstang.hardware.crio.outputs;

import org.wildstang.framework.io.outputs.AnalogOutput;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 *
 */
public class WsRemoteAnalogOutput extends AnalogOutput {

    NetworkTable remoteIOTable;

    public WsRemoteAnalogOutput(String name, String p_networkTbl, double p_default) {
        super(name, p_default);
        remoteIOTable = NetworkTableInstance.getDefault().getTable(p_networkTbl);
        System.out.println("Got Table");
    }

    public void notifyConfigChange() {
        // Nothing to update here, since the config value only affect the
        // start state.
    }

    @Override
    protected void sendDataToOutput() {
        remoteIOTable.getEntry(getName()).forceSetDouble(getValue());
    }

}