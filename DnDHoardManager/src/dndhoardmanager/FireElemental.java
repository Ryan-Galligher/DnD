/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndhoardmanager;

/**
 *
 * @author Ryan
 */
public class FireElemental extends Minions{
     
     public FireElemental()
    {
        super.hp=2;
        super.ac=2;
        super.name="Fire Elemental";
        super.combinations=new Attacks[]{};
    }
    
    public FireElemental(int wizlv, int proficiency, int acBoost)
    {
        this.setUp( wizlv,  proficiency,  acBoost);
    }
    
     @Override
    public void setUp(int wizlv, int proficiency, int acBoost)
    {
        super.hp=102;
        super.ac=13+acBoost;
        super.name="Fire Elemental";
        
        super.combinations=new Attacks[]
        {                                   //the different Attacks in Attack[] are the different combos that the creature could attack with on their turn, while diff attacks in Attack() will all be done consecutively
            new Attacks(
                new Dice[]{
                    new Dice(20,1,6),new Dice(20,1,6)},
                new Dice[]{
                    new Dice(6,2,3), new Dice(6,2,3)},
                new String[]{name + " Touch, reach 5ft, set fire to creature who takes 1d10 fire each turn",name + " Touch, reach 5ft, set fire to creature who takes 1d10 fire each turn"})
            
        };
    }
    
    @Override
    public void decreaseHealth(int amount){super.hp-=amount;}

    @Override
    public boolean shouldRemove(){return(super.isDead());}
    
}
