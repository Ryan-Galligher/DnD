/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndhoardmanager;

import java.util.Random;

/**
 * Holds all of the possible dice rolls that can be run, for either to hit or damage, depending on how it is implemented.
 * @author Ryan
 */
public class Dice {
    private final int diceType;
    private final int diceNum;
    private final int modifier;
    
    /**
     * 
     * @param diceType The type of dice (d2,d4,d8,etc) that is to be rolled
     * @param diceNum The number of times that the dice is to be rolled
     * @param modifier The constant number that is to be added to the roll
     */
    public Dice(int diceType, int diceNum, int modifier)
    {
        this.diceType=diceType;
        this.diceNum=diceNum;
        this.modifier=modifier;
    }
    
    /**
     * Takes the type of dice, the number of times to roll the dice, and the modifier to add to the roll.
     * @return The value calculated from the roll
     */
    public int roll()
    {
        if(diceType==0)
            return 0;
        if(diceType==1)
            return 1;
        if(diceNum==0)
            return diceType;
        int total=0;
        Random rand = new Random();
        for (int i = 0; i < diceNum; i++)
        {
            total += rand.nextInt(diceType + 1);    //if say a d8, would generate num 0-7, so add 1 to get 1-8
        }
        return total+modifier;
    }
    
    /**
     * @return the modifier constant that was given.
     */
    public int getModifier(){return modifier;}
    
}
