package org.wildstang.framework.auto.steps;

import java.io.File;

import org.wildstang.framework.CoreUtils;
import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.logger.Log;
import org.wildstang.framework.subsystems.drive.TankPath;
import org.wildstang.framework.subsystems.drive.PathFollowingDrive;
import org.wildstang.framework.subsystems.drive.Trajectory;

import edu.wpi.first.wpilibj.Filesystem;

public class PathFollowerStep extends AutoStep {

    private String filePath;
    private TankPath path;
    private PathFollowingDrive drive;
    private boolean isForwards;
    private boolean isLeft;
    private double ticksPerInchMod;

    public PathFollowerStep(String p_path, PathFollowingDrive drive, boolean isLeft, boolean isForwards, double ticksPerInchMod) {
        filePath = Filesystem.getDeployDirectory().toString() + "/paths/" + p_path;
        this.drive = drive;
        this.isForwards = !isForwards;
        this.isLeft = isLeft;
    }

    @Override
    public void initialize() {
        path = new TankPath();
        
        File leftFile = new File(filePath + "_left.csv");
        File rightFile = new File(filePath + "_right.csv");
        if (!isForwards || !isLeft) { // TODO In the event of flipped robot movement, switch which file each side reads
            File holder = rightFile;
            rightFile = leftFile;
            leftFile = holder;
        } 

        Trajectory leftTrajectory;
        Trajectory rightTrajectory;
        leftTrajectory = CoreUtils.readTrajectory(leftFile, isForwards, ticksPerInchMod);
        rightTrajectory = CoreUtils.readTrajectory(rightFile, isForwards, ticksPerInchMod);
        path.setLeft(leftTrajectory);
        path.setRight(rightTrajectory);

        drive.setBrakeMode(true);
        drive.resetEncoders();

        drive.initPathFollower(path, isForwards);
        drive.startPathFollower();
    }

    @Override
    public void update() {
        // keep running the PathFollower is active and not e-stopped
        if (!drive.isEstopActive()) {
            drive.updatePathFollower();
        }
        // otherwise cleanup and finish
        else {
            drive.stopPathFollower();
            setFinished(true);
        }
    }

    @Override
    public String toString() {
        return "Path Follower";
    }
    
}