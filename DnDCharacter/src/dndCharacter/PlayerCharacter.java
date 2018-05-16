/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndCharacter;
import dndMoney.Wallet;
import dndMoney.Money;
import dndClasses.Classes;
import dndBackground.Background;
import dndRace.Race;

/**
 *
 * @author ryan
 */
public interface PlayerCharacter extends Character
{
    //All enumerated types for ease of use
    

    //Dealing with dying, death saves, etc
    public void rollDeathSave();
    public short getCurrentDeathSaves();
    public short getTotalDeathSaves();
    
    //Methods dealing with amount of money
    public Wallet getMoney();
    public void setMoney(Wallet money);
    public boolean addMoney(Money money);
    public boolean payMoney(Money money);
    public boolean payMoney(Money[] money);
    
    //Proficiency bonus
    public short getProficiencyBonus();
    public void updateSkills();
    
    //Holds Classes
    public Classes[] getPlayerClasses();
    public void setPlayerClass(Classes classType);
    
    //Holds Background
    public Background getPlayerBackground();
    public void setPlayerBackground();
    
    //Holds Race
    public Race getPlayerRace();
    public void setPlayerRace(Race playerRace);
    
}
