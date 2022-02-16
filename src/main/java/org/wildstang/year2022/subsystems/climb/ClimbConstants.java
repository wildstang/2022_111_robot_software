package org.wildstang.year2022.subsystems.climb;

public class ClimbConstants{

    public final double DEPLOY_PERCENT_SPEED = 0.5;  //speed for deploying climb. 1.0 used for retracting climb.
    public final double RETRACT_PERCENT_SPEED = 1;

    public final double RETRACTED_POS = 5; //Encoder tick threshold for retracted position, 0 min
    public final double EXTENDED_POS = 86; //Encodr tick threshold for exended position, 88.5 max
    public final double DEPLOY_POS = 75;//Encodr tick threshold for deployed at mid bar
    

}