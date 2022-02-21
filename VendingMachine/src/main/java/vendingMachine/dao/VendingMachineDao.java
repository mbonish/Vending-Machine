/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.dao;

import java.util.List;
import vendingMachine.dto.Item;

/**
 *
 * @author mariana.bonish
 */
public interface VendingMachineDao {
    //get all the items gives back a list of items
    
    List<Item> getAllItems()throws VendingMachinePersistenceException;
   
    //get a single item- pass it an id and it will return a sinlge item 
    
   Item getItem(int ItemId)throws VendingMachinePersistenceException;
   
   //update
   void updateQuantity(int itemId) throws VendingMachinePersistenceException;
}
