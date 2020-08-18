package com.zakris.records;

import java.io.*;

public class RecordCollector {
    public static void main(String[] args) throws IOException {

        RecordGui gui = new RecordGui();
        gui.startGui();
        DatabaseConnection dbCon = new DatabaseConnection();
        System.out.println(dbCon.dbPath());

        
    }

}