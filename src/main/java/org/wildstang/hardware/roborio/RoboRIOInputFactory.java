package org.wildstang.hardware.roborio;

import org.wildstang.framework.core.Inputs;
import org.wildstang.framework.hardware.InputConfig;
import org.wildstang.framework.hardware.InputFactory;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.hardware.roborio.inputs.WsAbsoluteEncoder;
import org.wildstang.hardware.roborio.inputs.WsAnalogGyro;
import org.wildstang.hardware.roborio.inputs.WsAnalogInput;
import org.wildstang.hardware.roborio.inputs.WsContinuousI2CInput;
import org.wildstang.hardware.roborio.inputs.WsDPadButton;
import org.wildstang.hardware.roborio.inputs.WsDigitalInput;
import org.wildstang.hardware.roborio.inputs.WsI2CInput;
import org.wildstang.hardware.roborio.inputs.WsJoystickAxis;
import org.wildstang.hardware.roborio.inputs.WsJoystickButton;
import org.wildstang.hardware.roborio.inputs.WsLidarSensor;
import org.wildstang.hardware.roborio.inputs.WsMotionProfileControl;
import org.wildstang.hardware.roborio.inputs.WsRemoteAnalogInput;
import org.wildstang.hardware.roborio.inputs.WsRemoteDigitalInput;
import org.wildstang.hardware.roborio.inputs.config.WsAbsoluteEncoderConfig;
import org.wildstang.hardware.roborio.inputs.config.WsAnalogGyroConfig;
import org.wildstang.hardware.roborio.inputs.config.WsAnalogInputConfig;
import org.wildstang.hardware.roborio.inputs.config.WsContinuousI2CInputConfig;
import org.wildstang.hardware.roborio.inputs.config.WsDPadButtonInputConfig;
import org.wildstang.hardware.roborio.inputs.config.WsDigitalInputConfig;
import org.wildstang.hardware.roborio.inputs.config.WsI2CInputConfig;
import org.wildstang.hardware.roborio.inputs.config.WsJSButtonInputConfig;
import org.wildstang.hardware.roborio.inputs.config.WsJSJoystickInputConfig;
import org.wildstang.hardware.roborio.inputs.config.WsLidarInputConfig;
import org.wildstang.hardware.roborio.inputs.config.WsMotionProfileConfig;
import org.wildstang.hardware.roborio.inputs.config.WsRemoteAnalogInputConfig;
import org.wildstang.hardware.roborio.inputs.config.WsRemoteDigitalInputConfig;

/**
 * Builds inputs from WsInputs enumerations.
 */
public class RoboRIOInputFactory implements InputFactory {

    private boolean s_initialised = false;

    /**
     * Empty constructor override WPILib InputFactory constructor.
     */
    public RoboRIOInputFactory() {}

    /**
     * Prepares logger.
     */
    public void init() {
        if (!s_initialised) {
            s_initialised = true;
        }
    }

    /**
     * Creates an Input from an enumeration of WsInputs.
     * @param p_input An enumeration of WsInputs.
     * @return A constructed Input.
     */
    public Input createInput(Inputs p_input) {
        Input in = null;
        InputConfig config = p_input.getConfig();

        if (config instanceof WsAbsoluteEncoderConfig) {
            WsAbsoluteEncoderConfig c = (WsAbsoluteEncoderConfig) config;
            in = new WsAbsoluteEncoder(p_input.getName(), c.getChannel(), c.getMaxVoltage());
        }
        else if (config instanceof WsLidarInputConfig) {
            // Port is the address, module is the port - such as I2C.kMXP
            WsLidarInputConfig c = (WsLidarInputConfig) config;
            in = new WsLidarSensor(p_input.getName(), c.getPort(), c.getAddress(), c.getUpdateInterval());
        }
        else if (config instanceof WsContinuousI2CInputConfig) {
            WsContinuousI2CInputConfig c = (WsContinuousI2CInputConfig) config;
            in = new WsContinuousI2CInput(p_input.getName(), c.getPort(), c.getAddress(), c.getUpdateInterval());
        }
        else if (config instanceof WsDPadButtonInputConfig) {
            WsDPadButtonInputConfig c = (WsDPadButtonInputConfig) config;
            in = new WsDPadButton(p_input.getName(), c.getPort(), c.getButton());
        }
        else if (config instanceof WsJSButtonInputConfig) {
            WsJSButtonInputConfig c = (WsJSButtonInputConfig) config;
            in = new WsJoystickButton(p_input.getName(), c.getPort(), c.getButton());
        }
        else if (config instanceof WsJSJoystickInputConfig) {
            WsJSJoystickInputConfig c = (WsJSJoystickInputConfig) config;
            in = new WsJoystickAxis(p_input.getName(), c.getPort(), c.getAxis());
        }
        else if (config instanceof WsRemoteDigitalInputConfig) {
            WsRemoteDigitalInputConfig c = (WsRemoteDigitalInputConfig) config;
            in = new WsRemoteDigitalInput(p_input.getName(), c.getTableName());
        }
        else if (config instanceof WsRemoteAnalogInputConfig) {
            WsRemoteAnalogInputConfig c = (WsRemoteAnalogInputConfig) config;
            in = new WsRemoteAnalogInput(p_input.getName(), c.getTableName());
        }
        else if (config instanceof WsI2CInputConfig) {
            WsI2CInputConfig c = (WsI2CInputConfig) config;
            in = new WsI2CInput(p_input.getName(), c.getPort(), c.getAddress());
        }
        else if (config instanceof WsAnalogGyroConfig) {
            WsAnalogGyroConfig c = (WsAnalogGyroConfig) config;
            in = new WsAnalogGyro(p_input.getName(), c.getChannel(), c.getCompensate());
        }
        else if (config instanceof WsMotionProfileConfig) {
            in = new WsMotionProfileControl(p_input.getName());
        }
        else if (config instanceof WsAnalogInputConfig) {
            WsAnalogInputConfig c = (WsAnalogInputConfig) config;
            in = new WsAnalogInput(p_input.getName(), c.getChannel());
        }
        else if (config instanceof WsDigitalInputConfig) {
            WsDigitalInputConfig c = (WsDigitalInputConfig) config;
            in = new WsDigitalInput(p_input.getName(), c.getChannel(), c.getPullup());
        }
        
        return in;
    }

}
