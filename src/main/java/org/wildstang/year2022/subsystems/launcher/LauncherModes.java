package org.wildstang.year2022.subsystems.launcher;

public enum LauncherModes{
    FENDER_SHOT (0.4, 2200.0, 0.516),
    TARMAC_EDGE (0.4, 2200.0, 1.113),
    LAUNCH_PAD(0.5, 2778.0, 1.297),
    ZERO(0, 0, 0.070);

    private final double SPEED;//motor % output
    private final double RPM;//measured velocity of the flywheel
    private final double HOODMA3;//MA3 value of the hood at this point

    LauncherModes(double SPEED, double RPM, double hoodMA3) {
        this.SPEED = SPEED;
        this.RPM = RPM;
        this.HOODMA3 = hoodMA3;
    }
    public double getSpeed(){ 
        return SPEED;
    }
    public double getRPM(){
        return RPM;
    }
    public double getHood(){
        return HOODMA3;
    }
}
