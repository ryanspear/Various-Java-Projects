package rollin;

import java.util.*;

public class Kral extends Rollin{


    public static void main(String [] args){

        

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
