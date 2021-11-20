package data;

import org.junit.jupiter.api.BeforeEach;
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
    @BeforeEach
    void initializeDefaultList() {
        //create a default list to be test (all values are correct by default)
    }
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
        String expected = "<!doctype html><html lang=\"en\"><head><style type=\"text/css\">table, td {border: 1px solid #333;}thead, tfoot {background-color: #333;color: #fff;}</style><meta charset=\"UTF-8\"><title>saveData</title></head><body><table><thead><tr><th scope=\"col\">Serial Number</th><th scope=\"col\">Name</th><th scope=\"col\">Value</th></tr></thead><tbody><tr><td>A-XB1-24A-XY3</td><td>Xbox Series X</td><td>$1499.00</td><td hidden>1499.0</td></tr><tr><td>S-40A-ZBD-E47</td><td>Samsung TV</td><td>$599.99</td><td hidden>599.99</td></tr></tbody></table></body></html>";
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
        //compare the files
        assertEquals(expected,actual);
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
    void testLoadListFromHTML() {
        //load from sample html and compare to correct values
    }
    @Test
    void testLoadListFromJSON() {
        //load from sample json and compare to correct values
    }
    @Test
    void testGetList() {
        //verify list received matches initialized values
        ArrayList<Item> expectedList = new ArrayList<>();
        assertEquals(Arrays.asList(expectedList),Arrays.asList(expectedList));
    }
    @Test
    void testAddItemToList() {
        //test that add items works properly
        ArrayList<Item> expectedList = new ArrayList<>();
        assertEquals(Arrays.asList(expectedList),Arrays.asList(expectedList));
    }
    @Test
    void testAdd1024ItemsToList() {
        //test that it is possible to add 1024 items using add item method
        ArrayList<Item> expectedList = new ArrayList<>();
        assertEquals(Arrays.asList(expectedList),Arrays.asList(expectedList));
    }
    @Test
    void testEditItemInList() {
        //edit an item in the list
        ArrayList<Item> expectedList = new ArrayList<>();
        assertEquals(Arrays.asList(expectedList),Arrays.asList(expectedList));
    }
    @Test
    void testDeleteItemInList() {
        //test that add items works properly
        ArrayList<Item> expectedList = new ArrayList<>();
        assertEquals(Arrays.asList(expectedList),Arrays.asList(expectedList));
    }
    @Test
    void testDeleteAllItemsInList() {
        //edit an item in the list
        ArrayList<Item> expectedList = new ArrayList<>();
        assertEquals(Arrays.asList(expectedList),Arrays.asList(expectedList));
    }
}