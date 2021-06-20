package org.wildstang.sample.robot;

// expand this and edit if trouble with Ws
import org.wildstang.framework.core.Outputs;
import org.wildstang.framework.hardware.OutputConfig;
import org.wildstang.framework.io.outputs.OutputType;

/**
 * Output mappings are stored here.
 * Below each PWM, Digital Output, Solenoid, and Relay is enumerated with their appropriated IDs.
 * The enumeration includes a name, output type, output object, and tracking boolean.
 * Note, our motors are currently all controlled via the CAN bus, so they do not appear in this enum.
 */
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
    private OutputType m_type;
    private OutputConfig m_config;
    private boolean m_trackingState;

    private static boolean isLogging = true;

    /**
     * Initialize a new Output.
     * @param p_name Name, must match that in class to prevent errors.
     * @param p_type Type of Output to use.
     * @param p_config Corresponding configuration for OutputType.
     * @param p_trackingState If the Output is tracking.
     */
    WSOutputs(String p_name, OutputType p_type, OutputConfig p_config, boolean p_trackingState) {
        m_name = p_name;
        m_type = p_type;
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
     * Returns the type of Output for the enumeration.
     * @return OutputType of enumeration.
     */
    public OutputType getType() {
        return m_type;
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
     * @return True if of tracking state.
     */
    public boolean isTrackingState() {
        return m_trackingState;
    }


    /**
     * Returns true if the Outputs are set to log.
     * @return True if set to log.
     */
    public static boolean getLogging() {
        return isLogging;
    }

}