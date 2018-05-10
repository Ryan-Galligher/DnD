/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndcharacter;

import dndDice.Dice;

/**
 *
 * @author ryan
 */
public interface Character {
    public static enum AbilityScore{STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA};
    public static enum Health{FULL, MAX, CURRENT, TEMP};
    public static enum Travel{WALK, CLIMB, FLY, SWIM, BURROW};
    public static enum Languages{ABYSSAL, AQAN, AURAN, CELESTIAL, COMMON, DEEPSPEECH, DRACONIC, DRUIDIC, DWARVISH, ELVISH, GIANT, GNOMISH, GOBLIN, GNOLL, HALFLING, IGNAN, INFERNAL, ORC, PRIMORDIAL, SYLVAN, TERRAN, UNDERCOMMON};
    public static enum Senses{PASSIVEPERCEPTION, INITIATIVE, VISION };
    public static enum Alignment{CHAOTICGOOD, CHAOTICNEUTRAL, CHAOTICEVIL, NEUTRALGOOD, NEUTRALNEUTRAL, NEUTRALEVIL, LAWFULGOOD, LAWFULNEUTRAL, LAWFULEVIL};
    public static enum Size{TINY, SMALL, MEDIUM, LARGE, HUGE, GARGANTUAN};
    public static enum DamageType{ACID, BLUDGEONING, COLD, FIRE, FORCE, LIGHTNING, NECROTIC, PIERCING, POISON, PSYCHIC, RADIANT, SLASHING, THUNDER};

    //All methods dealing with resistances
    public DamageType[] getResistances();
    public DamageType[] getImmunities();
    public void addResistance(DamageType type);
    public void addResistance(DamageType[] types);
    public void addImmunity(DamageType type);
    public void addImmunity(DamageType[] types);
    public boolean containsResistance(DamageType type);
    public boolean containsImmunity(DamageType type);
    
    
    //All methods dealing with Alignment
    public Alignment getAlignment();
    public void setAlignment(Alignment type);
    public boolean isAlignment(Alignment type);
    
    //All methods dealing with size
    public Size getSize();
    public void setSize(Size type);
    
    //All methods dealing with Health
    public short damageBy(short amount);
    
    public short getHp(Health type);
    public void setHp(Health type, short amount);
    public void changeHp(Health type, short amount);
    public Dice.DICETYPES getHitDice();
    public void setHitDice(Dice.DICETYPES type);
    
    //Languages
    public Languages[] getLanguages();
    public void addLanguages(Languages type);
    public void removeLanguage(Languages type);
    public boolean speaks(Languages type);

    //Dealing with dying, death saves, etc
    public boolean isDying();
    public boolean isDead();
    public void setDying(boolean isDying);
    public void setDead(boolean isDead);
    
    //Dealing with AC and if attacks hit
    public short getAC();
    public short getDefaultAC();
    public boolean attackHit(int toHitValue);
    
    public void setDefaultAC(short amount);
    public void changeACBy(short amount);
    
    //Methods dealing with modes of travel inherent to character
    public short getSpeed(Travel type);
    public void setSpeed(Travel type, short speed);
    
    //Inspiration
    public short getInspiration();
    public boolean increaseInspiration();
    public boolean decreaseInspiration();
    
    //All methods associated with the 6 ability scores
    public short getAbilityScore(AbilityScore type);
    public void setAbilityScore(AbilityScore type, short value);
    public void modifyAbilityScoreMax(AbilityScore type, short value);  //modifies the cap of an ability score, such as if character given item that raises max score to 22, call this function to change max from 20 to 22, even if score is at 18 at time. Does not affect current ability score.
    public short getModifier(AbilityScore type);
    public short getSavingThrow(AbilityScore type);
    public void setAbilityScoreProficiency(AbilityScore type, boolean isProficient);
    
    public void updateAbilityScore();
    
}
