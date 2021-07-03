package org.wildstang.hardware.roborio.inputs;

import java.util.TimerTask;

import org.wildstang.framework.io.inputs.DiscreteInput;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Timer;

/**
 * Reads a LIDAR sensor.
 * TODO: this should probably extend I2C.
 */
public class WsLidarSensor extends DiscreteInput {

    /* Code to write to config register to begin measuring */
    private static final int INITIATE_MEASUREMENT = 0x04;
    /* Delay to wait for measurement data to come in */
    private static final double MEASUREMENT_DELAY = 0.04;
    /* Delay to wait between measurements to avoid over-polling the LIDAR */
    private static final double INTER_MEASUREMENT_DELAY = 0.005;
    /* Number of samples to keep */
    private static final int DISTANCE_HISTORY_LENGTH = 6;

    private static final int LIDAR_CONFIG_REGISTER = 0x00;
    private static final int LIDAR_DISTANCE_REGISTER = 0x8f;

    private I2C i2c;
    private byte[] distance;
    private java.util.Timer updater;
    private Integer[] recordedDistances = new Integer[DISTANCE_HISTORY_LENGTH];

    private final int LIDAR_ADDR;

    /**
     * Construct the sensor.
     * @param name Descriptive name of the axis.
     * @param port I2C port the sensor is connected to.
     * @param p_address I2C address of the sensor.
     */
    public WsLidarSensor(String name, Port port, int p_address) {
        super(name);

        LIDAR_ADDR = p_address;
        i2c = new I2C(port, LIDAR_ADDR);

        distance = new byte[2];

        updater = new java.util.Timer();
        start();
    }

    /**
     * Reads the raw value from the sensor.
     * @return sensor value.
     */
    @Override
    protected int readRawValue() {
        return getSmoothedDistance();
    }

    /**
     * Returns 0.
     * @return 0.
     */
    public int getDistance() {
        return 0; // (int) Integer.toUnsignedLong(distance[0] << 8) +
                  // Byte.toUnsignedInt(distance[1]);
    }

    /**
     * Returns the average of the last 5 readings.
     * @return The rolling (5) average of the read distance.
     */
    public int getSmoothedDistance() {
        int accumulator = 0;
        int numValidElements = 0;
        for (int i = 0; i < recordedDistances.length; i++) {
            if (recordedDistances[i] != null) {
                accumulator += recordedDistances[i].intValue();
                numValidElements++;
            }
        }

        // Avoid divide by zero errors
        if (numValidElements > 0) {
            return accumulator / numValidElements;
        } else {
            return 0;
        }
    }

    /**
     * Returns 0, from getDistance().
     * @return 0.
     */
    public double pidGet() {
        return getDistance();
    }

    /**
     * Begins 10 Hz polling of the sensor.
     */
    public void start() {
        updater.scheduleAtFixedRate(new LIDARUpdater(), 0, 100);
    }

    /**
     * Begins polling for a given period.
     * @param period Polling period in milliseconds.
     */
    public void start(int period) {
        updater.scheduleAtFixedRate(new LIDARUpdater(), 0, period);
    }

    /**
     * Stops sensor polling.
     */
    public void stop() {
        updater.cancel();
        updater = new java.util.Timer();
    }

    /**
     * Reads and calculates the distance.
     */
    public void update() {
        i2c.write(LIDAR_CONFIG_REGISTER, INITIATE_MEASUREMENT);
        Timer.delay(MEASUREMENT_DELAY); // Delay for measurement to be taken
        i2c.read(LIDAR_DISTANCE_REGISTER, 2, distance); // Read in measurement
        Timer.delay(INTER_MEASUREMENT_DELAY); // Delay to prevent over polling

        // Store most recent results in a sliding window
        for (int i = 0; i < recordedDistances.length; i++) {
            if (i < recordedDistances.length - 1) {
                recordedDistances[i] = recordedDistances[i + 1];
            } else {
                recordedDistances[i] = Integer.valueOf(getDistance());
            }
        }
    }

    /**
     * Timer to poll the sensor every 10 ms.
     */
    private class LIDARUpdater extends TimerTask {

        /**
         * Runnable run() that runs update() every 10 ms.
         */
        @Override
        public void run() {
            while (true) {
                update();
                try {
                    // Why do we sleep here and also delay on line 84? Suspicious... FIXME
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
