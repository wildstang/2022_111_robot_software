package org.wildstang.hardware.roborio.outputs;

import org.wildstang.framework.io.outputs.AnalogOutput;

import edu.wpi.first.wpilibj.Victor;

/**
 * Controls a Victor as a PWMSpeedController.
 * @author Nathan
 * @deprecated Since we use CAN, use VictorSPX instead.
 */
@Deprecated
public class WsVictor extends AnalogOutput {

    Victor victor;

    /**
     * Constructs the Victor from config.
     * @param name Descriptive name of the Victor.
     * @param channel Hardware port number the Victor is connected to.
     * @param p_default Default output value.
     */
    public WsVictor(String name, int channel, double p_default) {
        super(name, p_default);
        this.victor = new Victor(channel);
    }

    /**
     * Sets Victor speed to current value, from -1.0 to 1.0.
     */
    @Override
    protected void sendDataToOutput() {
        victor.set(getValue());
    }

    /**
     * Does nothing, config values only affects start state.
     */
    public void notifyConfigChange() { }
}
