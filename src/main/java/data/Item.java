package data;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Jacob Cordonero
 */

import java.text.NumberFormat;

public class Item {
    //US dollars, greater than 0
    private double value;
    private String monetaryValue;
    //A-XXX-XXX-XXX where A is a letter and X is a number or letter
    private String serialNumber;
    //between 2-256 (inclusive) characters
    private String name;
    private transient NumberFormat formatter = NumberFormat.getCurrencyInstance();
    //constructor
    Item() {
        this.value = 0;
        updateMonetaryValue();
        this.serialNumber = "";
        this.name = "";
    }
    Item(double monetaryValue, String serialNumber, String name) {
        this.value = monetaryValue;
        updateMonetaryValue();
        this.serialNumber = serialNumber;
        this.name = name;
    }
    //validation methods, return boolean
    public boolean validateMonetaryValue(double value) {
        //return true if an error occurred
        return (value <= 0);
    }
    public boolean validateSerialNumber(String string) {
        //return true if an error occurred
        return !string.matches("^[a-zA-z](([- \\\\.])([\\da-zA-z]{3})){3}$");
    }
    public boolean validateName(String string) {
        //return true if an error occurred
        return string.length() < 2 || string.length() > 256;
    }
    //accessor methods
    public double getValue() {
        //return the monetary value as double
        return value;
    }
    public String getMonetaryValue() {
        //return the monetary value as string
        return monetaryValue;
    }
    public String getSerialNumber() {
        //return the serial number
        return serialNumber;
    }
    public String getName() {
        //return the name
        return name;
    }
    //uses validate method, return boolean
    public void setValue(double value) {
        //set the value
        this.value = value;
        updateMonetaryValue();
    }
    public void setSerialNumber(String serialNumber) {
        //set the serial number
        this.serialNumber = serialNumber;
    }
    public void setName(String name) {
        //set the name
        this.name = name;
    }
    private void updateMonetaryValue() {
        String buffer = formatter.format(this.value);
        this.monetaryValue = buffer.replace(",","");
    }
    public void setMonetaryValue(String monetaryValue) {
        this.monetaryValue = monetaryValue;
        this.value = Double.parseDouble(monetaryValue.substring(1));
    }
}
