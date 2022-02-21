/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine;

import vendingMachine.controller.VendingMachineController;
import vendingMachine.dao.VendingMachineDao;
import vendingMachine.dao.VendingMachineDaoImpl;
import vendingMachine.dao.VendingMachinePersistenceException;
import vendingMachine.UI.UserIO;
import vendingMachine.UI.UserIOConsoleImpl;
import vendingMachine.UI.VendingMachineView;

/**
 *
 * @author mariana.bonish
 */
public class main {
    public static void main(String[] args) {
        UserIO myIO = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIO);
        VendingMachineDao myDao = new VendingMachineDaoImpl();
        VendingMachineController controller = new VendingMachineController(myView, myDao);
        controller.run();
    }
}
