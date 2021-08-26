package org.wildstang.hardware.roborio.outputs;

import org.wildstang.framework.io.outputs.AnalogOutput;
import org.wildstang.hardware.roborio.outputs.config.WsMotorControllers;

/**
 * Abstract class decribing those that control some kind of motor controller.
 * @author Liam
 */
public abstract class WsMotorController extends AnalogOutput {

    /**
     * Generic motor controller constructor, cannot be called.
     * @param p_name Descriptive name of the controller.
     */
    public WsMotorController(String p_name) {
        super(p_name);
    }

    /**
     * Generic motor controller constructor, cannot be called.
     * @param p_name Descriptive name of the controller.
     * @param p_default Default output value.
     */
    public WsMotorController(String p_name, double p_default) {
        super(p_name, p_default);
    }

    /**
     * Add a follower motor to the current motor.
     * @param canConstant CAN constant of the new follower motor.
     * @param controller Enumeration representing type of controller.
     * @param oppose True if the follow should oppose the direction of this motor.
     */
    public abstract void addFollower(int canConstant, WsMotorControllers controller, boolean oppose);

    /**
     * Sets the motor to brake mode, will not freely spin.
     */
    public abstract void setBrake();

    /**
     * Sets the motor to coast mode, will freely spin.
     */
    public abstract void setCoast();

    /**
     * Sets the current limit of the motor controller.
     * Note: parameters may vary
     * @param a See child class for parameter.
     * @param b See child class for parameter.
     * @param c See child class for parameter.
     */
    public abstract void setCurrentLimit(int a, int b, int c);

    /**
     * Disables the current limit of the motor controller.
     */
    public abstract void disableCurrentLimit();

    /**
     * Returns the quadrature velocity from an encoder.
     * @return Current velocity.
     */
    public abstract double getVelocity();

    /**
     * Returns the quadrature position from an encoder.
     * @return Current position.
     */
    public abstract double getPosition();

    /**
     * Resets the position of the encoder.
     */
    public abstract void resetEncoder();

    /**
     * Returns the current motor output percent.
     * @return Current motor output as a percent.
     */
    public abstract double getOutput();

    /**
     * Return the motor controller or motor temperature
     * @return Current temperature.
     */
    public abstract double getTemperature();
    
    /**
     * Sets motor speed to current value, from -1.0 to 1.0.
     */
    public abstract void sendDataToOutput();

    /**
     * Wraps setValue().
     * @param value New motor percent speed, from -1.0 to 1.0.
     */
    public void setSpeed(double value) {
        setValue(value);
    }

    /**
     * Sets the motors to 0 speed.
     */
    public void stop() {
        setSpeed(0);
    }
}