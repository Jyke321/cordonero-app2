package data;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Jacob Cordonero
 */

public class Item {
    //US dollars, greater than 0
    private double monetaryValue;
    //A-XXX-XXX-XXX where A is a letter and X is a number or letter
    private String serialNumber;
    //between 2-256 (inclusive) characters
    private String name;
    //validation methods, return boolean
    public boolean validateMonetaryValue(String string) {
        //return true if an error occurred
        return false;
    }
    public boolean validateSerialNumber(String string) {
        //return true if an error occurred
        return false;
    }
    public boolean validateName(String string) {
        //return true if an error occurred
        return false;
    }
    //accessor methods
    public double getMonetaryValue() {
        //return the monetary value
        return 0;
    }
    public String getSerialNumber() {
        //return the serial number
        return "";
    }
    public String getName() {
        //return the name
        return "";
    }
    //uses validate method, return boolean
    public void setMonetaryValue(String value) {
        //set the value
    }
    public void setSerialNumber(String serialNumber) {
        //set the serial number
    }
    public void setName(String name) {
        //set the name
    }
}
