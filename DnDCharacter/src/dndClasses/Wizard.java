/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndClasses;
import dndDice.Dice;
import dndSpells.*;
import dndWeapons.Weapon;
import dndWeapons.Weapon.WeaponTypes;
import dndCharacter.Character;
import dndCharacter.PlayerCharacter;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *Base Wizard class as found in the Player handbook. 
 * @author ryan
 */
public class Wizard extends Classes
{

    public Wizard(Character character)
    {
        super(character, Character.AbilityScore.INTELLIGENCE);
        super.setHitDice(Dice.DiceTypes.d6);
        
        weaponProf = new ArrayList<>(Arrays.asList(WeaponTypes.DAGGER, WeaponTypes.DART, WeaponTypes.SLING, WeaponTypes.QUARTERSTAFF, WeaponTypes.LIGHTCROSSBOW));
       
        //TODO: FIGURE OUT HOW TO GET 2 Skill Proficiencies FROM MAKER For WIZARD CLASS
        skillProf = new ArrayList<>();
    }
    
    @Override
    protected short getSpellDC(Character character) {
        if( getCharacter() instanceof PlayerCharacter )
            return (short)(8 + ((PlayerCharacter)getCharacter()).getProficiencyBonus() + getCharacter().getAbilityScore(super.getSpellCastingAbility()) + super.spellSaveDCModifiers);
        return (short)(8 + getCharacter().getAbilityScore(super.getSpellCastingAbility()) + super.spellSaveDCModifiers);
    }

    @Override
    protected short getSpellAttack(Character character) {
        if( getCharacter() instanceof PlayerCharacter )
            return (short)(((PlayerCharacter)getCharacter()).getProficiencyBonus() + getCharacter().getAbilityScore(super.getSpellCastingAbility()) + super.spellAttackModifiers);
        return (short)(getCharacter().getAbilityScore(super.getSpellCastingAbility()) + super.spellAttackModifiers);
    }
    
}
