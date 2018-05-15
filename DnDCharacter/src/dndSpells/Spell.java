/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndSpells;
import dndClasses.Classes;
/**
 *
 * @author ryan
 */
public interface Spell
{
    public static enum School{ABJURATION, CONJURATION, DIVINATION, ENCHANTMENT, EVOCATION, ILLUSION, NECROMANCY, TRANSMUTATION};
    
    //Methods that deal with the allowed classes that can cast this spell
    public Classes[] allowedSpellcasters();
    public void addSpellcaster(Classes givenClass);
    public void removeSpellcaster(Classes givenClass);
    public boolean containsSpellcaster(Classes givenClass);
    
    
    
}
