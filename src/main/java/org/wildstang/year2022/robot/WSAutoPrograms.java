package org.wildstang.year2022.robot;

import org.wildstang.framework.core.AutoPrograms;
import org.wildstang.year2022.auto.SampleAutoProgram;
import org.wildstang.year2022.auto.Programs.FrictionTest;
import org.wildstang.year2022.auto.Programs.InertiaTest;
import org.wildstang.year2022.auto.Programs.MomentumTest;
import org.wildstang.year2022.auto.Programs.Offset5Ball;
import org.wildstang.year2022.auto.Programs.Shoot;
import org.wildstang.year2022.auto.Programs.TestCircle;
import org.wildstang.year2022.auto.Programs.TestHeading;
import org.wildstang.year2022.auto.Programs.TestStraight10;
import org.wildstang.year2022.auto.Programs.TestStraight5;

/**
 * All active AutoPrograms are enumerated here.
 * It is used in Robot.java to initialize all programs.
 */
public enum WSAutoPrograms implements AutoPrograms {

    // enumerate programs
    TEST_PROGRAM("Sample", SampleAutoProgram.class),
    SHOOT("Shoot", Shoot.class),
    FRICTIONTEST("Friction Test", FrictionTest.class),
    MOMENTUMTEST("Momentum Test", MomentumTest.class),
    INERTIATEST("Inertia Test", InertiaTest.class),
    OFFSET5BALL("Offset 5 Ball", Offset5Ball.class),
    CIRCLE("Test Circle", TestCircle.class),
    STRAIGHT5("Test Straight5", TestStraight5.class),
    STRAIGHT10("Test Straight10", TestStraight10.class),
    HEADING("Test Heading", TestHeading.class)
    ;

    /**
     * Do not modify below code, provides template for enumerations.
     * We would like to have a super class for this structure, however,
     * Java does not support enums extending classes.
     */
    
    private String name;
    private Class<?> programClass;

    /**
     * Initialize name and AutoProgram map.
     * @param name Name, must match that in class to prevent errors.
     * @param programClass Class containing AutoProgram
     */
    WSAutoPrograms(String name, Class<?> programClass) {
        this.name = name;
        this.programClass = programClass;
    }

    /**
     * Returns the name mapped to the AutoProgram.
     * @return Name mapped to the AutoProgram.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns AutoProgram's class.
     * @return AutoProgram's class.
     */
    @Override
    public Class<?> getProgramClass() {
        return programClass;
    }
}