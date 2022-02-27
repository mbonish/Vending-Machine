/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.service;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import vendingMachine.dao.VendingMachineAuditDao;
import vendingMachine.dao.VendingMachineDao;
import vendingMachine.dao.VendingMachinePersistenceException;
import vendingMachine.dto.Item;

/**
 *
 * @author mariana.bonish
 */
public class VendingMachineServiceLayerImplTest {

    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerImplTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMacineAuditDaoStubImpl();

        service = new VendingMachineServiceLayerImpl(dao, auditDao);
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void getItem() throws Exception {
        try {
            Item zero =service.getItem(2);
            fail("No items.");
        } catch (NoItemInventoryException e) {
            return;
        }
     
    }

       public void getItem2() throws Exception {
           try{
               Item item1 = service.getItem(1);
           }catch(NoItemInventoryException e){
               fail("There are items to vend.");
           }
       }
    @Test
    public void insufficientFundsException() throws Exception {

        Item vendingItem = service.getItem(1);
        BigDecimal moneyIn = new BigDecimal(.75);
        try {
            service.insufficientFundsException(vendingItem, moneyIn);
            fail("This should not be enough money.");
        } catch (InsufficientFundsException e) {
            return;
        }
    }
        @Test
    public void insufficientFundsException2() throws Exception {
        Item vendingItem = service.getItem(1);
        BigDecimal moneyIn = new BigDecimal(2.00);
        try{
            service.insufficientFundsException(vendingItem, moneyIn);
        }catch (InsufficientFundsException e){
            fail("There was sufficent funds.");
        }
    }
    
}
