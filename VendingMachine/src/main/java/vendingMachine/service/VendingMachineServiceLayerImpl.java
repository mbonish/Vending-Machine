/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.service;

import java.math.BigDecimal;
import java.util.List;
import vendingMachine.dao.VendingMachineAuditDao;
import vendingMachine.dao.VendingMachineDao;
import vendingMachine.dao.VendingMachinePersistenceException;
import vendingMachine.dto.Item;

/**
 *
 * @author mariana.bonish
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;
    VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
//pass through method

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }

    @Override
    public Item getItem(int ItemId) throws VendingMachinePersistenceException,
            NoItemInventoryException {
        if(dao.getItem(ItemId).getQuantity() <+0)
            throw new NoItemInventoryException("ERROR: there are none of these "
                    + "items to vend.");
        return dao.getItem(ItemId);
    }

    @Override
    public void updateQuantity(int itemId) throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry("Item " + itemId + " was purchased.");
        dao.updateQuantity(itemId);
    }
    
    @Override
    public void insufficientFundsException(Item item, BigDecimal moneyIn) throws 
            InsufficientFundsException{
        
        if(item.getCost().compareTo(moneyIn)> 0){
            throw new InsufficientFundsException("ERROR--- Insufficient funds--"
                    + "Please insert more more money. ");
        }
    }

}
