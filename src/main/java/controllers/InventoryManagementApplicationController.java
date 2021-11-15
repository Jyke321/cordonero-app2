package controllers;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Jacob Cordonero
 */

import data.DataHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class InventoryManagementApplicationController {
    //controller data
    private DataHandler data = new DataHandler();
    //controller properties
    @FXML
    private Label itemsCount;
    @FXML
    private TableView<?> itemsTable;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> valueColumn;
    @FXML
    private TableColumn<?, ?> serialNumberColumn;
    @FXML
    private Label addNameLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField valueTextField;
    @FXML
    private TextField serialNumberTextField;
    @FXML
    private Button addItemButton;
    @FXML
    private TextField searchTextField;
    //controller methods
    @FXML
    void addItem(ActionEvent event) {
        //attempt to add item to list (should have no issues if values are validated properly)
    }
    @FXML
    void menuClose(ActionEvent event) {
        //close the open window
    }
    @FXML
    void menuDelete(ActionEvent event) {
        //delete selected item from list
    }
    @FXML
    void menuDeleteAll(ActionEvent event) {
        //delete all items from list
    }
    @FXML
    void menuLoad(ActionEvent event) {
        //start the loading sequence with file chooser

        //call load function in data to finish the rest

    }
    @FXML
    void menuSave(ActionEvent event) {
        //start the saving sequence with file chooser

        //call save function in data to finish the rest

    }
    @FXML
    void menuOpenGuide(ActionEvent event) {
        //open the user guid on my github page (when I add it)

    }
    void initialize() {
        //setup listeners to validate text as it is typed
        //setup listeners to check number of items in list
    }
    void errorHandler(String prompt) {
        //displays an error message with a given prompt
    }
}
