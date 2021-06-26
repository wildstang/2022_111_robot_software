package org.wildstang.hardware.roborio.inputs.config;

import edu.wpi.first.wpilibj.I2C.Port;

/**
 * Wrapper class to identify LIDAR sensors separate from I2C inputs.
 */
public class WsLidarInputConfig extends WsI2CInputConfig {

    /**
     * Wrapper constructor.
     * @param p_port    I2C hardware port number.
     * @param p_address I2C address, normally static by device.
     */
    public WsLidarInputConfig(Port p_port, int p_address) {
        super(p_port, p_address);
    }
}