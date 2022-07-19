package org.wildstang.year2022.subsystems.swerve;

import com.ctre.phoenix.sensors.CANCoder;
import com.kauailabs.navx.frc.AHRS;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.framework.subsystems.swerve.SwerveDriveTemplate;
import org.wildstang.year2022.robot.CANConstants;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.Hood.AimHelper;
import org.wildstang.year2022.subsystems.swerve.DriveConstants;
import org.wildstang.year2022.subsystems.swerve.SwerveSignal;
import org.wildstang.year2022.subsystems.swerve.WSSwerveHelper;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;
import org.wildstang.sample.subsystems.drive.Drive.DriveState;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**Class: SwerveDrive
 * inputs: driver left joystick x/y, right joystick x, right trigger, right bumper, select, face buttons all, gyro
 * outputs: four swerveModule objects
 * description: controls a swerve drive for four swerveModules through autonomous and teleoperated control
 */
public class SwerveDrive extends SwerveDriveTemplate {

    private final SlewRateLimiter xSpeedLimiter = new SlewRateLimiter(DriveConstants.SLEW_RATE_LIMIT);
    private final SlewRateLimiter ySpeedLimiter = new SlewRateLimiter(DriveConstants.SLEW_RATE_LIMIT);
    //private final SlewRateLimiter rotSpeedLimiter = new SlewRateLimiter(DriveConstants.SLEW_RATE_LIMIT);

    private AnalogInput leftStickX;//translation joystick x
    private AnalogInput leftStickY;//translation joystick y
    private AnalogInput rightStickX;//rot joystick
    private AnalogInput rightTrigger;//thrust
    private AnalogInput leftTrigger;//intake, shoot while aiming
    private DigitalInput rightBumper;//aim
    private DigitalInput leftBumper;//robot centric intake
    private DigitalInput select;//gyro reset
    private DigitalInput start;
    private DigitalInput faceUp;//rotation lock 0 degrees
    private DigitalInput faceRight;//rotation lock 90 degrees
    private DigitalInput faceLeft;//rotation lock 270 degrees
    private DigitalInput faceDown;//rotation lock 180 degrees
    private DigitalInput dpadLeft;//defense mode

    private double xSpeed;
    private double ySpeed;
    private double rotSpeed;
    private double thrustValue;
    private boolean rotLocked;
    private boolean isFieldCentric;
    private double rotTarget;
    private double pathPos;
    private double pathVel;
    private double pathHeading;
    private double pathTarget;
    private double autoTravelled;
    private double autoTempX;
    private double autoTempY;

    private final AHRS gyro = new AHRS(SerialPort.Port.kUSB);
    public SwerveModule[] modules;
    private SwerveSignal swerveSignal;
    private WSSwerveHelper swerveHelper = new WSSwerveHelper();
    private AimHelper limelight;

    public enum driveType {TELEOP, AUTO, CROSS, LL};
    public driveType driveState;


    @Override
    public void inputUpdate(Input source) {
        //determine if we are in cross or teleop
        if (source == leftBumper && leftBumper.getValue()){
            resetDriveEncoders();
        }
        if (driveState != driveType.AUTO && leftBumper.getValue()){
            driveState = driveType.CROSS;
            this.swerveSignal = new SwerveSignal(new double[]{-modules[0].getPosition()*0.0025, -modules[1].getPosition()*0.01, -modules[2].getPosition()*0.01, -modules[3].getPosition()*0.01, }, swerveHelper.setCross().getAngles());
        } else if (driveState != driveType.AUTO){
            driveState = driveType.TELEOP;
        }
        //get x and y speeds
        //xSpeed = -xSpeedLimiter.calculate(-leftStickX.getValue());
        xSpeed = leftStickX.getValue();
        ySpeed = leftStickY.getValue();
        if (Math.abs(leftStickX.getValue()) < DriveConstants.DEADBAND) xSpeed = 0;
        //ySpeed = -ySpeedLimiter.calculate(-leftStickY.getValue());
        if (Math.abs(leftStickY.getValue()) < DriveConstants.DEADBAND) ySpeed = 0;
        
        if (source == select && select.getValue()) {
            gyro.reset();
            gyro.setAngleAdjustment(0);
        }
        thrustValue = 1 - DriveConstants.DRIVE_THRUST + DriveConstants.DRIVE_THRUST * Math.abs(rightTrigger.getValue());
        xSpeed *= thrustValue;
        ySpeed *= thrustValue;
        rotSpeed *= thrustValue;

        //update auto tracking values
        if (source == faceUp && faceUp.getValue()){
            if (faceLeft.getValue()){ rotTarget = 291.0;
            } else if (faceRight.getValue()){ rotTarget = 21.0;
            } else  rotTarget = 0.0;
            rotLocked = true;
        }
        if (source == faceLeft && faceLeft.getValue()){
            if (faceUp.getValue()){ rotTarget = 291.0;
            } else if (faceDown.getValue()){ rotTarget = 201.0;
            } else rotTarget = 270.0;
            rotLocked = true;
        }
        if (source == faceDown && faceDown.getValue()){
            if (faceLeft.getValue()){ rotTarget = 201.0;
            } else if (faceRight.getValue()){ rotTarget = 111.0;
            } else rotTarget = 180.0;
            rotLocked = true;
        }
        if (source == faceRight && faceRight.getValue()){
            if (faceUp.getValue()){ rotTarget = 21.0;
            } else if (faceDown.getValue()){ rotTarget = 111.0;
            } else rotTarget = 90.0;
            rotLocked = true;
        }
        if (start.getValue()){
            rotTarget = 191;
            rotLocked = true;
        }

        //get rotational joystick
        rotSpeed = rightStickX.getValue()*Math.abs(rightStickX.getValue());
        //rotSpeed = -rotSpeedLimiter.calculate(-rightStickX.getValue());
        if (Math.abs(rightStickX.getValue()) < DriveConstants.DEADBAND) rotSpeed = 0;
        //if the rotational joystick is being used, the robot should not be auto tracking heading
        if (rotSpeed != 0) rotLocked = false;
        if (rightBumper.getValue()){
            driveState = driveType.LL;
        } else {
            if (driveState == driveType.LL){
                driveState = driveType.TELEOP;
                rotLocked = false;
            }
        }
        isFieldCentric = !(Math.abs(leftTrigger.getValue())>0.15);
        if (!isFieldCentric) {
            rotTarget = 0;
            rotSpeed *= 0.25;
        }
    }
 
    @Override
    public void init() {
        // TODO Auto-generated method stub
        initInputs();
        initOutputs();
        resetState();
        gyro.enableLogging(true);
        gyro.reset();
    }

    public void initInputs(){
        leftStickX = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_JOYSTICK_X);
        leftStickX.addInputListener(this);
        leftStickY = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_JOYSTICK_Y);
        leftStickY.addInputListener(this);
        rightStickX = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_RIGHT_JOYSTICK_X);
        rightStickX.addInputListener(this);
        rightTrigger = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_RIGHT_TRIGGER);
        rightTrigger.addInputListener(this);
        leftTrigger = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_TRIGGER);
        leftTrigger.addInputListener(this);
        rightBumper = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_RIGHT_SHOULDER);
        rightBumper.addInputListener(this);
        leftBumper = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_SHOULDER);
        leftBumper.addInputListener(this);
        select = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_SELECT);
        select.addInputListener(this);
        start = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_START);
        start.addInputListener(this);
        faceUp = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_FACE_UP);
        faceUp.addInputListener(this);
        faceLeft = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_FACE_LEFT);
        faceLeft.addInputListener(this);
        faceRight = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_FACE_RIGHT);
        faceRight.addInputListener(this);
        faceDown = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_FACE_DOWN);
        faceDown.addInputListener(this);
        dpadLeft = (DigitalInput) Core.getInputManager().getInput(WSInputs.DRIVER_DPAD_LEFT);
        dpadLeft.addInputListener(this);
    }

    public void initOutputs(){
        //create four swerve modules
        modules = new SwerveModule[]{
            new SwerveModule((WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.DRIVE1), 
                (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.ANGLE1), new CANCoder(CANConstants.ENC1), DriveConstants.FRONT_LEFT_OFFSET),
            new SwerveModule((WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.DRIVE2), 
                (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.ANGLE2), new CANCoder(CANConstants.ENC2), DriveConstants.FRONT_RIGHT_OFFSET),
            new SwerveModule((WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.DRIVE3), 
                (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.ANGLE3), new CANCoder(CANConstants.ENC3), DriveConstants.REAR_LEFT_OFFSET),
            new SwerveModule((WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.DRIVE4), 
                (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.ANGLE4), new CANCoder(CANConstants.ENC4), DriveConstants.REAR_RIGHT_OFFSET)
        };
        //create default swerveSignal
        swerveSignal = new SwerveSignal(new double[]{0.0, 0.0, 0.0, 0.0}, new double[]{0.0, 0.0, 0.0, 0.0});
        limelight = (AimHelper) Core.getSubsystemManager().getSubsystem(WSSubsystems.LIMELIGHT);
    }
    
    @Override
    public void selfTest() {
        // TODO Auto-generated method stub
    }

    @Override
    public void update() {
        if (driveState == driveType.CROSS){
            //set to cross - done in inputupdate
            //this.swerveSignal = swerveHelper.setCross();
            drive();
        }
        if (driveState == driveType.TELEOP){
            if (rotLocked){
                //if rotation tracking, replace rotational joystick value with controller generated one
                rotSpeed = swerveHelper.getRotControl(rotTarget, getGyroAngle());
            }
            this.swerveSignal = swerveHelper.setDrive(xSpeed, ySpeed, rotSpeed, getGyroAngle());
            SmartDashboard.putNumber("FR signal", swerveSignal.getSpeed(0));
            drive();
        }
        if (driveState == driveType.AUTO){
            //get controller generated rotation value
            rotSpeed = Math.max(-0.2, Math.min(0.2, swerveHelper.getRotControl(pathTarget, getGyroAngle())));
            //ensure rotation is never more than 0.2 to prevent normalization of translation from occuring
            
            //update where the robot is, to determine error in path
            updateAutoDistance();
            this.swerveSignal = swerveHelper.setAuto(swerveHelper.getAutoPower(pathPos, pathVel, autoTravelled), pathHeading, rotSpeed, getGyroAngle());
            drive();        
        }
        if (driveState == driveType.LL){
            rotSpeed = -limelight.getRotPID();
            this.swerveSignal = swerveHelper.setDrive(xSpeed, ySpeed, rotSpeed, getGyroAngle());
            drive();
        }
        SmartDashboard.putNumber("Gyro Reading", getGyroAngle());
        SmartDashboard.putNumber("X speed", xSpeed);
        SmartDashboard.putNumber("Y speed", ySpeed);
        SmartDashboard.putNumber("rotSpeed", rotSpeed);
        SmartDashboard.putString("Drive mode", driveState.toString());
        SmartDashboard.putBoolean("rotLocked", rotLocked);
        SmartDashboard.putNumber("Auto position", pathPos);
        SmartDashboard.putNumber("Auto velocity", pathVel);
        SmartDashboard.putNumber("Auto translate direction", pathHeading);
        SmartDashboard.putNumber("Auto rotation target", pathTarget);
    }
    

    @Override
    public void resetState() {
        xSpeed = 0;
        ySpeed = 0;
        rotSpeed = 0;
        //gyro.reset();
        setToTeleop();
        rotLocked = false;
        rotTarget = 0.0;
        pathPos = 0.0;
        pathVel = 0.0;
        pathHeading = 0.0;
        pathTarget = 0.0;

        isFieldCentric = true;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Swerve Drive";
    }
    /** resets the drive encoders on each module */
    public void resetDriveEncoders(){
        for (int i = 0; i < modules.length; i++){
            modules[i].resetDriveEncoders();
        }
        //autoTravelled = 0.0;
    }
    /** sets the drive to teleop/cross, and sets drive motors to coast */
    public void setToTeleop(){
        driveState = driveType.TELEOP;
        for (int i = 0; i < modules.length; i++){
            modules[i].setDriveBrake(false);
        }
        rotSpeed = 0;
        xSpeed = 0;
        ySpeed = 0;
        pathHeading = 0;
        pathPos = 0;
        pathVel = 0;
        rotLocked = false;
    }
    /**sets the drive to autonomous */
    public void setToAuto(){
        driveState = driveType.AUTO;
        resetDriveEncoders();
    }
    /**drives the robot at the current swerveSignal, and displays information for each swerve module */
    private void drive(){
        if (driveState == driveType.CROSS){
            for (int i = 0; i < modules.length; i++){
                modules[i].runCross(swerveSignal.getSpeed(i), swerveSignal.getAngle(i));
                modules[i].displayNumbers(DriveConstants.POD_NAMES[i]);
            }
        } else {
            for (int i = 0; i < modules.length; i++){
                modules[i].run(swerveSignal.getSpeed(i), swerveSignal.getAngle(i));
                modules[i].displayNumbers(DriveConstants.POD_NAMES[i]);
            }
        }
    }
    /**sets autonomous values from the path data file */
    public void setAutoValues(double position, double velocity, double heading){
        pathPos = position;
        pathVel = velocity;
        pathHeading = heading;
    }
    /**sets the autonomous heading controller to a new target */
    public void setAutoHeading(double headingTarget){
        pathTarget = headingTarget;
    }
    public void setAiming(){
        driveState = driveType.LL;
    }
    /**updates distance travelled in autonomous, to determine error in path following */
    private void updateAutoDistance(){
        for (int i = 0; i < modules.length; i++){
            autoTempX += modules[i].getPosition() * Math.cos(Math.toRadians(modules[i].getAngle()));
            autoTempY += modules[i].getPosition() * Math.sin(Math.toRadians(modules[i].getAngle()));
            //System.out.println("Auto temp X and Y" + autoTempX + "And Y " + autoTempY);
        }
        autoTravelled += Math.abs(Math.hypot(autoTempX/modules.length, autoTempY/modules.length))/10000;
        autoTempX = 0;
        autoTempY = 0;
        resetDriveEncoders();
        //System.out.println("AutoTravelled: " + autoTravelled);
    }

    /**
     * Resets the gyro, and sets it the input number of degrees
     * Used for starting the match at a non-0 angle
     * @param degrees the current value the gyro should read
     */
    public void setGyro(double degrees){
        gyro.reset();
        resetState();
        setToAuto();
        gyro.setAngleAdjustment(degrees);
    }
    public double getGyroAngle(){
        if (!isFieldCentric) return 0;
        return (gyro.getAngle()+360)%360;
    } 
    public double[] getVelocity(){
        double[] out = {xSpeed,ySpeed};
        return out;
    }   
    public AHRS getGyro(){
        return gyro;
    }
}
