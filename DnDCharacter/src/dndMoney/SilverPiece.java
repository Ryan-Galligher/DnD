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
public class SilverPiece implements Money{
    private int amount;
    private static final short VALUE = 10;
    private static final String NAME  = "SilverPiece";
    
    public SilverPiece(){amount=0;}
    public SilverPiece(int amount){this.amount=amount;}
    public String getName(){return NAME;}
    public Money createEmptyInstance(){return new SilverPiece();}

    @Override
    public int getAmount() {return amount;}

    @Override
    public boolean setAmount(int amount) 
    {
        if(amount>0) 
            this.amount = amount; 
        return (amount>0);
    }

    @Override
    public boolean changeAmountBy(int amount)
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
         int number;
        if(!amountLargerThan(other))
            number = ((amount*VALUE + other.getAmount()*other.getValue())/VALUE);
        else
            number = (int)Math.ceil(((amount*VALUE + other.getAmount()*other.getValue())/VALUE));
        if(number>0)
        {
            amount=number;
            return true;
        }
        return false;
    }

    @Override
    public int getValue() {return VALUE;}

    @Override
    public boolean valueLargerThan(Money other) {
        return (VALUE > other.getValue());
    }

    @Override
    public boolean amountLargerThan(Money other) {
        return (amount > other.getAmount());
    }
}
