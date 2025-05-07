package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import dao.*;
import model.*;

public class UpdateDataAdminGUI {
	
	protected JFrame mainFrame;
	private static JLabel clockLabel;
	
	/**
	 * Create the application.
	 */
	public UpdateDataAdminGUI() {
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateDataAdminGUI windowAdmin = new UpdateDataAdminGUI();
					windowAdmin.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("Admin Page");
		mainFrame.setBounds(60, 30, 1350, 770);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GradientBackgroundPanel panelPrincipal = new GradientBackgroundPanel();
		panelPrincipal.setLayout(new GridBagLayout());
		mainFrame.getContentPane().add(panelPrincipal);
		
		GridBagConstraints gbcPrincipal = new GridBagConstraints();
		gbcPrincipal.insets = new Insets(10, 10, 10, 10);
		gbcPrincipal.anchor = GridBagConstraints.CENTER;
		
		ImageIcon logoEsigelec = new ImageIcon(LoginGUI.class.getResource("esigBW.png"));
		Image logo = logoEsigelec.getImage();
		Image editLogo = logo.getScaledInstance(180, 75, Image.SCALE_SMOOTH);
		ImageIcon logoEsig = new ImageIcon(editLogo);
		JLabel image = new JLabel();
		image.setIcon(logoEsig);
		gbcPrincipal.gridy = 0;
		panelPrincipal.add(image, gbcPrincipal);
		
		JLabel title = new JLabel();
		title.setText("MISE A JOUR DES DONNEES");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		gbcPrincipal.gridy = 0;
		panelPrincipal.add(title, gbcPrincipal);
		
		updateTime();
		clockLabel = new JLabel();
		clockLabel.setForeground(Color.WHITE);
		clockLabel.setFont(new Font("Arial", Font.BOLD, 30));
		gbcPrincipal.gridy = 0;
		panelPrincipal.add(clockLabel, gbcPrincipal);
		
		JLabel titleEtudiant = new JLabel();
		titleEtudiant.setText("Mettre à jour étudiant");
		titleEtudiant.setForeground(Color.WHITE);
		titleEtudiant.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 1;
		panelPrincipal.add(titleEtudiant, gbcPrincipal);
		
		TransparantPanel addEtudiant = new TransparantPanel();
		addEtudiant.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		//addEtudiant.setLayout(new GridLayout(2, 10, 5, 10)); // 10 pixels d'espacement entre les boutons
		addEtudiant.setPreferredSize(new Dimension(800,80));
		addEtudiant.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2)); // Espacement des bords
		gbcPrincipal.gridy = 1;
		panelPrincipal.add(addEtudiant, gbcPrincipal);
		
		JLabel idEtudiant = new JLabel();
		idEtudiant.setText("id :");
		idEtudiant.setForeground(Color.WHITE);
		idEtudiant.setFont(new Font("Arial", Font.BOLD, 18));
		addEtudiant.add(idEtudiant);
		
		JTextField fieldIdEtudiant = new JTextField();
		fieldIdEtudiant.setColumns(10);
		addEtudiant.add(fieldIdEtudiant);
		
		JLabel nomEtudiant = new JLabel();
		nomEtudiant.setText("nom :");
		nomEtudiant.setForeground(Color.WHITE);
		nomEtudiant.setFont(new Font("Arial", Font.BOLD, 18));
		addEtudiant.add(nomEtudiant);
		
		JTextField fieldNomEtudiant = new JTextField();
		fieldNomEtudiant.setColumns(10);
		addEtudiant.add(fieldNomEtudiant);
		
		JLabel prenomEtudiant = new JLabel();
		prenomEtudiant.setText("prenom :");
		prenomEtudiant.setForeground(Color.WHITE);
		prenomEtudiant.setFont(new Font("Arial", Font.BOLD, 18));
		addEtudiant.add(prenomEtudiant);
		
		JTextField fieldPrenomEtudiant = new JTextField();
		fieldPrenomEtudiant.setColumns(10);
		addEtudiant.add(fieldPrenomEtudiant);
		
		JLabel classement1A = new JLabel();
		classement1A.setText("rang 1A :");
		classement1A.setForeground(Color.WHITE);
		classement1A.setFont(new Font("Arial", Font.BOLD, 18));
		addEtudiant.add(classement1A);
		
		JTextField fieldClassement1A = new JTextField();
		fieldClassement1A.setColumns(10);
		addEtudiant.add(fieldClassement1A);
		
		JLabel dateEtudiant = new JLabel();
		dateEtudiant.setText("date(DD-MM-YYYY):");
		dateEtudiant.setForeground(Color.WHITE);
		dateEtudiant.setFont(new Font("Arial", Font.BOLD, 18));
		addEtudiant.add(dateEtudiant);
		
		JTextField fieldDateEtudiant = new JTextField();
		fieldDateEtudiant.setColumns(10);
		addEtudiant.add(fieldDateEtudiant);
		
		JLabel promoEtudiant = new JLabel();
		promoEtudiant.setText("promotion :");
		promoEtudiant.setForeground(Color.WHITE);
		promoEtudiant.setFont(new Font("Arial", Font.BOLD, 18));
		addEtudiant.add(promoEtudiant);
		
		JComboBox<String> fieldPromoEtudiant = new JComboBox<>(getAnneePromo());
		fieldPromoEtudiant.setPreferredSize(new Dimension(115, 22));
		addEtudiant.add(fieldPromoEtudiant);
		
		JLabel filiereEtudiant = new JLabel();
		filiereEtudiant.setText("filiere :");
		filiereEtudiant.setForeground(Color.WHITE);
		filiereEtudiant.setFont(new Font("Arial", Font.BOLD, 18));
		addEtudiant.add(filiereEtudiant);
		
		JComboBox<String> fieldFiliereEtudiant = new JComboBox<>(new String[] {"","classique","alternant"});
		fieldFiliereEtudiant.setPreferredSize(new Dimension(115, 22));
		addEtudiant.add(fieldFiliereEtudiant);
		
		JButton ajoutEtudiantBtn = new JButton("Modifier Etudiant");
		ajoutEtudiantBtn.setBackground(Color.BLUE);
		ajoutEtudiantBtn.setForeground(Color.WHITE);
		ajoutEtudiantBtn.setPreferredSize(new Dimension(170,50));
		ajoutEtudiantBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 1;
		ajoutEtudiantBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition1 = fieldIdEtudiant.getText().length() > 0 && fieldNomEtudiant.getText().length() > 0;
				Boolean condition2 = fieldIdEtudiant.getText().length() > 0 && fieldPrenomEtudiant.getText().length() > 0;
				Boolean condition3 = fieldIdEtudiant.getText().length() > 0 && fieldClassement1A.getText().length() > 0;
				Boolean condition4 = fieldIdEtudiant.getText().length() > 0 && fieldDateEtudiant.getText().length() > 0;
				Boolean condition5 = fieldIdEtudiant.getText().length() > 0 && fieldPromoEtudiant.getSelectedItem() != "";
				Boolean condition6 = fieldIdEtudiant.getText().length() > 0 && fieldFiliereEtudiant.getSelectedItem() != "";
				
				if(condition1 || condition2 || condition3 || condition4 || condition5 || condition6) {
					int idEtu = Integer.parseInt(fieldIdEtudiant.getText()) ;
					String nom = fieldNomEtudiant.getText() ;
					String prenom = fieldPrenomEtudiant.getText() ;
					int rang1A = 0 ;
					if(fieldClassement1A.getText().length() > 0)
						rang1A = Integer.parseInt(fieldClassement1A.getText()) ;
					String date = fieldDateEtudiant.getText() ;
					String annee = (String) fieldPromoEtudiant.getSelectedItem() ;
					String filiere = (String) fieldFiliereEtudiant.getSelectedItem() ;
					//Ca marche pas il y a un problème de format
					updateEtudiant(idEtu, annee, nom, prenom, date, rang1A, filiere);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Entrez l'id et la valeur des éléments à modifier", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(ajoutEtudiantBtn, gbcPrincipal);
		
		JLabel titleUtilisateur = new JLabel();
		titleUtilisateur.setText("Mettre à jour utilisateur");
		titleUtilisateur.setForeground(Color.WHITE);
		titleUtilisateur.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 2;
		panelPrincipal.add(titleUtilisateur, gbcPrincipal);
		
		TransparantPanel addUtilisateur = new TransparantPanel();
		addUtilisateur.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		addUtilisateur.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2)); // Espacement des bords
		gbcPrincipal.gridy = 2;
		panelPrincipal.add(addUtilisateur, gbcPrincipal);
		
		JLabel idUtilisateur = new JLabel();
		idUtilisateur.setText("id :");
		idUtilisateur.setForeground(Color.WHITE);
		idUtilisateur.setFont(new Font("Arial", Font.BOLD, 18));
		addUtilisateur.add(idUtilisateur);
		
		JTextField fieldIdUtilisateur = new JTextField();
		fieldIdUtilisateur.setColumns(10);
		addUtilisateur.add(fieldIdUtilisateur);
		
		JLabel passwordUtilisateur = new JLabel();
		passwordUtilisateur.setText("password :");
		passwordUtilisateur.setForeground(Color.WHITE);
		passwordUtilisateur.setFont(new Font("Arial", Font.BOLD, 18));
		addUtilisateur.add(passwordUtilisateur);
		
		JTextField fieldPasswordUtilisateur = new JTextField();
		fieldPasswordUtilisateur.setColumns(10);
		addUtilisateur.add(fieldPasswordUtilisateur);
		
		JLabel typeUtilisateur = new JLabel();
		typeUtilisateur.setText("type :");
		typeUtilisateur.setForeground(Color.WHITE);
		typeUtilisateur.setFont(new Font("Arial", Font.BOLD, 18));
		addUtilisateur.add(typeUtilisateur);
		
		JComboBox<String> fieldTypeUtilisateur = new JComboBox<>(new String[] {"","etudiant","administrateur"});
		addUtilisateur.add(fieldTypeUtilisateur);
		
		JButton ajoutUserBtn = new JButton("Modifier Utilisateur");
		ajoutUserBtn.setBackground(Color.BLUE);
		ajoutUserBtn.setForeground(Color.WHITE);
		ajoutUserBtn.setPreferredSize(new Dimension(185,50));
		ajoutUserBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 2;
		ajoutUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition1 = fieldIdUtilisateur.getText().length() > 0 && fieldPasswordUtilisateur.getText().length() > 0;
				Boolean condition2 = fieldIdUtilisateur.getText().length() > 0 && fieldTypeUtilisateur.getSelectedItem() != "";
				
				if(condition1 || condition2) {
					int id = Integer.parseInt(fieldIdUtilisateur.getText());
					String password = fieldPasswordUtilisateur.getText() ;
					String type = (String) fieldTypeUtilisateur.getSelectedItem();
					updateUtilisateur(id, password, type );
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Entrer l'id de l'utilisateur et la valeur du champ a modifié !", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(ajoutUserBtn, gbcPrincipal);
		
		JLabel titleDominante = new JLabel();
		titleDominante.setText("Mettre à jour dominante");
		titleDominante.setForeground(Color.WHITE);
		titleDominante.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 3;
		panelPrincipal.add(titleDominante, gbcPrincipal);
		
		TransparantPanel addDominante = new TransparantPanel();
		addDominante.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		addDominante.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2)); // Espacement des bords
		gbcPrincipal.gridy = 3;
		panelPrincipal.add(addDominante, gbcPrincipal);
		
		JLabel idDominante = new JLabel();
		idDominante.setText("id :");
		idDominante.setForeground(Color.WHITE);
		idDominante.setFont(new Font("Arial", Font.BOLD, 18));
		addDominante.add(idDominante);
		
		JTextField fieldIdDominante = new JTextField();
		fieldIdDominante.setColumns(5);
		addDominante.add(fieldIdDominante);
		
		JLabel nomDominante = new JLabel();
		nomDominante.setText("nom :");
		nomDominante.setForeground(Color.WHITE);
		nomDominante.setFont(new Font("Arial", Font.BOLD, 18));
		addDominante.add(nomDominante);
		
		JTextField fieldNomDominante = new JTextField();
		fieldNomDominante.setColumns(25);
		addDominante.add(fieldNomDominante);
		
		JLabel sigleDominante = new JLabel();
		sigleDominante.setText("sigle :");
		sigleDominante.setForeground(Color.WHITE);
		sigleDominante.setFont(new Font("Arial", Font.BOLD, 18));
		addDominante.add(sigleDominante);
		
		JTextField fieldSigleDominante = new JTextField();
		fieldSigleDominante.setColumns(7);
		addDominante.add(fieldSigleDominante);
		
		JButton ajoutDominanteBtn = new JButton("Modifier Dominante");
		ajoutDominanteBtn.setBackground(Color.BLUE);
		ajoutDominanteBtn.setForeground(Color.WHITE);
		ajoutDominanteBtn.setPreferredSize(new Dimension(180,50));
		ajoutDominanteBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 3;
		ajoutDominanteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition1 = fieldIdDominante.getText().length() > 0 && fieldNomDominante.getText().length() > 0;
				Boolean condition2 = fieldIdDominante.getText().length() > 0 && fieldSigleDominante.getText().length() > 0;
				
				if(condition1 || condition2) {
					int id = Integer.parseInt(fieldIdDominante.getText()) ;
					String nom = fieldNomDominante.getText() ;
					String sigle = fieldSigleDominante.getText() ;
					updateDominante(id, nom, sigle);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Entrer l'id et le champ de la dominante a modifé", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(ajoutDominanteBtn, gbcPrincipal);
		
		JLabel titlePromotion = new JLabel();
		titlePromotion.setText("Mettre à jour promotion");
		titlePromotion.setForeground(Color.WHITE);
		titlePromotion.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 4;
		panelPrincipal.add(titlePromotion, gbcPrincipal);
		
		TransparantPanel addPromotion = new TransparantPanel();
		addPromotion.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		addPromotion.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2)); // Espacement des bords
		gbcPrincipal.gridy = 4;
		panelPrincipal.add(addPromotion, gbcPrincipal);
		
		JLabel idPromotion = new JLabel();
		idPromotion.setText("id :");
		idPromotion.setForeground(Color.WHITE);
		idPromotion.setFont(new Font("Arial", Font.BOLD, 18));
		addPromotion.add(idPromotion);
		
		JTextField fieldIdPromotion = new JTextField();
		fieldIdPromotion.setColumns(5);
		addPromotion.add(fieldIdPromotion);
		
		JLabel anneePromotion = new JLabel();
		anneePromotion.setText("annee :");
		anneePromotion.setForeground(Color.WHITE);
		anneePromotion.setFont(new Font("Arial", Font.BOLD, 18));
		addPromotion.add(anneePromotion);
		
		JTextField fieldAnneePromotion = new JTextField();
		fieldAnneePromotion.setColumns(10);
		addPromotion.add(fieldAnneePromotion);
		
		JLabel etapeTraitement = new JLabel();
		etapeTraitement.setText("etape de traitement :");
		etapeTraitement.setForeground(Color.WHITE);
		etapeTraitement.setFont(new Font("Arial", Font.BOLD, 18));
		addPromotion.add(etapeTraitement);
		
		//recupération des niveaux de traitements qui sont dans la table etape
		JComboBox<String> fieldEtapeTraitement = new JComboBox<>(getNiveauEtape());
		addPromotion.add(fieldEtapeTraitement);
		
		JButton ajoutPromoBtn = new JButton("Modifier Promotion");
		ajoutPromoBtn.setBackground(Color.BLUE);
		ajoutPromoBtn.setForeground(Color.WHITE);
		ajoutPromoBtn.setPreferredSize(new Dimension(180,50));
		ajoutPromoBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 4;
		ajoutPromoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition1 = fieldIdPromotion.getText().length() > 0 && fieldAnneePromotion.getText().length() > 0;
				Boolean condition2 = fieldIdPromotion.getText().length() > 0 && fieldEtapeTraitement.getSelectedItem() != "";
				
				if(condition1 || condition2) {
					int id = Integer.parseInt(fieldIdPromotion.getText()) ;
					String annee = fieldAnneePromotion.getText() ;
					String etape = (String) fieldEtapeTraitement.getSelectedItem() ;
					updatePromotion(id, annee, etape);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Entrez l'id et la valeur du champ de la promotion a modifé !", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(ajoutPromoBtn, gbcPrincipal);
		
		JLabel titleChoix = new JLabel();
		titleChoix.setText("Mettre à jour choix");
		titleChoix.setForeground(Color.WHITE);
		titleChoix.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 5;
		panelPrincipal.add(titleChoix, gbcPrincipal);
		
		TransparantPanel addChoix = new TransparantPanel();
		addChoix.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		addChoix.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2)); // Espacement des bords
		gbcPrincipal.gridy = 5;
		panelPrincipal.add(addChoix, gbcPrincipal);
		
		JLabel numeroChoix = new JLabel();
		numeroChoix.setText("N° :");
		numeroChoix.setForeground(Color.WHITE);
		numeroChoix.setFont(new Font("Arial", Font.BOLD, 18));
		addChoix.add(numeroChoix);
		
		JComboBox<String> fieldNumeroChoix = new JComboBox<>(new String[] {"","1","2","3","4","5"});
		fieldNumeroChoix.setPreferredSize(new Dimension(60, 22));
		addChoix.add(fieldNumeroChoix);
		
		JLabel choixIdEtudiant = new JLabel();
		choixIdEtudiant.setText("id etudiant :");
		choixIdEtudiant.setForeground(Color.WHITE);
		choixIdEtudiant.setFont(new Font("Arial", Font.BOLD, 18));
		addChoix.add(choixIdEtudiant);
		
		JTextField fieldChoixIdEtudiant = new JTextField();
		fieldChoixIdEtudiant.setColumns(10);
		addChoix.add(fieldChoixIdEtudiant);
		
		JLabel choixSigleDominante = new JLabel();
		choixSigleDominante.setText("dominante :");
		choixSigleDominante.setForeground(Color.WHITE);
		choixSigleDominante.setFont(new Font("Arial", Font.BOLD, 18));
		addChoix.add(choixSigleDominante);
		
		JComboBox<String> fieldChoixSigleDominante = new JComboBox<>(getSigleDominante());
		fieldChoixSigleDominante.setPreferredSize(new Dimension(85, 22));
		addChoix.add(fieldChoixSigleDominante);
		
		JLabel choixValidation = new JLabel();
		choixValidation.setText("statut :");
		choixValidation.setForeground(Color.WHITE);
		choixValidation.setFont(new Font("Arial", Font.BOLD, 18));
		addChoix.add(choixValidation);
		
		JComboBox<String> fieldChoixValidation = new JComboBox<>(new String[] {"","en attente", "non validé", "validé"});
		fieldChoixValidation.setPreferredSize(new Dimension(85, 22));
		addChoix.add(fieldChoixValidation);
		
		JButton ajoutChoixBtn = new JButton("Modifier Choix");
		ajoutChoixBtn.setBackground(Color.BLUE);
		ajoutChoixBtn.setForeground(Color.WHITE);
		ajoutChoixBtn.setPreferredSize(new Dimension(170,50));
		ajoutChoixBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 5;
		ajoutChoixBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition1 = fieldNumeroChoix.getSelectedItem() != "";
				Boolean condition2 = fieldChoixIdEtudiant.getText().length() > 0;
				Boolean condition3 = fieldChoixSigleDominante.getSelectedItem() != "";
				Boolean condition4 = fieldChoixValidation.getSelectedItem() != "";
				
				if(condition1 || (condition2 && condition3) || condition4) {
					int numOrdre = 0;
					if(fieldNumeroChoix.getSelectedItem() != "")
						numOrdre = Integer.parseInt((String) fieldNumeroChoix.getSelectedItem()) ;
					int idEtu = 0 ;
					if(fieldChoixIdEtudiant.getText().length() > 0)
						idEtu = Integer.parseInt(fieldChoixIdEtudiant.getText()) ;
					String sigle = (String) fieldChoixSigleDominante.getSelectedItem() ;
					String validation = (String) fieldChoixValidation.getSelectedItem() ;
					updateChoix(idEtu, sigle, numOrdre, validation);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Entrez l'id de l'utilisateur, la dominante et les valeurs "
							+ " des champs à modifier", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(ajoutChoixBtn, gbcPrincipal);
		
		JLabel titlePlace = new JLabel();
		titlePlace.setText("Mettre à jour place");
		titlePlace.setForeground(Color.WHITE);
		titlePlace.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 6;
		panelPrincipal.add(titlePlace, gbcPrincipal);
		
		TransparantPanel addPlace = new TransparantPanel();
		addPlace.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		//addPlace.setLayout(new GridLayout(1, 3, 10, 10)); // 10 pixels d'espacement entre les boutons
		addPlace.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2)); // Espacement des bords
		gbcPrincipal.gridy = 6;
		panelPrincipal.add(addPlace, gbcPrincipal);
		
		JLabel placeSigleDominante = new JLabel();
		placeSigleDominante.setText("dominante :");
		placeSigleDominante.setForeground(Color.WHITE);
		placeSigleDominante.setFont(new Font("Arial", Font.BOLD, 18));
		addPlace.add(placeSigleDominante);
		
		JComboBox<String> fieldPlaceSigleDominante = new JComboBox<>(getSigleDominante());
		fieldPlaceSigleDominante.setPreferredSize(new Dimension(70, 22));
		addPlace.add(fieldPlaceSigleDominante);
		
		JLabel placePromotion = new JLabel();
		placePromotion.setText("promotion :");
		placePromotion.setForeground(Color.WHITE);
		placePromotion.setFont(new Font("Arial", Font.BOLD, 18));
		addPlace.add(placePromotion);
		
		JComboBox<String> fieldPlacePromotion = new JComboBox<>(getAnneePromo());
		fieldPlacePromotion.setPreferredSize(new Dimension(70, 22));
		addPlace.add(fieldPlacePromotion);
		
		JLabel placeNombreMax = new JLabel();
		placeNombreMax.setText("place max :");
		placeNombreMax.setForeground(Color.WHITE);
		placeNombreMax.setFont(new Font("Arial", Font.BOLD, 18));
		addPlace.add(placeNombreMax);
		
		JTextField fieldPlaceNombreMax = new JTextField();
		fieldPlaceNombreMax.setColumns(6);
		addPlace.add(fieldPlaceNombreMax);
		
		JLabel placeNombreAlternant = new JLabel();
		placeNombreAlternant.setText("place alternant :");
		placeNombreAlternant.setForeground(Color.WHITE);
		placeNombreAlternant.setFont(new Font("Arial", Font.BOLD, 18));
		addPlace.add(placeNombreAlternant);
		
		JTextField fieldPlaceAlternant = new JTextField();
		fieldPlaceAlternant.setColumns(6);
		addPlace.add(fieldPlaceAlternant);
		
		JButton ajoutPlaceBtn = new JButton("Modifier Place");
		ajoutPlaceBtn.setBackground(Color.BLUE);
		ajoutPlaceBtn.setForeground(Color.WHITE);
		ajoutPlaceBtn.setPreferredSize(new Dimension(170,50));
		ajoutPlaceBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 6;
		ajoutPlaceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition1 = fieldPlaceSigleDominante.getSelectedItem() != "";
				Boolean condition2 = fieldPlacePromotion.getSelectedItem() != "";
				Boolean condition3 = fieldPlaceNombreMax.getText().length() > 0;
				Boolean condition4 = fieldPlaceAlternant.getText().length() > 0;
				
				if((condition1 && condition2 ) || condition3 || condition4) {
					String sigle = (String) fieldPlaceSigleDominante.getSelectedItem() ;
					String annee = (String) fieldPlacePromotion.getSelectedItem() ;
					int nbreMax = 0 ;
					if(fieldPlaceNombreMax.getText().length() > 0)
						nbreMax = Integer.parseInt(fieldPlaceNombreMax.getText()) ;
					int nbreReserveAlt = 0 ;
					if(fieldPlaceAlternant.getText().length() > 0)
						nbreReserveAlt = Integer.parseInt(fieldPlaceAlternant.getText());
					updatePlace(sigle, annee, nbreMax, nbreReserveAlt);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Saisissez la dominaniante, la promotion et renseignez les valeurs"
							+ " des champs à modifier", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(ajoutPlaceBtn, gbcPrincipal);
		
		JLabel titleEtape = new JLabel();
		titleEtape.setText("Mettre à jour une étape");
		titleEtape.setForeground(Color.WHITE);
		titleEtape.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 7;
		panelPrincipal.add(titleEtape, gbcPrincipal);
		
		TransparantPanel addEtape = new TransparantPanel();
		addEtape.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		addEtape.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2)); // Espacement des bords
		gbcPrincipal.gridy = 7;
		panelPrincipal.add(addEtape, gbcPrincipal);
		
		JLabel etapeId = new JLabel();
		etapeId.setText("id :");
		etapeId.setForeground(Color.WHITE);
		etapeId.setFont(new Font("Arial", Font.BOLD, 18));
		addEtape.add(etapeId);
		
		JTextField fieldEtapeId = new JTextField();
		fieldEtapeId.setColumns(10);
		addEtape.add(fieldEtapeId);
		
		JLabel etapeNiveau = new JLabel();
		etapeNiveau.setText("niveau traitement :");
		etapeNiveau.setForeground(Color.WHITE);
		etapeNiveau.setFont(new Font("Arial", Font.BOLD, 18));
		addEtape.add(etapeNiveau);
		
		JTextField fieldEtapeNiveau = new JTextField();
		fieldEtapeNiveau.setColumns(10);
		addEtape.add(fieldEtapeNiveau);
		
		JButton ajoutEtapeBtn = new JButton("Modifier Etape");
		ajoutEtapeBtn.setBackground(Color.BLUE);
		ajoutEtapeBtn.setForeground(Color.WHITE);
		ajoutEtapeBtn.setPreferredSize(new Dimension(170,50));
		ajoutEtapeBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 7;
		ajoutEtapeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition1 = fieldEtapeId.getText().length() > 0;
				Boolean condition2 = fieldEtapeNiveau.getText().length() > 0;
				if(condition1 && condition2) {
					int id = Integer.parseInt(fieldEtapeId.getText()) ;
					String niveau = fieldEtapeNiveau.getText() ;
					updateEtape(id, niveau);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Entrez l'id et le niveau de traitement de l'étape", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(ajoutEtapeBtn, gbcPrincipal);
		
		
		JLabel label = new JLabel("");
		gbcPrincipal.gridy = 8;
		panelPrincipal.add(label, gbcPrincipal);
		
		JButton comeBackBtn = new JButton("Revenir a la page d'accueil");
		comeBackBtn.setBackground(Color.BLACK);
		comeBackBtn.setForeground(Color.WHITE);
		comeBackBtn.setPreferredSize(new Dimension(250,50));
		comeBackBtn.setBorder(new RoundBtn(5));
		comeBackBtn.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 8;
		comeBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click Retour");
				mainFrame.dispose();
			}
		});
		panelPrincipal.add(comeBackBtn, gbcPrincipal);
		
		
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
	 * Permet de recupérer le tableau des années des promotions dans la table promotion
	 * pour les différents chexbox
	 * @return String[] un tableau des années des promotions
	 */
	public String[] getAnneePromo() {
		PromotionDAO promoDAO = new PromotionDAO();
		ArrayList<Promotion> list = promoDAO.getList();
		ArrayList<String> listAnnee = new ArrayList<>();
		String[] tableauAnneePromo ;
		listAnnee.add("");
		for (Promotion promo : list) {
			String annee = Integer.toString(promo.getAnneeDiplomation());
			listAnnee.add(annee);
		}
		tableauAnneePromo = listAnnee.toArray(new String[0]);
		return tableauAnneePromo;
	}
	
	/**
	 * Permet de recupérer le tableau des niveaux des étapes dans la table etape
	 * pour les différents chexbox
	 * @return String[] un tableau de niveau des étapes
	 */
	public String[] getNiveauEtape() {
		EtapeDAO etapeDAO = new EtapeDAO();
		ArrayList<EtapeTraitement> list = etapeDAO.getList();
		ArrayList<String> listNiveauEtape = new ArrayList<>();
		String[] tableauNomDominante ;
		listNiveauEtape.add("");
		for (EtapeTraitement etape : list) {
			listNiveauEtape.add(etape.getNiveauTraitement());
		}
		tableauNomDominante = listNiveauEtape.toArray(new String[0]);
		return tableauNomDominante;
	}
	
	//Pour arrondir les bordures de mon bouton code trouvé sure une plateforme de 
	// formation java
	class RoundBtn implements Border {
		private int r;
		RoundBtn(int r) {
			this.r = r;
			}
			public Insets getBorderInsets(Component c) {
			    return new Insets(this.r+1, this.r+1, this.r+2, this.r);
			}
			public boolean isBorderOpaque() {
			    return true;
			}	    
			public void paintBorder(Component c, Graphics g, int x, int y, 
				int width, int height) {
		            g.drawRoundRect(x, y, width-1, height-1, r, r);
			}
	}
	
	public static String formatDate(String dateString) {
        // Définir le format d'entrée
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

        // Analyser la chaîne en LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(dateString, inputFormatter);

        // Définir le format de sortie
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Formater la date et retourner le résultat
        return dateTime.format(outputFormatter);
    }
	
	
	//Cette methode permet de supprimer les utilisateurs dans la table utilisateur
	public void updateEtudiant(int id, String annee, String nom, String prenom, String date, int rang1A,  String filiere) {
		EtudiantDAO etuDAO = new EtudiantDAO();
		Etudiant etudiant = etuDAO.get(id);
		if(etudiant != null) {
			etudiant.afficher();
			if(nom != null && !nom.isBlank())
				etudiant.setNom(nom);
			
			if(prenom != null && !prenom.isBlank())
				etudiant.setPrenom(prenom);
				
			if(rang1A != 0)
				etudiant.setClassement1A(rang1A);
			
			if(date != null && !date.isBlank())
				etudiant.setDateNaissance(date);
				
			if(annee != null && !annee.isBlank()) {
				int idPromotion = 0;
				String[] anneePromo = getAnneePromo();;
				for(int j = 0; j < anneePromo.length ; j ++) {
					if(annee.equals(anneePromo[j]))
						idPromotion = j;
				}
				etudiant.setEtuIdPromo(idPromotion);
			}
			
			/*if(idUser != 0) {
				UtilisateurDAO userDAO = new UtilisateurDAO();
				Utilisateur user = userDAO.get(id);
				if(user != null) {
					etudiant.setEtuIdUser(idUser);
				}
			}*/
			
			if(filiere != null && !filiere.isBlank())
				etudiant.setFiliere(filiere);
			
			if(date.length() != 10) {
				String dateFormatBD = formatDate(etudiant.getDateNaissance());
				etudiant.setDateNaissance(dateFormatBD);
			}
			
			System.out.println("--------------------------------------------");
			etudiant.afficher();
			System.out.println("Request update student vers BD..............");
			int ligne = etuDAO.update(etudiant);
			System.out.println(ligne + " ligne mise à jour");
			if(ligne != 0)
				JOptionPane.showMessageDialog(new JFrame(), "Mise à jour de l'étudiant réussie", "Dialog",
								JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(new JFrame(), "Echec lors de la mise à jour de l'étudiant", "Dialog",
								JOptionPane.ERROR_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(new JFrame(), "Erreur ! cet étudiant n'existe pas", "Dialog",
							JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	/**
	 * Cette methode permet de mettre à jour un utilisateur dans la table utilisateur
	 * @param id
	 * @param password
	 * @param type
	 */
	public void updateUtilisateur(int id, String password, String type) {
		UtilisateurDAO userDAO = new UtilisateurDAO();
		Utilisateur user = userDAO.get(id);
		if(user != null) {
			user.afficher();
			if(password != null && !password.isEmpty()) {
				user.setPassword(password);
			}
			if(type != null && !type.isEmpty()) {
				user.setType(type);
			}
			int ligne = userDAO.update(user);
			System.out.println(ligne + " ligne mise à jour");
			user.afficher();
			if(ligne != 0)
				JOptionPane.showMessageDialog(new JFrame(), "Mise à jour de l'utilisateur réussie", "Dialog",
						JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(new JFrame(), "Echec lors de la mise à jour de l'utilisateur", "Dialog",
						JOptionPane.ERROR_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(new JFrame(), "Erreur ! cet utilisateur n'existe pas", "Dialog",
						JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Cette methode permet de mettre a jour une dominante dans la table dominante
	 * @param id
	 * @param nom
	 * @param sigle
	 */
	public void updateDominante(int id, String nom, String sigle) {
		DominanteDAO domDAO = new DominanteDAO();
		Dominante dom = domDAO.get(id);
		if(dom != null) {
			dom.afficher();
			if(nom != null && !nom.isEmpty()) 
				dom.setNom(nom);
			if(sigle != null && !sigle.isEmpty()) 
				dom.setSigle(sigle);
			int ligne = domDAO.update(dom);
			System.out.println(ligne + " ligne mise à jour");
			dom.afficher();
			if(ligne != 0)
				JOptionPane.showMessageDialog(new JFrame(), "Mise à jour de la dominante réussie", "Dialog",
						JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(new JFrame(), "Echec lors de la mise à jour de la dominante", "Dialog",
						JOptionPane.ERROR_MESSAGE);
		}else {
				JOptionPane.showMessageDialog(new JFrame(), "Erreur ! cette dominante n'existe pas", "Dialog",
						JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Cette methode permet de mettre a jour une promotion dans la table promotion
	 * @param id
	 * @param annee
	 * @param etape
	 */
	public void updatePromotion(int id, String annee, String etape) {
		PromotionDAO promoDAO = new PromotionDAO();
		Promotion promo = promoDAO.get(id);
		if(promo != null) {
			promo.afficher();
			if(annee != null && !annee.isEmpty()) {
				int anneeDiplomation  = Integer.parseInt(annee);
				promo.setAnneeDiplomation(anneeDiplomation);
			}
			if(etape != null && !etape.isEmpty()) {
				int idEtape = 0;
				String[] niveauEtape = getNiveauEtape();;
				for(int j = 0; j < niveauEtape.length ; j ++) {
					if(etape.equals(niveauEtape[j]))
						idEtape = j;
				}
				promo.setPromIdEtapeTraitement(idEtape);
			}
			int ligne = promoDAO.update(promo);
			System.out.println(ligne + " ligne mise à jour");
			promo.afficher();
			if(ligne != 0)
				JOptionPane.showMessageDialog(new JFrame(), "Mise à jour de la promotion réussie", "Dialog",
						JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(new JFrame(), "Echec lors de la mise à jour de la promotion", "Dialog",
						JOptionPane.ERROR_MESSAGE);
		}else {
				JOptionPane.showMessageDialog(new JFrame(), "Erreur ! cette promotion n'existe pas", "Dialog",
						JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//Pour mettre à jour une étape a partir de l'interface dans la table étape
	public void updateEtape(int id, String niveau) {
		EtapeDAO etapeDAO = new EtapeDAO();
		EtapeTraitement etape = etapeDAO.get(id);
		if(etape != null) {
			etape.setniveauTraitement(niveau);
			int resultat = etapeDAO.update(etape);
			if(resultat != 0)
				JOptionPane.showMessageDialog(new JFrame(), "Mise à jour de l'étape réussie !", "Dialog",
							JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(new JFrame(), "Erreur lors de la mise à jour de l'étape", "Dialog",
							JOptionPane.ERROR_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(new JFrame(), "Mise à jour impossible ! Etape inexistante", "Dialog",
						JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Permet de mettre à jour une place depuis l'interface utilisateur dans la table place
	 * @param sigle
	 * @param annee
	 * @param nbreMax
	 * @param nbreReserveAlternant
	 */
	public void updatePlace(String sigle, String annee, int nbreMax, int nbreReserveAlternant) {
		int idDominante = 0;
		String[] sigleDominante = getSigleDominante();
		for(int i = 0; i < sigleDominante.length ; i ++) {
			if(sigle.equals(sigleDominante[i]))
				idDominante = i;
		}
		int idPromotion = 0;
		String[] anneePromo = getAnneePromo();
		for(int j = 0; j < anneePromo.length ; j ++) {
			if(annee.equals(anneePromo[j]))
				idPromotion = j;
		}
		
		PlaceDAO placeDAO = new PlaceDAO();
		Place place = placeDAO.get(idDominante, idPromotion);
		if(place != null) {
			place.afficher();
			if(nbreMax != 0)
				place.setnombrePlaceMax(nbreMax);
			if(nbreReserveAlternant != 0)
				place.setnombrePlaceMax(nbreMax);
			System.out.println("------------------------------");
			place.afficher();
			int resultat = placeDAO.update(place);
			if(resultat != 0)
				JOptionPane.showMessageDialog(new JFrame(), "Mise à jour de la place réussie !", "Dialog",
						JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(new JFrame(), "Erreur lors de la mise à jour de l'étape", "Dialog",
						JOptionPane.ERROR_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(new JFrame(), "Mise à jour impossible ! Place inexistante", "Dialog",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void updateChoix(int idEtudiant, String sigle, int numOrdre, String validation) {
		int idDominante = 0;
		String[] sigleDominante = getSigleDominante();
		for(int i = 0; i < sigleDominante.length ; i ++) {
			if(sigle.equals(sigleDominante[i]))
				idDominante = i;
		}
		
		ChoixDAO choixDAO = new ChoixDAO();
		Choix choix = choixDAO.get(idEtudiant, idDominante);
		if(choix != null) {
			choix.afficher();
			
			if(numOrdre > 0)
				choix.setNumeroOrdre(numOrdre);
				
			if(validation != null && !validation.isBlank())
				choix.setValidation(validation);
			System.out.println("-----------------------");
			choix.afficher();
			int resultat = choixDAO.update(choix);
			System.out.println(resultat +  " ligne mise à jour ");
			if(resultat != 0)
				JOptionPane.showMessageDialog(new JFrame(), "Mise à jour du choix réussie !", "Dialog",
						JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(new JFrame(), "Erreur lors de la mise à jour du choix", "Dialog",
						JOptionPane.ERROR_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(new JFrame(), "Mise à jour impossible ! Choix inexistant", "Dialog",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
