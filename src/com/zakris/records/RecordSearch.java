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

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        final JLabel recordSearchLabel = new JLabel("Please enter a search term");
        recordSearchLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        recordSearchLabel.setBounds(362, 52, 325, 50);
        panel.add(recordSearchLabel);

        final JLabel searchWordLabel = new JLabel("Search");
        searchWordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        searchWordLabel.setBounds(58, 152, 99, 43);
        panel.add(searchWordLabel);

        searchWordInput = new JTextField();
        searchWordInput.setFont(new Font("Tahoma", Font.PLAIN, textFieldSize));
        searchWordInput.setBounds(214, 151, 228, 50);
        panel.add(searchWordInput);
        searchWordInput.setColumns(10);

        final JLabel searchTypeLabel = new JLabel("Search for");
        searchTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        searchTypeLabel.setBounds(542, 159, 99, 29);
        panel.add(searchTypeLabel);

        final JComboBox<String> searchTypeInput = new JComboBox<>(formats);
        searchTypeInput.setFont(new Font("Tahoma", Font.PLAIN, textFieldSize));
        searchTypeInput.setBounds(707, 151, 228, 50);
        panel.add(searchTypeInput);

        searchButton = new JButton("Search!");
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        searchButton.setBounds(399, 447, 259, 74);
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