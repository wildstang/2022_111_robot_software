package org.wildstang.hardware.roborio.outputs;

import org.wildstang.framework.io.outputs.AnalogOutput;

import edu.wpi.first.wpilibj.Talon;

/**
 * Controls a Talon as a PWMSpeedController.
 * @author Nathan
 * @deprecated Since we use CAN, use TalonSRX instead.
 */
@Deprecated
public class WsTalon extends AnalogOutput {

    Talon talon;

    /**
     * Constructs the Talon from config.
     * @param name Descriptive name of the Talon.
     * @param channel Hardware port number the Talon is connected to.
     * @param p_default Default output value.
     */
    public WsTalon(String name, int channel, double p_default) {
        super(name, p_default);
        this.talon = new Talon(channel);

    }

    /**
     * Sets Talon speed to current value, from -1.0 to 1.0.
     */
    @Override
    public void sendDataToOutput() {
        talon.set(getValue());
    }

    /**
     * Does nothing, config values only affects start state.
     */
    public void notifyConfigChange() { }
}
