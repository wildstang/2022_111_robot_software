package org.wildstang.hardware.roborio.inputs;

import java.util.TimerTask;

import org.wildstang.framework.io.inputs.DiscreteInput;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

/**
 * Reads a hall effect sensor using I2C.
 * TODO: this class should probably extend I2C
 */
public class WsHallEffectInput extends DiscreteInput {
    private static final int defaultUpdateInterval = 20;

    I2C i2c;

    private int lastHallEffectSensor = -1;
    private int selectedHallEffectSensor = -1;

    private java.util.Timer updater;

    private Object lock = new Object();

    /**
     * Construct the hall effect sensor.
     * @param name Descriptive name of the sensor.
     * @param port Hardware port number the sensor is connected to.
     * @param address I2C address of the sensor.
     */
    public WsHallEffectInput(String name, Port port, int address) {
        super(name);

        i2c = new I2C(port, address);

        updater = new java.util.Timer();

        // Update at 50Hz
        start(defaultUpdateInterval);
    }

    /**
     * Reads the value from the hall effect sensor.
     * @return Raw value from the sensor.
     */
    @Override
    protected int readRawValue() {
        // The selected sensor is set by the background thread update. Simply return the
        // last value we know of
        return selectedHallEffectSensor;
    }

    /**
     * Reads a byte from the sensor and stores the value as the current reading.
     */
    private void updateSensor() {
        byte[] buffer = new byte[1];
        i2c.readOnly(buffer, 1);
        try {
            synchronized (lock) {
                selectedHallEffectSensor = buffer[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        lastHallEffectSensor = selectedHallEffectSensor;
    }

    /**
     * Begin polling the hall effect sensor.
     * @param period Time in milliseconds between successive updates.
     */
    public void start(int period) {
        updater.scheduleAtFixedRate(new HallEffectUpdater(), 0, period);
    }

    /**
     * Timer object for polling sensor.
     */
    private class HallEffectUpdater extends TimerTask {

        /**
         * Runs updateSensor().
         */
        @Override
        public void run() {
            updateSensor();
        }
    }

}
