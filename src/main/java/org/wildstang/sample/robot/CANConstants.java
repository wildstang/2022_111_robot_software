package org.wildstang.sample.robot;

/**
 * CAN Constants are stored here.
 * We primarily use CAN to communicate with Talon motor controllers.
 * These constants must correlate with the IDs set in Phoenix Tuner.
 * Official documentation can be found here:
 * https://phoenix-documentation.readthedocs.io/en/latest/ch08_BringUpCAN.html
 */
public final class CANConstants {

    public static final int   EXAMPLE_MOTOR_CONTROLLER      = 1;
    public static final int[] EXAMPLE_PAIRED_CONTROLLERS    = {2,3};
    
}