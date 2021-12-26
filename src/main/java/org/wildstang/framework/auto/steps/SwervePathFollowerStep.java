package org.wildstang.framework.auto.steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.sample.robot.WSSubsystems;
import org.wildstang.sample.subsystems.swerve.SwerveDrive;

public class SwervePathFollowerStep extends AutoStep {

    private static final double ftToIn = 12;
    private static final int positionP = 7;
    private static final int velocityP = 8;
    private static final int headingP = 15;
    //dt, x, y, leftPos, leftVel, leftAcc, leftJer, centerPos, centerVel, centerAcc, centerJer, rightPos, rightVel, rightAcc, rightJer, heading
    //0   1  2    3          4       5        6          7          8         9          10        11         12       13        14       15

    private SwerveDrive m_drive;
    private double[][] pathData;

    private int counter;

    /** Sets the robot to track a new path
     * finishes after all values have been read to robot
     * @param pathData double[][] that contains path, should be from \frc\paths
     */
    public SwervePathFollowerStep(double[][] pathData) {
        this.pathData = pathData;
        m_drive = (SwerveDrive) Core.getSubsystemManager().getSubsystem(WSSubsystems.SWERVE_DRIVE.getName());
    }

    @Override
    public void initialize() {
        //start path
        counter = 0;
        m_drive.resetDriveEncoders();
    }

    @Override
    public void update() {
        if (counter >= pathData.length){
            //end path
            m_drive.stopMoving();
            setFinished(true);
        } else {
            //update values the robot is tracking to
            m_drive.setAutoValues(pathData[counter][positionP]*ftToIn, pathData[counter][velocityP]*ftToIn, Math.toDegrees(pathData[counter][headingP]));
            counter++;
        }
    }

    @Override
    public String toString() {
        return "Swerve Path Follower";
    }

}
