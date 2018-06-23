/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndminions;
import dndhoardmanager.*;
/**
 *
 * @author Ryan
 */
public class Mastiff extends Minions{
    public Mastiff()
    {
        super.hp=5;
        super.ac=12;
        super.name="Mastiff";
        super.combinations=new Attacks[]{};
    }
    
    public Mastiff(int wizlv, int proficiency, int acBoost)
    {
        setUp( wizlv,  proficiency,  acBoost);
    }
    
    @Override
    public void setUp(int wizlv, int proficiency, int acBoost)
    {
        super.hp=5;
        super.ac=12+acBoost;
        super.name="Mastiff";
        
        super.combinations=new Attacks[]
        {                                   //the different Attacks in Attack[] are the different combos that the creature could attack with on their turn, while diff attacks in Attack() will all be done consecutively
            new Attacks(
                new Dice[]{
                    new Dice(20,1,3)},
                new Dice[]{
                    new Dice(6,1,1)},
                new String[]{name + " Bite, Range 5ft piercing, Target make DC11 Strength throw or prone"}),
        };
    }
    
    @Override
    /**
     * returns all of the attacks that the minion made, and each attack has 3 parts: attack roll, damage, and description (so things that get special stuff can do so)
     */
    public void decreaseHealth(int amount){super.hp-=amount;}
    @Override
    public boolean shouldRemove(){return(super.isDead());}
}
