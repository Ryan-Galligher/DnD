/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndSpells;
import dndClasses.Classes;
import dndDice.Dice;
import dndCharacter.Character;
import java.util.ArrayList;
/**
 *
 * @author ryan
 */
public interface Spell
{
    /**
     * List of the different Schools of magic, so magic is always one of these. These include:<p>
     * ABJURATION:<p>
     * CONJURATION:<p>
     * DIVINATION:<p>
     * ENCHANTMENT:<p>
     * EVOCATION:<p>
     * ILLUSION:<p>
     * NECROMANCY:<p>
     * TRANSMUTATION:
     */
    public static enum School{ABJURATION, CONJURATION, DIVINATION, ENCHANTMENT, EVOCATION, ILLUSION, NECROMANCY, TRANSMUTATION};
    public static final short TOUCHRANGE=0;
    
    public static final int INSTANTTIME=0;
    public static final int HOURTIME=60;
    public static final int DAYTIME=1440;
    public static final int WEEKTIME=10080;
    public static final int MONTHTIME=40320;
    public static final int YEARTIME=483840;
    
    //Methods that deal with the allowed classes that can cast this spell
    public String[] allowedSpellcasters();
    public boolean containsSpellcaster(Classes givenClass);
    
    //Method that gets what spell school that the spell is from
    public School getSpellSchool();
    
    //Types of methods that determine how the spell hits, and how it affects creatures in it's radius if it does so
    public boolean affectsCreatures();
    public boolean hasToHit();
    public Dice ToHitDice();
    public boolean hasSavingThrow();
    public short getSpellSaveDC();
    public ArrayList<Character> affectGivenCharacters(ArrayList<Character> creatures);
    
    //Methods for if the spell is radius spell or if it affects target items
    public boolean isRadiusSpell();
    public int radiusOfSpell();
    public boolean isTargetSpell();
    public int numOfTargets();
    
    //Methods for how long a spell will be in effect for time. (in min)
    public int lengthOfSpellEffect();    
    
    public int getSpellLevel();
}
