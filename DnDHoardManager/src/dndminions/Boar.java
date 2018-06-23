/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndminions;

import dndhoardmanager.Attacks;
import dndhoardmanager.Dice;

/**
 *
 * @author Ryan
 */
public class Boar extends Minions{
       
    public Boar()
    {
        super.hp=11;
        super.ac=11;
        super.name="Boar";
        super.combinations=new Attacks[]{};
    }
    
    public Boar(int wizlv, int proficiency, int acBoost)
    {
        setUp( wizlv,  proficiency,  acBoost);
    }
    
    @Override
    public void setUp(int wizlv, int proficiency, int acBoost)
    {
        super.hp=11;
        super.ac=11+acBoost;
        super.name="Boar";
        
        super.combinations=new Attacks[]
        {                                   //the different Attacks in Attack[] are the different combos that the creature could attack with on their turn, while diff attacks in Attack() will all be done consecutively
            new Attacks(
                new Dice[]{
                    new Dice(20,1,3)},
                new Dice[]{
                    new Dice(6,1,1)},
                new String[]{name + " Tusk, Range 5ft, Don't forget Charge"}),
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
