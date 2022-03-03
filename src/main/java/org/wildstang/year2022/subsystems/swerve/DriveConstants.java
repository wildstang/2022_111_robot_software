package org.wildstang.year2022.subsystems.swerve;

public final class DriveConstants {

    /** robot length from swerve pod to swerve pod, in inches */
    public static final double ROBOT_LENGTH = 11.5;
    /** robot width from swerve pod to swerve pod, in inches */
    public static final double ROBOT_WIDTH = 11.5;
    /**speed with which the robot rotates relative to drive speed */
    public static final double ROTATION_SPEED = 1.0;
    /**drive motor gear ratio */
    public static final double DRIVE_RATIO = 6.86;
    /**angle motor gear ratio */
    public static final double ANGLE_RATIO = 12.8;
    /**diameter of drive wheel, in inches */
    public static final double WHEEL_DIAMETER = 4.0;
    /**offset of module 1, the front left module, in degrees */
    public static final double FRONT_LEFT_OFFSET = -44.64;//-280.98;
    /**offset of module 2, the front right module, in degrees */
    public static final double FRONT_RIGHT_OFFSET = -139.91;//-313.59;
    /**offset of module 3, the rear left module, in degrees */
    public static final double REAR_LEFT_OFFSET = -16.61;//-199.95;
    /**offset of module 4, the rear right module, in degrees */
    public static final double REAR_RIGHT_OFFSET = -284.19;//-52.03;
    /**deadband of the controller's joysticks */
    public static final double DEADBAND = 0.1;
    /**factor of thrust for the drive trigger */
    public static final double DRIVE_THRUST = 0.4;
    /**slew rate limiter rates of limit for the drive
    *  value is max change per second
    *  i.e. 2.0 means it can go from 0 to 1.0 in 0.5 seconds
    */
    public static final double SLEW_RATE_LIMIT = 3.0;
    /**encoder ticks per revolution, 1.0 for neos */
    public static final double TICKS_PER_REV = 1.0;
    /**PID values for drive P */
    public static final double DRIVE_P = 0.0;//0.02
    /**PID values for drive I */
    public static final double DRIVE_I = 0.01;
    /**PID values for drive D */
    public static final double DRIVE_D = 0.1;
    /**PID values for driveF coefficient of momentum */
    public static final double DRIVE_F_V = 0.006;//0.00581 on old treads
    /**PID values for drive F coefficient of kinetic friction */
    public static final double DRIVE_F_K = 0.016;
    /**PID values for drive F coefficient of inertia */
    public static final double DRIVE_F_I = 0.0;
    /**PID values for angle P */
    public static final double ANGLE_P = 0.5;
    /**PID values for angle I */
    public static final double ANGLE_I = 0.0;
    /**PID values for angle D */
    public static final double ANGLE_D = 0.0;
    /**Drive motor current limit */
    public static final int DRIVE_CURRENT_LIMIT = 50;
    /**Angle motor current limit */
    public static final int ANGLE_CURRENT_LIMIT = 30;
    /**Swerve Module Names */
    public static final String[] POD_NAMES = new String[]{"FL", "FR", "BL", "BR"};

}
