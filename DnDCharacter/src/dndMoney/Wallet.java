/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndMoney;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *Used to Hold and manage Money classes
 * @author ryan
 */
public class Wallet
{
    ArrayList<Money> cashInWallet;
    
    public Wallet()
    {
        cashInWallet = new ArrayList<>(Arrays.asList(new GoldPiece(), new SilverPiece(), new CopperPiece()));
    }
    public Wallet(Money[] money)
    {
        cashInWallet = new ArrayList<>(Arrays.asList(money));
    }
    
    public void setMoney(Money[] money)
    {
        cashInWallet = new ArrayList<>(Arrays.asList(money));
        combineLikeMoney();
    }
    public void setMoney(ArrayList<Money> money)
    {
        cashInWallet = money;
        combineLikeMoney();
    }
    /**
     * Combines the money in the wallet that have the same values
     */
    private void combineLikeMoney()
    {
        for(int i = 0; i < cashInWallet.size(); i++)    //for every item of money in cashinwallet
        {
            for(int a = (i+1); a < cashInWallet.size(); a++)    //for every item of greater than the given i in cashinwallet (since all items before have already been dealt with)
            {
                if( cashInWallet.get(i).getName().equals(cashInWallet.get(a).getName()) )  //If the names are the same, then type of money is the same
                {
                    cashInWallet.get(i).changeAmountBy(cashInWallet.remove(a--));   //combines the two together since they are the same, while removing the one farther along in the array so it isn't evaluated more than once, and decreasing a by one so at the beginning of the next loop it loops over same spot. 
                    continue;
                }
                if(cashInWallet.get(i).getValue() < cashInWallet.get(a).getValue()) //If the value currently evaluating is smaller than another value, then switch the two so the ArrayList is ordered. There is no need to change value of a since none of the previous values will be of the same or larger value, else it would have been switched already.
                {
                    cashInWallet.set(i, cashInWallet.remove(a));
                    cashInWallet.set(a, cashInWallet.remove(i+1));
                }
            }
        }
    }
    /**
     * Adds money to the Wallet.
     * @param money
     * @return If the money was able to be added to the wallet
     */
    public boolean addMoney(Money money)
    {
        try{
            for(int i = 0; i < cashInWallet.size(); i++)    //For every type of money in the wallet
            {
                if(cashInWallet.get(i).getName().equals(money.getName()))  //If the two names of money are the same, then add the amounts together and break loop since added
                {
                    return cashInWallet.get(i).changeAmountBy(money.getAmount());
                }
                if(cashInWallet.get(i).getValue() < money.getValue())   //If the value of the current item in the wallet is smaller than the current money type, since the arrayList is ordered from 0=larges to last=smallest, that the correct money type is not in the wallet. Because of this, add the money to the current spot.
                {
                    cashInWallet.set(i, money);
                }
            }
            return true;
        }catch(Exception e){return false;}
    }
    /**
     * 
     * @param money
     * @return
     * @throws IllegalArgumentException If the type of money that is to be deducted does not exist in the wallet, then throw an exception.
     */
    public boolean payMoney(Money money) throws IllegalArgumentException
    {
        try{
            for(int i = 0; i < cashInWallet.size(); i++)    //For every type of money in the wallet
            {
                if(cashInWallet.get(i).getName().equals(money.getName()))   //If the names are the same then they are the samy type of Money, so return the attempt to decrease the money by the amount 
                {
                    return cashInWallet.get(i).changeAmountBy( (short) (-1 * money.getAmount()));
                }
                if(cashInWallet.get(i).getValue() < money.getValue())   
                {
                    throw new IllegalArgumentException("The wallet does not contain the type of money requested to remove");
                }
            }
            return true;    //If everything works correctly, return that the money was added correctly
        }catch(Exception e){if(e instanceof IllegalArgumentException) throw e;return false;}
    }
    /**
     * Converts money amount from source to changeTo, then returns changeTo with amount.
     * @param source amount to be converted to changeTo.
     * @param changeTo Type of money that needs to have source amount converted to.
     * @return The changeTo object with the source money added. If unsuccessful for any reason, return null.
     */
    public static Money convertMoney(Money source, Money changeTo)
    {
        if(changeTo.changeAmountBy(source))
            return changeTo;
        return null;
    }
    /**
     * Exchanges amount of money from type source in wallet, converts it to money type changeTo, then adds changeTo money to wallet.
     * @param source Amount of money to be taken from wallet, then converted to form changeTo.
     * @param changeTo Amount should start out at zero, then have source amount of money converted and added to wallet.
     * @return  If the exchange of money types was successful.
     */
    public boolean exchangeMoney(Money source, Money changeTo)
    {
        if(changeTo.getAmount()!=0)
            return false;
        if(payMoney(source) && changeTo.changeAmountBy(source) && addMoney(changeTo))   //If you can extract the money from this wallet, convert money from one type to another, and add that money to wallet
            return true;
        return false;
    }
    
    public boolean exchangeMoney(Money amountNeededToAdd)
    {
        int i;
        for(i = 0; i < cashInWallet.size(); i++)
        {
            if(cashInWallet.get(i).getValue() <= amountNeededToAdd.getValue())
            {
                break;  //If they get to a point where the value of current item is smaller or equal to ammount to convert to, break (used so edge case of convert to is small amount that isn't in wallet still works)
            }
        }
        if(i==0)
            return false;
        Money mon = cashInWallet.get(i-1).createEmptyInstance();
        mon.changeAmountBy(amountNeededToAdd);
        return exchangeMoney(mon, amountNeededToAdd.createEmptyInstance());
 
    }
}
