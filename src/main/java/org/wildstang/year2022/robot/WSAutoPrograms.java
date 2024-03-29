package org.wildstang.year2022.robot;

import org.wildstang.framework.core.AutoPrograms;
import org.wildstang.year2022.auto.SampleAutoProgram;
import org.wildstang.year2022.auto.Programs.Billiards3Ball;
import org.wildstang.year2022.auto.Programs.Offset5Ball;
import org.wildstang.year2022.auto.Programs.Shoot;
import org.wildstang.year2022.auto.Programs.SlamJam;
import org.wildstang.year2022.auto.Programs.TestCircle;
import org.wildstang.year2022.auto.Programs.TestHeading;
import org.wildstang.year2022.auto.Programs.TestStraight10;
import org.wildstang.year2022.auto.Programs.TestStraight5;
import org.wildstang.year2022.auto.Programs.TwoBallAndHide;
import org.wildstang.year2022.auto.Programs.TwoBallAndSteal;
import org.wildstang.year2022.auto.Programs.Two_Ball;

/**
 * All active AutoPrograms are enumerated here.
 * It is used in Robot.java to initialize all programs.
 */
public enum WSAutoPrograms implements AutoPrograms {

    // enumerate programs
    //TEST_PROGRAM("Sample", SampleAutoProgram.class),
    //SHOOT("Shoot", Shoot.class),
    OFFSET5BALL("Offset 5 Ball", Offset5Ball.class),
    //CIRCLE("Test Circle", TestCircle.class),
    //STRAIGHT5("Test Straight5", TestStraight5.class),
    //STRAIGHT10("Test Straight10", TestStraight10.class),
    //HEADING("Test Heading", TestHeading.class),
    TWOBALL("Two Ball", Two_Ball.class),
    STEALTWOBALL("Two Ball then Hangar", TwoBallAndSteal.class),
    HIDETWOBALL("Two Ball then hide", TwoBallAndHide.class),
    SLAMJAM("Two Ball then SlamJam", SlamJam.class),
    THREEBALL("Two Ball and another", Billiards3Ball.class)

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