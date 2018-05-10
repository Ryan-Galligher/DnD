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
    
    public short getAmount();
    public boolean setAmount(short amount);
    public boolean changeAmountBy(short amount);
    public boolean changeAmountBy(Money Other);
    
    public short getValue();    //Returns in value of units
    public boolean valueLargerThan(Money other);
    public boolean amountLargerThan(Money other);
    
    public String getName();
    
}
