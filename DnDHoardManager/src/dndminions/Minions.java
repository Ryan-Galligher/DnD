/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndminions;
import dndhoardmanager.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ryan
 */
public abstract class Minions {
    protected int hp;
    protected int ac;
    protected Attacks[] combinations;
    protected String name;
    
    public static final int ATTACKROLLPLACE=0;
    public static final int DAMAGEPLACE=1;
    public static final int DESCRIPTIONPLACE=2;
    public static final int ISCRITICALPLACE=3;
    
    public int getHp(){return hp;}
    public int getAc(){return ac;}
    public String getName(){return name;}
    
    public void setHp(int hp){this.hp=hp;}
    public void setAc(int ac){this.ac=ac;}

    /**
     * Returns the Descriptions, with consecutive attacks broken by ":" and different combos broken by "\n"
     * @return 
     */
    public String getDescriptions()
    {
        String collection="";
        for(int row = 0; row < combinations.length; row++)
        {
            for (int column=0; column < combinations[row].getLength(); column++)
            {
                collection+=combinations[row].getDescription(column);
                if(column+1!=combinations[row].getLength())
                    collection+=":";
            }
            if(row+1 != combinations.length)
                collection+="\n";
        }
        return collection;
    }
    
    abstract public void setUp(int wizlv, int proficiency, int acBoost);
    abstract public void decreaseHealth(int amount);
    public String[][] attack()
    {
        int attackChoice=0;
        //ADD IN THING TO GIVE POP UP ASKING WHICH ATTACK TO DO
        //THEN CALCULATE ATTACK ROLL AND DAMAGE AND ADD IT TO DOUBLE MATRIX
        if(combinations.length > attackChoice+1)
        {
            while(combinations.length > attackChoice+1)
            {
                if ( JOptionPane.showConfirmDialog(new JFrame(), 
                    "There are " + combinations.length + " attacks that " + name + " can do. Do you want to do the combo: [ " + combinations[attackChoice].getAllDescriptions() + " ] ?", 

                    "Exitting Window", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    break;
                }
                attackChoice++;
            }
        }
        
        //NEED TO ASK FOR ADVANTAGE
        //Later, should change to have this already know who you are attacking, then read if creatures get advantage to hit it, then act accordingly
        boolean advantage = false;
        boolean disadvantage = false;
        if (JOptionPane.showConfirmDialog(new JFrame(), 
            "Does "+name+" attack under normal conditions (No advantage/disadvantage) ", 
            "Exitting Window", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
        {
            if (JOptionPane.showConfirmDialog(new JFrame(), 
            "Does "+name+" get advantage", 
            "Exitting Window", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                advantage = true;
            else
                disadvantage=true;
        }
        
        
        //combinations[attackChoice] gives back an Attack from the Attacks[], and so the getLength() of an Attack gives the # of individual hits that that creature can do
        String[][] attacksMade = new String[combinations[attackChoice].getLength() ][4];       //4 Places: attack to hit, damage, and description, and isCritical
        int onHitRoll;
        int damageRoll;
        int natBonus=0;
        boolean isCritical = false;
        for (int i = 0; i < combinations[attackChoice].getLength(); i++)    //for each attack in the chosen attack path
        {
            onHitRoll=combinations[attackChoice].getOneHitNumber(i, advantage, disadvantage);
            if(onHitRoll==20+combinations[attackChoice].getHitModifier(i)){
                damageRoll=combinations[attackChoice].getOneDamageNumber(i);
                natBonus=combinations[attackChoice].getOneDamageNumberNoModifier(i);
                isCritical=true;
                System.out.println("\t\t\tThe attack was critical, with a normal damage of " + damageRoll + " plus a bonus roll of " + natBonus);
                damageRoll+=natBonus;
            }
            else
                damageRoll=combinations[attackChoice].getOneDamageNumber(i);
            attacksMade[i]=new String[]{"" +onHitRoll, "" + damageRoll, combinations[attackChoice].getDescription(i), "" + isCritical };
        }
        return attacksMade;
    }
    protected boolean isDead(){return ((hp <= 0));}
    abstract public boolean shouldRemove();
    
}
