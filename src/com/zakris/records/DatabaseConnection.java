package com.zakris.records;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DatabaseConnection {

    public String dbPath() {
        return "db.csv";
    }

    public int getLastDbId(String dbFileName) throws IOException {
        BufferedReader inFile = new BufferedReader(new FileReader(dbFileName));
        int lastId = -1;
        while (inFile.readLine() != null) {
            lastId++;
        }
        inFile.close();

        return lastId;
    }
}