package org.wildstang.sample.robot;

// expand this and edit if trouble with Ws
import org.wildstang.framework.core.Outputs;
import org.wildstang.framework.hardware.OutputConfig;
import org.wildstang.hardware.roborio.outputs.config.WsServoConfig;
import org.wildstang.hardware.roborio.outputs.config.WsPhoenixConfig;
import org.wildstang.hardware.roborio.outputs.config.WsI2COutputConfig;
import org.wildstang.hardware.roborio.outputs.config.WsMotorControllers;
import org.wildstang.hardware.roborio.outputs.config.WsDigitalOutputConfig;
import org.wildstang.hardware.roborio.outputs.config.WsPhoenixFollowerConfig;
import org.wildstang.hardware.roborio.outputs.config.WsSparkMaxConfig;

import edu.wpi.first.wpilibj.I2C;

/**
 * Output mappings are stored here.
 * Below each Motor, PWM, Digital Output, Solenoid, and Relay is enumerated with their appropriated IDs.
 * The enumeration includes a name, output type, and output config object.
 */
public enum WSOutputs implements Outputs {

    // ********************************
    // PWM Outputs
    // ********************************
    // ---------------------------------
    // Motors
    // ---------------------------------
    LEFT_DRIVE("Left Drive Motor", new WsSparkMaxConfig(1, true)),
    RIGHT_DRIVE("Right Drive Motor", new WsSparkMaxConfig(2, true)),
    TEST_MOTOR("Test Motor", new WsPhoenixConfig(CANConstants.EXAMPLE_MOTOR_CONTROLLER, WsMotorControllers.VICTOR_SPX)),

    // ---------------------------------
    // Servos
    // ---------------------------------
    TEST_SERVO("Test Servo", new WsServoConfig(0, 0)),

    // ********************************
    // DIO Outputs
    // ********************************
    DIO_O_0("Test Digital Output 0", new WsDigitalOutputConfig(0, true)), // Channel 0, Initially Low

    // ********************************
    // Solenoids
    // ********************************
    
    // ********************************
    // Relays
    // ********************************

    // ********************************
    // Others ...
    // ********************************
    LED("LEDs", new WsI2COutputConfig(I2C.Port.kMXP, 0x10));

    ; // end of enum

    /**
     * Do not modify below code, provides template for enumerations.
     * We would like to have a super class for this structure, however,
     * Java does not support enums extending classes.
     */

    private String m_name;
    private OutputConfig m_config;

    /**
     * Initialize a new Output.
     * @param p_name Name, must match that in class to prevent errors.
     * @param p_config Corresponding configuration for OutputType.
     */
    WSOutputs(String p_name, OutputConfig p_config) {
        m_name = p_name;
        m_config = p_config;
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

}