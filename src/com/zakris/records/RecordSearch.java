package com.zakris.records;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RecordSearch {

    public String doSearch() {
        String search = "zep";
        return search;
    }

    public String showSearchResult(String searchWord) throws IOException {

        String searchType = "Artist".toLowerCase();
        StringBuilder results = new StringBuilder();
        DatabaseConnection con = new DatabaseConnection();
        BufferedReader dbFile = new BufferedReader(new FileReader(con.dbPath()));

        int searchTypeIndex = 0;
        if (searchType.equals("artist")) {
            searchTypeIndex = 2;
        } else if (searchType.equals("artist")) {
            searchTypeIndex = 3;
        } else if (searchType.equals("artist")) {
            searchTypeIndex = 4;
        }
        
        String line = "";
        int counter = 0;
        int counter2 = 0;
        while ((line = dbFile.readLine()) != null) {

            String[] recordArray = line.split(",");

            if (recordArray[searchTypeIndex].toLowerCase().contains(searchWord.toLowerCase()) && counter2 > 0) {
                results.append(recordArray[2] + ", " + recordArray[3] + ", " + recordArray[4] + ", "
                        + recordArray[5] + ", Index: " + recordArray[1] + "\n");
                counter++;
            }
            counter2++;
        }
        if (counter == 0) {
            results.append("Nothing was found!");
        }
        dbFile.close();

        return results.toString();
    }
}