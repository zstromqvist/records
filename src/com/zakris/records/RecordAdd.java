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
    private final JTextField notesInput;
    private final JButton okButton;

    public RecordAdd() throws IOException {

        final String[] formats = { "Vinyl", "CD", "Other" };
        final int textFieldSize = 18;
        int textLabelSize = 16;

        int inputLabelX = 35;
        int inputFieldX = 215;
        int inputY = 15;

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(500, 100, 450, 380);
        setResizable(false);
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        final JLabel artistInputLabel = new JLabel("Artist");
        artistInputLabel.setFont(new Font("Tahoma", Font.PLAIN, textLabelSize));
        artistInputLabel.setBounds(inputLabelX, inputY, 70, 30);
        panel.add(artistInputLabel);

        artistInput = new JTextField();
        artistInput.setFont(new Font("Tahoma", Font.PLAIN, textFieldSize));
        artistInput.setBounds(inputFieldX, inputY, 200, 30);
        panel.add(artistInput);
        artistInput.setColumns(10);

        final JLabel nameInputLabel = new JLabel("Album name");
        nameInputLabel.setFont(new Font("Tahoma", Font.PLAIN, textLabelSize));
        nameInputLabel.setBounds(inputLabelX, inputY + 40, 200, 30);
        panel.add(nameInputLabel);

        nameInput = new JTextField();
        nameInput.setFont(new Font("Tahoma", Font.PLAIN, textFieldSize));
        nameInput.setBounds(inputFieldX, inputY + 40, 200, 30);
        panel.add(nameInput);
        nameInput.setColumns(10);

        final JLabel yearInputLabel = new JLabel("Year");
        yearInputLabel.setFont(new Font("Tahoma", Font.PLAIN, textLabelSize));
        yearInputLabel.setBounds(inputLabelX, inputY + 2*40, 70, 30);
        panel.add(yearInputLabel);

        yearInput = new JTextField();
        yearInput.setFont(new Font("Tahoma", Font.PLAIN, textFieldSize));
        yearInput.setBounds(inputFieldX, inputY + 2*40, 200, 30);
        panel.add(yearInput);
        yearInput.setColumns(10);

        final JLabel pressYearInputLabel = new JLabel("Press Year");
        pressYearInputLabel.setFont(new Font("Tahoma", Font.PLAIN, textLabelSize));
        pressYearInputLabel.setBounds(inputLabelX, inputY + 3*40, 200, 30);
        panel.add(pressYearInputLabel);

        pressYearInput = new JTextField();
        pressYearInput.setFont(new Font("Tahoma", Font.PLAIN, textFieldSize));
        pressYearInput.setBounds(inputFieldX, inputY + 3*40, 200, 30);
        panel.add(pressYearInput);
        pressYearInput.setColumns(10);

        final JLabel formatLabel = new JLabel("Format");
        formatLabel.setFont(new Font("Tahoma", Font.PLAIN, textLabelSize));
        formatLabel.setBounds(inputLabelX, inputY + 4*40, 70, 30);
        panel.add(formatLabel);

        final JComboBox<String> formatInput = new JComboBox<>(formats);
        formatInput.setFont(new Font("Tahoma", Font.PLAIN, textFieldSize));
        formatInput.setBounds(inputFieldX, inputY + 4*40, 200, 30);
        panel.add(formatInput);

        final JLabel indexInputLabel = new JLabel("Index");
        indexInputLabel.setFont(new Font("Tahoma", Font.PLAIN, textLabelSize));
        indexInputLabel.setBounds(inputLabelX, inputY + 5*40, 70, 30);
        panel.add(indexInputLabel);

        indexInput = new JTextField();
        indexInput.setFont(new Font("Tahoma", Font.PLAIN, textFieldSize));
        indexInput.setBounds(inputFieldX, inputY + 5*40, 200, 30);
        panel.add(indexInput);

        final JLabel notesInputLabel = new JLabel("Notes");
        notesInputLabel.setFont(new Font("Tahoma", Font.PLAIN, textLabelSize));
        notesInputLabel.setBounds(inputLabelX, inputY + 6*40, 70, 30);
        panel.add(notesInputLabel);

        notesInput = new JTextField();
        notesInput.setFont(new Font("Tahoma", Font.PLAIN, textFieldSize));
        notesInput.setBounds(inputFieldX, inputY + 6*40, 200, 30);
        panel.add(notesInput);

        okButton = new JButton("Add record!");
        okButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        okButton.setBounds(150, 310, 150, 30);
        panel.add(okButton);

        this.getRootPane().setDefaultButton(okButton);

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
                final String recordNotes = notesInput.getText();

                final Record newRecord = new Record(idInput, recordIndex, recordArtist, recordName, recordYear,
                        recordFormat, recordPressYear, recordNotes);

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
                notesInput.setText("");
                //setVisible(false);
                //dispose();

            }

        });

    }

}