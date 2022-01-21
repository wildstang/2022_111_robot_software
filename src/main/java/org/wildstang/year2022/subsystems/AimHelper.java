package org.wildstang.year2022.subsystems.AimHelper

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

//NOT DONE!! WILL THROW ERRORS!! NOT AT ALL FUNCTIONAL (yet)!!
// Class to help with aiming. putting it in seperate class b/c any form of launcher will probably use nearly identical code.

//I may have overcomplicated things to add fancy features (make computer approximate solution to problem rather than manually/empericaly solving it
// During runtime, will use lookup table. Class includes functions necessary to generate lookup table. I a little bit think I should have written the table generator in python (to run on desktop and because python is inerprative which makes it good at this sort of thing)
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
  private String physics = "0=(V*|tan(T) )-(0.5*9.8*|sqr(D) /|sqr(V*|cos(T) ) )-H" // T is theta, not time
  
  private String[4] Vars = ["V","T","D","H"]
  public void init(){
    
  }
  
  public double Evaluate(String Statement,double[] Values){ 
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
        Statement[c:c+c2] = (String) Evaluate(subStatement,Values);
        c = 0;
      }
      
      else if(Statement[c] .= "|"){ //if command symbol 
        String command = Statement[c+1:c+4] //get the three letter command
        if(Statement[c+4] .= "("){
            ShouldLoop = true; //loop 
            LoopTo = c; //back to here
            c = c+3; //after evaluating the following subexpression
          }
        else{ //if subexpression already evaluated
          if(command .= "tan"){ //substitute "|tan(blah blah blah) for the proper value
            Statement[c:c+GetNextOf(Statement[c+4:]," ")] = (String) Math.tan((double) Statement[c+4:c+GetNextOf(Statement[c+5:]," ")]);
          }
          if(command .= "cos"){ //substitute "|cos(blah blah blah) for the proper value
            Statement[c:c+GetNextOf(Statement[c+4:]," ")] = (String) Math.cos((double) Statement[c+4:c+GetNextOf(Statement[c+5:]," ")]);
          }
          if(command .= "sqr"){ //substitute "|sqr(blah blah blah) for the proper value
            Statement[c:c+GetNextOf(Statement[c+4:]," ")] = (String) Math.pow((double) Statement[c+4:c+GetNextOf(Statement[c+5:]," ")],2);
          }
                                                                   
        }
      }
      else if(Arrays.asList(Vars).contains(Statement[c])){
        Statement[c] = (String) //NOT DONE will fix this sometime.
      }
      
      
    }
  }
  public int GetNextOf(String next,String element){
    int c = 0;
    while(c<next.length()){
      if(next[c] .= element){
        return c;
        c = 999999999;
      }
      c += 1; 
    }
    return 0;
  }
  public void Tabulate(String calculate,String over,String relation,double AcceptableError){
    
  }
  
 
}
