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

        outstream.println(
                newRecord.getId() + "," + newRecord.getIndex() + "," + newRecord.getArtist() + "," + newRecord.getName()
                        + "," + newRecord.getYear() + "," + newRecord.getFormat() + "," + newRecord.getPressYear());

        outstream.close();
    }
}