/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndminions;
import dndhoardmanager.*;


/**
 *
 * @author ryan
 */
public class Babau extends Minions {
    public Babau()
    {
        super.hp=2;
        super.ac=2;
        super.name="Babau";
        super.combinations=new Attacks[]{};
    }
    
    public Babau(int wizlv, int proficiency, int acBoost)
    {
        setUp( wizlv,  proficiency,  acBoost);
    }
    
    @Override
    public void setUp(int wizlv, int proficiency, int acBoost)
    {
        super.hp=82+wizlv;
        super.ac=16+acBoost;
        super.name="Babau";
        
        super.combinations=new Attacks[]
        {                                   //the different Attacks in Attack[] are the different combos that the creature could attack with on their turn, while diff attacks in Attack() will all be done consecutively
            new Attacks(
                new Dice[]{
                    new Dice(20,1,6), new Dice(20,1,6), new Dice(20, 1, 1)},
                new Dice[]{
                    new Dice(8,1,4), new Dice(8,1,4), new Dice(0,1,0)},
                new String[]{name + " Double Claw, reach 5ft. Use Weakening Gaze after (target make DC 13 Con save or deals only half damage with weapon attacks). Then rolls its charisma save at end of turn."}),
             new Attacks(
                new Dice[]{
                    new Dice(20, 1, 1)},
                new Dice[]{
                    new Dice(0,1,0)},
                new String[]{name + " Uses one of its spells. Then rolls its charisma save at end of turn."}),
            new Attacks(
                new Dice[]{
                    new Dice(20,1,6), new Dice(20,1,6), new Dice(20, 1, 1)},
                new Dice[]{
                    new Dice(8,1,4), new Dice(8,1,4), new Dice(0,1,0)},
                new String[]{name + " Double Weapon, each 5ft. Use Weakening Gaze after (target make DC 13 Con save or deals only half damage with weapon attacks). Then rolls its charisma save at end of turn."}),
            new Attacks(
                new Dice[]{
                    new Dice(20,1,6), new Dice(20,1,6), new Dice(20, 1, 1)},
                new Dice[]{
                    new Dice(8,1,4), new Dice(6,1,4), new Dice(0,1,0)},
                new String[]{name + " One Claw One Weapon, reach 5ft. Use Weakening Gaze after (target make DC 13 Con save or deals only half damage with weapon attacks). Then rolls its charisma save at end of turn."})
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
