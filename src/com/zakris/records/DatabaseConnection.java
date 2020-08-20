package com.zakris.records;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DatabaseConnection {
    private String dbFileName = "db.csv";

    public String dbPath() {
        return dbFileName;
    }

    public int getLastDbId() throws IOException {
        BufferedReader inFile = new BufferedReader(new FileReader(dbFileName));
        int lastId = -1;
        while (inFile.readLine() != null) {
            lastId++;
        }
        inFile.close();

        return lastId;
    }

    public void addRecordToDb(Record newRecord) throws IOException {

        PrintWriter outstream = new PrintWriter(new BufferedWriter(new FileWriter(dbFileName, true)));

        outstream.println(newRecord.getId() + "," + newRecord.getIndex() + "," + newRecord.getArtist() + ","
                + newRecord.getName() + "," + newRecord.getYear() + "," + newRecord.getFormat() + ","
                + newRecord.getPressYear() + "," + newRecord.getNotes());

        outstream.close();
    }

    public String searchDb(String searchTerm, String searchType) throws IOException {

        BufferedReader dbFile = new BufferedReader(new FileReader(dbFileName));
        StringBuilder results = new StringBuilder();

        int searchTypeIndex = 0;
        if (searchType.toLowerCase().equals("artist")) {
            searchTypeIndex = 2;
        } else if (searchType.toLowerCase().equals("album")) {
            searchTypeIndex = 3;
        } else if (searchType.toLowerCase().equals("year")) {
            searchTypeIndex = 4;
        }

        String line = "";
        int counter = 0;
        int counter2 = 0;
        while ((line = dbFile.readLine()) != null) {

            String[] recordArray = line.split(",");

            if (recordArray[searchTypeIndex].toLowerCase().contains(searchTerm.toLowerCase()) && counter2 > 0) {
                results.append(recordArray[2] + ", " + recordArray[3] + ", " + recordArray[4] + ", " + recordArray[5]
                        + ", Index: " + recordArray[1] + "\n");
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