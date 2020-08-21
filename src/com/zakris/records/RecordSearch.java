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

public class RecordSearch extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JPanel panel;
    private final JTextField searchWordInput;
    private final JButton searchButton;

    public RecordSearch() throws IOException {

        final String[] formats = { "Artist", "Album", "Year" };
        final int textFieldSize = 18;
        int textLabelSize = 16;

        int inputLabelX = 35;
        int inputFieldX = 215;
        int inputY = 15;

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(2000, 100, 450, 380);
        setResizable(false);
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        final JLabel freeTextInputLabel = new JLabel("Free text search");
        freeTextInputLabel.setFont(new Font("Tahoma", Font.PLAIN, textLabelSize));
        freeTextInputLabel.setBounds(inputLabelX, inputY, 200, 30);
        panel.add(freeTextInputLabel);

        searchWordInput = new JTextField();
        searchWordInput.setFont(new Font("Tahoma", Font.PLAIN, textFieldSize));
        searchWordInput.setBounds(inputFieldX, inputY, 200, 30);
        panel.add(searchWordInput);
        searchWordInput.setColumns(10);

        final JLabel formatLabel = new JLabel("Search type");
        formatLabel.setFont(new Font("Tahoma", Font.PLAIN, textLabelSize));
        formatLabel.setBounds(inputLabelX, inputY + 1*40, 200, 30);
        panel.add(formatLabel);

        final JComboBox<String> searchTypeInput = new JComboBox<>(formats);
        searchTypeInput.setFont(new Font("Tahoma", Font.PLAIN, textFieldSize));
        searchTypeInput.setBounds(inputFieldX, inputY + 1*40, 200, 30);
        panel.add(searchTypeInput);

        searchButton = new JButton("Search!");
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchButton.setBounds(150, 310, 150, 30);
        panel.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {

                final DatabaseConnection con = new DatabaseConnection();
                final String searchWord = searchWordInput.getText();
                final String searchType = (String) searchTypeInput.getSelectedItem();
                String test = "";
                try {
                    test = con.searchDb(searchWord, searchType);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                JOptionPane.showMessageDialog(searchButton, test, "Search results", 1);

            }

        });

    }

}