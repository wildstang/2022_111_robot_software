package org.wildstang.year2022.subsystems.drive;

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

public class PlungerMain {

    /**
     * Initialise the subsystem. Performs any required setup work.
     */
  //  @Override
    private WsSparkMax punch, angle; // motors


    private WsAnalogInput ManualTargetDistance; 

    private WsDigitalInput Launch; 

    private WsDigitalInput ManualWindup; // if there is windup problem

    private WsDigitalInput AutoTarget;



    private SwerveDrive driveBase; // To turn base while auto aiming

    private double TargetDist;
    private double AimingAngle;

    private Boolean IsLoaded;
    private Boolean IsFiring;

    
    public void init(){
      Latch = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.CAT_LATCH); //vars not avalable until we decide
      Windup = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.CAT_WIND);
      Angle = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.CAT_ANGLE);
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
      
      if(Launch.getValue() && IsLoaded){
        IsLoaded = false;
        IsFiring = true;
      }
      else if (ManualWindup.getValue){

      }

  }
  
  
    public void update(){

    }

    /**
     * Can be called to reset any state variables. Can be used when changing modes
     * or reenabling system to reset to a default state without reinitialising
     * connected components.
     */
    //@Override
    public void resetState(){

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

