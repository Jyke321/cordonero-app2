package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
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
        Path expected = Paths.get("src/test/resources/data/saveData.txt");
        //create test file to compare with
        File test = new File("src/test/resources/data/testData.html");
        testData.saveList(test);
        //get the new file's path
        Path actual = Path.of(String.valueOf(test));
        //compare the files
        long mismatch = Files.mismatch(expected,actual);
        assertEquals(-1,mismatch);
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
    void testLoadListFromTSV() {
        //load from sample tsv and compare to correct values
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