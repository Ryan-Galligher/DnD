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
public class GiantGoat extends Minions{
    
    public GiantGoat()
    {
        super.hp=2;
        super.ac=2;
        super.name="Giant Goat";
        super.combinations=new Attacks[]{};
    }
    
    public GiantGoat(int wizlv, int proficiency, int acBoost)
    {
        setUp( wizlv,  proficiency,  acBoost);
    }
    
    @Override
    public void setUp(int wizlv, int proficiency, int acBoost)
    {
        super.hp=19;
        super.ac=11+acBoost;
        super.name="Giant Goat";
        
        super.combinations=new Attacks[]
        {                                   //the different Attacks in Attack[] are the different combos that the creature could attack with on their turn, while diff attacks in Attack() will all be done consecutively
            new Attacks(
                new Dice[]{
                    new Dice(20,1,5)},
                new Dice[]{
                    new Dice(4,2,3)},
                new String[]{name + " Ram, 5 ft one target bludgenoning. Also Make Note of Charge mechanic."}),
            
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
