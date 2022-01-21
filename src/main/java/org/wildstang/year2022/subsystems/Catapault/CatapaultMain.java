package org.wildstang.year2022.subsystems.Catapault; 
// ton of imports
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


    private WsAnalogInput ManualTargetDistance; //joystick or trigger for maual aiming by distance

    private WsDigitalInput Launch; //launch button

    private WsDigitalInput ManualWindup; // button to click if there is windup problem

    private WsDigitalInput AutoTarget; //hold to auto-aim



    private SwerveDrive driveBase; // To turn base while auto aiming

    private double TargetDist;
    private double AimingAngle;

    private Boolean IsLoaded; //to keep track of current state. could use enum
    private Boolean IsFiring;


    private double MAXDIST = 100; // in meters??

    private double DEADBAND = 0.05; //for the manual targeting.
    
    private double LATCHED = 0; //latched position in encoder ticks
    
    private double UNLATCHED = 1024; //unlatched position in encoder ticks
    
    private double WIND_SPEED = 0.3; // percent power to run windup motor at
    
    private double WOUND = 1000; // wound position in non-modal encoder ticks. 
    private double UNWOUND = 0;
    
    private double windupPosition; // to keep track of windup motor's position

    

    // For Limelight:
    private NetworkTable LimeTable; //Stolen from 2020 code
    private NetworkTableEntry ty; //y position of target in camera veiw
    private NetworkTableEntry tx; //x position
    private NetworkTableEntry thor; // t horizontal (i think its the target width. didn't end up using it)
    //also the god of Thursdays 
    private NetworkTableEntry tvert; // t vertical
    
    
    private double CameraViewAngle = Math.PI/4; //fixthis. The fov of the camera in radians.
    private double CAMMAXY = 100; //fixthis.  Number of vertical divisions in limelight y output
    private double TARGHEIGHTABOVECAMERA = 10; //fixthis. Height between camera and targer
    private double CAMABOVECAT = 0.25; //fix. Catapault is actually above camera, so name is backwards.

    private double TICKS = 4096; //ticks per rotation for catapault

    public void init(){


      Latch = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.CAT_LATCH); //vars not avalable until we decide
      Windup = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.CAT_WIND); //init sparkmaxes
      Angle = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.CAT_ANGLE);
      
       //init inputs
      ManualTargetDistance = (WsAnalogInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_LEFT_JOYSTICK_Y); 
      ManualTargetDistance.addInputListener(this);
      
      Launch = (WsDigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_FACE_DOWN);
      Launch.addInputListener(this);

      ManualWindup = (WsDigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_FACE_UP);
      ManualWindup.addInputListener(this);
      
      //swerve. so that auto-targeting can override drive base commands for horizontal aiming. Currently not implemented.
      driveBase = new SwerveDrive();


      //limelight. Code stolen from 2020
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
      if(Launch.getValue() && IsLoaded){ //if you click launch, and not catapault is loaded
        IsLoaded = false;
        IsFiring = true;
      }
      else if (ManualWindup.getValue()){ //if you click the manual windup button, go to reloading state (which is neither loaded nor firing)
        IsLoaded = false;
        IsFiring = false;
      }
      if (Math.abs(ManualTargetDistance.getValue())>= DEADBAND){ //for manual distance input
        TargetDist = ManualTargetDistance.getValue()*MAXDIST;
          //TODO: calculate angle (copy/paste from autotarget section)
      }
      else{
          if (AutoTarget.getValue()){ // auto-targeting code. I screwed up and forgot the physics bit, so right now it just acts as though the ball travels in a straigt line. Will fix.
            double TargetAngle = Math.asin(ty.getDouble(0)/CAMMAXY); // angle between camera and target. Currently assumes camera is pointed straight forward, but if there is offset it should be added here.
            TargetDist = TARGHEIGHTABOVECAMERA/Math.tan(TargetAngle); //distance between robot and target
            AimingAngle = (TICKS/Math.PI)*Math.atan((TARGHEIGHTABOVECAMERA-CAMABOVECAT)/TargetDist); //angle between catapault and target
          //TODO: physics
              //Set automatic target dist here.
          }          
      }
        
        
      // non- input based state changes
      if(!IsLoaded &&! IsFiring){  // check if catapault has finished winding and change state accordingly
          if(windupPosition >= WOUND){
              IsLoaded = true;
          }
      }
      if(IsFiring){
          if(windupPosition <= UNWOUND){ //once catapault finishes unwinding, isfiring is no longer true.
              windupPosition = UNWOUND;
              IsFiring = false;
          }
          
      }
        

  }
  
  
    public void update(){
        if(!IsLoaded){ // if not (supposed to be) loaded, unlatch catapault
            Latch.setPosition(UNLATCHED);
        }
        else{ //otherwise, keep it latched
            Latch.setPosition(LATCHED);
        }
        if(!IsLoaded && !IsFiring){ //if not loaded or firing (so is reloading), run the windup motor
            Windup.setSpeed(WIND_SPEED);
        }
        else{ //otherwise don't
            Windup.setSpeed(0);
        }
        
        Angle.setPosition(AimingAngle); //set angle control to proper position
        if(WindupPosition < (TICKS/2)){ //otherwise it probably moved backwards. Value may need tuning if catapault launches to fast.
            windupPosition += Windup.getPosition(); // keep track of ticks the motor has gone since last encoder reset
        }
        else{
            windupPosition -= Windup.getPosition();
        }
        
        Windup.resetEncoder(); //reset encoder, so next Windup.getPosition() will return change in position since this frame
      
    }

    /**
     * Can be called to reset any state variables. Can be used when changing modes
     * or reenabling system to reset to a default state without reinitialising
     * connected components.
     */
    //@Override
    public void resetState(){ //assume the catapault starts properly loaded
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

