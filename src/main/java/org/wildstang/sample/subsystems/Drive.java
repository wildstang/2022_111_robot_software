package org.wildstang.sample.subsystems;

import com.ctre.phoenix.motion.MotionProfileStatus;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.logger.Log;
import org.wildstang.framework.subsystems.drive.Path;
import org.wildstang.framework.subsystems.drive.PathFollowingDrive;
import org.wildstang.framework.subsystems.drive.TankPath;
import org.wildstang.hardware.roborio.outputs.WsPhoenix;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;
import org.wildstang.sample.robot.WSOutputs;

import edu.wpi.first.wpilibj.Notifier;

public class Drive extends PathFollowingDrive {

    WsSparkMax left;
    WsSparkMax right;

    @Override
    public void init() {
        left = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.LEFT_DRIVE);
        right = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.RIGHT_DRIVE);
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
    
    @Override
    public void startPathFollower() {
    }
    
    @Override
    public void stopPathFollower() {
    }

    @Override
    public void updatePathFollower(double[] trajectoryInfo) {
        //do stuff here
    }

}