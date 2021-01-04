package org.wildstang.year2021.robot;

// expand this and edit if trouble with Ws
import org.wildstang.framework.core.Outputs;
import org.wildstang.framework.hardware.OutputConfig;
import org.wildstang.framework.io.outputs.OutputType;
import org.wildstang.hardware.crio.outputs.WSOutputType;
import org.wildstang.hardware.crio.outputs.config.WsI2COutputConfig;
import org.wildstang.hardware.crio.outputs.config.WsSolenoidConfig;
import org.wildstang.hardware.crio.outputs.config.WsDoubleSolenoidConfig;
import org.wildstang.hardware.crio.outputs.WsDoubleSolenoidState;
import org.wildstang.hardware.crio.outputs.config.WsVictorConfig;
import org.wildstang.framework.hardware.WsRemoteDigitalOutputConfig;
import org.wildstang.hardware.crio.outputs.config.WsDigitalOutputConfig;

import edu.wpi.first.wpilibj.I2C;

public enum WSOutputs implements Outputs {
    // ********************************
    // PWM Outputs
    // ********************************
    // ---------------------------------
    // Motors
    // ---------------------------------

    // ---------------------------------
    // Servos
    // ---------------------------------

    // ********************************
    // DIO Outputs
    // ********************************
    // DIO_O_0("Test Digital Output 0", WSOutputType.DIGITAL_OUTPUT, new
    // WsDigitalOutputConfig(0, true), false), // Channel 0, Initially Low

    // ********************************
    // Solenoids
    // ********************************

    
    // ********************************
    // Relays
    // ********************************
    // RELAY_0("Relay 0", WSOutputType.RELAY, new WsRelayConfig(0,
    // WsRelayState.RELAY_OFF), false), // Relay 0, Both Off

    // ********************************
    // Others ...
    // ********************************
    LED("LEDs", WSOutputType.I2C, new WsI2COutputConfig(I2C.Port.kMXP, 0x10), false);

    private String m_name;
    private OutputType m_type;
    private OutputConfig m_config;
    private boolean m_trackingState;

    private static boolean isLogging = true;

    WSOutputs(String p_name, OutputType p_type, OutputConfig p_config, boolean p_trackingState) {
        m_name = p_name;
        m_type = p_type;
        m_config = p_config;
        m_trackingState = p_trackingState;
    }

    @Override
    public String getName() {
        return m_name;
    }

    @Override
    public OutputType getType() {
        return m_type;
    }

    public OutputConfig getConfig() {
        return m_config;
    }

    public boolean isTrackingState() {
        return m_trackingState;
    }

    public static boolean getLogging() {
        return isLogging;
    }

}
