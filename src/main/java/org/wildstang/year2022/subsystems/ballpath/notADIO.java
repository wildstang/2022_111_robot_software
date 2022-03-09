package org.wildstang.year2022.subsystems.ballpath;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;

public class notADIO {

    private DigitalInput dio;

    public notADIO(int channel){
        this.dio = new DigitalInput(channel);
    }

    public boolean get(){
        return dio.get();
    }
    
}
