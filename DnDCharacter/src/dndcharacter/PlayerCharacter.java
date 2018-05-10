/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndcharacter;
import dndMoney.Wallet;
import dndMoney.Money;
import dndClasses.Classes;
import dndBackground.Background;

/**
 *
 * @author ryan
 */
public interface PlayerCharacter extends Character
{
    //All enumerated types for ease of use
    public static enum Skill{ACROBATICS,ANIMALHANDLING,ARCANA,ATHLETICS,DECEPTION,HISTORY,INSIGHT,INTIMIDATION,INVESTIGATION,MEDICINE,NATURE,PERCEPTION,PERFORMANCE,PERSUASION,RELIGION,SLEIGHTOFHAND,STEALTH,SURVIVAL};
    

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
    
    //All methods associated with skills 
    public short getSkill(Skill type);
    public void modifySkill(Skill type, short amount);  //Specifically for when something like feat affects a skill
    public void setSkillProficiency(Skill type, boolean hasProficiency);
    public void setSkillExpertiese(Skill type, boolean hasExpertiese);
    
    public void updateSkills();
    
    //Holds Classes
    public Classes getPlayerClass();
    public void setPlayerClass(Classes classType);
    
    //Holds Background
    public Background getPlayerBackground();
    public void setPlayerBackground();
    
    
}
