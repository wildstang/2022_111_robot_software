package org.wildstang.sample.robot;

// expand this and edit if trouble with Ws
import org.wildstang.framework.core.Outputs;
import org.wildstang.framework.hardware.OutputConfig;
import org.wildstang.hardware.roborio.outputs.config.WsPhoenixConfig;
import org.wildstang.hardware.roborio.outputs.config.WsPhoenixControllers;

/**
 * Output mappings are stored here.
 * Below each Motor, PWM, Digital Output, Solenoid, and Relay is enumerated with their appropriated IDs.
 * The enumeration includes a name, output type, output config object, and tracking boolean.
 */
public enum WSOutputs implements Outputs {

    // ********************************
    // PWM Outputs
    // ********************************
    // ---------------------------------
    // Motors
    // ---------------------------------
    //TEST_PAIRED_MOTOR("Test Paired Motor", new WsPhoenixConfig(CANConstants.EXAMPLE_PAIRED_CONTROLLERS[0], true), false),
    //TEST_FOLLOWER_MOTOR("Test Follower Motor", new WsPhoenixFollowerConfig(TEST_PAIRED_MOTOR, CANConstants.EXAMPLE_PAIRED_CONTROLLERS[1], true), false),
    LEFT_DRIVE("Left Drive Motor", new WsPhoenixConfig(1, WsPhoenixControllers.TALON_SRX), false),
    RIGHT_DRIVE("Right Drive Motor", new WsPhoenixConfig(1, WsPhoenixControllers.TALON_SRX), false),

    // ---------------------------------
    // Servos
    // ---------------------------------
    //TEST_SERVO("Test Servo", new WsServoConfig(0, 0), false),

    // ********************************
    // DIO Outputs
    // ********************************
    //DIO_O_0("Test Digital Output 0", WSOutputType.DIGITAL_OUTPUT, new WsDigitalOutputConfig(0, true), false), // Channel 0, Initially Low

    // ********************************
    // Solenoids
    // ********************************
    
    // ********************************
    // Relays
    // ********************************

    // ********************************
    // Others ...
    // ********************************
    //LED("LEDs", WSOutputType.I2C, new WsI2COutputConfig(I2C.Port.kMXP, 0x10), false);

    ; // end of enum

    /**
     * Do not modify below code, provides template for enumerations.
     * We would like to have a super class for this structure, however,
     * Java does not support enums extending classes.
     */

    private String m_name;
    private OutputConfig m_config;
    private boolean m_trackingState;

    /**
     * Initialize a new Output.
     * @param p_name Name, must match that in class to prevent errors.
     * @param p_config Corresponding configuration for OutputType.
     * @param p_trackingState True if the StateTracker should track this Output.
     */
    WSOutputs(String p_name, OutputConfig p_config, boolean p_trackingState) {
        m_name = p_name;
        m_config = p_config;
        m_trackingState = p_trackingState;
    }

    /**
     * Returns the name mapped to the Output.
     * @return Name mapped to the Output.
     */
    public String getName() {
        return m_name;
    }

    /**
     * Returns the config of Output for the enumeration.
     * @return OutputConfig of enumeration.
     */
    public OutputConfig getConfig() {
        return m_config;
    }

    /**
     * Returns true if the Logger should track the Output's state.
     * @return True if the StateTracker should track this Output.
     */
    public boolean isTrackingState() {
        return m_trackingState;
    }

}