package org.wildstang.framework.auto.steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.framework.subsystems.swerve.SwerveDriveTemplate;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwervePathFollowerStep extends AutoStep {

    private static final double ftToIn = 12;
    private static final int positionP = 7;
    private static final int velocityP = 8;
    private static final int headingP = 15;
    //dt, x, y, leftPos, leftVel, leftAcc, leftJer, centerPos, centerVel, centerAcc, centerJer, rightPos, rightVel, rightAcc, rightJer, heading
    //0   1  2    3          4       5        6          7          8         9          10        11         12       13        14       15

    private SwerveDriveTemplate m_drive;
    private double[][] pathData;

    private int counter;
    private double lastPos;

    /** Sets the robot to track a new path
     * finishes after all values have been read to robot
     * @param pathData double[][] that contains path, should be from \frc\paths
     * @param drive the swerveDrive subsystem
     */
    public SwervePathFollowerStep(double[][] pathData, SwerveDriveTemplate drive) {
        this.pathData = pathData;
        m_drive = drive;
    }

    @Override
    public void initialize() {
        //start path
        counter = 0;
        lastPos = 0;
        m_drive.resetDriveEncoders();
        m_drive.setToAuto();
    }

    @Override
    public void update() {
        SmartDashboard.putNumber("Auto Time", counter*0.02);
        SmartDashboard.putNumber("Auto Attempted position", pathData[counter][positionP]);
        if (counter >= pathData.length || lastPos == pathData[counter][positionP]){
            //end path
            m_drive.setAutoValues(pathData[counter-1][positionP]*ftToIn, 0, -Math.toDegrees(pathData[counter-1][headingP]));
            setFinished(true);
        } else {
            //update values the robot is tracking to
            m_drive.setAutoValues(pathData[counter][positionP]*ftToIn, (pathData[counter][positionP] - lastPos)/0.02*ftToIn, -Math.toDegrees(pathData[counter][headingP]));
            lastPos = pathData[counter][positionP];
            counter++;
        }
    }

    @Override
    public String toString() {
        return "Swerve Path Follower";
    }

}
