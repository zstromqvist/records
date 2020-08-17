package com.zakris.records;

import java.io.*;
import java.util.Random;
import java.awt.GridLayout;
//import java.awt.Insets;
import javax.swing.*;
//import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Records {
    public static void main(String[] args) throws NumberFormatException, IOException {

        // General stuff
        String fileName = "db.csv";
        JFrame frame = new JFrame("Record Collector");
        JLabel label = new JLabel("Welcome! What do you want to do?", JLabel.CENTER);
        JPanel panel = new JPanel();

        JButton button1 = new JButton("Search records");
        JButton button2 = new JButton("Add record(s)");
        JButton button3 = new JButton("Pick a record for me!");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                buttonActionPerformed1(evt, frame);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                buttonActionPerformed2(evt, button2);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    buttonActionPerformed3(evt, frame, fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        // add everything to the frame
        frame.setLayout(new GridLayout(2, 1));
        frame.add(label);
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        System.out.println(
                "Welcome! What do you want to do?\n 1: Search records\n 2: Add record(s)\n 3: Pick a record for me!");
        // System.out.print("Choice: ");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int choice = Integer.parseInt(input.readLine());

        if (choice == 1) {
            System.out.println("What do you want to search for?\n 1: Artist (Default)\n 2: Album\n 3: Year");
            System.out.print("Choice: ");
            BufferedReader choiceInput = new BufferedReader(new InputStreamReader(System.in));
            String inputChoice = choiceInput.readLine();
            int searchChoice;

            if (inputChoice.equals("")) {
                searchChoice = 1;
            } else {
                searchChoice = Integer.parseInt(inputChoice);
            }

            System.out.print("Please enter what you're looking for: ");
            BufferedReader searchInput = new BufferedReader(new InputStreamReader(System.in));
            String searchWord = searchInput.readLine();
            searchInput.close();

            BufferedReader inFile = new BufferedReader(new FileReader(fileName));
            String line = "";
            int counter = 0;
            int counter2 = 0;
            while ((line = inFile.readLine()) != null) {

                String[] recordArray = line.split(",");

                if (recordArray[searchChoice + 1].toLowerCase().contains(searchWord.toLowerCase()) && counter2 > 0) {
                    System.out.println(recordArray[2] + ", " + recordArray[3] + ", " + recordArray[4] + ", "
                            + recordArray[5] + ", Index: " + recordArray[1]);
                    counter++;
                }
                counter2++;
            }
            if (counter == 0) {
                System.out.println("Nothing was found!");
            }

            inFile.close();
        } else if (choice == 2) {

            PrintWriter outstream = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));

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

                System.out.print("Year of album press (0 = Same as origin year): ");
                int recordPressYear = Integer.parseInt(recordInput.readLine());
                if (recordPressYear == 0) {
                    recordPressYear = recordYear;
                }

                System.out.println("Format\n 1: Vinyl\n 2: CD");
                System.out.print("1 or 2: ");
                int recordFormatInput = Integer.parseInt(recordInput.readLine());
                String recordFormat = "";

                if (recordFormatInput == 1) {
                    recordFormat = "Vinyl";
                } else {
                    recordFormat = "CD";
                }

                System.out.print("Index: ");
                String recordIndex = recordInput.readLine();

                System.out.print("Notes: ");
                String recordNotes = recordInput.readLine();

                Record newRecord = new Record(recordId, recordIndex, recordArtist, recordName, recordYear, recordFormat,
                        recordPressYear, recordNotes);

                outstream.println(newRecord.getId() + "," + newRecord.getIndex() + "," + newRecord.getArtist() + ","
                        + newRecord.getName() + "," + newRecord.getYear() + "," + newRecord.getFormat() + ","
                        + newRecord.getPressYear() + "," + newRecord.getNotes());

                System.out.println("You added:");
                System.out.println("Artist: " + newRecord.getArtist() + ", Album name: " + newRecord.getName()
                        + ", Year: " + newRecord.getYear() + ", Press year: " + newRecord.getPressYear() + ", Format: "
                        + newRecord.getFormat() + ", Notes: " + newRecord.getNotes() + ", Index: "
                        + newRecord.getIndex());

                System.out.print("Do you want to add another one? y/n: ");
                String addMore = recordInput.readLine();

                if (addMore.equals("n")) {
                    outstream.close();
                    break;
                }
            }

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

    private static void buttonActionPerformed1(ActionEvent evt, JFrame frame) {
        JOptionPane.showMessageDialog(frame, "message", "Title", 0);
    }

    private static void buttonActionPerformed2(ActionEvent evt, JButton button2) {
        button2.setEnabled(false);
    }

    private static void buttonActionPerformed3(ActionEvent evt, JFrame frame, String fileName) throws IOException {

            Random r = new Random();
            int randomRecordId = r.nextInt((getLastDbId(fileName) - 1) + 1) + 1;
            BufferedReader inFile = new BufferedReader(new FileReader(fileName));

            String line = "";
            while ((line = inFile.readLine()) != null) {

                String[] recordArray = line.split(",");
                if (Integer.toString(randomRecordId).equals(recordArray[0])) {
                    JOptionPane.showMessageDialog(frame, recordArray[2] + ", " + recordArray[3] + ", " + recordArray[4] + ", "
                            + recordArray[5] + ", Index: " + recordArray[1], "Listen to this!", 1);
                }
            }
            inFile.close();
    }
}