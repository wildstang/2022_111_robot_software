package org.wildstang.hardware.roborio;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.wildstang.framework.core.Outputs;
import org.wildstang.framework.io.outputs.Output;
import org.wildstang.hardware.roborio.outputs.WSOutputType;
import org.wildstang.hardware.roborio.outputs.WsDigitalOutput;
import org.wildstang.hardware.roborio.outputs.WsDoubleSolenoid;
import org.wildstang.hardware.roborio.outputs.WsI2COutput;
import org.wildstang.hardware.roborio.outputs.WsPhoenix;
import org.wildstang.hardware.roborio.outputs.WsRelay;
import org.wildstang.hardware.roborio.outputs.WsServo;
import org.wildstang.hardware.roborio.outputs.WsSolenoid;
import org.wildstang.hardware.roborio.outputs.WsTalon;
import org.wildstang.hardware.roborio.outputs.WsVictor;
import org.wildstang.hardware.roborio.outputs.WsRemoteAnalogOutput;
import org.wildstang.hardware.roborio.outputs.WsRemoteDigitalOutput;
import org.wildstang.hardware.roborio.outputs.config.WsDigitalOutputConfig;
import org.wildstang.hardware.roborio.outputs.config.WsDoubleSolenoidConfig;
import org.wildstang.hardware.roborio.outputs.config.WsI2COutputConfig;
import org.wildstang.hardware.roborio.outputs.config.WsPhoenixConfig;
import org.wildstang.hardware.roborio.outputs.config.WsRelayConfig;
import org.wildstang.hardware.roborio.outputs.config.WsServoConfig;
import org.wildstang.hardware.roborio.outputs.config.WsSolenoidConfig;
import org.wildstang.hardware.roborio.outputs.config.WsTalonConfig;
import org.wildstang.hardware.roborio.outputs.config.WsVictorConfig;
import org.wildstang.hardware.roborio.outputs.config.WsRemoteAnalogOutputConfig;
import org.wildstang.hardware.roborio.outputs.config.WsRemoteDigitalOutputConfig;

/**
 * Builds outputs from WsOutputs enumerations.
 */
public class RoboRIOOutputFactory {

    private static Logger s_log = Logger.getLogger(RoboRIOOutputFactory.class.getName());
    private static final String s_className = "RoboRIOOutputFactory";
    private boolean s_initialised = false;

    /**
     * Empty constructor override WPILib InputFactory constructor.
     */
    public RoboRIOOutputFactory() {}

    /**
     * Prepares logger.
     */
    public void init() {
        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "init");
        }

        if (!s_initialised) {
            s_initialised = true;
        }

        if (s_log.isLoggable(Level.FINER)) {
            s_log.exiting(s_className, "init");
        }
    }

    /**
     * Creates an Output from an enumeration of WsOutputs.
     * @param p_output An enumeration of WsOutputs.
     * @return A constructed Output.
     */
    public Output createOutput(Outputs p_output) {
        if (s_log.isLoggable(Level.FINER)) {
            s_log.entering(s_className, "createDigitalInput");
        }

        Output out = null;

        if (s_log.isLoggable(Level.FINE)) {
            s_log.fine("Creating digital output: Name = " + p_output.getName() + ", type = "
                    + p_output.getType());
        }

        switch ((WSOutputType) p_output.getType()) {
        case DIGITAL_OUTPUT:
            out = new WsDigitalOutput(p_output.getName(),
                    ((WsDigitalOutputConfig) p_output.getConfig()).getChannel(),
                    ((WsDigitalOutputConfig) p_output.getConfig()).getDefault());
        break;
        case SERVO:
            out = new WsServo(p_output.getName(),
                    ((WsServoConfig) p_output.getConfig()).getChannel(),
                    ((WsServoConfig) p_output.getConfig()).getDefault());
        break;
        case RELAY:
            out = new WsRelay(p_output.getName(),
                    ((WsRelayConfig) p_output.getConfig()).getChannel());
        break;
        case PHOENIX:
            out = new WsPhoenix(p_output.getName(),
                    ((WsPhoenixConfig) p_output.getConfig()).getChannel(),
                    ((WsPhoenixConfig) p_output.getConfig()).getDefault(),
                    ((WsPhoenixConfig) p_output.getConfig()).isTalon(),
                    ((WsPhoenixConfig) p_output.getConfig()).isInverted());
        break;
        case VICTOR:
            out = new WsVictor(p_output.getName(),
                    ((WsVictorConfig) p_output.getConfig()).getChannel(),
                    ((WsVictorConfig) p_output.getConfig()).getDefault());
        break;
        case TALON:
            out = new WsTalon(p_output.getName(),
                    ((WsTalonConfig) p_output.getConfig()).getChannel(),
                    ((WsTalonConfig) p_output.getConfig()).getDefault());
        break;
        case SOLENOID_SINGLE:
            WsSolenoidConfig ssConfig = (WsSolenoidConfig) p_output.getConfig();
            out = new WsSolenoid(p_output.getName(), ssConfig.getModule(), ssConfig.getChannel(),
                    ssConfig.getDefault());
        break;
        case SOLENOID_DOUBLE:
            WsDoubleSolenoidConfig dsConfig = (WsDoubleSolenoidConfig) p_output.getConfig();
            out = new WsDoubleSolenoid(p_output.getName(), dsConfig.getModule(),
                    dsConfig.getChannel1(), dsConfig.getChannel2(), dsConfig.getDefault());
        break;
        case I2C:
            out = new WsI2COutput(p_output.getName(),
                    ((WsI2COutputConfig) p_output.getConfig()).getPort(),
                    ((WsI2COutputConfig) p_output.getConfig()).getAddress());
        break;
        case REMOTE_DIGITAL:
            out = new WsRemoteDigitalOutput(p_output.getName(),
                    ((WsRemoteDigitalOutputConfig) p_output.getConfig()).getTableName(),
                    ((WsRemoteDigitalOutputConfig) p_output.getConfig()).getDefault());
        break;
        case REMOTE_ANALOG:
            out = new WsRemoteAnalogOutput(p_output.getName(),
                    ((WsRemoteAnalogOutputConfig) p_output.getConfig()).getTableName(),
                    ((WsRemoteAnalogOutputConfig) p_output.getConfig()).getDefault());
        break;
        case NULL:
        default:
        }

        if (s_log.isLoggable(Level.FINER)) {
            s_log.exiting(s_className, "createDigitalInput");
        }

        return out;
    }

}
