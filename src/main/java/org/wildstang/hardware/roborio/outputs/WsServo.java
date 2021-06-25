package org.wildstang.hardware.roborio.outputs;

import org.wildstang.framework.io.outputs.AnalogOutput;

import edu.wpi.first.wpilibj.Servo;

/**
 * Controls a servo.
 */
public class WsServo extends AnalogOutput {

    Servo servo;

    /**
     * Constructs the servo from config.
     * @param name Descriptive name of the servo.
     * @param channel Hardware port number the servo is connected to.
     * @param p_default Default position.
     */
    public WsServo(String name, int channel, double p_default) {
        super(name, p_default);

        this.servo = new Servo(channel);
    }

    /**
     * Sets servo position to current value.
     */
    @Override
    public void sendDataToOutput() {
        servo.setAngle(getValue());
    }

    /**
     * Does nothing, config values only affects start state.
     */
    public void notifyConfigChange() { }
}
