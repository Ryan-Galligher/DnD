/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndDice;

import java.util.Random;

/**
 * Holds all of the possible dice rolls that can be run, for either to hit/damage/saves/etc, depending on how it is implemented.
 * @author Ryan
 */
public class Dice {
    private final int diceTypeValue;
    private final DiceTypes diceType;
    private final int diceNum;
    private final int modifier;
    /**
     * A list of the the allowed forms of dice that can be used by the {@link Dice} class. <b>d0 is for constant values.</b>
     */
    public static enum DiceTypes{d20, d12, d10, d8, d6, d4, d2, d0};    //Types of "dice". A d0 indicates a constant number. So in practice, a 0d0 returns 0 and a 5d0 returns 5.//Types of "dice". A d0 indicates a constant number. So in practice, a 0d0 returns 0 and a 5d0 returns 5.
    
    /**
     * 
     * @param diceType The type of dice (d2,d4,d8,etc) that is to be rolled
     * @param diceNum The number of times that the dice is to be rolled
     * @param modifier The constant number that is to be added to the roll
     */
    public Dice(DiceTypes diceType, int diceNum, int modifier)
    {
        this.diceType=diceType;
        diceTypeValue=convertDiceTypesToInt(diceType);
        this.diceNum=diceNum;
        this.modifier=modifier;
    }
    
    public final short convertDiceTypesToInt(DiceTypes dice)
    {
        switch(diceType)
        {
            case d2: 
                return 2;
            case d4: 
                return 4;
            case d6:
                return 6;
            case d8:
                return 8;
            case d10: 
                return 10;
            case d12:
                return 12;
            case d20:
                return 20;
            default:
                return 0;
        }
    }
    
    /**
     * Takes the type of dice, the number of times to roll the dice, and the modifier to add to the roll.
     * @return The value calculated from the roll
     */
    public int roll()
    {
        if(diceTypeValue==0)
            return 0;
        if(diceTypeValue==1)
            return 1;
        if(diceNum==0)
            return diceTypeValue;
        int total=0;
        Random rand = new Random();
        for (int i = 0; i < diceNum; i++)
        {
            total += rand.nextInt(diceTypeValue + 1);    //if say a d8, would generate num 0-7, so add 1 to get 1-8
        }
        return total+modifier;
    }
    
    /**
     * @return the modifier constant that was given.
     */
    public int getModifier(){return modifier;}
    
}
