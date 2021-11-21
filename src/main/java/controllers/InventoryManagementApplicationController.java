package controllers;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Jacob Cordonero
 */

import data.DataHandler;
import data.Item;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Locale;

public class InventoryManagementApplicationController {
    //controller data
    private final DataHandler data = new DataHandler();
    //controller properties
    @FXML
    private Label itemsCount;
    @FXML
    private TableView<Item> itemsTable;
    @FXML
    private TableColumn<Item, String> nameColumn;
    @FXML
    private TableColumn<Item, String> valueColumn;
    @FXML
    private TableColumn<Item, String> serialNumberColumn;
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
    @FXML
    private VBox vBox;
    //controller methods
    @FXML
    void addItem() {
        //attempt to add item to list (should have no issues if values are validated properly)
        String error = "Invalid Monetary Value";
        if (valueTextField.getText().matches("^[\\$][\\d]+$")) {
            Item addItem = new Item(valueTextField.getText(),serialNumberTextField.getText(),
                    nameTextField.getText());
            error = data.addItemToList(addItem);
        }
        if (!error.isEmpty())
            errorHandler(error);
        else {
            itemsCount.setText("Items (" + data.getItemCount() + "/1024)");
            itemsTable.getItems();
            updateTable();
            clearTextFields();
        }
    }
    private void updateTable() {
        String s = searchTextField.getText();
        searchTextField.setText(" ");
        searchTextField.setText(s);
    }
    private void clearTextFields() {
        valueTextField.clear();
        nameTextField.clear();
        serialNumberTextField.clear();
    }
    @FXML
    void menuClose() {
        //close the open window
        System.exit(0);
    }
    @FXML
    void menuDelete() {
        //delete selected item from list
        data.deleteItemInList(itemsTable.getSelectionModel().getFocusedIndex());
    }
    @FXML
    void menuDeleteAll() {
        //delete all items from list
        data.deleteAllItemsInList();
    }
    FileChooser fileChooser = new FileChooser();
    @FXML
    void menuLoad() {
        //start the loading sequence with file chooser
        Window stage = vBox.getScene().getWindow();
        fileChooser.setTitle("Load Dialog");
        //reset file extensions
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file","*.txt"),
                new FileChooser.ExtensionFilter("html","*.html"),new FileChooser.ExtensionFilter("json","*.json"));
        try {
            File file = fileChooser.showOpenDialog(stage);
            fileChooser.setInitialDirectory(file.getParentFile());
            //call load function in data to finish the rest
            boolean error = data.loadList(file);
            if (error)
                errorHandler("Invalid file");
        } catch (Exception e) {

        }
    }
    @FXML
    void menuSave() {
        //start the saving sequence with file chooser
        Window stage = vBox.getScene().getWindow();
        fileChooser.setTitle("Save Dialog");
        //reset file extensions
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file","*.txt"),
                new FileChooser.ExtensionFilter("html","*.html"),new FileChooser.ExtensionFilter("json","*.json"));
        try {
            File file = fileChooser.showSaveDialog(stage);
            fileChooser.setInitialDirectory(file.getParentFile());//save the chosen directory
            //call save function in data to finish the rest
            boolean error = data.saveList(file);
            if (error)
                errorHandler("Invalid file");
        } catch (Exception e) {

        }
    }
    @FXML
    void menuOpenGuide() {
        //open the user guide on my GitHub page (when I add it)
        //https://github.com/Jyke321/cordonero-app2
        String guideUrl = "https://github.com/Jyke321/cordonero-app2";
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(URI.create(guideUrl));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void initialize() throws IOException {
        String currentPath = new java.io.File(".").getCanonicalPath();
        fileChooser.setInitialDirectory(new File(currentPath));
        //set up columns
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("monetaryValue"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        //setup observable list
        ObservableList<Item> observableList = FXCollections.observableList(data.getList());
        FilteredList<Item> filteredList = new FilteredList<>(observableList, p->true);
        SortedList<Item> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(itemsTable.comparatorProperty());
        searchTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredList.setPredicate(item -> {
                if (newValue.isEmpty() || observable.getValue().isEmpty())
                    return true;
                else if(item.getName().toLowerCase().contains(newValue.toLowerCase()))
                    return true;
                else if(item.getSerialNumber().contains(newValue))
                    return true;
                else
                    return false;
            });
        }));
        observableList.addListener((ListChangeListener<Item>) c -> {
            itemsCount.setText("Items (" + data.getItemCount() + "/1024)");
            itemsTable.getItems();
        });
        itemsTable.setItems(sortedList);
        //setup listeners to validate text as it is typed
        valueTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                if (newValue.charAt(0) != '$')
                    valueTextField.setText("$" + newValue);
            }
        });
        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            addNameLabel.setText("Name (" + newValue.length() + "/256)");
        });
        serialNumberTextField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER))
                addItem();
        });
    }
    void errorHandler(String prompt) {
        //displays an error message with a given prompt
        Alert alert = new Alert(Alert.AlertType.ERROR, prompt, ButtonType.CLOSE);
        alert.showAndWait();
    }
}
