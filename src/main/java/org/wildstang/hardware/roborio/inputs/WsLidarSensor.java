package org.wildstang.hardware.roborio.inputs;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Timer;

/**
 * Reads a LIDAR sensor.
 */
public class WsLidarSensor extends WsContinuousI2CInput {

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

    private Integer[] recordedDistances = new Integer[DISTANCE_HISTORY_LENGTH];

    /**
     * Construct the sensor.
     * @param name Descriptive name of the axis.
     * @param port I2C port the sensor is connected to.
     * @param p_address I2C address of the sensor.
     * @param updateInterval Interval at which to fetch a value from the sensor.
     */
    public WsLidarSensor(String name, Port port, int p_address, int updateInterval) {
        super(name, port, p_address, updateInterval);
    }

    /**
     * Returns a single integer value.
     * @return Int representation of latest sensor reading.
     */
    public int getIntValue() {
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
     * Reads and calculates the distance.
     */
    public void updateSensor() {
        i2c.write(LIDAR_CONFIG_REGISTER, INITIATE_MEASUREMENT);
        Timer.delay(MEASUREMENT_DELAY); // Delay for measurement to be taken
        i2c.read(LIDAR_DISTANCE_REGISTER, 2, currentValue); // Read in measurement
        Timer.delay(INTER_MEASUREMENT_DELAY); // Delay to prevent over polling

        // Store most recent results in a sliding window
        for (int i = 0; i < recordedDistances.length; i++) {
            if (i < recordedDistances.length - 1) {
                recordedDistances[i] = recordedDistances[i + 1];
            }
            else {
                // honestly this is a guess, previous version always had 0
                recordedDistances[i] = (int) currentValue[0];
            }
        }
    }
}
