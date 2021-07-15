package org.wildstang.hardware.roborio.inputs;

import java.util.TimerTask;

import edu.wpi.first.wpilibj.I2C.Port;

/**
 * Contiunously reads a sensor using I2C.
 */
public class WsContinuousI2CInput extends WsI2CInput {

    protected byte[] currentValue;
    private java.util.Timer updater;

    /**
     * Construct the hall effect sensor.
     * @param name Descriptive name of the sensor.
     * @param port Hardware port number the sensor is connected to.
     * @param address I2C address of the sensor.
     * @param updateInterval Number of milliseconds between updates.
     */
    public WsContinuousI2CInput(String name, Port port, int address, int updateInterval) {
        super(name, port, address);
        updater = new java.util.Timer();
        start(updateInterval);
    }

    /**
     * Reads the value from the sensor.
     * @return Raw value from the sensor.
     */
    @Override
    protected byte[] readRawValue() {
        // The selected sensor is set by the background thread update. Simply return the
        // last value we know of
        return currentValue;
    }

    /**
     * Returns a single integer value.
     * @return Int representation of latest sensor reading.
     */
    public int getIntValue() {
        return (int) currentValue[0];
    }

    /**
     * Reads a byte from the sensor and stores the value as the current reading.
     */
    private void updateSensor() {
        i2c.readOnly(currentValue, 1);
    }

    /**
     * Begin polling the sensor.
     * @param period Time in milliseconds between successive updates.
     */
    public void start(int period) {
        updater.scheduleAtFixedRate(new SensorUpdater(), 0, period);
    }

    /**
     * Stops sensor polling.
     */
    public void stop() {
        updater.cancel();
        updater = new java.util.Timer();
    }

    /**
     * Timer object for polling sensor.
     */
    private class SensorUpdater extends TimerTask {

        /**
         * Runs updates the sensor on each iteration.
         */
        @Override
        public void run() {
            updateSensor();
        }
    }

}
