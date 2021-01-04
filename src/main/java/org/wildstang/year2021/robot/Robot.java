package org.wildstang.year2021.robot;

import org.wildstang.framework.auto.AutoManager;

//import com.sun.management.GarbageCollectionNotificationInfo;
//import com.sun.management.internal.GarbageCollectionNotifInfoCompositeData;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.InputManager;
import org.wildstang.framework.io.inputs.RemoteAnalogInput;
import org.wildstang.framework.timer.WsTimer;
import org.wildstang.hardware.crio.RoboRIOInputFactory;
import org.wildstang.hardware.crio.RoboRIOOutputFactory;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.lang.management.ManagementFactory;
import java.util.List;
import java.lang.management.GarbageCollectorMXBean;

import org.wildstang.year2021.auto.programs.ExampleAutoProgram;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
    Core core;
    private boolean AutoFirstRun = true;

    /** Nothing to do in the Robot constructor; real setup happens in robotInit. */
    public Robot() {
        super();
    }

    @Override
    public void robotInit() {
        System.out.println("Initializing robot.");

        core = new Core(RoboRIOInputFactory.class, RoboRIOOutputFactory.class);
        core.createInputs(WSInputs.values());
        core.createOutputs(WSOutputs.values());
        core.createSubsystems(WSSubsystems.values());

        AutoManager.getInstance().addProgram(new ExampleAutoProgram());
        
    }

    @Override
    public void disabledInit() {
        System.out.println("Engaging disabled mode.");
        
    }

    @Override
    public void autonomousInit() {
        Core.getSubsystemManager().resetState();

        core.setAutoManager(AutoManager.getInstance());
        AutoManager.getInstance().startCurrentProgram();
    }

    @Override
    public void teleopInit() {
        System.out.println("Engaging teleoperation mode.");
        Core.getSubsystemManager().resetState();

    }

    @Override
    public void testInit() {
        System.out.println("Engaging test mode.");
    }

    @Override
    public void robotPeriodic() {


        core.executeUpdate();

        // Empty the state tracker so we don't OOM out
        // TODO: figure out what this thing is and why
        Core.getStateTracker().getStateList();

    }

    @Override
    public void disabledPeriodic() {
        //Drive drive = (Drive) Core.getSubsystemManager().getSubsystem(WSSubsystems.DRIVEBASE.getName());
        //drive.setFullBrakeMode();
        resetRobotState();

    }
    
    private void resetRobotState() {
        AutoFirstRun = true;
    }

    @Override
    public void autonomousPeriodic() {
        core.executeUpdate();

        double time = System.currentTimeMillis();

        if (AutoFirstRun) {
            AutoFirstRun = false;
        }
    }

    @Override
    public void teleopPeriodic() {
        try {

            // Update all inputs, outputs and subsystems
            
            long start = System.currentTimeMillis();
            core.executeUpdate();
            long end = System.currentTimeMillis();

            
            SmartDashboard.putNumber("Cycle Time", (end - start));
        } catch (Throwable e) {
            SmartDashboard.putString("Last error", "Exception thrown during teleopPeriodic");
            SmartDashboard.putString("Exception thrown", e.toString());
            //exceptionThrown = true;
            throw e;
        } finally {
            SmartDashboard.putBoolean("ExceptionThrown",true);
        }
    }

    @Override
    public void testPeriodic() {
    }
}