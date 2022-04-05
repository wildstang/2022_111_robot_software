package org.wildstang.year2022.subsystems.EasyAuto;

import java.io.File;  
import java.io.FileWriter;  
import java.io.IOException; 
import edu.wpi.first.wpilibj.Timer;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.Input;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.hardware.roborio.inputs.WsJoystickAxis;
import org.wildstang.year2022.robot.WSInputs;
import org.wildstang.year2022.robot.WSOutputs;
import org.wildstang.year2022.robot.WSSubsystems;
import org.wildstang.hardware.roborio.outputs.WsSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.SparkMaxAnalogSensor.Mode;

/*
Have you ever waited to the very last minute to create an auto program? Have you had to use dead reckoning when you wanted pathfollowing?
Then this program is for you! Make an auto program in a bit over 15 seconds!

In writing mode, it records the buttons pressed by the driver and manipilator. Then, during auto, it plays back those buttons to run the recorded actions.

I litterally just started writing this today, so its not going to work.


*/
public class EasyAuto {
    private Stirng writeFile = "InstantAutoTest.txt";

    private double AutonomousTime = 15.0;
    private FileWriter writer;
    private Timer timer;

    public void init() {
        try { //this bit copy/pasted from w3 school tutorial
            File myObj = new File(writeFile);
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } else {
              System.out.println("File already exists.");
            }
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        writer = new FileWriter(writeFile,true);
        resetState();
        timer = new Timer();
        timer.reset();
        timer.start();
    }



    // update the subsystem everytime the framework updates (every ~0.02 seconds)
    public void update() {
        if(timer.get() >= AutonomousTime){
            timer.stop()
            writer.close()
        }
    }

    // respond to input updates
    public void inputUpdate(Input source) {
        
        writer.write((String) timer.get() +"-!_!_!:" source.getName()+"-!_!_!:"+ (String) source.getValue());

    }
    

    // used for testing
    public void selfTest() {

    }

    // resets all variables to the default state
    public void resetState() {

    }

    // returns the unique name of the subsystem
    public String getName() {
        return "EasyAuto";
    }


}
