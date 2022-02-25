package org.wildstang.framework.auto.steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.Hood.Hood;

public class HoodPresetAuto extends AutoStep {

    private Hood hood;

    private boolean preset;

    public HoodPresetAuto(boolean preset){
        this.preset = preset;
        hood = (Hood) Core.getSubsystemManager().getSubsystem(WSSubsystems.HOOD_PRESET.getName());
    }

    public HoodPresetAuto(){
        this(true);
    }

    
    @Override
    public void initialize() {
        if (preset){
            Input dpad_left;
            hood.inputUpdate(dpad_left);
            //dpad_left is a placeholder for the preset we want. I am basing this code to work with preset3, therefore dpad_left is the corresponding input.
            //If we base the auto code off of a different preset, we will change the input
        }
    }

    @Override
    public void update() {
        setFinished(true);
        
    }

    @Override
    public String toString() {
        return "Hood Preset Auto";
    }
        
    }

    
    
}