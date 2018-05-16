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
import dndMoney.GoldPiece;
import dndMoney.Money;
import dndMoney.Wallet;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *Base Wizard class as found in the Player handbook. 
 * @author ryan
 */
public abstract class Wizard extends Classes
{
    private final String SUBCLASSNAME;
    private ArrayList<Spell> knownSpells;
    private ArrayList<Spell> preparedSpells;
    
    public Wizard(Character character, String subclass)
    {
        super(character, Character.AbilityScore.INTELLIGENCE, "Wizard");
        SUBCLASSNAME=subclass;
        super.setHitDice(Dice.DiceTypes.d6);
        
        knownSpells = new ArrayList<>();
        preparedSpells = new ArrayList<>();
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
    
    protected int timeToAddSpell(int spellLv){return spellLv*Spell.HOURTIME*2;}
    protected Money moneyForSpell(int spellLv){return new GoldPiece(spellLv*50);}
    
    public abstract int timeToAddSpell(Spell given);
    public abstract Money moneyForSpell(Spell given);
    
    /**
     * Used when the character finds a spell in the world and wants to add it to their spells known. To do so takes money and time to scribe it. 
     * @param given
     * @return 
     */
    public boolean learnSpell(Spell given)
    {
        if(super.getCharacter() instanceof PlayerCharacter)
        {
            try{
            if( !((PlayerCharacter)super.getCharacter()).getMoney().payMoney(moneyForSpell(given)) ) //If the money is properly taken from the character, resume. If not, return false.
            {
                Wallet wallet = ((PlayerCharacter)super.getCharacter()).getMoney();
                if( !wallet.exchangeMoney(moneyForSpell(given)) )
                    return false;
            }
            }catch(Exception e){return false;}
        }
        knownSpells.add(given);
        return true;
    }
    /**
     * Used for adding spells to the wizard, through other means not including inscribing them to the spellbook in world.
     * @param given
     * @return 
     */
    public boolean addSpell(Spell given)
    {
        knownSpells.add(given);
        return true;
    }
}
