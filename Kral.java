package rollin;

import java.util.*;
  

public class Kral extends Rollin{
    static int dice[];

    public Kral(int[] dice){
        super(dice);
    }
        

    /**
     * Main method initialises dice with random integers.
     * Getted is just a lil trial array to make sure getDice works, which it
     * looks like it does right now
     */
    
    public static void main(String [] args){
        //dice = firstRoll();
        int[] oneSet = {1,2,3,2,3,3};
        dice = oneSet;
        Kral obj = new Kral(dice);
       
       
        int[] getted = obj.getDice();
        for(int i = 0; i < 6; i++){
          System.out.println(getted[i]); 
          }
        obj.handleRoll(1);

        int[][][] newIndices = setIndices;
        
    }

    /** initalises the first 6 dice as random integers from 1-6
     * @return the array of 6 random die values
     */
    public static int[] firstRoll(){
        int roll[] = new int[6];
        Random rnd = new Random();
        int i = 0;
        while(i < roll.length){
        
        roll[i] = rnd.nextInt(6) +1;
        i++;
        }

        return roll;

    }
    /** this needs to be here as it is an abstract method in the parent class
     * (I think) We will write the method in this class possibly? Maybe
     * it doesn't matter where we write it. Find this out.
     */ 
    public int handleRoll(int roll){
        int[] set;
        int[] noSet;
        int[][][] newIndices = setIndices;
        if(isComplete()){
            System.out.println("true 2 sets");
        }else{
            for (int[][] si : newIndices){
                if (isSet(si[0])){
                        set = si[0];
                        noSet = si[1];
                    } else if(isSet(si[1])){
                        set = si[1];
                        noSet = si[0];
                }
            }  
        }

            // if theres a set we have it indexed.
            //
        
    

        return 6;
    }
    


}
