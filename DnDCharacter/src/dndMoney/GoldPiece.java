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
public class GoldPiece implements Money {
    private short amount;
    private static final short VALUE = 1000;
    private static final String NAME = "GoldPiece";
    
    public GoldPiece(){amount=0;}
    public GoldPiece(short amount){this.amount=amount;}
    public String getName(){return NAME;}

    @Override
    public short getAmount() {return amount;}

    @Override
    public boolean setAmount(short amount) 
    {
        if(amount>0) 
            this.amount = amount; 
        return (amount>0);
    }

    @Override
    public boolean changeAmountBy(short amount)
    {
        if(this.amount+amount >0)
        {
            this.amount+=amount;
            return true;
        }
        return false;
    }

    @Override
    public boolean changeAmountBy(Money other) {
        short number = (short)((amount*VALUE + other.getAmount()*other.getValue())/VALUE);
        if(number>0)
        {
            amount=number;
            return true;
        }
        return false;
    }

    @Override
    public short getValue() {return VALUE;}

    @Override
    public boolean valueLargerThan(Money other) {
        return (VALUE > other.getValue());
    }

    @Override
    public boolean amountLargerThan(Money other) {
        return (amount > other.getAmount());
    }
    
    
}
