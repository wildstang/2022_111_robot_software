package org.wildstang.framework.auto.steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.Hood.Hood;

public class HoodPresetAuto extends AutoStep{

    private Hood hood;

    private boolean preset;

    DigitalInput dpad_left;

    public HoodPresetAuto(boolean preset){
        this.preset = preset;
        hood = (Hood) Core.getSubsystemManager().getSubsystem(WSSubsystems.HOOD.getName());
    }

    public HoodPresetAuto(){
        this(true);
    }

    
    @Override
    public void initialize() {
        if (preset){
            hood.autoPreset();
        }else {
            hood.resetState();
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

    
    
