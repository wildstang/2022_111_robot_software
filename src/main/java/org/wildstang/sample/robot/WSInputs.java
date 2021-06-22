package org.wildstang.sample.robot;

import org.wildstang.framework.core.Inputs;
import org.wildstang.framework.hardware.InputConfig;
import org.wildstang.framework.io.inputs.InputType;
import org.wildstang.hardware.JoystickConstants;
import org.wildstang.hardware.crio.inputs.WSInputType;
import org.wildstang.hardware.crio.inputs.config.WsJSButtonInputConfig;
import org.wildstang.hardware.crio.inputs.config.WsJSJoystickInputConfig;

/**
 * Input mappings are stored here.
 * We currently use two Xbox controller for input, driver and manipulator, plus additional sensors.
 * Below each button, axis, and sensor is enumerated with their appropriated IDs.
 * Unclaimed inputs should have a name representing the button.
 * Claimed inputs by changing the name to represent the control.
 */
public enum WSInputs implements Inputs {
    
    //***************************************************************
    //      Driver and Manipulator Controller Button Locations
    //***************************************************************
    //
    //    +-------------------------------------------------------+
    //  +  +---------+                                 +---------+  +       
    //  |  |  RIGHT  |           TRIGGERS              |  LEFT   |  |       
    //  |  +---------+                                 +---------+  |       
    //  |      			                                            |   
    //  |  +---------+                                 +---------+  |       
    //  |  |    4    |           SHOULDERS             |    5    |  |
    //  +  +---------+                                 +---------+  +
    //    +-------------------------------------------------------+
    //  
    //    +-------------------------------------------------------+
    //   /    +--+                 [FRONT]                         \
    //  +     |YU|                                         (3)      +       
    //  |  +--+  +--+        +----+       +----+                    | 
    //  |  |XL    XR|        |  6 |  (X)  |  7 |       (2)     (1)  |       
    //  |  +--+  +--+        +----+       +----+                    | 
    //  |     |YD|                                         (0)      |       
    //  |     +--+     +--+          (X)          +--+              |
    //  |             /    \                     /    \             |
    //  |            |   8  |                   |   9  |            |
    //  |             \    /                     \    /             |
    //  +              +--+                       +--+              +
    //   \                                                         /
    //    \            +-----------------------------+            /
    //     \          /                               \          /
    //      \        /                                 \        /
    //       \      /                                   \      /
    //        +----+                                     +----+
    //
    //
    // ********************************
    // Driver Enums
    // ********************************
    //
    // ---------------------------------
    // Driver Joysticks
    // ---------------------------------
    DRIVER_LEFT_JOYSTICK_Y  ("Driver left joystick y",
            WSInputType.JS_JOYSTICK, new WsJSJoystickInputConfig(0, JoystickConstants.LEFT_JOYSTICK_Y),  true),
    DRIVER_LEFT_JOYSTICK_X  ("Driver left joystick x",
            WSInputType.JS_JOYSTICK, new WsJSJoystickInputConfig(0, JoystickConstants.LEFT_JOYSTICK_X),  true),
    DRIVER_RIGHT_JOYSTICK_Y ("Driver right joystick y",
            WSInputType.JS_JOYSTICK, new WsJSJoystickInputConfig(0, JoystickConstants.RIGHT_JOYSTICK_Y), true),
    DRIVER_RIGHT_JOYSTICK_X ("Driver right joystick x",
            WSInputType.JS_JOYSTICK, new WsJSJoystickInputConfig(0, JoystickConstants.RIGHT_JOYSTICK_X), true),
    
    // ---------------------------------
    // Driver DPAD Buttons
    // ---------------------------------
    DRIVER_DPAD_DOWN  ("Driver dpad down",
            WSInputType.JS_DPAD_BUTTON, new WsJSButtonInputConfig(0, JoystickConstants.DPAD_Y_DOWN),  false),
    DRIVER_DPAD_LEFT  ("Driver dpad left",
            WSInputType.JS_DPAD_BUTTON, new WsJSButtonInputConfig(0, JoystickConstants.DPAD_X_LEFT),  false),
    DRIVER_DPAD_RIGHT ("Driver dpad right",
            WSInputType.JS_DPAD_BUTTON, new WsJSButtonInputConfig(0, JoystickConstants.DPAD_X_RIGHT), false),
    DRIVER_DPAD_UP    ("Driver dpad up",
            WSInputType.JS_DPAD_BUTTON, new WsJSButtonInputConfig(0, JoystickConstants.DPAD_Y_UP),    false),

    // ---------------------------------
    // Driver Buttons
    // --------------------------------- 
    DRIVER_FACE_DOWN             ("Driver face down",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(0, 0),                                 false),
    DRIVER_FACE_LEFT             ("Driver face left",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(0, 2),                                 false),
    DRIVER_FACE_RIGHT            ("Driver face right",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(0, 1),                                 false),
    DRIVER_FACE_UP               ("Driver face up",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(0, 3),                                 false),
    DRIVER_LEFT_SHOULDER         ("Driver left shoulder",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(0, 4),                                 false),
    DRIVER_RIGHT_SHOULDER        ("Driver right shoulder",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(0, 5),                                 false),
    DRIVER_LEFT_TRIGGER          ("Driver left trigger",
            WSInputType.JS_JOYSTICK, new WsJSJoystickInputConfig(0, JoystickConstants.LEFT_TRIGGER),  false),
    DRIVER_RIGHT_TRIGGER         ("Driver right trigger",
            WSInputType.JS_JOYSTICK, new WsJSJoystickInputConfig(0, JoystickConstants.RIGHT_TRIGGER), false),
    DRIVER_SELECT                ("Driver select",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(0, 6),                                 false),
    DRIVER_START                 ("Driver start",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(0, 7),                                 false),
    DRIVER_LEFT_JOYSTICK_BUTTON  ("Driver left joystick button",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(0, 8),                                 false),
    DRIVER_RIGHT_JOYSTICK_BUTTON ("Driver right joystick button",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(0, 9),                                 false),

    // ---------------------------------
    // Manipulator Joysticks
    // ---------------------------------
    MANIPULATOR_LEFT_JOYSTICK_Y  ("Manipulator left joystick y",
            WSInputType.JS_JOYSTICK, new WsJSJoystickInputConfig(1, JoystickConstants.LEFT_JOYSTICK_Y),  true),
    MANIPULATOR_LEFT_JOYSTICK_X  ("Manipulator left joystick x",
            WSInputType.JS_JOYSTICK, new WsJSJoystickInputConfig(1, JoystickConstants.LEFT_JOYSTICK_X),  true),
    MANIPULATOR_RIGHT_JOYSTICK_Y ("Manipulator right joystick y",
            WSInputType.JS_JOYSTICK, new WsJSJoystickInputConfig(1, JoystickConstants.RIGHT_JOYSTICK_Y), true),
    MANIPULATOR_RIGHT_JOYSTICK_X ("Manipulator right joystick x",
            WSInputType.JS_JOYSTICK, new WsJSJoystickInputConfig(1, JoystickConstants.RIGHT_JOYSTICK_X), true),

    // ---------------------------------
    // Manipulator DPAD Buttons
    // ---------------------------------
    MANIPULATOR_DPAD_DOWN  ("Manipulator dpad down",
            WSInputType.JS_DPAD_BUTTON, new WsJSButtonInputConfig(1, JoystickConstants.DPAD_Y_DOWN),  false),
    MANIPULATOR_DPAD_LEFT  ("Manipulator dpad left",
            WSInputType.JS_DPAD_BUTTON, new WsJSButtonInputConfig(1, JoystickConstants.DPAD_X_LEFT),  false),
    MANIPULATOR_DPAD_RIGHT ("Manipulator dpad right",
            WSInputType.JS_DPAD_BUTTON, new WsJSButtonInputConfig(1, JoystickConstants.DPAD_X_RIGHT), false),
    MANIPULATOR_DPAD_UP    ("Manipulator dpad up",
            WSInputType.JS_DPAD_BUTTON, new WsJSButtonInputConfig(1, JoystickConstants.DPAD_Y_UP),    false),

    // ---------------------------------
    // Manipulator Buttons
    // ---------------------------------
    MANIPULATOR_FACE_DOWN             ("Manipulator face down",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(1, 0),                                 false),
    MANIPULATOR_FACE_LEFT             ("Manipulator face left",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(1, 2),                                 false),
    MANIPULATOR_FACE_RIGHT            ("Manipulator face right",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(1, 1),                                 false),
    MANIPULATOR_FACE_UP               ("Manipulator face up",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(1, 3),                                 false),
    MANIPULATOR_LEFT_SHOULDER         ("Manipulator left shoulder",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(1, 4),                                 false),
    MANIPULATOR_RIGHT_SHOULDER        ("Manipulator right shoulder",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(1, 5),                                 false),
    MANIPULATOR_LEFT_TRIGGER          ("Manipulator left trigger",
            WSInputType.JS_JOYSTICK, new WsJSJoystickInputConfig(1, JoystickConstants.LEFT_TRIGGER),  false),
    MANIPULATOR_RIGHT_TRIGGER         ("Manipulator right trigger",
            WSInputType.JS_JOYSTICK, new WsJSJoystickInputConfig(1, JoystickConstants.RIGHT_TRIGGER), false),
    MANIPULATOR_SELECT                ("Manipulator select",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(1, 6),                                 false),
    MANIPULATOR_START                 ("Manipulator start",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(1, 7),                                 false),
    MANIPULATOR_LEFT_JOYSTICK_BUTTON  ("Manipulator left joystick button",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(1, 8),                                 false),
    MANIPULATOR_RIGHT_JOYSTICK_BUTTON ("Manipulator right joystick button",
            WSInputType.JS_BUTTON,   new WsJSButtonInputConfig(1, 9),                                 false),

    // ********************************
    // Digital IOs
    // ********************************
    
    // -------------------------------
    // Networked sensors
    // -------------------------------
    
    // ********************************
    // Others ...
    // ********************************
    //GYRO                    ("Gyro", 
    //        WSInputType.ANALOG_GYRO,   new WsAnalogGyroConfig(0, true),         false),
            
    ; // end of enum
    
    /**
     * Do not modify below code, provides template for enumerations.
     * We would like to have a super class for this structure, however,
     * Java does not support enums extending classes.
     */

    private final String m_name;
    private final InputType m_type;

    private InputConfig m_config = null;

    private boolean m_trackingState;

    private static boolean isLogging = true;

    /**
     * Initialize a new Input.
     * @param p_name Name, must match that in class to prevent errors.
     * @param p_type Type of Input to use.
     * @param p_config Corresponding configuration for InputType.
     * @param p_trackingState If the Input is tracking.
     */
    WSInputs(String p_name, InputType p_type, InputConfig p_config, boolean p_trackingState) {
        m_name = p_name;
        m_type = p_type;
        m_config = p_config;
        m_trackingState = p_trackingState;
    }

    /**
     * Returns the name mapped to the Input.
     * @return Name mapped to the Input.
     */
    public String getName() {
        return m_name;
    }

    /**
     * Returns the type of Input for the enumeration.
     * @return InputType of enumeration.
     */
    public InputType getType() {
        return m_type;
    }

    /**
     * Returns the config of Input for the enumeration.
     * @return InputConfig of enumeration.
     */
    public InputConfig getConfig() {
        return m_config;
    }

    /**
     * Returns true if the Logger should track the Input's state.
     * @return True if of tracking state.
     */
    public boolean isTrackingState() {
        return m_trackingState;
    }

    /**
     * Returns true if the Inputs are set to log.
     * @return True if set to log.
     */
    public static boolean getLogging() {
        return isLogging;
    }

}
