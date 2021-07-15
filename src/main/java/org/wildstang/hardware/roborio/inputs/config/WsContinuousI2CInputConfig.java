package org.wildstang.hardware.roborio.inputs.config;

import edu.wpi.first.wpilibj.I2C.Port;

/**
 * Wrapper class to identify hall effect sensors separate from I2C inputs.
 */
public class WsContinuousI2CInputConfig extends WsI2CInputConfig {

    private static final int defaultUpdateInterval = 20;

    private int updateInterval;

    /**
     * Construct the hall effect sensor config with the default update interval (20 ms).
     * @param port Hardware port number the sensor is connected to.
     * @param address I2C address of the sensor.
     */
    public WsContinuousI2CInputConfig(Port port, int address) {
        this(port, address, defaultUpdateInterval);
    }

    /**
     * Construct the hall effect sensor config.
     * @param port Hardware port number the sensor is connected to.
     * @param address I2C address of the sensor.
     * @param updateInterval Number of milliseconds between updates.
     */
    public WsContinuousI2CInputConfig(Port port, int address, int updateInterval) {
        super(port, address);
        this.updateInterval = updateInterval;
    }

    /**
     * Return the update interval in ms.
     * @return The update interval in ms.
     */
    public int getUpdateInterval() {
        return updateInterval;
    }

    /**
     * Builds a JSON String describing the I2C input config.
     * @return Channel number, address, and update interval.
     */
    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("{\"port\": \"");
        buf.append(getPort());
        buf.append("\",\"address\": ");
        buf.append(Integer.toHexString(getAddress()));
        buf.append("\",\"interval\": ");
        buf.append(updateInterval);
        buf.append("}");
        return buf.toString();
    }
}