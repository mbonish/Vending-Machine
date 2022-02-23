/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.service;

import vendingMachine.dao.VendingMachineAuditDao;
import vendingMachine.dao.VendingMachinePersistenceException;

/**
 *
 * @author mariana.bonish
 */
public class VendingMacineAuditDaoStubImpl implements VendingMachineAuditDao {

    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
