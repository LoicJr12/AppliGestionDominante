package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.*;
//import javax.swing.table.DefaultTableModel;

import dao.*;
import model.*;

public class DisplayListChoiceEtudiant {
	
	protected JFrame mainFrame;
	private static JLabel clockLabel;
	private ArrayList<Choix> list ;
	/*private JTable table;
	private DefaultTableModel tableModel;*/
	private JTextArea resultArea;
	
	/**
	 * Create the application.
	 */
	public DisplayListChoiceEtudiant() {
		initialize();
	}
	
	/**
	 * Pour afficher le temps
	 */
	private static void clock() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeString = now.format(formatter);
        clockLabel.setText(timeString);
    }
	
	/*
	 * Pour mettre a jour le temps chaque 1s
	 */
	private static void updateTime() {
		Timer timer = new Timer(1000, e -> clock());
        timer.start();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayListChoiceEtudiant window = new DisplayListChoiceEtudiant();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("Admin Page");
		mainFrame.setBounds(100, 100, 730, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GradientBackgroundPanel panelPrincipal = new GradientBackgroundPanel();
		panelPrincipal.setLayout(new GridBagLayout());
		mainFrame.getContentPane().add(panelPrincipal);
		
		GridBagConstraints gbcPrincipal = new GridBagConstraints();
		gbcPrincipal.insets = new Insets(5, 15, 20, 15);
		gbcPrincipal.anchor = GridBagConstraints.CENTER;
		
		updateTime();
		clockLabel = new JLabel();
		clockLabel.setForeground(Color.WHITE);
		clockLabel.setFont(new Font("Arial", Font.BOLD, 30));
		gbcPrincipal.gridy = 0;
		panelPrincipal.add(clockLabel, gbcPrincipal);
		
		JPanel panelHeader = new JPanel();
		panelHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		panelHeader.setOpaque(false);
		gbcPrincipal.gridy = 1;
		panelPrincipal.add(panelHeader, gbcPrincipal);
		
		ImageIcon logoEsigelec = new ImageIcon(LoginGUI.class.getResource("esigBW.png"));
		Image logo = logoEsigelec.getImage();
		Image editLogo = logo.getScaledInstance(200, 80, Image.SCALE_SMOOTH);
		ImageIcon logoEsig = new ImageIcon(editLogo);
		JLabel image = new JLabel();
		image.setIcon(logoEsig);
		panelHeader.add(image);
		
		JLabel title = new JLabel();
		title.setText("CONSULTER LES CHOIX D'UN ETUDIANT");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		gbcPrincipal.gridy = 2;
		panelPrincipal.add(title, gbcPrincipal);
		
		JPanel panelSearch = new JPanel();
		panelSearch.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		panelSearch.setOpaque(false);
		gbcPrincipal.gridy = 3;
		panelPrincipal.add(panelSearch, gbcPrincipal);
		
		TransparantPanel panelDisplayChoice = new TransparantPanel();
		panelDisplayChoice.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		panelDisplayChoice.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espacement des bords
		gbcPrincipal.gridy = 4;
		panelPrincipal.add(panelDisplayChoice, gbcPrincipal);
		
		resultArea = new JTextArea(6, 22);
		resultArea.setOpaque(false);  //ca peut ne pas prendre 
		resultArea.setBorder(null);  //ca peut ne pas prendre 
		resultArea.setEditable(false);
		resultArea.setForeground(Color.WHITE); 
		resultArea.setFont(new Font("Arial", Font.BOLD, 18));
		JScrollPane scrollPane = new JScrollPane(resultArea);
		scrollPane.setOpaque(false); //ca peut ne pas prendre 
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(null);  // optionnel
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//gbcPrincipal.gridy = 4;
		panelDisplayChoice.add(scrollPane);
		
		JLabel labelID = new JLabel();
		labelID.setText("Entrez l'id étudiant : ");
		labelID.setForeground(Color.WHITE);
		labelID.setFont(new Font("Arial", Font.BOLD, 20));
		panelSearch.add(labelID);
		
		JTextField fieldId = new JTextField();
		fieldId.setColumns(10);
		panelSearch.add(fieldId);
		
		/*String[] columnNames = {"ID Étudiant", "ID Dominante", "Ordre", "Validation"};
		tableModel = new DefaultTableModel(columnNames, 0); // 0 lignes au départ
		table = new JTable(tableModel);
		table.setFillsViewportHeight(true);
		table.setPreferredScrollableViewportSize(new Dimension(600, 150));
		654823
		JScrollPane scrollPane = new JScrollPane(table);
		gbcPrincipal.gridy = 3; // en dessous du bouton déconnexion
		panelPrincipal.add(scrollPane, gbcPrincipal);*/
		
		JButton searchButton = new JButton("Consulter choix"); 
		searchButton.setBackground(Color.BLUE);
		searchButton.setForeground(Color.WHITE);
		//searchButton.setBorder(new RoundBtn(5));
		searchButton.setFont(new Font("Arial", Font.BOLD, 18));
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldId.getText().length() > 0) {
					try {
		                int id = Integer.parseInt(fieldId.getText());
		                DisplayListChoiceEtudiant.this.list = consulterChoix(id);

		                if(list != null && !list.isEmpty()) {
		                	// Affichage dans la JTextArea
			                StringBuilder sb = new StringBuilder();
			                sb.append(String.format("%-8s %-14s %-12s%n", "Ordre", "Dominante", "Statut"));
			                sb.append("-----------------------------------------------\n");
			                
			                String[] sigleDominante = getSigleDominante();
			                
			                for(int i = 0; i < list.size(); i++) {
			                	Choix c = list.get(i);
			                	sb.append(String.format("%-12d %-18s %-12s%n",
			                    		c.getNumeroOrdre(),
			                    		sigleDominante[i+1],
			                            c.getValidation())
			                	);
			                }

			                resultArea.setText(sb.toString());
		                }else {
		                	JOptionPane.showMessageDialog(new JFrame(), "Cet étudiant n'a éffectué aucun choix", "Dialog",
		        					JOptionPane.INFORMATION_MESSAGE);
		                }

		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(mainFrame, "Veuillez entrer un ID valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
		            }
					/*int id = Integer.parseInt(fieldId.getText());
					DisplayListChoiceEtudiant.this.list = consulterChoix(id);
					
					tableModel.setRowCount(0);
					
					for(Choix c : list) {
						Object[] row = {
							c.getChoixIdEtudiant(),
							c.getChoixIdDominante(),
							c.getNumeroOrdre(),
							c.getValidation()
						};
						tableModel.addRow(row);
					}*/
			
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Entrer l'id de l'étudiant", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelSearch.add(searchButton);
		
		
		JButton deconnexionBtn = new JButton("Deconnexion");
		deconnexionBtn.setBackground(Color.BLACK);
		deconnexionBtn.setForeground(Color.WHITE);
		deconnexionBtn.setPreferredSize(new Dimension(150,50));
		//deconnexionBtn.setBorder(new RoundBtn(5));
		deconnexionBtn.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 5;
		deconnexionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
			}
		});
		panelPrincipal.add(deconnexionBtn, gbcPrincipal);
				
	}
	
	
	/**
	 * Permet de recupérer le tableau des sigles de dominante dans la table Dominante
	 * pour les différents chexbox
	 * @param filiere
	 * @return String[] un tableau de nom des dominantes
	 */
	public String[] getSigleDominante() {
		DominanteDAO dom = new DominanteDAO();
		ArrayList<Dominante> list = dom.getList();
		ArrayList<String> listNomDominante = new ArrayList<>();
		String[] tableauNomDominante ;
		listNomDominante.add("");
		for (Dominante de : list) {
			listNomDominante.add(de.getSigle());
		}
		tableauNomDominante = listNomDominante.toArray(new String[0]);
		return tableauNomDominante;
	}
	
	/**
	 * Premet de récupérer la liste des choix d'un étudiant dans la table choix
	 * @param id
	 * @return ArrayList<Choix> listChoixEtudiant;
	 */
	public ArrayList<Choix> consulterChoix(int id) {
		ArrayList<Choix> listChoixEtudiant = new ArrayList<>();
		EtudiantDAO etuDAO = new EtudiantDAO();
		Etudiant etudiant = etuDAO.get(id);
		if(etudiant != null) {
			ChoixDAO choixDAO = new ChoixDAO();
			ArrayList<Choix> list = choixDAO.getList();
			for(Choix c : list) {
				if(c.getChoixIdEtudiant()==id)
					listChoixEtudiant.add(c);
			}
			return listChoixEtudiant;
			
		}else {
			JOptionPane.showMessageDialog(new JFrame(), "Erreur ! cet étudiant n'existe pas ", "Dialog",
					JOptionPane.ERROR_MESSAGE);
		}
		return listChoixEtudiant;
		
	}

}
