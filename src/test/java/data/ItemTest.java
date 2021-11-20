package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    Item testItem = new Item();
    @Test
    void testValidateMonetaryValueFindsError() {
        //test if validating monetary value finds an error
        assertTrue(testItem.validateMonetaryValue(-1));
    }
    @Test
    void testValidateMonetaryValueDoesNotFindError() {
        //test if validating monetary value finds an error
        assertFalse(testItem.validateMonetaryValue(100));
    }
    @Test
    void testValidateSerialNumberFindsError() {
        //test if serial number value finds an error
        assertTrue(testItem.validateSerialNumber("BX-123-23-1234"));
    }
    @Test
    void testValidateSerialNumberDoesNotFindError() {
        //test if serial number value finds an error
        assertFalse(testItem.validateSerialNumber("X-123-23A-123"));
    }
    @Test
    void testValidateNameFindsError() {
        //test if name value finds an error
        assertTrue(testItem.validateName("A"));
    }
    @Test
    void testValidateNameDoesNotFindError() {
        //test if name value finds an error
        assertFalse(testItem.validateName("Aaron"));
    }
    @Test
    void testGetValue() {
        //compare whether getting a value works
        double expected = 0;
        assertEquals(expected,testItem.getValue());
    }
    @Test
    void testGetSerialNumber() {
        //compare whether getting a value works
        String expected = "";
        assertEquals(expected,testItem.getSerialNumber());
    }
    @Test
    void testGetName() {
        //compare whether getting a value works
        String expected = "";
        assertEquals(expected,testItem.getName());
    }
    @Test
    void testSetMonetaryValue() {
        //compare whether the value held within monetary value changed to new value
        Item expectedItem = new Item(100,"","");
        testItem.setValue(100);
        assertEquals(expectedItem.getValue(),testItem.getValue());
    }
    @Test
    void testSetSerialNumber() {
        //compare whether the value held within monetary value changed to new value
        Item expectedItem = new Item(0,"A-XXX-XXX-XXX","");
        testItem.setSerialNumber("A-XXX-XXX-XXX");
        assertEquals(expectedItem.getSerialNumber(),testItem.getSerialNumber());
    }
    @Test
    void testSetName() {
        //compare whether the value held within monetary value changed to new value
        Item expectedItem = new Item(0,"","Box");
        testItem.setName("Box");
        assertEquals(expectedItem.getName(),testItem.getName());
    }
    @Test
    void testGetMonetaryValue() {
        //monetary value is represented in a special string such as $1499.00
        String expected = "$1499.00";
        testItem.setValue(1499);
        assertEquals(expected,testItem.getMonetaryValue());
    }
}