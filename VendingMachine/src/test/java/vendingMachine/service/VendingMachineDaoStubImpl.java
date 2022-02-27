/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import vendingMachine.dao.VendingMachineDao;
import vendingMachine.dao.VendingMachinePersistenceException;
import vendingMachine.dto.Item;

/**
 *
 * @author mariana.bonish
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {

    public Item item1;
    public Item item2;
    List<Item> itemList = new ArrayList<>();
    public VendingMachineDaoStubImpl() {

        item1 = new Item(1);
        item1.setName("TestItem1");
        BigDecimal cost = new BigDecimal(1.00);
        item1.setCost(cost);
        item1.setQuantity(3);

        item2 = new Item(2);
        item2.setName("TestItem1");
        BigDecimal cost2 = new BigDecimal(1.00);
        item2.setCost(cost2);
        item2.setQuantity(0);
       
        itemList.add(item1);
        itemList.add(item2);

    }
//
//    public VendingMachineDaoStubImpl(Item testItem, Item testItem2) {
//        this.item1 = testItem;
//        this.item2 = testItem2;
//    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
            return itemList;
    }

    @Override
    public Item getItem(int ItemId) throws VendingMachinePersistenceException {
        if (ItemId == item1.getItemId()) {
            return item1;
        }
        if (ItemId == item2.getItemId()) {
            return item2;
        } else {
            return null;
        }
    }

    @Override
    public void updateQuantity(int itemId) throws VendingMachinePersistenceException {
    }

}
