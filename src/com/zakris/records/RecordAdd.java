package com.zakris.records;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RecordAdd extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JPanel panel;
    private final JTextField artistInput;
    private final JTextField nameInput;
    private final JTextField yearInput;
    private final JTextField pressYearInput;
    private final JTextField indexInput;
    private final JButton okButton;

    public RecordAdd() throws IOException {

        final String[] formats = { "Vinyl", "CD", "Other" };
        final int textFieldSize = 24;

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        final JLabel labelNewRecord = new JLabel("Add new record");
        labelNewRecord.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        labelNewRecord.setBounds(362, 52, 325, 50);
        panel.add(labelNewRecord);

        final JLabel artistInputLabel = new JLabel("Artist");
        artistInputLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        artistInputLabel.setBounds(58, 152, 99, 43);
        panel.add(artistInputLabel);

        artistInput = new JTextField();
        artistInput.setFont(new Font("Tahoma", Font.PLAIN, textFieldSize));
        artistInput.setBounds(214, 151, 228, 50);
        panel.add(artistInput);
        artistInput.setColumns(10);

        final JLabel nameInputLabel = new JLabel("Album name");
        nameInputLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        nameInputLabel.setBounds(58, 243, 110, 29);
        panel.add(nameInputLabel);

        nameInput = new JTextField();
        nameInput.setFont(new Font("Tahoma", Font.PLAIN, textFieldSize));
        nameInput.setBounds(214, 235, 228, 50);
        panel.add(nameInput);
        nameInput.setColumns(10);

        final JLabel yearInputLabel = new JLabel("Year");
        yearInputLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        yearInputLabel.setBounds(58, 324, 124, 36);
        panel.add(yearInputLabel);

        yearInput = new JTextField();
        yearInput.setFont(new Font("Tahoma", Font.PLAIN, textFieldSize));
        yearInput.setBounds(214, 320, 228, 50);
        panel.add(yearInput);
        yearInput.setColumns(10);

        final JLabel pressYearInputLabel = new JLabel("Press Year");
        pressYearInputLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        pressYearInputLabel.setBounds(542, 159, 99, 29);
        panel.add(pressYearInputLabel);

        pressYearInput = new JTextField();
        pressYearInput.setFont(new Font("Tahoma", Font.PLAIN, textFieldSize));
        pressYearInput.setBounds(707, 151, 228, 50);
        panel.add(pressYearInput);
        pressYearInput.setColumns(10);

        final JLabel formatLabel = new JLabel("Index");
        formatLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        formatLabel.setBounds(542, 245, 99, 24);
        panel.add(formatLabel);

        final JComboBox<String> formatInput = new JComboBox<>(formats);
        formatInput.setFont(new Font("Tahoma", Font.PLAIN, textFieldSize));
        formatInput.setBounds(707, 320, 228, 50);
        panel.add(formatInput);

        final JLabel indexInputLabel = new JLabel("Format");
        indexInputLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        indexInputLabel.setBounds(542, 329, 139, 26);
        panel.add(indexInputLabel);

        indexInput = new JTextField();
        indexInput.setFont(new Font("Tahoma", Font.PLAIN, textFieldSize));
        indexInput.setBounds(707, 235, 228, 50);
        panel.add(indexInput);

        okButton = new JButton("Add record");
        okButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        okButton.setBounds(399, 447, 259, 74);
        panel.add(okButton);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {

                final DatabaseConnection con = new DatabaseConnection();
                int idInput = 0;
                try {
                    idInput = con.getLastDbId() + 1;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                final String recordArtist = artistInput.getText();
                final String recordName = nameInput.getText();
                final int recordYear = Integer.parseInt(yearInput.getText());
                final int recordPressYear = Integer.parseInt(pressYearInput.getText());
                final String recordFormat = (String) formatInput.getSelectedItem();
                final String recordIndex = indexInput.getText();

                final Record newRecord = new Record(idInput, recordIndex, recordArtist, recordName, recordYear,
                        recordFormat, recordPressYear);

                try {
                    con.addRecordToDb(newRecord);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                JOptionPane.showMessageDialog(okButton,
                        "You added: " + "Artist: " + newRecord.getArtist() + ", Album name: " + newRecord.getName()
                                + ", Year: " + newRecord.getYear() + ", Press year: " + newRecord.getPressYear()
                                + ", Format: " + newRecord.getFormat() + ", Index: " + newRecord.getIndex(),
                        "Record added", 1);

                artistInput.setText("");
                nameInput.setText("");
                yearInput.setText("");
                pressYearInput.setText("");
                indexInput.setText("");
                //setVisible(false);
                //dispose();

            }

        });

    }

}