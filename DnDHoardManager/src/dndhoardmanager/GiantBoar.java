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
public class GiantBoar extends Minions{
    boolean relentlessUsed=false;

    public GiantBoar()
    {
        super.hp=2;
        super.ac=2;
        super.name="Giant Boar";
        super.combinations=new Attacks[]{};
    }
    
    public GiantBoar(int wizlv, int proficiency, int acBoost)
    {
        setUp( wizlv,  proficiency,  acBoost);
    }
    
    @Override
    public void setUp(int wizlv, int proficiency, int acBoost)
    {
        super.hp=42;
        super.ac=12+acBoost;
        super.name="Giant Boar";
        
        super.combinations=new Attacks[]
        {                                   //the different Attacks in Attack[] are the different combos that the creature could attack with on their turn, while diff attacks in Attack() will all be done consecutively
            new Attacks(
                new Dice[]{
                    new Dice(20,1,5)},
                new Dice[]{
                    new Dice(6,2,3)},
                new String[]{name + " Tusk, Range 5ft Slashing, Charge mechanic"}),
        };
    }
    
    @Override
    /**
     * returns all of the attacks that the minion made, and each attack has 3 parts: attack roll, damage, and description (so things that get special stuff can do so)
     */
    public void decreaseHealth(int amount)
    {
        if(!relentlessUsed && super.hp-amount<=0)
        {
            if(amount<=10)
            {
                super.hp=1;
                relentlessUsed=true;
            }
            else
                super.hp-=amount;
        }
        else
            super.hp-=amount;
    }
    @Override
    public boolean shouldRemove(){return(super.isDead());}

}
