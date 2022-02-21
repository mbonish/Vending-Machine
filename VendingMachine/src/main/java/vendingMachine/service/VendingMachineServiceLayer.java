/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.service;

import java.util.List;
import vendingMachine.dao.VendingMachinePersistenceException;
import vendingMachine.dto.Item;

/**
 *
 * @author mariana.bonish
 */
public interface VendingMachineServiceLayer {
       List<Item> getAllItems()throws VendingMachinePersistenceException;
   
    //get a single item- pass it an id and it will return a sinlge item 
    
   Item getItem(int ItemId)throws VendingMachinePersistenceException, NoItemInventoryException;
   
   //update
   void updateQuantity(int itemId) throws VendingMachinePersistenceException;
    
}
