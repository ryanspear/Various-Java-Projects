package rollin;

import java.util.*;

public class Kral{


    public static void main(String [] args){
        int dice[] = new int[6];
        dice = firstRoll();
        System.out.println("Woohoo");
        

    }


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
    


}
