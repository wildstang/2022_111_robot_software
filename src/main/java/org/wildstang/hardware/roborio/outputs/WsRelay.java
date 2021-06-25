package org.wildstang.hardware.roborio.outputs;

import org.wildstang.framework.io.outputs.DiscreteOutput;

import edu.wpi.first.wpilibj.Relay;

/**
 * Controls a relay.
 */
public class WsRelay extends DiscreteOutput {
    private Relay relay;

    /**
     * Constructs the relay from config.
     * @param p_name Descriptive name of the relay.
     * @param channel Hardware port number the relay is connected to.
     */
    public WsRelay(String p_name, int channel)// , Direction direction)
    {
        super(p_name);
        relay = new Relay(channel, Relay.Direction.kBoth);
    }

    /**
     * Constructs the relay from config.
     * @param p_name Descriptive name of the relay.
     * @param channel Hardware port number the relay is connected to.
     * @param direction Relay operation direction.
     */
    public WsRelay(String p_name, int channel, Relay.Direction direction)
    {
        super(p_name);
        relay = new Relay(channel, direction);
    }

    /**
     * Sets relay state to a value based off the current state.
     */
    @Override
    public void sendDataToOutput() {
        Relay.Value value = Relay.Value.kOff;

        if (getValue() == WsRelayState.RELAY_ON.ordinal()) {
            value = Relay.Value.kOn;
        } else if (getValue() == WsRelayState.RELAY_FORWARD.ordinal()) {
            value = Relay.Value.kForward;
        } else if (getValue() == WsRelayState.RELAY_REVERSE.ordinal()) {
            value = Relay.Value.kReverse;
        } else if (getValue() == WsRelayState.RELAY_OFF.ordinal()) {
            value = Relay.Value.kOff;
        }

        relay.set(value);
    }

}
