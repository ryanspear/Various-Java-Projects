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
        dice = firstRoll();
        //int[] oneSet = {1,1,3,3,5,5};
        //dice = oneSet;
        
        for(int i = 0; i < dice.length; i++){
            System.out.print(dice[i] + ", ");
        }
        System.out.println("");
        System.out.println("Roll: 5");
        Kral obj = new Kral(dice);
       

        int[][][] newIndices = setIndices;
        System.out.println(obj.handleRoll(4));
        
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
            return 6;
        }else{
            for (int[][] si : setIndices){
                if (isSet(si[0])){
                    set = si[0];
                    noSet = si[1];
                    return oneSetDeal(set, noSet, roll);
                    //break;
                } else if(isSet(si[1])){
                    set = si[1];
                    noSet = si[0];
                    return oneSetDeal(set, noSet, roll);
                    //break;
                }
            }

            System.out.println("No sets made from initial roll");
            return noSetsDeal(roll);   
        }
    }

    public int oneSetDeal(int[] set, int[] noSet, int roll){


        /** if the 2 of 3 in noSet are a pair do this */
        if(pair(noSet) != null){
            //System.out.println("There is a pair: ");
            int[] t = pair(noSet); // has the indices of the two that are a pair.
            //System.out.println(dice[t[0]] + ", " + dice[t[1]]);
            if(roll == dice[t[0]]){
                for(int i = 0; i < noSet.length; i++){
                    if(dice[noSet[i]] != dice[t[0]]){
                        return noSet[i];
                    }  
                }
            }

            int[] sortedNoSet = sortNoSet(noSet);

            int average = (dice[sortedNoSet[0]] + dice[sortedNoSet[2]])/2;
            if(roll == average){
                return sortedNoSet[1];
            }
            
        }
        
        /** if 2 of the 3 in noSet is consecutive do this */
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

        /** if it's neither, do this */
        
        int[] sortedNoSet = sortNoSet(noSet);

        int average = (dice[sortedNoSet[0]] + dice[sortedNoSet[2]])/2;
        if(roll == average){
            return sortedNoSet[1];
        }
        if(roll == dice[sortedNoSet[1]] + 1){
            return sortedNoSet[0];
        } else {
            if(roll == dice[sortedNoSet[1]] - 1){
                return sortedNoSet[2];
            }
        }
            
        return 6;



    }


    public int noSetsDeal(int roll){
        for (int[][] si : setIndices){
            int[] set1 = si[0];
            int[] set2 = si[1];
            int swap = oneSetDeal(set1, set2, roll);
            if(swap != 6){
                return swap;
            }
            
            swap = oneSetDeal(set2, set1, roll);
            if(swap != 6){
                return swap;
            }
        }

        return 6;


    }
    
    /** method sorts the 3 dice which aren't in a set into
        numerical order to find the median
        @return the sorted noSet
        @param takes the unsorted noSet
    */
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
   
}
