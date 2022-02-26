package org.wildstang.year2022.subsystems.LimeLight; 

public class LimeConsts{

    public double DEADBAND = 0.01; //min speed for the robot to be treated as moving

    public double BALL_VELOCITY = 100; // ball shooting velocity in feet per second

    public double GRAVITY = 32; // magnitude of acceleration due to gravity in feet per second
    
    public double CAMERA_ANGLE_OFFSET = 15; //angular offset between ground and center of camera in degrees

    public double TARGET_HEIGHT = 6; //In feet, between limelight and top of target. Not the height of the target
    
    public double[] Dists = {0}; //dists from lowest to highest

    public double[] Angles = {0}; //correspnding hood angles

}