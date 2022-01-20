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
    private WsSparkMax angle, windup; // motors


    private WsAnalogInput ManualTargetDistance; 

    private WsDigitalInput Launch; 

    private WsAnalogInput AngleChange;

    private WsDigitalInput ManualWindup; // if there is windup problem

    private WsDigitalInput AutoTarget;



    private SwerveDrive driveBase; // To turn base while auto aiming

    private double TargetDist;
    private double AimingAngle;

    private Boolean goForLaunch;

    private Boolean launchBool = false;
    private double time = 100;
    
    public void init(){
      windup = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.CAT_WIND);
      angle = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.CAT_ANGLE);

    Launch = (WsDigitalInput) Core.getOutputManager().getInput(WSInputs.MANIPULATOR_FACE_LEFT);
    Launch.addInputListener(this);
    AngleChange = (WsDigitalInput) Core.getOutputManager().getInput(WSInputs.MANIPULATOR_RIGHT_JOYSTICK_Y);
    AngleChange.addInputListener(this);

    resetState();
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
      
      launchBool = Launch.getValue();
      if (launchBool) {
          goForLaunch = true;
      }
  }
  
  
    public void update(){
      AimingAngle = Angle.getPosition();
      if (((AimingAngle < 200 && AngleChange.getValue() > 0) || (AimingAngle > 5 && AngleChange.getValue() <0)) && (AngleChange.getValue() > .1 || AngleChange.getValue() < -.1)) {
      Angle.setSpeed(AngleChange.getValue()*.1);
      } else {
        Angle.setSpeed(0);
      }
      if (goForLaunch) {
        Windup.setSpeed(1);
        time = time - 1;
      }
      if (time == 1) {
        goForLaunch = false;
        time = 100;
      }
    }

    /**
     * Can be called to reset any state variables. Can be used when changing modes
     * or reenabling system to reset to a default state without reinitialising
     * connected components.
     */
    //@Override
    public void resetState(){
      Angle.resetEncoder();
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

