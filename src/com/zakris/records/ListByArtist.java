package com.zakris.records;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ListByArtist extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JPanel panel;
    private final JButton searchButton;

    public ListByArtist() throws IOException {

        final DatabaseConnection con = new DatabaseConnection();
        final String[] artists = con.getArtists(con.getLastDbId());
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

        final JLabel artistLabel = new JLabel("Artist");
        artistLabel.setFont(new Font("Tahoma", Font.PLAIN, textLabelSize));
        artistLabel.setBounds(inputLabelX, inputY, 200, 30);
        panel.add(artistLabel);

        final JComboBox<String> artist = new JComboBox<>(artists);
        artist.setFont(new Font("Tahoma", Font.PLAIN, textFieldSize));
        artist.setBounds(inputFieldX, inputY, 200, 30);
        panel.add(artist);

        searchButton = new JButton("Search!");
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchButton.setBounds(150, 310, 150, 30);
        panel.add(searchButton);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {

                final String artistInput = (String) artist.getSelectedItem();
                String test = "";
                try {
                    test = con.searchDb(artistInput, "artist");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                JOptionPane.showMessageDialog(searchButton, test, "Search results", 1);

            }

        });

    }
}