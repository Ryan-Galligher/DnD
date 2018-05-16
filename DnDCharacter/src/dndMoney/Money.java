/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndMoney;

/**
 *
 * @author ryan
 */
public interface Money {
    
    public int getAmount();
    public boolean setAmount(int amount);
    public boolean changeAmountBy(int amount);
    /**
     * Changes current amount of money by the given value. If the value of other is smaller than current value, then rounds up the value.
     * @param Other
     * @return 
     */
    public boolean changeAmountBy(Money Other);
    
    public int getValue();    //Returns in value of units
    public boolean valueLargerThan(Money other);
    public boolean amountLargerThan(Money other);
    
    public String getName();
    public Money createEmptyInstance();
    
}
