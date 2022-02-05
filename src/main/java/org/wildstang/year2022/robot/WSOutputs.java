package org.wildstang.year2022.robot;

// expand this and edit if trouble with Ws
import org.wildstang.framework.core.Outputs;
import org.wildstang.framework.hardware.OutputConfig;
import org.wildstang.hardware.roborio.outputs.config.WsServoConfig;
import org.wildstang.hardware.roborio.outputs.config.WsPhoenixConfig;
import org.wildstang.hardware.roborio.outputs.config.WsI2COutputConfig;
import org.wildstang.hardware.roborio.outputs.config.WsMotorControllers;
import org.wildstang.hardware.roborio.outputs.WsDoubleSolenoidState;
import org.wildstang.hardware.roborio.outputs.config.WsDigitalOutputConfig;
import org.wildstang.hardware.roborio.outputs.config.WsDoubleSolenoidConfig;
import org.wildstang.hardware.roborio.outputs.config.WsPhoenixFollowerConfig;
import org.wildstang.hardware.roborio.outputs.config.WsSparkMaxConfig;
import org.wildstang.hardware.roborio.outputs.config.WsSparkMaxFollowerConfig;
import org.wildstang.hardware.roborio.outputs.config.WsSolenoidConfig;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

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
    TEST_MOTOR("Test Motor", new WsPhoenixConfig(CANConstants.EXAMPLE_MOTOR_CONTROLLER, WsMotorControllers.VICTOR_SPX)),

    DRIVE1("Module 1 Drive Motor", new WsSparkMaxConfig(CANConstants.DRIVE1, true)),
    ANGLE1("Module 1 Angle Motor", new WsSparkMaxConfig(CANConstants.ANGLE1, true)),
    DRIVE2("Module 2 Drive Motor", new WsSparkMaxConfig(CANConstants.DRIVE2, true)),
    ANGLE2("Module 2 Angle Motor", new WsSparkMaxConfig(CANConstants.ANGLE2, true)),
    DRIVE3("Module 3 Drive Motor", new WsSparkMaxConfig(CANConstants.DRIVE3, true)),
    ANGLE3("Module 3 Angle Motor", new WsSparkMaxConfig(CANConstants.ANGLE3, true)),
    DRIVE4("Module 4 Drive Motor", new WsSparkMaxConfig(CANConstants.DRIVE4, true)),
    ANGLE4("Module 4 Angle Motor", new WsSparkMaxConfig(CANConstants.ANGLE4, true)),

    INTAKE("Intake Motor", new WsSparkMaxConfig(CANConstants.INTAKE, true)),
    FEED("Feed Motor", new WsSparkMaxConfig(CANConstants.FEED, true)),
    KICKER("Kicker Motor", new WsSparkMaxConfig(CANConstants.KICKER, true)),
    HOOD("Hood Motor", new WsSparkMaxConfig(CANConstants.HOOD, true)),
    LAUNCHER("Launcher Motor", new WsSparkMaxConfig(CANConstants.SHOOTER, true)),
    LAUNCHER_FOLLOWER("Launcher Follower Motor", new WsSparkMaxFollowerConfig("Launcher Motor",CANConstants.SHOOTER_FOLLOWER, true, true)),
    

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

    //TEST_SOLENOID("Test Solenoid", new WsSolenoidConfig(PneumaticsModuleType.REVPH, 0, false)),
    LAUNCHER_SOLENOID("Launcher Solenoid", new WsSolenoidConfig(PneumaticsModuleType.REVPH, 0, true)),
    CLIMB_SOLENOID_1("Climb Solenoid 1", new WsDoubleSolenoidConfig(PneumaticsModuleType.REVPH, 2, 3, WsDoubleSolenoidState.FORWARD)),
    CLIMB_SOLENOID_2("Climb Solenoid 2", new WsDoubleSolenoidConfig(PneumaticsModuleType.REVPH, 4, 5, WsDoubleSolenoidState.FORWARD)),
    
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