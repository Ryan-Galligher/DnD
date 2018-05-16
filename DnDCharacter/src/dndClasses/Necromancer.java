/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndClasses;
import dndCharacter.Character;
import dndMoney.GoldPiece;
import dndMoney.Money;
import dndSpells.Spell;
/**
 *
 * @author ryan
 */
public class Necromancer extends Wizard
{
    public Necromancer(Character character)
    {
        super(character, "Necromancer");
    }
    
    @Override
    public int levelUp()
    {
        int healthGain = super.levelUp();
        return healthGain;
    }
    
    public int timeToAddSpell(Spell given)
    {
        if(level>=2 && given.getSpellSchool().equals(Spell.School.NECROMANCY))
            return super.timeToAddSpell(given.getSpellLevel())/2;
        return super.timeToAddSpell(given.getSpellLevel());
    }
    public Money moneyForSpell(Spell given)
    {
        if(level>=2 && given.getSpellSchool().equals(Spell.School.NECROMANCY))
            return new GoldPiece(super.moneyForSpell(given.getSpellLevel()).getAmount()/2);
        return super.moneyForSpell(given.getSpellLevel());
    }
    
}
