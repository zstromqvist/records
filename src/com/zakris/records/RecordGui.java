package com.zakris.records;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.util.Random;

import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RecordGui {

    protected JButton button1, button2, button3;

    public void createMenu() {

        JFrame frame = new JFrame("Record Collector");
        JLabel label = new JLabel("Welcome! \nWhat do you want to do?", JLabel.CENTER);
        JPanel panel = new JPanel();

        JButton button1 = new JButton("Search records");
        JButton button2 = new JButton("Add record(s)");
        JButton button3 = new JButton("Pick a record for me!");
        JButton button4 = new JButton("List by artist");

        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    buttonActionPerformed1(evt, frame);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    buttonActionPerformed2(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    buttonActionPerformed3(evt, frame);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        button4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    buttonActionPerformed4(evt, frame);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

        // add everything to the frame
        frame.setLayout(new GridLayout(2, 1));
        frame.add(label);
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void buttonActionPerformed1(ActionEvent evt, JFrame frame) throws IOException {
        RecordSearch search = new RecordSearch();
        search.setVisible(true);
    }

    private static void buttonActionPerformed2(ActionEvent evt) throws IOException {
        RecordAdd addRecord = new RecordAdd();
        addRecord.setVisible(true);
    }

    private static void buttonActionPerformed3(ActionEvent evt, JFrame frame) throws IOException {

        DatabaseConnection con = new DatabaseConnection();
        Random r = new Random();
        int randomRecordId = r.nextInt((con.getLastDbId() - 1) + 1) + 1;
        BufferedReader inFile = new BufferedReader(new FileReader(con.dbPath()));

        String line = "";
        while ((line = inFile.readLine()) != null) {

            String[] recordArray = line.split(",");
            if (Integer.toString(randomRecordId).equals(recordArray[0])) {
                JOptionPane
                        .showMessageDialog(frame,
                                recordArray[2] + ", " + recordArray[3] + ", " + recordArray[4] + ", " + recordArray[5]
                                        + ", Index: " + recordArray[1],
                                "Listen to this!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        inFile.close();
    }

    private static void buttonActionPerformed4(ActionEvent evt, JFrame frame) throws IOException {
        ListByArtist artistList = new ListByArtist();
        artistList.setVisible(true);
    }
}