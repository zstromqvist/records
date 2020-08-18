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

    public void startGui() throws IOException {
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

    }

    private static void buttonActionPerformed1(ActionEvent evt, JFrame frame) {
        JOptionPane.showMessageDialog(frame, "message", "Title", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void buttonActionPerformed2(ActionEvent evt, JButton button2) {
        button2.setEnabled(false);
    }

    private static void buttonActionPerformed3(ActionEvent evt, JFrame frame, String fileName) throws IOException {

        DatabaseConnection con = new DatabaseConnection();
        Random r = new Random();
        int randomRecordId = r.nextInt((con.getLastDbId(con.dbPath()) - 1) + 1) + 1;
        BufferedReader inFile = new BufferedReader(new FileReader(fileName));

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
}