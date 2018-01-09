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
        // dice = firstRoll();
        int[] oneSet = {6,6,3,4,3,3};
        dice = oneSet;
        for(int i = 0; i < dice.length; i++){
            System.out.print(dice[i] + ", ");
        }
        System.out.println("");
        Kral obj = new Kral(dice);
       
       
        int[] getted = obj.getDice();
        for(int i = 0; i < 6; i++){
            System.out.println(getted[i]); 
        }

        int[][][] newIndices = setIndices;
        System.out.println(obj.handleRoll(5));
        
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
            if(isSet(set) && !isSet(noSet)) {
                System.out.println("Set:");
                for(int i = 0; i < set.length; i++){
                    System.out.println("position: " + set[i] + ", Value: " + dice[set[i]]);
                }

                System.out.println("No set:");
                for(int i = 0; i < noSet.length; i++){
                    System.out.println("position: " + noSet[i] + ", Value: " + dice[noSet[i]]);
                }
            }    
        }
        if(pair(noSet) != null){
            int[] t = pair(noSet); // has the indices of the two that are a pair.
            if(roll == dice[t[0]]){
                // swap for the other number, we need the index of it!!
                for(int i = 0; i < noSet.length; i++){
                    if(dice[noSet[i]] != dice[t[0]]){
                        return noSet[i];
                    }  
                }
            }
            if(roll == dice[t[0]]+1 || roll == dice[t[0]]-1){
                for(int i = 0; i < noSet.length; i++){
                    if(dice[noSet[i]] != dice[t[0]]){
                        return noSet[i];
                    }
                }
            }
        }
        if(consecutive(noSet) != null){ // outsource this case later on.
            int[] s = consecutive(noSet);
            for(int i = 0; i < s.length; i++){
                if(roll == dice[s[i]] + 1 || roll == dice[s[i]] - 1){
                    int total = 0;
                    for (int s_digit : s){
                        total += s_digit;
                    }
                    for (int digit : set){
                        total += digit;
                    }
                    return 15 - total;
                }
                    
            }
        }

      

        int[] sortedNoSet = sortNoSet(noSet);
        if(roll == dice[sortedNoSet[1]] + 1){
            return sortedNoSet[0];
        } else {
            if(roll == dice[sortedNoSet[1]] - 1){
                return sortedNoSet[2];
            }
        }
            
        return 6;
    }
        
    /*     Arrays.sort(sortedNoSet);
        int median = sortedNoSet[1];
        System.out.println("median is in position: " + median);
        if(roll == dice[median] + 1){
            return sortedNoSet[0];
        } else if(roll == dice[median] - 1){
            return sortedNoSet[2];
        }
        return 6;
        }*/
    

    
    public int[] sortNoSet(int[] noSet){

        
        int[] sortedNoSet = noSet;
        if(dice[noSet[0]] > dice[noSet[1]]){
            int temp = 0;
            temp = noSet[0];
            sortedNoSet[0] = sortedNoSet[1];
            sortedNoSet[1] = temp;
        }
        
        if(dice[noSet[1]] > dice[noSet[2]]){
            int temp1 = 0;
            temp1 = noSet[1];
            sortedNoSet[1] = sortedNoSet[2];
            sortedNoSet[2] = temp1;
        
          
        
            if(dice[noSet[0]] > dice[noSet[1]]){
                int temp2 = 0;
                temp2 = noSet[0];
                sortedNoSet[0] = sortedNoSet[1];
                sortedNoSet[1] = temp2;
            
            }
        }
        
        return sortedNoSet;
    }
        


        
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
