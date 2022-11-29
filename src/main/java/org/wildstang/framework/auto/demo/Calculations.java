package org.wildstang.framework.auto.demo;


public class Calculations {
    //rn just a black box stuff goes in and things go out4
    private double x;
    private double y;

    public Calculations(){
        x = 0;
        y = 0;
    }

    public void update(Double robDirection, Double robVelocity){
        x += Math.cos(robDirection) * robVelocity;
        y += Math.sin(robDirection) * robVelocity;
    }
    public boolean Calc(){
        if (Math.abs(x) >= 4 || Math.abs(y) >= 4)
        { 
            return true;
        }
        else return false;
    } 
}
