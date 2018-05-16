/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndClasses;

import dndDice.Dice.DiceTypes;
import dndDice.Dice;
import dndCharacter.Character.*;
import java.util.ArrayList;
import dndCharacter.Character;
import dndWeapons.Weapon.WeaponTypes;

/**
 *Classes that {@link PlayerCharacter}s will use when creating and using their character. Dictates  {@link DiceTypes} of hit dice that the player will have, how many hit dice that player will have based upon their {@link #level} in the class, spell modifiers, etc.
 * @author ryan
 */
public abstract class Classes
{
    /**
     * Reference to a {@link Character} object that will have taken at least one level in a class. The current class implementation will be directly attached to that instance of a character. Usually will be attacked to a {@link PlayerCharacter}.
     */
    private final Character CHARACTER;
    private final String NAME;
    
    private DiceTypes hitDiceType;
    private Dice hitDice;
    /**
     * The level that the {@link #CHARACTER} has in the current class.
     */
    protected short level;
    
    private final AbilityScore spellCasting;
    private short spellSaveDC;
    protected short spellSaveDCModifiers=0;
    private short spellAttackModifier;
    protected short spellAttackModifiers=0;
    
    
    
    public Classes(Character characterWithClass, AbilityScore spellCastingType, String name) throws IllegalArgumentException
    {
        if(characterWithClass==null)
            throw new IllegalArgumentException("Must have reference to character using class");
        CHARACTER = characterWithClass;
        spellCasting = spellCastingType;
        this.NAME = name;
        
        spellSaveDC = getSpellDC(CHARACTER);
        spellAttackModifier = getSpellAttack(CHARACTER);
    }
    
    public String getName(){return NAME;}
    
    protected ArrayList<Skill> skillProf;
    protected ArrayList<WeaponTypes> weaponProf;
    
    protected Character getCharacter(){return CHARACTER;}
    
    protected void setHitDice(DiceTypes type){hitDiceType=type;hitDice=new Dice(type, level, 0);}
    public DiceTypes getHitDiceType(){return hitDiceType;}
    
    public AbilityScore getSpellCastingAbility(){return spellCasting;}
    
    public short getLevel(){return level;}
    /**
     * Levels up class
     * @return Amount of health gained by lv up (not including con modifier)
     */
    public int levelUp()
    {
        level++;
        hitDice = new Dice(hitDiceType, level, 0);
        
        spellSaveDC = getSpellDC(CHARACTER);
        spellAttackModifier = getSpellAttack(CHARACTER);
        
        return levelUpHealth();
        
    }
    /**
     * Upon leveling up, this method will calculate the amount of health that the character will gain.
     * @return amount that the {@link Health}.MAX will increase by.
     */
    private int levelUpHealth()
    {
        if(level==1)
            return 6 + CHARACTER.getModifier(AbilityScore.CONSTITUTION);
        return hitDice.roll() + CHARACTER.getModifier(AbilityScore.CONSTITUTION);
    }
    
    /**
     * Needed to determine SpellDC for class. If none, then implement to return 0.
     * @param character
     * @return 
     */
    protected abstract short getSpellDC(Character character);
    protected abstract short getSpellAttack(Character character);
}
