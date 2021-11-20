package data;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Jacob Cordonero
 */

import com.google.gson.Gson;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;

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
    public void loadList(File loadLocation) {

    }
    //other methods to access list values
    public List getList() {
        return list;
    }
    public int getItemCount() {
        return list.size();
    }
    public void addItemToList(Item item) {
        list.add(item);
    }
    //uses Item.set(), return boolean
    public void editItemInList() {

    }
    public void deleteItemInList() {

    }
    public void deleteAllItemsInList() {

    }
    private boolean validateItem() {
        return false;
    }
    //parse data into specified file format
    private boolean parseToSaveBuffer(File file) throws IOException {
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
                dataBuffer = fileType;
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
        
    }
    private void parseToJSON() {
         Gson gson = new Gson();
         dataBuffer = gson.toJson(list);
         /*
         Could be used to make format nice, but my test doesn't
         like it when one txt is LF and the other is CRLF
         dataBuffer = dataBuffer.replace("[","[\n");
         dataBuffer = dataBuffer.replace("{","  {\n");
         dataBuffer = dataBuffer.replace("]","\n]");
         dataBuffer = dataBuffer.replace("}","\n  }");
         dataBuffer = dataBuffer.replace(",",",\n");
         dataBuffer = dataBuffer.replaceAll("(\\G|\\n)(\\\")","\n    \"");
          */
    }
}
