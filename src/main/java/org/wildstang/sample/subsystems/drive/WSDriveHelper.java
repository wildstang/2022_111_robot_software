package org.wildstang.sample.subsystems.drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WSDriveHelper {

    public static final double DEADBAND = 0.05;
    private DriveSignal driveSignal = new DriveSignal(0, 0);

    public DriveSignal teleopDrive(double throttle, double heading) {

        heading = handleDeadband(heading, DEADBAND);
        throttle = handleDeadband(throttle, DEADBAND);

        double rightPwm = throttle - heading;
        double leftPwm = throttle + heading;

        if (Math.abs(leftPwm) > 1.0){
            leftPwm /= Math.abs(leftPwm);
            rightPwm -= Math.abs(leftPwm) - 1.0;
        } else if (Math.abs(rightPwm) > 1.0){
            rightPwm /= Math.abs(rightPwm);
            leftPwm -= Math.abs(rightPwm) - 1.0;
        }

        driveSignal.rightMotor = rightPwm;
        driveSignal.leftMotor = leftPwm;

        SmartDashboard.putNumber("Left Drive PWM", leftPwm);
        SmartDashboard.putNumber("Right Drive PWM", rightPwm);

        return driveSignal;
    }

    public double handleDeadband(double val, double deadband) {
        return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
    }

    public static double limit(double v, double limit) {
        return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
    }
}
