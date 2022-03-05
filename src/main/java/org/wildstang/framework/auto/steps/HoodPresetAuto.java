package org.wildstang.framework.auto.steps;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.year2022.subsystems.Hood.Hood;

public class HoodPresetAuto extends AutoStep{

    private Hood hood;

    private double preset;
    
    public HoodPresetAuto(double preset){
        this.preset = preset;
        hood = (Hood) Core.getSubsystemManager().getSubsystem(WSSubsystems.HOOD.getName());
    }

    public HoodPresetAuto(){
        this(Hood.preset1);
    }

    
    @Override
    public void initialize() {
        hood.autoPreset(preset);
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

    
    
