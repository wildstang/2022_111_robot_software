package org.wildstang.year2022.subsystems.swerve;

public class SwerveSignal {

    private double[] speed;
    private double[] angle;
    private double maxSpeed;

    /**contains motor speeds, robot relative angles in bearing-degrees 
     * @param i_speed double[] for the speed of each module, in [0,1] signal
     * @param i_angle double[] for the angle of the module, in robot centric bearing degrees
    */
    public SwerveSignal(double[] i_speed, double[] i_angle){
        this.speed = i_speed;
        this.angle = i_angle;
    }

    /**ensures all speed values are below 1, and scales down if needed */
    public void normalize(){
        maxSpeed = 1;
        for (int i = 0; i < speed.length; i++){
            if (Math.abs(speed[i]) > maxSpeed){
                maxSpeed = Math.abs(speed[i]);
            }
        }
        for (int i = 0; i < speed.length; i++){
            speed[i] /= maxSpeed;
        }
    }
    /**speed is normalized value [0, 1] 
     * @param i_module the module to get the speed from (1 through 4)
     * @return double for the speed to set that module to
    */
    public double getSpeed(int i_module){
        return speed[i_module];
    }
    /**angle is robot centric, in bearing degrees 
     * @param i_module the module to get the angle from (1 through 4)
     * @return double for the angle to set that module to
    */
    public double getAngle(int i_module){
        return angle[i_module];
    }
    
}
