package data;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Jacob Cordonero
 */

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class DataHandler {
    //1024 Items (will probably change to observable list
    private List<Item> list = new ArrayList<Item>();
    //a buffer to hold data to be saved or decrypted after being loaded
    private String dataBuffer;
    //holds save and load methods
    public boolean saveList(File saveLocation) throws IOException {
        //call toParse()
        boolean invalidFile = parseToSaveBuffer(saveLocation);
        //if extension is valid save the file, else cause an error
        //save the data in buffer to the desired file
        try (FileWriter fileWriter = new FileWriter(saveLocation.getAbsolutePath())) {
            fileWriter.write(dataBuffer);
        }
        return invalidFile;
    }
    public boolean loadList(File loadLocation) throws IOException {
        //call parse from method
        return parseFromFileToBuffer(loadLocation);
    }

    //other methods to access list values

    public List getList() {
        return list;
    }

    public int getItemCount() {
        return list.size();
    }
    public String addItemToList(Item item) {
        String error = validateItem(item);
        if (error.isEmpty())
            if (!list.contains(item)) {
                list.add(item);
            } else
                error = "Item already exists!";
        return error;
    }
    //uses Item.set(), return boolean
    public String editItemInList(int i,Item item) {
        String error = validateItem(item);
        if (error.isEmpty())
            if (!list.contains(item)) {
                list.add(i,item);
                list.remove(i+1);
            }
            else
                error = "Item already exists!";
        return error;
    }
    public void deleteItemInList(int i) {
        list.remove(i);
    }
    public void deleteAllItemsInList() {
        list.clear();
    }
    private String validateItem(Item item) {
        //validate an item that has already been populated
        String validationError = "";
        if (item.validateSerialNumber(item.getSerialNumber()))
            validationError = "Invalid Serial Number\n";
        else if (list.contains(item.getSerialNumber()))
            validationError = "Serial Number already in use\n";
        if (item.validateName(item.getName()))
            validationError = "Invalid Name\n";
        if (item.validateMonetaryValue(item.getValue()))
            validationError = "Invalid Monetary Value\n";
        return validationError;
    }
    //parse data into specified file format
    private boolean parseToSaveBuffer(File file) throws IOException {
        //calls a parse method based off file extension
        String fileType = Files.probeContentType(Path.of(String.valueOf(file)));
        switch (fileType) {
            case "text/plain":
                //call parseToTSV()
                parseToTSV();
                break;
            case "text/html":
                //call parseToHTML()
                parseToHTML();
                break;
            case "application/json":
                //call parseToJSON
                parseToJSON();
                break;
            default:
                dataBuffer = "Invalid File Type";
                return true;
        }
        return false;
    }
    private void parseToTSV() {
        StringBuilder stringBuffer = new StringBuilder();
        String tab = "    ";
        stringBuffer.append(String.format("%s%s%s%s%s","Serial Number", tab, "Name", tab, "Value"));
        for (int i = 0; i<getItemCount(); i++) {
            stringBuffer.append(String.format("%n%s%s%s%s%s",list.get(i).getSerialNumber(), tab,
                    list.get(i).getName(), tab, list.get(i).getMonetaryValue()));
        }
        dataBuffer = String.valueOf(stringBuffer);
    }
    private void parseToHTML() {
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("<!doctype html>" + "<html lang=\"en\">" +
            "<head>" + "<style type=\"text/css\">" + "table, td {" +
            "border: 1px solid #333;" + "}" + "thead, tfoot {" +
            "background-color: #333;" + "color: #fff;" + "}" +
            "</style>" + "<meta charset=\"UTF-8\">" +
            "<title>saveData</title>" + "</head>" + "<body>" +
            "<table>" + "<thead>" + "<tr>" + "<th scope=\"col\">Serial Number</th>" +
            "<th scope=\"col\">Name</th>" + "<th scope=\"col\">Value</th>" +
            "</tr>" + "</thead>" + "<tbody>");
        for (int i = 0; i<getItemCount(); i++) {
            stringBuffer.append(String.format("%s" +
                "%s%s%s" + "%s%s%s" + "%s%s%s" + "%s%s%s" + "%s",
                "<tr>", "<td>",list.get(i).getSerialNumber(),"</td>",
                "<td>",list.get(i).getName(),"</td>",
                "<td>",list.get(i).getMonetaryValue(),"</td>",
                "<td hidden>",list.get(i).getValue(),"</td>", "</tr>"));
        }
        stringBuffer.append("</tbody>" + "</table>" +
            "</body>" + "</html>");
        Document doc = Jsoup.parse(String.valueOf(stringBuffer));
        dataBuffer = doc.toString();
    }
    private void parseToJSON() {
         Gson gson = new Gson();
         dataBuffer = gson.toJson(list);
    }
    //parse data from specified file formats
    private boolean parseFromFileToBuffer(File file) throws IOException {
        //calls a parse from file method based on file type
        String fileType = Files.probeContentType(Path.of(String.valueOf(file)));
        switch (fileType) {
            case "text/plain":
                //call parseFromTSV()
                parseFromTSV(file);
                break;
            case "text/html":
                //call parseFromHTML()
                parseFromHTML(file);
                break;
            case "application/json":
                //call parseFromJSON
                parseFromJSON(file);
                break;
            default:
                dataBuffer = fileType;
                return true;
        }
        return false;
    }
    private void parseFromTSV(File file) {
        deleteAllItemsInList();
        try (Scanner in = new Scanner(new FileReader(file.getAbsolutePath()))) {
            if (in.hasNext())
                in.nextLine();
            in.useDelimiter("[    ]|[\n]");
            while(in.hasNext()) {
                dataBuffer = in.nextLine();
                dataBuffer = dataBuffer.replace("    ", "\n");
                Stream<String> stringBuffer = dataBuffer.lines();
                List<String> list = stringBuffer.toList();
                Item itemBuffer = new Item();
                itemBuffer.setSerialNumber(list.get(0));
                itemBuffer.setName(list.get(1));
                itemBuffer.setMonetaryValue(list.get(2));
                addItemToList(itemBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void parseFromHTML(File file) {
        deleteAllItemsInList();
        try (Scanner in = new Scanner(new FileReader(file.getAbsolutePath()))) {
            in.useDelimiter("<tr>\n        <td>|</tr>\n    <tr>");
            if (in.hasNext()) {
                in.next();
            }
            while(in.hasNext()) {
                dataBuffer = in.next();
                dataBuffer = dataBuffer.replace("</td>", "");
                dataBuffer = dataBuffer.replace("<td hidden>", "");
                dataBuffer = dataBuffer.replace("<td>", "");
                dataBuffer = dataBuffer.replace("\n","");
                dataBuffer = dataBuffer.replace("        ","\n");
                dataBuffer = dataBuffer.strip();
                Stream<String> stringBuffer = dataBuffer.lines();
                List<String> list = stringBuffer.toList();
                Item itemBuffer = new Item();
                itemBuffer.setSerialNumber(list.get(0));
                itemBuffer.setName(list.get(1));
                itemBuffer.setMonetaryValue(list.get(2));
                addItemToList(itemBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void parseFromJSON(File file) {
        deleteAllItemsInList();
        Gson gson = new Gson();
        try (Reader in = new FileReader(file.getAbsolutePath())){
            JsonReader json = new JsonReader(in);
            Item[] itemArray = gson.fromJson(json, Item[].class);
            list = Arrays.stream(itemArray).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //get Item member values
    public String getItemSerialNumber(int i) {
        return list.get(i).getSerialNumber();
    }
    public String getItemName(int i) {
        return list.get(i).getName();
    }
    public String getItemMonetaryValue(int i) {
        return list.get(i).getMonetaryValue();
    }
    public double getItemValue(int i) {
        return list.get(i).getValue();
    }
}
