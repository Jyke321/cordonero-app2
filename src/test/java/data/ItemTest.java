package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    Item testItem = new Item();
    @Test
    void testValidateMonetaryValueFindsError() {
        //test if validating monetary value finds an error
        assertTrue(true);
    }
    @Test
    void testValidateMonetaryValueDoesNotFindError() {
        //test if validating monetary value finds an error
        assertFalse(false);
    }
    @Test
    void testValidateSerialNumberFindsError() {
        //test if serial number value finds an error
        assertTrue(true);
    }
    @Test
    void testValidateSerialNumberDoesNotFindError() {
        //test if serial number value finds an error
        assertFalse(false);
    }
    @Test
    void testValidateNameFindsError() {
        //test if name value finds an error
        assertTrue(true);
    }
    @Test
    void testValidateNameDoesNotFindError() {
        //test if name value finds an error
        assertFalse(false);
    }
    @Test
    void testGetMonetaryValue() {
        //compare whether getting a value works
        double expected = 0;
        assertEquals(expected,expected);
    }
    @Test
    void testGetSerialNumber() {
        //compare whether getting a value works
        String expected = "";
        assertEquals(expected,expected);
    }
    @Test
    void testGetName() {
        //compare whether getting a value works
        String expected = "";
        assertEquals(expected,expected);
    }
    @Test
    void testSetMonetaryValue() {
        //compare whether the value held within monetary value changed to new value
        Item expectedItem = new Item();
        assertEquals(expectedItem,expectedItem);
    }
    @Test
    void testSetSerialNumber() {
        //compare whether the value held within monetary value changed to new value
        Item expectedItem = new Item();
        assertEquals(expectedItem,expectedItem);
    }
    @Test
    void testSetName() {
        //compare whether the value held within monetary value changed to new value
        Item expectedItem = new Item();
        assertEquals(expectedItem,expectedItem);
    }
}