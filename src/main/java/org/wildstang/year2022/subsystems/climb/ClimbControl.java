package org.wildstang.year2022.subsystems.climb;

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
import org.wildstang.year2022.subsystems.swerve.DriveConstants;
import org.wildstang.year2022.subsystems.swerve.SwerveSignal;
import org.wildstang.year2022.subsystems.swerve.WSSwerveHelper;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.wildstang.year2022.subsystems.climb.ClimbMotion;

/** this class takes controller input and executes commands through ClimbMotion. Does not directly accsess motors. */

public class ClimbControl implements Subsystem {
    
    private ClimbMotion motion;

    private DigitalInput dpadUp;
    private DigitalInput dpadDown;
    private DigitalInput dpadRight;
    private DigitalInput dpadLeft;

    private DigitalInput select;
    private DigitalInput start;

    /* Controls:
    Dpad:
        up: press to toggle extended/retracted
        right: tilt
        left: tilt
        down: recenter (untilt)

    Press select and start at the same time to climb a bar. (so it must be pressed 3 time total to reach transversal semi-automatically.)

    */
    
    @Override
    public void init() {

        dpadUp = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_DPAD_UP);
        dpadUp.addInputListener(this);
        dpadDown = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_DPAD_DOWN);
        dpadDown.addInputListener(this);
        dpadLeft = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_DPAD_LEFT);
        dpadLeft.addInputListener(this);
        dpadRight = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_DPAD_RIGHT);
        dpadRight.addInputListener(this);


        select = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_SELECT);
        select.addInputListener(this);
        start = (DigitalInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_START);
        start.addInputListener(this);

        motion = new ClimbMotion();

        resetState();
    }

    @Override
    public void update() {
        motion.update(); //movement delegated to ClimbMotion
        
    }

    @Override
    public void inputUpdate(Input source) {
        if(select.getValue() && start.getValue()){//when start and select pressed, climb one bar
            motion.AutoClimb(); 
        }
        if(dpadUp.getValue() && dpadUp == source){ //up on dpad to toggle retracted/extended
            if(motion.IsExtended){
                motion.Retract();
            }
            else{
                motion.Extend();
            }
        }
        if(dpadRight.getValue() || dpadLeft.getValue()){ //right or left to tilt
            motion.Tilt();
        }
        if(dpadDown.getValue()){ //down to untilt/center
            motion.UnTilt();
        }
        if(dpadUp.getValue() || dpadRight.getValue() || dpadLeft.getValue() || dpadDown.getValue()){ //if manual commands, ensure not autoclimbing
            motion.StopAutoClimb();
        }
    }

    @Override
    public void selfTest() {

    }

    @Override
    public void resetState() {
        motion.resetState();
    }

    @Override
    public String getName() {
        return "Climb Control";
    }
}