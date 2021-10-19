package org.wildstang.sample.subsystems;

import com.ctre.phoenix.motion.MotionProfileStatus;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.logger.Log;
import org.wildstang.framework.subsystems.drive.Path;
import org.wildstang.framework.subsystems.drive.PathFollowingDrive;
import org.wildstang.framework.subsystems.drive.TankPath;
import org.wildstang.hardware.roborio.outputs.WsPhoenix;
import org.wildstang.sample.robot.WSOutputs;

import edu.wpi.first.wpilibj.Notifier;

public class Drive extends PathFollowingDrive {

    WsPhoenix left;
    WsPhoenix right;

    @Override
    public void init() {
        left = (WsPhoenix) Core.getOutputManager().getOutput(WSOutputs.LEFT_DRIVE);
        right = (WsPhoenix) Core.getOutputManager().getOutput(WSOutputs.RIGHT_DRIVE);
    }

    @Override
    public void selfTest() {
        // TODO Auto-generated method stub

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resetState() {
        // TODO Auto-generated method stub

    }

    @Override
    public String getName() {
        return "Drive";
    }

    @Override
    public void inputUpdate(Input source) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setBrakeMode(boolean brake) {
        if (brake) {
            left.setBrake();
            right.setBrake();
        }
        else {
            left.setCoast();
            right.setCoast();
        }
    }

    @Override
    public void resetEncoders() {
        left.resetEncoder();
        right.resetEncoder();
    }

    // @Override
    // public void initPathFollower(Path path) {
    //     // do not initialize if the path is not a TankPath
    //     if (!(path instanceof TankPath)) {
    //         Log.error("Invalid Path, Drive requires TankPath");
    //         return;
    //     }
    //     TankPath tPath = (TankPath) path;
        
    //     left.setProfile(DrivePID.PATH.getSlot());
    //     right.setProfile(DrivePID.PATH.getSlot());

    //     left.setMotionControlFramePeriod(20);
    //     right.setMotionControlFramePeriod(20);

    //     left.fillProfile(tPath.getLeft().getTalonPoints());
    //     right.fillProfile(tPath.getRight().getTalonPoints());

    //     profileNotifier = new Notifier(new PeriodicRunnable());
    // }
    
    @Override
    public void startPathFollower() {
        left.setProfile(DrivePID.PATH.getSlot());
        right.setProfile(DrivePID.PATH.getSlot());
    }
    
    @Override
    public void stopPathFollower() {
        left.enableProfile(false);
        right.enableProfile(false);
    }

    @Override
    public void updatePathFollower(double[] trajectoryInfo) {
        //do stuff here
    }

    // class PeriodicRunnable implements java.lang.Runnable {
    //     @Override
    //     public void run() {
    //         left.updateProfile();
    //         right.updateProfile();
    //     }
    // }
}