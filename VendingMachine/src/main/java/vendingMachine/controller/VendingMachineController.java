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
                //enter your money
                BigDecimal dollars = view.enterMoney();

                //display list of items
                List<Item> items = service.getAllItems();
                view.displayAllItemsBanner();
                view.displayItemsList(items);

                //choice an item
                int itemChoice = view.getItemChoice();
                Item itemToVend = service.getItem(itemChoice);
                this.enoughMoney(dollars, itemToVend);
                
                //update the item in the vending machine 
                service.updateQuantity(itemChoice);

                //make change and give
                BigDecimal changeToGive = this.makeChange(dollars, itemToVend);
                String change = changeToGive.toString();
                view.dispenseChange(change);
                
                //Vend another item 
                
                String response = view.buyMore();
                if( response == "y"){
                    keepGoing = true;
                }
                else keepGoing = false; 
            }
        } catch (VendingMachinePersistenceException | InsufficientFundsException
                | NoItemInventoryException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void enoughMoney(BigDecimal dollars, Item item) throws InsufficientFundsException {
        service.insufficientFundsException(item, dollars);
    }

    private BigDecimal makeChange(BigDecimal dollar, Item item) {
        return dollar.subtract(item.getCost());
    }
}
