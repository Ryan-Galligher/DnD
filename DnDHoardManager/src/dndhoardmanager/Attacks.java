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
public class Attacks     //This holds not only multiple attacks, but also a single pairing of to hit dice and damage dice
{
    private final Dice[] toHitCombo;
    private final Dice[] toHurtCombo;
    private final String[] descriptions;
    

    public Attacks(Dice[] toHitCombo, Dice[] toHurtCombo, String[] descriptions)    //The items put in the arrays here are the attacks that will be done sequentially, so all within the Dice[] will be done
    {
        this.toHitCombo=toHitCombo;
        this.toHurtCombo=toHurtCombo;
        this.descriptions=descriptions;
    }
    
    public int getLength(){return descriptions.length;}
    public String getDescription(int spot){return descriptions[spot];}
    public int getHitModifier(int spot){return toHitCombo[spot].getModifier();}
    public String getAllDescriptions()
    {
        String total = "";
        for (String description : descriptions) {
            total += description + ":";
        }
        return total;
    }
    
    public int getOneHitNumber(int spot, boolean advantage)
    {
        int size=0;
        int roll=0;
        if(advantage)
        {
            size=toHitCombo[spot].roll();
        }
        roll=toHitCombo[spot].roll();
        
        return ((size>roll) ? size : roll);
    }
    public int getOneDamageNumber(int spot)
    {
        int size=0;
        int roll=0;
        roll=toHurtCombo[spot].roll();
        
        return ((size>roll) ? size : roll);
    }
    public int getOneDamageNumberNoModifier(int spot){return getOneDamageNumber(spot)-toHurtCombo[spot].getModifier(); }
    
    
}
