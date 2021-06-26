package org.wildstang.hardware.roborio.outputs;

import org.wildstang.framework.io.outputs.AnalogOutput;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

/**
 * Controls a Talon or Victor motor controller as a Phoenix BaseMotorController.
 * @author Liam
 */
public class WsPhoenix extends AnalogOutput {

    BaseMotorController motor;
    
    /**
     * Constructs the motor controller from config.
     * @param name Descriptive name of the controller.
     * @param channel Motor controller CAN constant.
     * @param p_default Default output value.
     * @param talon True if Talon, false if Victor.
     */
    public WsPhoenix(String name, int channel, double p_default, boolean talon) {
        this(name, channel, p_default, talon, false);
    }

    /**
     * Constructs the motor controller from config.
     * @param name Descriptive name of the controller.
     * @param channel Motor controller CAN constant.
     * @param p_default Default output value.
     * @param talon True if Talon, false if Victor.
     * @param invert Invert the motor's direction.
     */
    public WsPhoenix(String name, int channel, double p_default, boolean talon, boolean invert) {
        super(name, p_default);

        if (talon) {
            motor = new TalonSRX(channel);
        }
        else {
            motor = new VictorSPX(channel);
        }
        motor.setInverted(true);
    }

    /**
     * Sets the motor to brake mode, will not freely spin.
     */
    public void setBrake() {
        motor.setNeutralMode(NeutralMode.Brake);
    }

    /**
     * Sets the motor to coast mode, will freely spin.
     */
    public void setCoast() {
        motor.setNeutralMode(NeutralMode.Coast);
    }

    /**
     * Sets the current limit of the motor controller.
     * Only Talons support current limiting.
     * @param peakLimitAmps The instantaneous amount of amps drawn before limiting.
     * @param peakDuractionMs The amount of time peakLimitAmps must be broken before limiting, in milliseconds.
     * @param continuousLimitAmps The continuous amount of amps drawn before limiting, normally less than peakLimitAmps.
     */
    public void setCurrentLimit(int peakLimitAmps, int peakDuractionMs, int continuousLimitAmps) {
        if (motor instanceof TalonSRX) {
            TalonSRX t = (TalonSRX) motor;
            t.configPeakCurrentLimit(peakLimitAmps);
            t.configPeakCurrentDuration(peakDuractionMs);
            t.configContinuousCurrentLimit(continuousLimitAmps);
            t.enableCurrentLimit(true);
        }
    }

    /**
     * Disables the current limit of the motor controller.
     * Only Talons support current limiting.
     */
    public void disableCurrentLimit() {
        if (motor instanceof TalonSRX) {
            ((TalonSRX) motor).enableCurrentLimit(false);
        }
    }

    /**
     * Sets motor speed to current value, from -1.0 to 1.0.
     */
    @Override
    public void sendDataToOutput() {
        motor.set(ControlMode.PercentOutput, getValue());
    }

    /**
     * Does nothing, config values only affects start state.
     */
    public void notifyConfigChange() { }
}
