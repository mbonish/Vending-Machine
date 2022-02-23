/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
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
        String testFile = "testItems.txt";
        //Make the file blank with the file writer 
//        new FileWriter(testFile);
        testDao = new VendingMachineDaoImpl(testFile);
        
    }
    
    @AfterEach
    public void tearDown() {
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
        
    }
}
