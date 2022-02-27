/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import vendingMachine.dao.VendingMachineDao;
import vendingMachine.dao.VendingMachinePersistenceException;
import vendingMachine.dto.Item;
import vendingMachine.UI.VendingMachineView;
import vendingMachine.service.InsufficientFundsException;
import vendingMachine.service.NoItemInventoryException;
import vendingMachine.service.VendingMachineServiceLayer;

/**
 *
 * @author mariana.bonish
 */
public class VendingMachineController {

    VendingMachineView view;
    VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }
    //Automatically show all the items from the machine 
    //Enter item for purchase 
    //Enter dollar amount
    //vend item 
    //give change 

    public void run() {
        boolean keepGoing = true;
        try {

            while (keepGoing = true) {
                //display list of items
                List<Item> items = service.getAllItems();
                view.displayAllItemsBanner();
                view.displayItemsList(items);

                //enter your money
                BigDecimal dollars = view.enterMoney();

                //choice an item
                int itemChoice = view.getItemChoice();
                Item itemToVend = service.getItem(itemChoice);
                this.enoughMoney(dollars, itemToVend);

                //update the item in the vending machine 
                service.updateQuantity(itemChoice);

                //make change and give
                String change = this.makeChange(dollars, itemToVend);
                view.dispenseChange(change);

                //Vend another item 
                String response = view.buyMore();
                if (response == "y") {
                    keepGoing = true;
                } else {
                    keepGoing = false;
                }
            }
        } catch (VendingMachinePersistenceException | InsufficientFundsException
                | NoItemInventoryException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void enoughMoney(BigDecimal dollars, Item item) throws InsufficientFundsException {
        service.insufficientFundsException(item, dollars);
    }

    private String makeChange(BigDecimal dollar, Item item) {
        BigDecimal bDollars = dollar.subtract(item.getCost());
        BigDecimal oneHundred = new BigDecimal(100);
        //change big Decmai to double to do math on it 
        BigDecimal BDchange = bDollars.multiply(oneHundred);
        
        int change = Integer.valueOf(BDchange.intValue());
        

        int remainder;

    
        int quarters;
        int dimes;
        int nickles;
        int pennies = 0;

        int quartersToReturn =0;
        int dimesToReturn =0;
        int nicklesToReturn=0;
        int penniesToReturn=0;

        String totalChange;
        System.out.println(change);
        if (change >= 25) {
            quarters = (int) (change / 25); 
            remainder = quarters * 25;
            change = change - remainder;
            if (quarters > 0) {
                quartersToReturn = quarters;
            }
        }
        System.out.println(change);
        if (change >= 10) {
            dimes = (int) (change / 10);
            remainder = dimes * 10;
            change = change - remainder;
            if (dimes > 0) {
                dimesToReturn = dimes;
            }
        }
        
        System.out.println(change);
        if (change >= 5) {
            nickles = (int) (change / 5);
            remainder = nickles * 5;
            change = change - remainder;

            if (nickles > 0) {
                nicklesToReturn = nickles;
            }

        }
        System.out.println(change); 
        if (change >= 1) {
            pennies = (int)change;       
        }
        System.out.println(change);
        totalChange = "Your change is: "
                + "\nQUARTERS: " + quartersToReturn
                + "\nDIMES: " + dimesToReturn
                + "\nNICKLES: " + nicklesToReturn
                + "\nPENNIES: " + pennies;

        return totalChange;
    }
}
