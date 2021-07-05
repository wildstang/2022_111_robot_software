package org.wildstang.framework.core;

/**
 * Used in implementations to enumerate AutoProgram.
 * We would like to have a super class for this structure, however,
 * Java does not support enums extending classes.
 */
public interface AutoPrograms {

    /**
     * Returns the name mapped to the AutoProgram.
     * @return Name mapped to the AutoProgram.
     */
    public String getName();

    /**
     * Returns AutoProgram's class.
     * @return AutoProgram's class.
     */
    public Class<?> getProgramClass();
}