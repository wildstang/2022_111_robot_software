package org.wildstang.year2022.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.Input;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.year2022.robot.CANConstants;
import org.wildstang.year2022.robot.WSInputs;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//Based on 2021 C team code. Some comments and all imports persist from original.
// Uses talons to pivot. Is that really what we would use?

/**
 * Class:       RudementarySwerve.java
 * Inputs:      2 joysticks, 2 triggers
 * Outputs:     8 Talons (swerve drive)
 * Description: Basic swerve drive?
 **/
public class Drive implements Subsystem {
 
    // inputs
    private AnalogInput leftHorizontal;
    private AnalogInput rightHorizontal;

    private AnalogInput rightVertical;
    private AnalogInput leftVertical;

    private AnalogInput rightTrigger;
    private AnalogInput leftTrigger;
    //private AnalogInput gyro;

    // outputs
    private TalonSRX[] Drives = {null,null,null,null}; 
    private TalonSRX[] Swerves = {null,null,null,null};

    


    //Parameters
    public double MaxSpeed = 30; // ft per second
    public double DeadBand = 0.08;
    private double[] MotorRads = {0,0,0,0}; //coordinates of the motors, circular radi. In feet.
    private double[] MotorDegrees = {0,0,0,0}; //coordinates of the motors, circular degrees. In radians (sorry, probably will fix.) Counterclockwise from right side.
    public double EncoderTicks = 4096;
    public double AngularSensitivity = 0.1;
    public double DriveSensitivity = 0.1;
    //varibles
    private double[] PivotState = {0,0,0,0};

    private double[] DriveState = {0,0,0,0};
    // initializes the subsystem
    public void init() {
        
        Drives[0] = new TalonSRX(CANConstants.DRIVE_FRONT_LEFT);
        Drives[1] = new TalonSRX(CANConstants.DRIVE_FRONT_RIGHT);
        Drives[2] = new TalonSRX(CANConstants.DRIVE_BACK_LEFT);
        Drives[3] = new TalonSRX(CANConstants.DRIVE_BACK_RIGHT);
        
        Swerves[0] = new TalonSRX(CANConstants.PIVOT_FRONT_LEFT);
        Swerves[1] = new TalonSRX(CANConstants.PIVOT_FRONT_RIGHT);
        Swerves[2] = new TalonSRX(CANConstants.PIVOT_BACK_LEFT);
        Swerves[3] = new TalonSRX(CANConstants.PIVOT_BACK_RIGHT);



 
        // register button and attach input listener with WS Input
        rightVertical = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_RIGHT_JOYSTICK_Y.getName());
        rightVertical.addInputListener(this);

        leftVertical = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_JOYSTICK_Y.getName());
        leftVertical.addInputListener(this);


        rightHorizontal = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_RIGHT_JOYSTICK_X.getName());
        rightHorizontal.addInputListener(this);

        leftHorizontal = (AnalogInput) Core.getInputManager().getInput(WSInputs.DRIVER_LEFT_JOYSTICK_X.getName());
        leftHorizontal.addInputListener(this);
        

      
        resetState();
    }
 
    // update the subsystem everytime the framework updates (every ~0.02 seconds)
    public void update() {

        SetMotors(PivotState,DriveState);
        //SmartDashboard.putNumber("RightTrigger", testing);
      //  SmartDashboard.putNumber("left speed", leftSpeed);
       // SmartDashboard.putNumber("right speed", rightSpeed);
        //SmartDashboard.putNumber("gyro reading", gyro.getValue());
      ///  SmartDashboard.putNumber("left encoder",leftMotor.getSensorCollection().getQuadraturePosition());
       // SmartDashboard.putNumber("right encoder",rightMotor.getSensorCollection().getQuadraturePosition());
    
    }

    
    // respond to input updates
    public void inputUpdate(Input signal) {

        double PivotAngle = (AngularSensitivity*rightHorizontal.getValue())+(Math.PI/2); //anglular change relitive to front of bot
        double[] VelocityTarget = {leftHorizontal.getValue()*DriveSensitivity,leftVertical.getValue()*DriveSensitivity};
        
        double[][] target = CalculateMotorValues(PivotAngle,VelocityTarget); //calculate stuff.

        DriveState = target[0]; //unpack arrays from matrix.
        PivotState = target[1];



    }
 
    public void SetMotors(double[] Pivot, double[] Drive){
        int c = 0;
        while(c<4){
            Drives[c].set(ControlMode.PercentOutput,Drive[c]);
            Swerves[c].set(ControlMode.Position,Pivot[c]);
            c += 1;
        }
    }
    public double[][] CalculateMotorValues(double RadiansPerSecond,double[] Velocity){ //Degrees are counterclockwise in radians, velocity is ft/sec?
        double[] DriveOut = {0,0,0,0};
        double[] PivotOut = {0,0,0,0};

        int c = 0;
        while(c<4){
            double ArcLength = RadiansPerSecond*MotorRads[c]; //magnitude of tangential velocity, or arc length in a second
            double[] VTangential = {ArcLength*Math.cos(MotorDegrees[c]),ArcLength*Math.sin(MotorDegrees[c])}; //components of V tangential
            double[] V = {Velocity[0]+VTangential[0],Velocity[1]+VTangential[1]}; //components of total velocity
            double Magnitude = Math.sqrt(Math.pow(V[0],2)+Math.pow(V[1],2)); //magnitude of velocity
            double Angle = 0;
            if(Magnitude != 0){
                Angle = Math.acos(V[0]/Magnitude); 
                if(V[1] < 0){ //if below x axis... correct the angle and magnitude (arccos is only between 0 and pi, but angles go up to 2pi)
                    // graph to understand
                    Magnitude = -1*Magnitude;
                    if(V[0]>0){ //right of y axis
                        Angle += Math.PI/2;
                    }
                    if(V[0]<0){  //left of y axis
                         
                        Angle -= Math.PI/2;
                    }
                }
            }

            DriveOut[c] = Magnitude/MaxSpeed; //percent output
            PivotOut[c] = (Angle/(2*Math.PI))*EncoderTicks; //pivot target

            c += 1;
        }
        double[][] out = {DriveOut,PivotOut};
        return out;

    }
    // used for testing
    public void selfTest() {}
 
    // resets all variables to the default state
    public void resetState() {

        ResetEncoders();
    }

    public void ResetEncoders(){
        ResetSwerveEncoders();
        ResetPivotEncoders();
    }
    public void ResetSwerveEncoders(){
        int c = 0;
        while(c<4){
            Swerves[c].getSensorCollection().setQuadraturePosition(0,0);
            c += 1;
        }
    }
    public void ResetPivotEncoders(){
        int c = 0;
        while(c<4){
            Drives[c].getSensorCollection().setQuadraturePosition(0,0);
            c += 1;
        }
    }
    public double[] GetSwerveEncoders(){
        double[] output = {0,0,0,0};
        int c = 0;
        while(c<4){
            output[c] = Swerves[c].getSensorCollection().getQuadraturePosition();
            c += 1;
        }
        return output;
    }
    public double[] GetDriveEncoders(){
        double[] output = {0,0,0,0};
        int c = 0;
        while(c<4){
            output[c] = Drives[c].getSensorCollection().getQuadraturePosition();
            c += 1;
        }
        return output;
    }

    public double GetGyroValue(){
        //return gyro.getValue();
        return 0;
    }


    // returns the unique name of the example
    public String getName() {
        return "Drive";
    }
}
 
