/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static vendingMachine.dao.VendingMachineDaoImpl.DELIMITER;
import vendingMachine.dto.Item;

/**
 *
 * @author mariana.bonish
 */
public class VendingMachineDaoImplTest {
    
    VendingMachineDaoImpl testDao;
    public VendingMachineDaoImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    //This non-static method is run once before each test method in the JUnit test class. It can be used to set things to a known good state before each test.
    @BeforeEach
    public void setUp() throws Exception {
        //file to write to
        String testFile = "testItems.txt";
       
        //Make the file blank with the file writer 
        new FileWriter(testFile);
 
        //items to add
        String item1 = "1::TestItem1::1.00::1";
        String item2 = "2::TesItem2::2.00::2";
        String item3 = "3::TestItem3::3.00::3";
        
        //start writing proccess
         PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter("testItems.txt"));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "-_-Could update item quantity", e);
        }
        
        //add items to this list 
        List<String> stringItems = new ArrayList<>();
        stringItems.add(item1);
        stringItems.add(item2);
        stringItems.add(item3);

        for (String currentItem : stringItems) {
            out.println(currentItem);

            //force the print writer to write line to file
            out.flush();
        }
        //clean up
        out.close();
    
        testDao = new VendingMachineDaoImpl(testFile);
    }
    
    @AfterEach
    public void tearDown()throws Exception {

    }

    @Test
        public void getItem() throws Exception {
            //retrieve and item
            Item retrievedItem = testDao.getItem(1);
            
            //testing to see if these items are correct
            assertEquals(retrievedItem.getName(),"TestItem1");
            assertEquals(retrievedItem.getQuantity(),1);
            //make sure retrieving the correct object 
            assertFalse(retrievedItem.getName().equals("BadName"));
        }         
  
    @Test      
        public void getAllItems()throws Exception{  
            
            List<Item> allItems = testDao.getAllItems();
            
           Item testItem = testDao.getItem(2);
            
            assertNotNull(allItems);
            assertEquals(3, allItems.size());
            
            assertTrue(allItems.contains(testItem));
        }
        
   @Test
    public void updateQuantity() throws Exception{
        Item testItem3Before = testDao.getItem(3);

        assertEquals(testItem3Before.getQuantity(),3);
        
        testDao.updateQuantity(3);
        Item testItem3 = testDao.getItem(3);
        
        assertEquals(testItem3.getQuantity(),2);
        
    }
}
