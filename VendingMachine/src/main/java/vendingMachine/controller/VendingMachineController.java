/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.controller;

import vendingMachine.dao.VendingMachinePersistenceException;

/**
 *
 * @author mariana.bonish
 */
public class VendingMachineController {
    //Automatically show all the items from the machine 
    //Enter item for purchase 
    //Enter dollar amount
    //vend item 
    //give change 

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
//
//        try {
            while (keepGoing) {
                //must enter a dollar amount before continuing
                System.out.println("Enter a dollar amount");
                System.out.println("(1)__Show all items__");
                System.out.println("(2) Exit");
               
                //menuSelection int 
                System.out.println("Please Select from the above choices.");

                switch (menuSelection) {
                    case 1:
                        //list all the items
                    case 2:
                        //exit
                     default:
//                        this.io.print("UNKNOWN COMMAND");    
                }
            }
//        } catch(VendingMachinePersistenceException e) {
//            view.displayErrorMessage(e.getMessage());
        }
    }
