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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import vendingMachine.dto.Item;

/**
 *
 * @author mariana.bonish
 */
public class VendingMachineDaoImpl implements VendingMachineDao {

    //Map to hold the Items in using them itemId as the key and the item object
    //as the value
    private Map<Integer, Item> Items = new HashMap<>();

    public static final String DELIMITER = "::";
    public final String ITEMS_FILE;

    //Will need to create a constructor to pass in file for testing 
    public VendingMachineDaoImpl(String vendingTextFile){
        ITEMS_FILE = vendingTextFile;
    }

    public VendingMachineDaoImpl() {
        ITEMS_FILE = "items.txt";
    }

    //method takes the a a string passed in from the txt file and turns it 
    //int an item object
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        loadItems(); 
        return new ArrayList<Item>(Items.values());
    }

    @Override
    public Item getItem(int ItemId) throws VendingMachinePersistenceException {
        loadItems();
        return Items.get(ItemId);
    }

    private Item unmarshallItem(String itemAsText) {

        //splits the string between the delimiter
        String[] itemTokens = itemAsText.split(DELIMITER);

        //get the item id 
        String StringItemId = itemTokens[0];
        int itemId = parseInt(StringItemId);

        //create a new item with with the id
        Item itemFromFile = new Item(itemId);

        //set the name
        itemFromFile.setName(itemTokens[1]);

        //set the cost-- needed to use Big decimal
        BigDecimal cost = new BigDecimal(itemTokens[2]);
        itemFromFile.setCost(cost);

        //set the quantity
        itemFromFile.setQuantity(parseInt(itemTokens[3]));

        return itemFromFile;
    }

    private void loadItems() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ITEMS_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load data into memory.-_-", e);
        }
        String currentLine;

        Item currentItem;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);

            Items.put(currentItem.getItemId(), currentItem);
        }
        scanner.close();
    }

    private String marshallItem(Item anItem) {
        //want to take the current quantity of an item and update it 
        String itemAsText = anItem.getItemId() + DELIMITER;

        itemAsText += anItem.getName() + DELIMITER;
        itemAsText += anItem.getCost() + DELIMITER;
        itemAsText += anItem.getQuantity();

        return itemAsText;
    }

    private void writeItems() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ITEMS_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "-_-Could update item quantity", e);
        }

        //get all items 
        String itemAsText;

        List<Item> itemsList = this.getAllItems();

        for (Item currentItem : itemsList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);

            //force the print writer to write line to file
            out.flush();
        }
        //clean up
        out.close();
    }

    @Override
    public void updateQuantity(int itemId) throws VendingMachinePersistenceException {
        //gets all the items then allows you to select the one of them items 
        Item itemToUpdate = this.getItem(itemId);
        //update the item quantity to be -1
        itemToUpdate.setQuantity(itemToUpdate.getQuantity() - 1);
        writeItems();

    }

}
