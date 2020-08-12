package com.zakris.records;

import java.io.*;

public class Records {
    public static void main(String[] args) throws IOException {

        // General stuff
        String fileName = "db.csv";

        System.out.println("Welcome! What do you want to do?\n 1: Search records\n 2: Add record(s)");
        System.out.print("Choice: ");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int choice = Integer.parseInt(input.readLine());

        if (choice == 1) {
            System.out.println("What do you want to search for?\n 1: Artist\n 2: Album\n 3: Year");
            BufferedReader choiceInput = new BufferedReader(new InputStreamReader(System.in));
            int searchChoice = Integer.parseInt(choiceInput.readLine());

            System.out.println("Please enter what you're looking for?");
            System.out.print("Choice: ");
            BufferedReader searchInput = new BufferedReader(new InputStreamReader(System.in));
            String searchWord = searchInput.readLine();
            searchInput.close();

            BufferedReader inFile = new BufferedReader(new FileReader(fileName));
            String line = "";
            int counter = 0;
            while ((line = inFile.readLine()) != null) {
                

                String [] t = line.split(",");

                if (t[searchChoice + 1].equals(searchWord)) {
                    System.out.println(t[2] + ", " + t[3] + ", " + t[4] + ", Index: " + t[1]);
                    counter++;
                }
            }
            if (counter == 0) {
                System.out.println("Nothing was found!");
            }
            
            inFile.close();
        } else {
            System.out.println("You chose 2");
        }

        
    }
}
