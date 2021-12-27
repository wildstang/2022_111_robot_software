package org.wildstang.framework.auto.steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.framework.subsystems.swerve.SwerveDriveTemplate;
import org.wildstang.sample.robot.WSSubsystems;
import org.wildstang.sample.subsystems.swerve.SwerveDrive;

public class PathHeadingStep extends AutoStep {

    private SwerveDriveTemplate m_drive;
    private double heading;

    /** sets the robot in auto to face a certain direction, and stay facing that way
     * finishes automatically, just passes a value
     * @param heading field-centric value robot will align towards, in bearing degrees
     */
    public PathHeadingStep(double heading, SwerveDriveTemplate drive) {
        this.heading = heading;
        m_drive = drive;
    }

    @Override
    public void initialize() {
        //give robot heading controller a new value
        m_drive.setAutoHeading(heading);
    }

    @Override
    public void update() {
        setFinished(true);
    }

    @Override
    public String toString() {
        return "Swerve Path Heading";
    }

}
