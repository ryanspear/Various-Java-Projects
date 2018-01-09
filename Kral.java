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
        int[] set = new int[3];
        int[] noSet = new int[3];
        if(isComplete()){
            System.out.println("true 2 sets");
        }else{
            for (int[][] si : setIndices){
                if (isSet(si[0])){
                        set = si[0];
                        noSet = si[1];
                        break;
                    } else if(isSet(si[1])){
                        set = si[1];
                        noSet = si[0];
                        break;
                }
            }
           System.out.println("Set:");
           for(int i = 0; i < set.length; i++){
                System.out.println("position: " + set[i] + ", Value: " + dice[set[i]]);
            }

           System.out.println("No set:");
           for(int i = 0; i < noSet.length; i++){
               System.out.println("position: " + noSet[i] + ", Value: " + dice[noSet[i]]);
           }

            
        }

        replacee(noSet);
        if(pair(noSet) != null){
            System.out.println("There is a pair");
            int[] t = pair(noSet); // has the indices of the two that are a pair.
            if(roll == dice[t[0]]){
                // swap for the other number, we need the index of it!!
                for(int i = 0; i < noSet.length; i++){
                    if(dice[noSet[i]] != dice[t[0]]){
                        dice[noSet[i]] = roll;
                    }  
                }
            }
        }
        if(consecutive(noSet) != null){
            System.out.println("There is a consecutive");
        }
        return 6;
    }


    /** Method tells us whether the noSet has a pair or a consecutive of two. This is needed to decide
        whether to take the new roll or not
        @returns void, but this should be changed later to return a couple of possible options:
        a) the index of the number that isn't a pair or consecutive so it can be swapped easily
        b) the indices of the two that are a pair or consecutive
        c) true or false?
        d) something else I haven't thought of.
        @param noSet is the indices of the leftover values after the first set has been found.
    */
    public void replacee(int[] noSet){
        int[][] pairIndices = new int[][]{
            {0,1},{0,2},{1,2}
        };

        for(int[] su : pairIndices){
            if(dice[noSet[su[0]]] == dice[noSet[su[1]]]){ // if two are equal, its a pair
                System.out.println("We have a pair");
                System.out.println("1 at index: " + noSet[su[0]] + " with value: " + dice[noSet[su[0]]]);
                System.out.println("other at index: " + noSet[su[1]] + " with value: " + dice[noSet[su[1]]]);
                System.out.println("The one to be replaced should be!!: ");
                }
            // if one is one less or one greater than the other then it is consecutive
            if(dice[noSet[su[0]]] == dice[noSet[su[1]]] +1 || dice[noSet[su[0]]] == dice[noSet[su[1]]] -1){
                System.out.println("We have a consecutive");
                System.out.println("1 at index: " + noSet[su[0]] + " with value: " + dice[noSet[su[0]]]);
                System.out.println("other at index: " + noSet[su[1]] + " with value: " + dice[noSet[su[1]]]);
            }
        }
    }


    /* if(pair() != null){
        int[] pair = pair();
        if(roll == pair[0]){
           
        if(consecuvite != null){
    */      
        
    public int[] pair(int[] noSet){
        int[] output = null;
        int[][] pairIndices = new int[][]{
            {0,1},{0,2},{1,2}
        };

        for(int[] su : pairIndices){
            if(dice[noSet[su[0]]] == dice[noSet[su[1]]]){
                output = new int[3];
                output[0] = noSet[su[0]];
                output[1] = noSet[su[1]];
                return output;
            }
        }

        return output;
    }


    public int[] consecutive(int[] noSet){
            int[] output = null;
            int[][] pairIndices = new int[][]{
                {0,1},{0,2},{1,2}
            };

            for(int[] su : pairIndices){
                if(dice[noSet[su[0]]] == dice[noSet[su[1]]] +1 || dice[noSet[su[0]]] == dice[noSet[su[1]]] -1){
                    output = new int[2];
                    output[0] = noSet[su[0]];
                    output[1] = noSet[su[1]];
                    return output;
                }
            }

            return output;
        }

        

    //public boolean consecutive(int[] noSet){
        
        
    
}
