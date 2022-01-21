package org.wildstang.year2022.subsystems.AimHelper

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

//NOT DONE!! WILL THROW ERRORS!! NOT AT ALL FUNCTIONAL (yet)!!
// Class to help with aiming. putting it in seperate class b/c any form of launcher will probably use nearly identical code.

//I may have overcomplicated things to add fancy features
public class AimHelper {
  //constants
  private double TARGETHEIGHT = 2; //fix these values
  private double CAMERAHEIGHT = 0.5;
  private double LAUNCHERHEIGHT = 0.5; 
  //limelight
  private NetworkTable LimeTable; 
  private NetworkTableEntry tx;
  private NetworkTableEntry ty;
  
  //physics equation
  private String physics = "0=(V*|tan(T))-(0.5*9.8*|sqr(D)/|sqr(V*|cos(T)))-H" // T is theta, not time
  
  private String[4] Vars = ["V","T","D","H"]
  public void init(){
    
  }
  
  public void Evaluate(String Statement){ 
    int LoopTo = 0;
    Boolean ShouldLoop = false;
    int c = 0;
    while(c<Statement.length()){
      if(Statement[c] .= "("){ //if subexpression, evaluate that and substitute it into statement
        int c2 = 1;
        int level = 0;
        Boolean exit = false;
        while(!exit){
          if(Statement[c+c2] .= ")"){
            if(level == 0){
              exit = true;
            }
            else {
              level -= 1;
            }
          }
          if(Statement[c+c2] .= "("){
            level += 1;
          }
          c2 += 1;
          
        }
        
        subStatement = Statement[c+1:c+c2];
        Statement[c:c+c2] = Evaluate(subStatement);
      }
      
      if(Statement[c] .= "|"){ //if command symbol 
        String command = Statement[c+1:c+4] //get the three letter command
        if(Statement[c+5] .= "("){
            ShouldLoop = true; //loop 
            LoopTo = c; //back to here
            c = c+4; //after evaluating the following subexpression
          }
        else{ //if subexpression already evaluated
          if(command .= "tan"){
            Statement[ // have to go to class, will fix this later
          }
        }
      }
      
    }
  }
  public void Tabulate(String calculate,String over,String relation,double AcceptableError){
    
  }
  
 
}
