/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.view;

import java.math.BigDecimal;
import java.util.List;
import vendingMachine.dto.Item;

/**
 *
 * @author mariana.bonish
 */
public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void displayItemsList(List<Item> itemList) {
        for( Item currentItem : itemList){
            io.print(currentItem.getItemId()+ ":"
                    +currentItem.getName() + ":"
                    +currentItem.getCost() + ":"
                    +currentItem.getQuantity());
        }
        io.readString("Please Select Item or hit ENTER to continue.");
    }
    
    public void displayAllItemsBanner(){
        io.print("=== VENDING ITEMS ===");
    }
    
    public void displayItemNameBanner(){
        io.print("== SELECTED ITEM ===");
    }
    
    public void displayInsertMoneyBanner(){
        io.readInt("Please insert your money.");
}
    public int getItemChoice(){
        return io.readInt("Please enter the item number. ");
    }
    
    public void displaySingleItem(Item item){
        if (item != null){
            io.print(Integer.toString(item.getItemId()));
            io.print(item.getName());
             BigDecimal cost = item.getCost();
            io.print(cost.toString());
            io.print(Integer.toString(item.getQuantity()));
        }
    }
    
   public void displayErrorMessage(String errorMsg){
       io.print("=== Error ===");
       io.print(errorMsg);
   }
}
