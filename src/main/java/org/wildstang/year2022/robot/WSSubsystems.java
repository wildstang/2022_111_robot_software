package org.wildstang.year2022.robot;

import org.wildstang.framework.core.Subsystems;
import org.wildstang.year2022.subsystems.Hood.Hood;
import org.wildstang.year2022.subsystems.ballpath.Ballpath;
import org.wildstang.year2022.subsystems.swerve.SwerveDrive;
import org.wildstang.year2022.subsystems.test.Tester;
import org.wildstang.year2022.subsystems.launcher.Launcher;

//import org.wildstang.year2022.subsystems.climb.ClimbControl;
/**
 * All subsystems are enumerated here.
 * It is used in Robot.java to initialize all subsystems.
 */
public enum WSSubsystems implements Subsystems {

    // enumerate subsystems
    SWERVE_DRIVE("Swerve Drive", SwerveDrive.class),
    TESTER("Tester", Tester.class),
    BALLPATH("Ballpath", Ballpath.class),
    LAUNCHER("Launcher", Launcher.class),
    HOOD("Hood", Hood.class),
    //CLIMB("Climb", ClimbControl.class)
	AIM_HELPER("Aim Helper", AimHelper.class)
    ;

    /**
     * Do not modify below code, provides template for enumerations.
     * We would like to have a super class for this structure, however,
     * Java does not support enums extending classes.
     */
    
    private String name;
    private Class<?> subsystemClass;

    /**
     * Initialize name and Subsystem map.
     * @param name Name, must match that in class to prevent errors.
     * @param subsystemClass Class containing Subsystem
     */
    WSSubsystems(String name, Class<?> subsystemClass) {
        this.name = name;
        this.subsystemClass = subsystemClass;
    }

    /**
     * Returns the name mapped to the subsystem.
     * @return Name mapped to the subsystem.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns subsystem's class.
     * @return Subsystem's class.
     */
    @Override
    public Class<?> getSubsystemClass() {
        return subsystemClass;
    }
}