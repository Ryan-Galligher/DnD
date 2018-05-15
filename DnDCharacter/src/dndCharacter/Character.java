/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndCharacter;

import dndDice.Dice;

/**
 *
 * @author ryan
 */
public interface Character {
    /**
     * The 6 main Ability Scores that are used in DnD. These include:<p>
     * STRENGTH:Strength of character. Used for things like carrying capacity, push/drag/lift, Strength based attacks (melee, hand-to-hand), etc.<p>
     * DEXTERITY:Dex of character. Used for things like Initiative, AC, Dex attacks (ranged, finess), Stealth, etc.<p>
     * CONSTITUTION:Con of character. Used for things like health increase between levels, concentration checks, survive without breath/food/water/sleep/etc, etc.<p>
     * INTELLIGENCE:Intellect of character. Used for memory recall, investigation, wizard spellcasting mod, etc.<p>
     * WISDOM:Wisdom of character. Understand surroundings, perception, Cleric/Druid/Rangers Spell Mod.<p>
     * CHARISMA:charisma of character. Ability to interact effectively with others, persuasion, Bard/Paladin/Scorcer/Warlock Spell Mod.
     */
    public static enum AbilityScore{STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA};
    /**
     * List of the different types of Health that each character will need to manage. These include:<p>
     * FULL: This is the current maximum amount of health the character has. This will be changed if character is subjected to ability that decreases the max amount of health the character.<p>
     * MAX: Maximum amount of health character has. Is not changed when subjected to health drain, unless drain is permanent and irreversible.<p>
     * CURRENT: Current amount of hp that the character has. When the character takes damage, it decreases CURRENT.<p>
     * TEMP: Amount of TEMP hp that the character has. This is usually zero unless there is an effect that explicitly states that it adds TEMP hp. TEMP is decreased if non-zero before CURRENT is.
     */
    public static enum Health{FULL, MAX, CURRENT, TEMP};
    /**
     * Methods of travel that a character could use. These include:<p>
     * WALK:Primary mode of transportation, how fast a character would walk around on the ground without needing to make any sort of check.<P>
     * CLIMB: Form of travel which is how fast a creature can travel up a vertical surface/ceiling in one round.<P>
     * FLY: Form of travel that creature can move above the ground in one round.<P>
     * SWIM: Form of travel through water/viable liquids. Speed that can travel through that substance per round.<P>
     * BURROW: Form of travel through ground. Speed at which character can travel through ground.
     */
    public static enum Travel{WALK, CLIMB, FLY, SWIM, BURROW};
    /**
     * Methods of communication that creatures can know. 
     */
    public static enum Languages{ABYSSAL, AQAN, AURAN, CELESTIAL, COMMON, DEEPSPEECH, DRACONIC, DRUIDIC, DWARVISH, ELVISH, GIANT, GNOMISH, GOBLIN, GNOLL, HALFLING, IGNAN, INFERNAL, ORC, PRIMORDIAL, SYLVAN, TERRAN, UNDERCOMMON};
    public static enum Senses{PASSIVEPERCEPTION, INITIATIVE, VISION };
    /**
     * Alignments that characters can have.
     */
    public static enum Alignment{CHAOTICGOOD, CHAOTICNEUTRAL, CHAOTICEVIL, NEUTRALGOOD, NEUTRALNEUTRAL, NEUTRALEVIL, LAWFULGOOD, LAWFULNEUTRAL, LAWFULEVIL};
    /**
     * Size that the characters can have.
     */
    public static enum Size{TINY, SMALL, MEDIUM, LARGE, HUGE, GARGANTUAN};
    /**
     * Types of Damage that can be dealt. These are useful for vulnerabilities, resistances, and immunities.
     */
    public static enum DamageType{ACID, BLUDGEONING, COLD, FIRE, FORCE, LIGHTNING, NECROTIC, PIERCING, POISON, PSYCHIC, RADIANT, SLASHING, THUNDER};
    /**
     * Skills that characters (expecially {@link PlayerCharacter}s) will utilize.
     */
    public static enum Skill{ACROBATICS,ANIMALHANDLING,ARCANA,ATHLETICS,DECEPTION,HISTORY,INSIGHT,INTIMIDATION,INVESTIGATION,MEDICINE,NATURE,PERCEPTION,PERFORMANCE,PERSUASION,RELIGION,SLEIGHTOFHAND,STEALTH,SURVIVAL};

    
    
    //All methods associated with skills 
    public short getSkill(Skill type);
    public void modifySkill(Skill type, short amount);  //Specifically for when something like feat affects a skill
    public void setSkillProficiency(Skill type, boolean hasProficiency);
    public void setSkillExpertiese(Skill type, boolean hasExpertiese);
    
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
    public Dice.DiceTypes getHitDice();
    public void setHitDice(Dice.DiceTypes type);
    
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
    /**
     * Gets the current AC of the character.
     * @return current AC
     */
    public short getAC();
    /**
     * Gets the Default AC of a character. The difference between AC and default AC is that the default AC stores the AC modifiers calculated based upon the armor, Dex, etc without temporary modifiers.
     * @return The default AC.
     */
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
}
