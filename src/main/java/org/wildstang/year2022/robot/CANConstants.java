package org.wildstang.year2022.robot;

/**
 * CAN Constants are stored here.
 * We primarily use CAN to communicate with Talon motor controllers.
 * These constants must correlate with the IDs set in Phoenix Tuner.
 * Official documentation can be found here:
 * https://phoenix-documentation.readthedocs.io/en/latest/ch08_BringUpCAN.html
 */
public final class CANConstants {

    // Replace these examples.
    // While not independently dangerous if implemented these could have unintended effects.
    public static final int[] EXAMPLE_PAIRED_CONTROLLERS    = {1,2};
    public static final int   EXAMPLE_MOTOR_CONTROLLER      = 3;

    //swerve constants
    public static final int ENC1 = 31;
    public static final int ENC2 = 32;
    public static final int ENC3 = 33;
    public static final int ENC4 = 34;
    public static final int DRIVE1 = 10;
    public static final int ANGLE1 = 11;
    public static final int DRIVE2 = 12;
    public static final int ANGLE2 = 13;
    public static final int DRIVE3 = 14;
    public static final int ANGLE3 = 15;
    public static final int DRIVE4 = 16;
    public static final int ANGLE4 = 17;

    public static final int INTAKE = 20;
    public static final int FEED = 21;
    public static final int KICKER = 22;
    public static final int SHOOTER = 23;
    public static final int SHOOTER_FOLLOWER = 24;
    public static final int HOOD = 25;
    public static final int CLIMBER = 26;
    public static final int CLIMBER_FOLLOWER = 27;
    
}