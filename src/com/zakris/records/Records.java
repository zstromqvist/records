package com.zakris.records;

import java.io.*;
import java.util.Random;

public class Records {
    public static void main(String[] args) throws IOException {

        // General stuff
        String fileName = "db.csv";

        System.out.println("Welcome! What do you want to do?\n 1: Search records\n 2: Add record(s)\n 3: Pick a record for me!");
        System.out.print("Choice: ");

        BufferedReader input = new BufferedReader(
            new InputStreamReader(System.in));
        int choice = Integer.parseInt(input.readLine());

        if (choice == 1) {
            System.out.println("What do you want to search for?\n 1: Artist\n 2: Album\n 3: Year");
            BufferedReader choiceInput = new BufferedReader(
                new InputStreamReader(System.in));
            int searchChoice = Integer.parseInt(choiceInput.readLine());

            System.out.println("Please enter what you're looking for?");
            System.out.print("Choice: ");
            BufferedReader searchInput = new BufferedReader(
                new InputStreamReader(System.in));
            String searchWord = searchInput.readLine();
            searchInput.close();

            BufferedReader inFile = new BufferedReader(
                new FileReader(fileName));
            String line = "";
            int counter = 0;
            while ((line = inFile.readLine()) != null) {
                

                String [] recordArray = line.split(",");

                if (recordArray[searchChoice + 1].equals(searchWord)) {
                    System.out.println(recordArray[2] + ", " + recordArray[3] + ", " + recordArray[4] + ", Index: " + recordArray[1]);
                    counter++;
                }
            }
            if (counter == 0) {
                System.out.println("Nothing was found!");
            }
            
            inFile.close();
        } else if(choice == 2){

            PrintWriter outstream = new PrintWriter(
                new BufferedWriter(
                    new FileWriter(fileName, true)));
            
            int currentLastDbId = getLastDbId(fileName); 
            int counter = 1;

            while (true) {
                
                int recordId = currentLastDbId + counter;
                counter++;

                System.out.println("Add below");
                BufferedReader recordInput = new BufferedReader(new InputStreamReader(System.in));

                System.out.print("Artist: ");
                String recordArtist = recordInput.readLine();

                System.out.print("Name of record: ");
                String recordName = recordInput.readLine();

                System.out.print("Year: ");
                int recordYear = Integer.parseInt(recordInput.readLine());

                System.out.print("Year of album press: ");
                int recordPressYear = Integer.parseInt(recordInput.readLine());

                System.out.print("Index: ");
                String recordIndex = recordInput.readLine();

                System.out.print("Notes: ");
                String recordNotes = recordInput.readLine();

                Record newRecord = new Record(
                        recordId, 
                        recordArtist, 
                        recordName, 
                        recordYear, 
                        recordPressYear, 
                        recordIndex,
                        recordNotes);

                outstream.println(
                    newRecord.getId() + "," + 
                    newRecord.getIndex() + "," + 
                    newRecord.getArtist() + "," + 
                    newRecord.getName() + "," + 
                    newRecord.getYear() + "," + 
                    newRecord.getPressYear() + "," + 
                    newRecord.getNotes()
                    ); 
                
                System.out.print("Do you want to add another one? y/n: ");
                String addMore = recordInput.readLine();

                if (addMore.equals("n")) {
                    outstream.close();
                    break;
                }
            }
            
        } else {
            Random r = new Random();
            int randomRecordId = r.nextInt((getLastDbId(fileName) - 1) + 1) + 1;
            BufferedReader inFile = new BufferedReader(
                new FileReader(fileName));

            String line = "";
            while ((line = inFile.readLine()) != null) {

                String [] recordArray = line.split(",");
                if (Integer.toString(randomRecordId).equals(recordArray[0])) {
                    System.out.println(recordArray[2] + ", " + recordArray[3] + ", " + recordArray[4] + ", Index: " + recordArray[1]);
                }
            }
            inFile.close();
        }

    }

    public static int getLastDbId(String dbFileName) throws IOException {
        BufferedReader inFile = new BufferedReader(new FileReader(dbFileName));
        int lastId = -1;
        while (inFile.readLine() != null) {
            lastId++;
        }
        inFile.close();

        return lastId;
    }
}
