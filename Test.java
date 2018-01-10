/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rollin;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author MichaelAlbert
 */
public class Test {

    static Random R = new Random();

    public static void main(String[] args) {
        
        // Make the obivious substitution
        Rollin r = new Kral(new int[6]);
        
        int trials = 10;
        
        for (int i = 0; i < trials; i++) {
            System.out.println("Trial " + i);
            int[] dice = new int[6];
            for (int ii = 0; ii < 6; ii++) {
                dice[ii] = R.nextInt(6) + 1;
            }
            System.out.println("Initial dice: " + Arrays.toString(dice));
            
            int[] dc = Arrays.copyOf(dice, 6);
            
            int rolls = 0;
            do {
                r.setDice(dc);
                if (r.isComplete() || rolls > 100) break;
                rolls++;
                int roll = R.nextInt(6) + 1;
                int index = r.handleRoll(roll);
                if (0 <= index && index < 6) {
                    dc[index] = roll;
                }

            } while (true);
            
            System.out.println(r.isComplete() + " " + rolls);
            
        }
    
    }
    
}
