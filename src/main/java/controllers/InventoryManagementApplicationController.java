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
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Comparator;

public class InventoryManagementApplicationController {
    //controller data
    private static final DataHandler data = new DataHandler();
    private ObservableList<Item> observableList;
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
    private TextField searchTextField;
    @FXML
    private CheckMenuItem nameCheckMenu;
    @FXML
    private CheckMenuItem serialNumberCheckMenu;
    @FXML
    private VBox vBox;
    //controller methods
    @FXML
    void addItem() {
        //attempt to add item to list (should have no issues if values are validated properly)
        String error = "Invalid Monetary Value";
        if (valueTextField.getText().matches("^[$][\\d.]+$")) {
            Item addItem = new Item(valueTextField.getText(),serialNumberTextField.getText(),
                    nameTextField.getText());
            error = data.addItemToList(addItem);
        }
        if (!error.isEmpty())
            errorHandler(error);
        else {
            updateObservableList();
            clearTextFields();
        }
    }
    @FXML
    void menuClose() {
        //close the open window
        System.exit(0);
    }
    @FXML
    void menuDelete() {
        //delete selected item from list
        data.deleteItemInList(itemsTable.getSelectionModel().getSelectedItem());
        updateObservableList();
    }
    @FXML
    void menuDeleteAll() {
        //delete all items from list
        data.deleteAllItemsInList();
        updateObservableList();
    }
    FileChooser fileChooser = new FileChooser();
    @FXML
    void menuLoad() throws IOException {
        //start the loading sequence with file chooser
        Window stage = vBox.getScene().getWindow();
        fileChooser.setTitle("Load Dialog");
        //reset file extensions
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file","*.txt"),
                new FileChooser.ExtensionFilter("html","*.html"),new FileChooser.ExtensionFilter("json","*.json"));
        File file = fileChooser.showOpenDialog(stage);
        fileChooser.setInitialDirectory(file.getParentFile());
        //call load function in data to finish the rest
        boolean error = data.loadList(file);
        if (error)
            errorHandler("Invalid file");
        updateObservableList();
    }
    @FXML
    void menuSave() throws IOException {
        //start the saving sequence with file chooser
        Window stage = vBox.getScene().getWindow();
        fileChooser.setTitle("Save Dialog");
        //reset file extensions
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file","*.txt"),
                new FileChooser.ExtensionFilter("html","*.html"),new FileChooser.ExtensionFilter("json","*.json"));
        File file = fileChooser.showSaveDialog(stage);
        fileChooser.setInitialDirectory(file.getParentFile());//save the chosen directory
        //call save function in data to finish the rest
        boolean error = data.saveList(file);
        if (error)
            errorHandler("Invalid file");
        updateObservableList();
    }
    @FXML
    void setSearchByName() {
        if (!(nameCheckMenu.isSelected() || serialNumberCheckMenu.isSelected()))
            serialNumberCheckMenu.setSelected(true);
        updateSearchTextField();
    }
    @FXML
    void setSearchBySerialNumber() {
        if (!(nameCheckMenu.isSelected() || serialNumberCheckMenu.isSelected()))
            nameCheckMenu.setSelected(true);
        updateSearchTextField();
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
        //set up columns and their listeners
        initializeTableColumns();
        //set search methods to both true
        serialNumberCheckMenu.setSelected(true);
        nameCheckMenu.setSelected(true);
        //set up the observable list and it's associated fields
        initializeObservableList();
        //setup listeners on Item text fields
        initializeTextFields();
        updateObservableList();
        itemsTable.setEditable(true);
    }
    private void initializeTableColumns() {
        //monetary value column set up
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("monetaryValue"));
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        Comparator<String> valueComparator = (String v1, String v2) -> (int) (Double.parseDouble(v1.substring(1)) - Double.parseDouble(v2.substring(1)));
        valueColumn.setComparator(valueComparator);
        //name column set up
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //serial number column set up
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        serialNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //add listeners
        valueColumn.setOnEditCommit(t -> errorHandler(data.editItemInList(t.getRowValue(),
                new Item(t.getNewValue(),t.getRowValue().getSerialNumber(),t.getRowValue().getName()))));
        nameColumn.setOnEditCommit(t -> errorHandler(data.editItemInList(t.getRowValue(),
                new Item(t.getRowValue().getMonetaryValue(),t.getRowValue().getSerialNumber(),t.getNewValue()))));
        serialNumberColumn.setOnEditCommit(t -> errorHandler(data.editItemInList(t.getRowValue(),
                new Item(t.getRowValue().getMonetaryValue(),t.getNewValue(),t.getRowValue().getName()))));
    }
    private void initializeObservableList() {
        observableList = FXCollections.observableArrayList();
        FilteredList<Item> filteredList = new FilteredList<>(observableList, p->true);
        SortedList<Item> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(itemsTable.comparatorProperty());
        observableList.addListener((ListChangeListener<Item>) c -> {
            itemsCount.setText("Items (" + data.getItemCount() + "/1024)");
            itemsTable.getItems();
        });
        searchTextField.textProperty().addListener(((observable, oldValue, newValue) ->
                filteredList.setPredicate(item -> {
                    if (newValue.isEmpty() || observableList.isEmpty()) {
                        return true;
                    }
                    else if(item.getName().toLowerCase().contains(newValue.toLowerCase()) && nameCheckMenu.isSelected()) {
                        return true;
                    }
                    else return item.getSerialNumber().contains(newValue) && serialNumberCheckMenu.isSelected();
                })));
        itemsTable.setItems(sortedList);
    }
    private void initializeTextFields() {
        //value text field listeners
        valueTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            //update to ensure a $ is included
            if (!newValue.isEmpty() && newValue.charAt(0) != '$') {
                valueTextField.setText("$" + newValue);
            }});
        valueTextField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                addItem();
                resetCaretPositionToNameField();
            }
        });//enter key add item
        //name text field listeners
        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> addNameLabel.setText("Name (" + newValue.length() + "/256)"));
        nameTextField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                addItem();
                resetCaretPositionToNameField();
            }
        });//enter key add item
        serialNumberTextField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                addItem();
                resetCaretPositionToNameField();
            }
        });//enter key add item
    }
    private void resetCaretPositionToNameField() {
        if (serialNumberTextField.getText().isEmpty() && valueTextField.getText().isEmpty()
                && nameTextField.getText().isEmpty()) {
            nameTextField.requestFocus();
        }
    }
    private void clearTextFields() {
        valueTextField.clear();
        nameTextField.clear();
        serialNumberTextField.clear();
    }
    private void updateSearchTextField() {
        searchTextField.appendText(" ");
        searchTextField.setText(searchTextField.getText(0,searchTextField.getLength()-1));
        searchTextField.positionCaret(searchTextField.getLength());
    }
    private void updateObservableList() {
        observableList.clear();
        observableList.addAll(data.getList());
    }
    private void errorHandler(String prompt) {
        //displays an error message with a given prompt
        updateObservableList();
        if (prompt.isEmpty()) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR, prompt, ButtonType.CLOSE);
        alert.showAndWait();
    }
}
