/*package org.wildstang.year2022.subsystems.launcher;

import com.ctre.phoenix.sensors.CANCoder;
import com.kauailabs.navx.frc.AHRS;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.framework.timer.WsTimer;
import org.wildstang.framework.subsystems.swerve.SwerveDriveTemplate;
import org.wildstang.year2022.robot.CANConstants;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;
import org.wildstang.year2022.subsystems.swerve.DriveConstants;
import org.wildstang.year2022.subsystems.swerve.SwerveSignal;
import org.wildstang.year2022.subsystems.swerve.WSSwerveHelper;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;

public class Hood implements Subsystem{
    private AnalogInput ManualAim;
        ManualShoot.WsName = "MANIPULATOR_LEFT_JOYSTICK_Y";
    private DigitalInput ManualAimInput;
        AutoAim.WsName = "MANIPULATOR_LEFT_TRIGGER";

    private DigitalInput PresetUp;
        PresetUp.WsName = "MANIPULATOR_DPAD_UP";
    private DigitalInput PresetDown;
        PresetDown.WsName = "MANIPULATOR_DPAD_DOWN";

    private WsSparkMax hoodMotor;
        public static final double UPPER_GOAL_DISTANCE_LIMIT = 0.0;
        public static final double REVS_PER_INCH = 1.0 / 2.0;
        public static final double TICKS_PER_REV = 1024.0;
        public static final double TICKS_PER_INCH = TICKS_PER_REV * REVS_PER_INCH;
   
        public void HoodAngle (){
            return hoodMotor.AnalogInput()/TICKS_PER_REV
        }
        // hoodMotor.WsName = "hoodMotor";
        //for analog input do hoodMotor.AnalogInput()

    

    public static final double POINTBLANK_HOOD = 45; //hood angle for pointblank shot
    public static final double SAFE_SHOOTER_SPEED = 0*(3750 * TICKS_PER_REV) / 600.0;//dropped to 25600 from 34133
    public static final double POINT_BLANK_SHOOTER_SPEED = 26000;
    public static final double AIM_MODE_SHOOTER_SPEED = 38000;//4*(6750 * TICKS_PER_REV) / 600.0;//51200
    public static final double IDLE_SPEED = 0.6;//idle percent output

    public static final double AIMING_INNER_REGRESSION_A = -1.9325; //-2.418037;
    public static final double AIMING_INNER_REGRESSION_B = 74.177; //77.979872;
    public static final double AIMING_INNER_REGRESSION_C = -119.84-25; //-83.373173;
    public static final double AIMING_OUTER_REGRESSION_A = -1.9325; //-2.418037;
    public static final double AIMING_OUTER_REGRESSION_B = 74.177; //77.979872;
    public static final double AIMING_OUTER_REGRESSION_C = -119.84-25; //-83.373173;

    public static final double TRENCH_HOOD_PRESET = 610.0;
    public static final double TRENCH_SHOOTER_SPEED_PRESET = 35000;

    public static final double HOOD_OUTPUT_SCALE = 1.0;

    private static final double HOOD_REG_ADJUSTMENT_INCREMENT = 5;

    public static final double HOOD_KP = -0.0014;//.015
    public static final double HOOD_KD = 0.002;
    public double lastError;
    public double error;

    public static final double INNER_GOAL_MIN_DISTANCE = 10.00;
    // Ratio of horizontal length of target to distance from target (should be constant if robot is straight on target)
    public static final double INNER_GOAL_STANDARD_RATIO = 1.0;
    public static final double INNER_GOAL_THRESHOLD = 0.1;

    private boolean aimModeEnabled;

    private boolean shooterMotorSpeedSetForAimMode;
    private boolean hoodAimed;

    public List<Double> trailingHorizontalAngleOffsets;
    private long lastValueAddedTimestamp;

    private double hoodTravelDistance;
    private double hoodMotorOutput;
    private double minimumHoodAdjustment;
    private boolean hoodManualOverride;

    private boolean hoodEncoderResetPressed;
    private long hoodEncoderResetTimestamp;

    private double hoodEncoderOffset;

    public void inputUpdate(Input source) {
        if 
    }

    public void init(){

    }

    public void initInputs(){
        autoAimInput =
        manualAimInput = 

        LeftButtonDown =
    }

    public void initOutputs(){
        shooterMotor = 
    }

    @Override
    public void selfTest(){
        
    }

    public void update() {

    }
    
    @Override
    public void resetState() {

    }

    public String getName() {
        // TODO Auto-generated method stub
        return "Launcher";  
    }
    
}
*/
