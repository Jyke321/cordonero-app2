package controllers;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Jacob Cordonero
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class InventoryManagementApplicationController {

    @FXML
    private Button addItemButton;

    @FXML
    private Label addNameLabel;

    @FXML
    private Label itemsCount;

    @FXML
    private TableView<?> itemsTable;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableColumn<?, ?> serialNumberColumn;

    @FXML
    private TextField serialNumberTextField;

    @FXML
    private TableColumn<?, ?> valueColumn;

    @FXML
    private TextField valueTextField;

    @FXML
    void addItem(ActionEvent event) {

    }

}
