package org.wildstang.hardware.roborio.inputs;

import org.wildstang.framework.io.inputs.I2CInput;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

/**
 * Reads an I2C input.
 */
public class WsI2CInput extends I2CInput {

    private I2C i2c;

    /**
     * Construct the I2C input.
     * @param name Descriptive name of the input.
     * @param port Hardware I2C input used.
     * @param p_address Device I2C address.
     */
    public WsI2CInput(String name, Port port, int p_address) {
        super(name);

        i2c = new I2C(port, p_address);
    }

    /**
     * Reads the raw value from the I2C input.
     * @return Raw input value.
     */
    @Override
    protected byte[] readRawValue() {
        byte rcvBytes[] = new byte[1];

        i2c.readOnly(rcvBytes, 1);

        return rcvBytes;// data;
    }
}
