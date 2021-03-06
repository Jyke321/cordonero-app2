package data;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Jacob Cordonero
 */

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class DataHandler {
    //1024 Items (will probably change to observable list
    private List<Item> list = new ArrayList<>();
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

    public List<Item> getList() {
        return list;
    }

    public int getItemCount() {
        return list.size();
    }
    public String addItemToList(Item item) {
        String error = validateItem(item);
        if (error.isEmpty()) {
            if (checkListForRepeatedSerialNumber(item.getSerialNumber(),"")) {
                error = "Serial number already exists!";
            } else {
                list.add(item);
            }
        }
        return error;
    }
    //checks list for repeated serial number
    private boolean checkListForRepeatedSerialNumber(String serialNumber,String originalSN){
        if (serialNumber.compareTo(originalSN) != 0) {
            for (Item item : list) {
                if (item.getSerialNumber().contains(serialNumber))
                    return true;
            }
        }
        return false;
    }
    //swaps items return error message if any
    public String editItemInList(Item originalItem,Item item) {
        String error = validateItem(item);
        if (error.isEmpty()) {
            if (checkListForRepeatedSerialNumber(item.getSerialNumber(),originalItem.getSerialNumber())) {
                error = "Serial number already exists!";
            } else {
                list.set(list.indexOf(originalItem),item);
            }
        }
        return error;
    }
    //delete items
    public void deleteItemInList(Item i) {
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
            case "text/plain" ->
                    //call parseToTSV()
                    parseToTSV();
            case "text/html" ->
                    //call parseToHTML()
                    parseToHTML();
            case "application/json" ->
                    //call parseToJSON
                    parseToJSON();
            default -> {
                dataBuffer = "Invalid File Type";
                return true;
            }
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
            stringBuffer.append(String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
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
            case "text/plain" ->
                    //call parseFromTSV()
                    parseFromTSV(file);
            case "text/html" ->
                    //call parseFromHTML()
                    parseFromHTML(file);
            case "application/json" ->
                    //call parseFromJSON
                    parseFromJSON(file);
            default -> {
                dataBuffer = fileType;
                return true;
            }
        }
        return false;
    }
    private void parseFromTSV(File file) {
        deleteAllItemsInList();
        try (Scanner in = new Scanner(new FileReader(file.getAbsolutePath()))) {
            if (in.hasNext())
                in.nextLine();
            in.useDelimiter("[ ]{4}|[\n]");
            while(in.hasNext()) {
                dataBuffer = in.nextLine();
                dataBuffer = dataBuffer.replace("    ", "\n");
                Stream<String> stringBuffer = dataBuffer.lines();
                List<String> bufferList = stringBuffer.toList();
                Item itemBuffer = new Item();
                itemBuffer.setSerialNumber(bufferList.get(0));
                itemBuffer.setName(bufferList.get(1));
                itemBuffer.setMonetaryValue(bufferList.get(2));
                addItemToList(itemBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void parseFromHTML(File file) {
        deleteAllItemsInList();
        Document htmlDoc;
        try {
            htmlDoc = Jsoup.parse(file, "ISO-8859-1");
            Element table = htmlDoc.selectFirst("table");
            Iterator<Element> row = table.select("tr").iterator();
            //skip th row
            row.next();
            while (row.hasNext()) {
                Iterator<Element> ite = (row.next()).select("td").iterator();
                String serialNumber = ite.next().text();
                String name = ite.next().text();
                String value = ite.next().text();
                Item newItem = new Item(value,serialNumber,name);
                addItemToList(newItem);
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
