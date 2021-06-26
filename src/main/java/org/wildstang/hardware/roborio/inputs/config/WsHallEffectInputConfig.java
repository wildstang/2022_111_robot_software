package org.wildstang.hardware.roborio.inputs.config;

import edu.wpi.first.wpilibj.I2C.Port;

/**
 * Wrapper class to identify hall effect sensors separate from I2C inputs.
 */
public class WsHallEffectInputConfig extends WsI2CInputConfig {

    /**
     * Wrapper constructor.
     * @param p_port    I2C hardware port number.
     * @param p_address I2C address, normally static by device.
     */
    public WsHallEffectInputConfig(Port p_port, int p_address) {
        super(p_port, p_address);
    }
}