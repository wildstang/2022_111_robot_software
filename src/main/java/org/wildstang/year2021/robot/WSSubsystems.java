package org.wildstang.year2021.robot;

import org.wildstang.year2021.subsystems.drive.Drive;
import org.wildstang.year2021.subsystems.launching.Limelight;
import org.wildstang.year2021.subsystems.TestSubsystem;
import org.wildstang.framework.core.Subsystems;


/**
 * Enumerate all subsystems on the robot. This enum is used in Robot.java to
 * initialize all subsystems.
 **/
public enum WSSubsystems implements Subsystems {
    DRIVEBASE("Drive Base", Drive.class),
    LIMELIGHT("Limelight", Limelight.class);
    // LED("LED", LED.class);
    

    private String name;

    private Class<?> subsystemClass;

    WSSubsystems(String name, Class<?> subsystemClass) {
        this.name = name;
        this.subsystemClass = subsystemClass;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<?> getSubsystemClass() {
        return subsystemClass;
    }
}
