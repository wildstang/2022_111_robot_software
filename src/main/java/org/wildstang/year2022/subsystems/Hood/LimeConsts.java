package org.wildstang.year2022.subsystems.Hood; 

public class LimeConsts{
;
    
    public double CAMERA_ANGLE_OFFSET = 40; //angular offset between ground and center of camera in degrees

    public double TARGET_HEIGHT = 104-32; //In inches, between limelight and top of target. Not the height of the target
    
    public double[] Dists = {0}; //dists from lowest to highest

    public double[] Angles = {0}; //correspnding hood angles

    public const double OVERSHOOT = 0; //in feet? b/c you want the ball in the center of the target, not the nearest edge.

    public const double SHOT_HEIGHT = 0; //in feet? max height of shot

    public const double FIRE_TIME = 1; //in seconds. Delay before firing.
    
    public const double GRAVITY = 32.17;
}