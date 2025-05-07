package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserListUI  {

	 public static void main(String[] args) {
	        JFrame frame = new JFrame("Page de Recherche");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(600, 400);
	        frame.setLayout(new BorderLayout());

	        // Créer un panneau pour la barre de recherche
	        JPanel searchPanel = new JPanel(new BorderLayout());
	        JTextField searchField = new JTextField();
	        searchField.setPreferredSize(new Dimension(400, 30));
	        searchPanel.add(searchField, BorderLayout.CENTER);

	        JButton searchButton = new JButton("Rechercher");
	        searchPanel.add(searchButton, BorderLayout.EAST);

	        // Créer un panneau pour afficher les résultats
	        JPanel resultsPanel = new JPanel(new BorderLayout());
	        DefaultListModel<String> listModel = new DefaultListModel<>();
	        JList<String> resultsList = new JList<>(listModel);
	        resultsPanel.add(new JScrollPane(resultsList), BorderLayout.CENTER);

	        // Ajouter des données d'exemple à la liste
	        List<String> data = new ArrayList<>();
	        data.add("Élément 1");
	        data.add("Élément 2");
	        data.add("Élément 3");
	        data.add("Élément 4");

	        // Ajouter un ActionListener pour la recherche
	        searchButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String query = searchField.getText().toLowerCase();
	                listModel.clear();
	                for (String item : data) {
	                    if (item.toLowerCase().contains(query)) {
	                        listModel.addElement(item);
	                    }
	                }
	            }
	        });

	        // Ajouter les panneaux au frame
	        frame.add(searchPanel, BorderLayout.NORTH);
	        frame.add(resultsPanel, BorderLayout.CENTER);

	        frame.setVisible(true);
	    }
}

