package org.wildstang.hardware.roborio.inputs;

/**
 * Reads an absolute encoder.
 */
public class WsAbsoluteEncoder extends WsAnalogInput {

    private int m_maxVoltage;

    /**
     * Construct the encoder.
     * @param p_name Descriptive name of the encoder.
     * @param channel Hardware port the encoder is connected to.
     * @param p_maxVoltage Max output voltage of the encoder.
     */
    public WsAbsoluteEncoder(String p_name, int channel, int p_maxVoltage) {
        super(p_name, channel);
        m_maxVoltage = p_maxVoltage;
    }

    /**
     * Reads the value from the encoder.
     * @return Encoder position calucated from raw value.
     */
    @Override
    public double readRawValue() {
        double rawValue = super.readRawValue();

        double position = (rawValue / m_maxVoltage) * 360;

        return position;
    }

}
