package org.wildstang.year2022.subsystems.launcher;

import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.hardware.roborio.inputs.WsAnalogInput;
import org.wildstang.hardware.roborio.outputs.WsSolenoid;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;

import edu.wpi.first.wpilibj.Timer;


public final class Launcher implements Subsystem{

    private AnalogInput fireTrigger;

    private WsSparkMax leftFlywheelMotor, rightFlywheelMotor, kickerMotor;

    private WsSolenoid ballLatch;

    private String state;

    //timer
    private Timer timeTracker;

    //speeds
    private double speed, kickerSpeed;

    private double desiredVelocity;
    //the desired velocity of a motor. Will have a value identifier later

    private final double maxSpeed = 1;
    //This is the max speed a motor can go without burning out.

    @Override
    public void init() {
        leftFlywheelMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.LAUNCHER_LEFT);
        rightFlywheelMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.LAUNCHER_RIGHT);
        kickerMotor = (WsSparkMax) Core.getOutputManager().getOutput(WSOutputs.KICKER);

        ballLatch = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.LAUNCHER_SOLENOID);

     fireTrigger = (WsAnalogInput) Core.getInputManager().getInput(WSInputs.MANIPULATOR_RIGHT_TRIGGER);
        resetState();
    }

    @Override
    public void update(){
        if (state == "goodToGo"){
            leftFlywheelMotor.setValue(maxSpeed);
            rightFlywheelMotor.setValue(maxSpeed);
                //we will uncomment whichever we are using backwards later
            timeTracker.start();
            if (leftFlywheelMotor.getVelocity() >= desiredVelocity && rightFlywheelMotor.getVelocity() >= desiredVelocity){
                ballLatch.setValue(false);
            }
            //the ballLatch will return to true when the trigger is released
        }
    }

    @Override
    public void inputUpdate(Input source) {
        if (source == fireTrigger){
            if (fireTrigger.getValue() >= 0.5){
                state = "goodToGo";
                //the ball is ready to be shot
            }else{
                resetState();
            }
        }else{
            resetState();
        }
    }

    @Override
    public void selfTest() {
       
    }

    @Override
    public void resetState() {
        state = "void";
            //resets state to an unidentifieable string
        speed = 0.0;
        kickerSpeed = maxSpeed;
        ballLatch.setValue(true);
        kickerMotor.setValue(kickerSpeed);
    }

    @Override
    public String getName() {
        return "Launcher";
    }

}

//if(velocity <= desiredVelocity){
    //speed = maxspeed;
//}else{
    //speed = targetSpeed;
//}


//if(velocity <= 0.95 * desiredVelocity){
    //speed = maxspeed;
//}else if(velocity <= desiredVelocity){

//}