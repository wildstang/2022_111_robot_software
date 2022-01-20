package org.wildstang.year2022.subsystems.Catapault;

import org.wildstang.framework.subsystems.Subsystem;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.kauailabs.navx.frc.AHRS;

import org.wildstang.framework.core.Core;
import com.revrobotics.CANSparkMax;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.logger.Log;
import org.wildstang.framework.pid.PIDConstants;
import org.wildstang.framework.subsystems.drive.Path;
import org.wildstang.framework.subsystems.drive.PathFollowingDrive;
import org.wildstang.framework.subsystems.drive.TankPath;
import org.wildstang.hardware.roborio.inputs.WsAnalogInput;
import org.wildstang.hardware.roborio.inputs.WsDigitalInput;
import org.wildstang.hardware.roborio.inputs.WsJoystickButton;
import org.wildstang.hardware.roborio.outputs.WsPhoenix;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.I2C;

import org.wildstang.year2022.subsystems.swerve.SwerveDrive;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class CatapaultMain {

    /**
     * Initialise the subsystem. Performs any required setup work.
     */
  //  @Override
    private WsSparkMax Latch, Windup, Angle; // motors


    private WsAnalogInput ManualTargetDistance; 

    private WsDigitalInput Launch; 

    private WsDigitalInput ManualWindup; // if there is windup problem

    private WsDigitalInput AutoTarget;



    private SwerveDrive driveBase; // To turn base while auto aiming

    private double TargetDist;
    private double AimingAngle;

    private Boolean IsLoaded;
    private Boolean IsFiring;


    private double MAXDIST = 100; // in meters??

    private double DEADBAND = 0.05;
    
    private double LATCHED = 0; //latched position in encoder ticks
    
    private double UNLATCHED = 1024; 
    
    private double WIND_SPEED = 0.3; // percent power to run windup motor at
    
    private double WOUND = 1000; // wound position in non-modal encoder ticks. 
    private double UNWOUND = 0;
    
    private double windupPosition; 

    

    // For Limelight:
    private NetworkTable LimeTable;
    private NetworkTableEntry ty;
    private NetworkTableEntry tx;
    private NetworkTableEntry thor; // t horizontal
    private NetworkTableEntry tvert;
    
    
    private double CameraViewAngle = Math.PI/4; //fixthis
    private double CAMMAXY = 100; //fixthis
    private double TARGHEIGHTABOVECAMERA = 10; //fixthis
    private double CAMABOVECAT = 0.25; //fix

    private double TICKS = 4096; //ticks per rotation for catapault

    public void init(){


      Latch = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.CAT_LATCH); //vars not avalable until we decide
      Windup = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.CAT_WIND);
      Angle = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.CAT_ANGLE);
      

      ManualTargetDistance = (WsAnalogInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_LEFT_JOYSTICK_Y);
      ManualTargetDistance.addInputListener(this);
      
      Launch = (WsDigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_FACE_DOWN);
      Launch.addInputListener(this);

      ManualWindup = (WsDigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_FACE_UP);
      ManualWindup.addInputListener(this);
      
      //swerve
      driveBase = new SwerveDrive();


      //limelight
      LimeTable  = NetworkTableInstance.getDefault().getTable("limelight-stang");

      ty = LimeTable.getEntry("ty");
      tx = LimeTable.getEntry("tx");
      thor = LimeTable.getEntry("thor");
      tvert = LimeTable.getEntry("tvert");



    }

    /**
     * Performs a self test of the subsystem. 
     */
    //@Override
    public void selfTest(){

    }

    /**
     * Called to cause the subsystem to update its state and set new values on
     * outputs.
     *
     * Sending values to the hardware outputs is done outside of this method by the
     * framework.
     */
    //@Override
    public void inputUpdate(Input source) {
    
      // double inputValue = inputName.getValue();
        
      // handle input
      if(Launch.getValue() && IsLoaded){
        IsLoaded = false;
        IsFiring = true;
      }
      else if (ManualWindup.getValue()){
        IsLoaded = false;
        IsFiring = false;
      }
      if (Math.abs(ManualTargetDistance.getValue())>= DEADBAND){
        TargetDist = ManualTargetDistance.getValue()*MAXDIST;
      }
      else{
          if (AutoTarget.getValue()){
            double TargetAngle = Math.asin(ty.getDouble(0)/CAMMAXY);
            TargetDist = TARGHEIGHTABOVECAMERA/Math.tan(TargetAngle);
            AimingAngle = (TICKS/Math.PI)*Math.atan((TARGHEIGHTABOVECAMERA-CAMABOVECAT)/TargetDist);
             //Set automatic target dist here.
          }          
      }
        
        
      // non- input based state changes
      if(!IsLoaded &&! IsFiring){
          if(windupPosition >= WOUND){
              IsLoaded = true;
          }
      }
      if(IsFiring){
          if(windupPosition <= UNWOUND){ //once catapault unwinds, isfiring is no longer true.
              windupPosition = UNWOUND;
              IsFiring = false;
          }
          
      }
        

  }
  
  
    public void update(){
        if(!IsLoaded){
            Latch.setPosition(UNLATCHED);
        }
        else{
            Latch.setPosition(LATCHED);
        }
        if(!IsLoaded && !IsFiring){
            Windup.setSpeed(WIND_SPEED);
        }
        else{
            Windup.setSpeed(0);
        }
        
        Angle.setPosition(AimingAngle);
        
        windupPosition += Windup.getPosition(); // keep track of ticks the motor has gone.
        Windup.resetEncoder();
      
    }

    /**
     * Can be called to reset any state variables. Can be used when changing modes
     * or reenabling system to reset to a default state without reinitialising
     * connected components.
     */
    //@Override
    public void resetState(){
        IsLoaded = true;
        IsFiring = false;
        AimingAngle = 0;
        TargetDist = 0; // arbitrary value
        Angle.resetEncoder();
        Windup.resetEncoder();
        Latch.resetEncoder();
        windupPosition = WOUND;
    }

    /**
     * Returns the name of the subsystem.
     *
     * @return the name of the subsystem
     */
   // @Override
    public String getName(){
      return "Cat?";
    }

}

