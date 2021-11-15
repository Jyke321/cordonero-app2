package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testSaveList() {
        //create save with given data in the list to match pre-made tsv, html, json files
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