/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine;

import vendingMachine.controller.VendingMachineController;
import vendingMachine.dao.VendingMachineDao;
import vendingMachine.dao.VendingMachineDaoImpl;
import vendingMachine.UI.UserIO;
import vendingMachine.UI.UserIOConsoleImpl;
import vendingMachine.UI.VendingMachineView;
import vendingMachine.dao.VendingMachineAuditDao;
import vendingMachine.dao.VendingMachineAuditDaoImpl;
import vendingMachine.service.*;


/**
 *
 * @author mariana.bonish
 */
public class main {
    public static void main(String[] args) {
        UserIO myIO = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIO);
        VendingMachineDao myDao = new VendingMachineDaoImpl();
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoImpl();
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        VendingMachineController controller = new VendingMachineController(myView, myService);
        controller.run();
    }
}
