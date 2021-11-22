package data;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Jacob Cordonero
 */


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DataHandlerTest {
    //used in each test when needed
    DataHandler testData = new DataHandler();
    @Test
    void testSaveListAsTXT() throws IOException {
        //create save with given data in the list to match pre-made tsv, html, json files
        //set path for file to test against
        Item item = new Item(1499.00,"A-XB1-24A-XY3","Xbox Series X");
        Item item2 = new Item(599.99,"S-40A-ZBD-E47","Samsung TV");
        testData.addItemToList(item);
        testData.addItemToList(item2);
        Path expected = Paths.get("src/test/resources/data/saveData.txt");
        //create test file to compare with
        File test = new File("src/test/resources/data/testData.txt");
        testData.saveList(test);
        //get the new file's path
        Path actual = Path.of(String.valueOf(test));
        //compare the files
        long mismatch = Files.mismatch(expected,actual);
        assertEquals(-1,mismatch);
    }
    @Test
    void testSaveListAsHTML() throws IOException {
        //create save with given data in the list to match pre-made tsv, html, json files
        //set path for file to test against
        Item item = new Item(1499.00,"A-XB1-24A-XY3","Xbox Series X");
        Item item2 = new Item(599.99,"S-40A-ZBD-E47","Samsung TV");
        testData.addItemToList(item);
        testData.addItemToList(item2);
        String expected = "<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <style type=\"text/css\">table, td {border: 1px solid #333;}thead, tfoot {background-color: #333;color: #fff;}</style>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>saveData</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>\n" +
                "    <thead>\n" +
                "    <tr>\n" +
                "        <th scope=\"col\">Serial Number</th>\n" +
                "        <th scope=\"col\">Name</th>\n" +
                "        <th scope=\"col\">Value</th>\n" +
                "    </tr>\n" +
                "    </thead>\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td>A-XB1-24A-XY3</td>\n" +
                "        <td>Xbox Series X</td>\n" +
                "        <td>$1499.00</td>\n" +
                "        <td hidden>1499.0</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td>S-40A-ZBD-E47</td>\n" +
                "        <td>Samsung TV</td>\n" +
                "        <td>$599.99</td>\n" +
                "        <td hidden>599.99</td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>";
        //create test file to compare with
        File test = new File("src/test/resources/data/testData.html");
        testData.saveList(test);
        //scans file
        StringBuilder buffer = new StringBuilder();
        try (FileReader in = new  FileReader(test.getAbsolutePath())) {
            int c;
            while((c = in.read()) != -1) {
                buffer.append((char) c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String actual = buffer.toString();
        //compare the files not caring about whitespace
        assertEquals(expected.replace(" ",""),actual.replace(" ",""));
    }
    @Test
    void testSaveListAsJSON() throws IOException {
        //create save with given data in the list to match pre-made tsv, html, json files
        //set path for file to test against
        Item item = new Item(1499.00,"A-XB1-24A-XY3","Xbox Series X");
        Item item2 = new Item(599.99,"S-40A-ZBD-E47","Samsung TV");
        testData.addItemToList(item);
        testData.addItemToList(item2);
        Path expected = Paths.get("src/test/resources/data/saveData.json");
        //create test file to compare with
        File test = new File("src/test/resources/data/testData.json");
        testData.saveList(test);
        //get the new file's path
        Path actual = Path.of(String.valueOf(test));
        //compare the files
        long mismatch = Files.mismatch(expected,actual);
        assertEquals(-1,mismatch);
    }
    @Test
    void testLoadListFromTSV() throws IOException {
        //load from sample tsv and compare to correct values
        //get path for load file
        Path load = Paths.get("src/test/resources/data/saveData.txt");
        testData.loadList(load.toFile());
        //create item to test against
        DataHandler expectedData = new DataHandler();
        Item item = new Item(1499.00,"A-XB1-24A-XY3","Xbox Series X");
        Item item2 = new Item(599.99,"S-40A-ZBD-E47","Samsung TV");
        expectedData.addItemToList(item);
        expectedData.addItemToList(item2);
        //compare
        for (int i = 0; i<expectedData.getItemCount(); i++) {
            assertEquals(expectedData.getItemSerialNumber(i),testData.getItemSerialNumber(i));
            assertEquals(expectedData.getItemName(i),testData.getItemName(i));
            assertEquals(expectedData.getItemMonetaryValue(i),testData.getItemMonetaryValue(i));
            assertEquals(expectedData.getItemValue(i),testData.getItemValue(i));
        }
    }
    @Test
    void testLoadListFromHTML() throws IOException {
        //load from sample html and compare to correct values
        //get path for load file
        Path load = Paths.get("src/test/resources/data/saveData.html");
        testData.loadList(load.toFile());
        //create item to test against
        DataHandler expectedData = new DataHandler();
        Item item = new Item(1499.00,"A-XB1-24A-XY3","Xbox Series X");
        Item item2 = new Item(599.99,"S-40A-ZBD-E47","Samsung TV");
        expectedData.addItemToList(item);
        expectedData.addItemToList(item2);
        //compare
        for (int i = 0; i<expectedData.getItemCount(); i++) {
            assertEquals(expectedData.getItemSerialNumber(i),testData.getItemSerialNumber(i));
            assertEquals(expectedData.getItemName(i),testData.getItemName(i));
            assertEquals(expectedData.getItemMonetaryValue(i),testData.getItemMonetaryValue(i));
            assertEquals(expectedData.getItemValue(i),testData.getItemValue(i));
        }
    }
    @Test
    void testLoadListFromJSON() throws IOException {
        //load from sample json and compare to correct values
        //get path for load file
        Path load = Paths.get("src/test/resources/data/saveData.json");
        testData.loadList(load.toFile());
        //create item to test against
        DataHandler expectedData = new DataHandler();
        Item item = new Item(1499.00,"A-XB1-24A-XY3","Xbox Series X");
        Item item2 = new Item(599.99,"S-40A-ZBD-E47","Samsung TV");
        expectedData.addItemToList(item);
        expectedData.addItemToList(item2);
        //compare
        for (int i = 0; i<expectedData.getItemCount(); i++) {
            assertEquals(expectedData.getItemSerialNumber(i),testData.getItemSerialNumber(i));
            assertEquals(expectedData.getItemName(i),testData.getItemName(i));
            assertEquals(expectedData.getItemMonetaryValue(i),testData.getItemMonetaryValue(i));
            assertEquals(expectedData.getItemValue(i),testData.getItemValue(i));
        }
    }
    @Test
    void testGetList() {
        //verify list received matches initialized values
        //create list to test against
        Item item = new Item(1499.00,"A-XB1-24A-XY3","Xbox Series X");
        Item item2 = new Item(599.99,"S-40A-ZBD-E47","Samsung TV");
        testData.addItemToList(item);
        testData.addItemToList(item2);
        String[] expectedSerialNumber = {"A-XB1-24A-XY3","S-40A-ZBD-E47"};
        String[] expectedName = {"Xbox Series X","Samsung TV"};
        String[] expectedMonetaryValue = {"$1499.00","$599.99"};
        double[] expectedValue = {1499.00,599.99};
        for (int i = 0; i< testData.getItemCount(); i++) {
            assertEquals(expectedSerialNumber[i],testData.getItemSerialNumber(i));
            assertEquals(expectedName[i],testData.getItemName(i));
            assertEquals(expectedMonetaryValue[i],testData.getItemMonetaryValue(i));
            assertEquals(expectedValue[i],testData.getItemValue(i));
        }
    }
    @Test
    void testAddItemToList() {
        //test that add items works properly
        //make items to insert to list
        Item item = new Item(1499.00,"A-XB1-24A-XY3","Xbox Series X");
        Item item2 = new Item(599.99,"S-40A-ZBD-E47","Samsung TV");
        //make list to test against
        ArrayList<Item> expectedList = new ArrayList<>(
                Arrays.asList(item,item2)
        );
        //
        testData.addItemToList(item);
        testData.addItemToList(item2);
        assertEquals(Arrays.asList(expectedList),Arrays.asList(testData.getList()));
    }
    private String generateNextSerialNumber(String string) {
        StringBuilder builder = new StringBuilder(string);
        int length = string.length();
        if (builder.charAt(length-1)<9) {
            builder.replace(length-1,1, String.valueOf((char)(builder.charAt(length-1)+1)));
        }
        return builder.toString();
    }
    @Test
    void testAdd1024ItemsToList() {
        //test that it is possible to add 1024 items using add item method
        Item genericItem = new Item(1499.0,"A-111-111-111","Xbox Series X");
        for (int i = 0; i < 1024; i++) {
            testData.addItemToList(genericItem);
            genericItem.setSerialNumber(generateNextSerialNumber(genericItem.getSerialNumber()));
        }
        //test later
        //assertEquals(1024,testData.getItemCount());
    }
    @Test
    void testEditItemInList() {
        //edit an item in the list
        Item item = new Item(1499.00,"A-XB1-24A-XY3","Xbox Series X");
        Item item2 = new Item(599.99,"S-40A-ZBD-E47","Samsung TV");
        ArrayList<Item> expectedList = new ArrayList<>(
                Arrays.asList(item,item2)
        );
        //different serial number required
        Item item3 = new Item(500.00,"A-XB2-24A-XY3","Xbox Series X");
        testData.addItemToList(item3);
        testData.addItemToList(item2);
        testData.editItemInList(item3,item);
        assertEquals(Arrays.asList(expectedList),Arrays.asList(testData.getList()));
    }
    @Test
    void testDeleteItemInList() {
        //test that add items works properly
        Item item = new Item(1499.00,"A-XB1-24A-XY3","Xbox Series X");
        Item item2 = new Item(599.99,"S-40A-ZBD-E47","Samsung TV");
        ArrayList<Item> expectedList = new ArrayList<>(
                Arrays.asList(item,item2)
        );
        Item item3 = new Item(500.00,"A-XB1-24A-XY3","Xbox Series X");
        testData.addItemToList(item);
        testData.addItemToList(item2);
        testData.addItemToList(item3);
        testData.deleteItemInList(item3);
        assertEquals(Arrays.asList(expectedList),Arrays.asList(testData.getList()));
    }
    @Test
    void testDeleteAllItemsInList() {
        //edit an item in the list
        Item item = new Item(1499.00,"A-XB1-24A-XY3","Xbox Series X");
        Item item2 = new Item(599.99,"S-40A-ZBD-E47","Samsung TV");
        ArrayList<Item> expectedList = new ArrayList<>();
        testData.addItemToList(item);
        testData.addItemToList(item2);
        testData.deleteAllItemsInList();
        assertEquals(Arrays.asList(expectedList),Arrays.asList(testData.getList()));
    }
}