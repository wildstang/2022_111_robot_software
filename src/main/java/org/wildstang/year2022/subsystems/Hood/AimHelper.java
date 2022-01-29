package org.wildstang.year2022.subsystems.Hood; 
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class AimHelper{
    
    private NetworkTable LimeTable;
    private NetworkTableEntry ty; //y angle
    private NetworkTableEntry tx; //x angle
    private NetworkTableEntry tv;
    
    public double x;
    public double y;

    public boolean TargetInVeiw;

    private double TargetDistance;

    private LimeConsts LC;


    public AimHelper(){ //initialize. Call before use.
        LC = new LimeConsts();
        x = 0;
        y = 0;
        TargetInVeiw = false;
        TargetDistance = 0;


        LimeTable  = NetworkTableInstance.getDefault().getTable("limelight-stang");

        ty = LimeTable.getEntry("ty");
        tx = LimeTable.getEntry("tx");
        tv = LimeTable.getEntry("tv");
    }
    private void calcTargetCoords(){ //update target coords. For internal use
        if(tv.getDouble(0) == 1){
            x = tx.getDouble(0);
            y = ty.getDouble(0);
            TargetInVeiw = true;
        }
        else{
            x = 0; //no target case
            y = 0;
            TargetInVeiw = false;
        }
    }

    private void getDistance(){ //update target dist. for internal use.
        calcTargetCoords();
        //h = lsin(0), d = lcos(0)
        // l = h/sin(0) = d/cos(0)
        // d = cos(0)*h/sin(0) = h/tan(0)
        TargetDistance = LC.TARGET_HEIGHT/Math.tan(y+(Math.PI*LC.CAMERA_OFFSET/180));
        
    }

    public double getAngle(){ //get hood angle for autoaim
        getDistance();
        return ApproximateAngle(TargetDistance);
    }
    public double ApproximateAngle(double dist){ //linear interlopation. 
        double[] dists = LC.Dists;
        int max = dists.length-1;
        int min = 0;
        int c = 0;
        boolean done = false;
        boolean exact = false;
        while(done == false){
            if(max-min <= 1){
                done = true;
                c = max;
            }
            else{
                c = (int) ((max-min)/2)+min;
                
                if(dists[c]>dist){
                    max = c;
                }
                else if(dists[c] < dist){
                    min = c;
                }
                else{
                    exact = true;
                    done = true;
                }
                
            }

            
        }
        double out = 0;
        // now c is index of nearest value (rounded up)

        if(exact){ 
            //exact is always true out of the while loop
            out = LC.Angles[c]; //we have the required angle in our databace so  use it
        }
        else{ //incase its not
            if(c-1 >= min){
                double interval = dists[c]-dists[c-1];
                double range = LC.Angles[c]-LC.Angles[c-1];
                out = (((dist-dists[c-1])/interval)*range)+dists[c-1];
            }
            else{ //outside of domain
                out = LC.Angles[c];
            }
        }
        return out;
    
    }

    

}