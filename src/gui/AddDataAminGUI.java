package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import dao.*;
import model.*;

/**
 * Interface d'ajout des données dans la base de donnée pour un profil admin
 * @author KAKOU
 * @version 1.0
 */
public class AddDataAminGUI {
	
	protected JFrame mainFrame;
	private static JLabel clockLabel;
	
	/**
	 * Create the application.
	 */
	public AddDataAminGUI() {
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
	
	/**
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
					AddDataAminGUI windowAdmin = new AddDataAminGUI();
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
		mainFrame.setBounds(100, 30, 1350, 770);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GradientBackgroundPanel panelPrincipal = new GradientBackgroundPanel();
		panelPrincipal.setLayout(new GridBagLayout());
		mainFrame.getContentPane().add(panelPrincipal);
		
		GridBagConstraints gbcPrincipal = new GridBagConstraints();
		gbcPrincipal.insets = new Insets(10, 20, 10, 10);
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
		title.setText("AJOUT DES DONNEES");
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
		titleEtudiant.setText("Ajouter un etudiant");
		titleEtudiant.setForeground(Color.WHITE);
		titleEtudiant.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 1;
		panelPrincipal.add(titleEtudiant, gbcPrincipal);
		
		TransparantPanel addEtudiant = new TransparantPanel();
		addEtudiant.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		//addEtudiant.setLayout(new GridLayout(2, 10, 5, 10)); // 10 pixels d'espacement entre les boutons
		addEtudiant.setPreferredSize(new Dimension(770,80));
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
		
		JTextField fieldnomEtudiant = new JTextField();
		fieldnomEtudiant.setColumns(10);
		addEtudiant.add(fieldnomEtudiant);
		
		JLabel prenomEtudiant = new JLabel();
		prenomEtudiant.setText("prenom :");
		prenomEtudiant.setForeground(Color.WHITE);
		prenomEtudiant.setFont(new Font("Arial", Font.BOLD, 18));
		addEtudiant.add(prenomEtudiant);
		
		JTextField fieldprenomEtudiant = new JTextField();
		fieldprenomEtudiant.setColumns(10);
		addEtudiant.add(fieldprenomEtudiant);
		
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
		
		JButton ajoutEtudiantBtn = new JButton("Ajouter Etudiant");
		ajoutEtudiantBtn.setBackground(Color.BLUE);
		ajoutEtudiantBtn.setForeground(Color.WHITE);
		ajoutEtudiantBtn.setPreferredSize(new Dimension(170,50));
		ajoutEtudiantBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 1;
		ajoutEtudiantBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition1 = fieldIdEtudiant.getText().length() > 0;
				Boolean condition2 = fieldnomEtudiant.getText().length() > 0;
				Boolean condition3 = fieldprenomEtudiant.getText().length() > 0;
				Boolean condition4 = fieldClassement1A.getText().length() > 0;
				Boolean condition5 = fieldDateEtudiant.getText().length() > 0;
				Boolean condition6 = fieldPromoEtudiant.getSelectedItem() != " ";
				Boolean condition7 = fieldFiliereEtudiant.getSelectedItem() != "";
				
				if(condition1 && condition2 && condition3 && condition4 && condition5 && condition6 && condition7) {
					int idEtu = Integer.parseInt(fieldIdEtudiant.getText()) ;
					String nom = fieldnomEtudiant.getText() ;
					String prenom = fieldprenomEtudiant.getText() ;
					int rang1A = Integer.parseInt(fieldClassement1A.getText()) ;
					String date = fieldDateEtudiant.getText() ;
					String annee = (String) fieldPromoEtudiant.getSelectedItem() ;
					String filiere = (String) fieldFiliereEtudiant.getSelectedItem() ;
					ajouterEtudiant(idEtu, annee, nom, prenom, date, rang1A, filiere);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Entrez les informations dans tous les champs", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(ajoutEtudiantBtn, gbcPrincipal);
		
		JLabel titleUtilisateur = new JLabel();
		titleUtilisateur.setText("Ajouter un utilisateur");
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
		
		JButton ajoutUserBtn = new JButton("Ajouter Utilisateur");
		ajoutUserBtn.setBackground(Color.BLUE);
		ajoutUserBtn.setForeground(Color.WHITE);
		ajoutUserBtn.setPreferredSize(new Dimension(175,50));
		ajoutUserBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 2;
		ajoutUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition1 = fieldIdUtilisateur.getText().length() > 0;
				Boolean condition2 = fieldPasswordUtilisateur.getText().length() > 0;
				Boolean condition3 = fieldTypeUtilisateur.getSelectedItem() != "";
				
				if(condition1 && condition2 && condition3) {
					int id = Integer.parseInt(fieldIdUtilisateur.getText());
					String password = fieldPasswordUtilisateur.getText() ;
					String type = (String) fieldTypeUtilisateur.getSelectedItem();
					ajouterUtisalateur(id, password, type );
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Entrer l'id le mot de passe et le type de l'utilisateur", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(ajoutUserBtn, gbcPrincipal);
		
		JLabel titleDominante = new JLabel();
		titleDominante.setText("Ajouter une dominante");
		titleDominante.setForeground(Color.WHITE);
		titleDominante.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 3;
		panelPrincipal.add(titleDominante, gbcPrincipal);
		
		TransparantPanel addDominante = new TransparantPanel();
		addDominante.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		addDominante.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2)); // Espacement des bords
		gbcPrincipal.gridy = 3;
		panelPrincipal.add(addDominante, gbcPrincipal);
		
		JLabel nomDominante = new JLabel();
		nomDominante.setText("nom :");
		nomDominante.setForeground(Color.WHITE);
		nomDominante.setFont(new Font("Arial", Font.BOLD, 18));
		addDominante.add(nomDominante);
		
		JTextField fieldNomDominante = new JTextField();
		fieldNomDominante.setColumns(30);
		addDominante.add(fieldNomDominante);
		
		JLabel sigleDominante = new JLabel();
		sigleDominante.setText("sigle :");
		sigleDominante.setForeground(Color.WHITE);
		sigleDominante.setFont(new Font("Arial", Font.BOLD, 18));
		addDominante.add(sigleDominante);
		
		JTextField fieldSigleDominante = new JTextField();
		fieldSigleDominante.setColumns(10);
		addDominante.add(fieldSigleDominante);
		
		JButton ajoutDominanteBtn = new JButton("Ajouter Dominante");
		ajoutDominanteBtn.setBackground(Color.BLUE);
		ajoutDominanteBtn.setForeground(Color.WHITE);
		ajoutDominanteBtn.setPreferredSize(new Dimension(180,50));
		ajoutDominanteBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 3;
		ajoutDominanteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition1 = fieldNomDominante.getText().length() > 0;
				Boolean condition2 = fieldSigleDominante.getText().length() > 0;
				
				if(condition1 && condition2) {
					String nom = fieldNomDominante.getText() ;
					String sigle = fieldSigleDominante.getText() ;
					ajouterDominante(nom,sigle);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Entrer le nom et le sigle de la dominante", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(ajoutDominanteBtn, gbcPrincipal);
		
		JLabel titlePromotion = new JLabel();
		titlePromotion.setText("Ajouter une promotion");
		titlePromotion.setForeground(Color.WHITE);
		titlePromotion.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 4;
		panelPrincipal.add(titlePromotion, gbcPrincipal);
		
		TransparantPanel addPromotion = new TransparantPanel();
		addPromotion.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		addPromotion.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2)); // Espacement des bords
		gbcPrincipal.gridy = 4;
		panelPrincipal.add(addPromotion, gbcPrincipal);
		
		JLabel anneePromotion = new JLabel();
		anneePromotion.setText("annee de diplomation :");
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
		JComboBox<String> fieldEtapeTraitement = new JComboBox<>(getListNiveauEtape());
		addPromotion.add(fieldEtapeTraitement);
		
		JButton ajoutPromoBtn = new JButton("Ajouter Promotion");
		ajoutPromoBtn.setBackground(Color.BLUE);
		ajoutPromoBtn.setForeground(Color.WHITE);
		ajoutPromoBtn.setPreferredSize(new Dimension(170,50));
		ajoutPromoBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 4;
		ajoutPromoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition1 = fieldAnneePromotion.getText().length() > 0;
				Boolean condition2 = fieldEtapeTraitement.getSelectedItem() != "";
				
				if(condition1 && condition2) {
					int annee = Integer.parseInt(fieldAnneePromotion.getText()) ;
					String etape = (String) fieldEtapeTraitement.getSelectedItem() ;
					ajouterPromotion(annee, etape);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Entrez l'annee et selectionnez une étape", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(ajoutPromoBtn, gbcPrincipal);
		
		JLabel titleChoix = new JLabel();
		titleChoix.setText("Ajouter un choix");
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
		
		JButton ajoutChoixBtn = new JButton("Ajouter Choix");
		ajoutChoixBtn.setBackground(Color.BLUE);
		ajoutChoixBtn.setForeground(Color.WHITE);
		ajoutChoixBtn.setPreferredSize(new Dimension(170,50));
		ajoutChoixBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 5;
		ajoutChoixBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition1 = fieldNumeroChoix.getSelectedItem() != "";
				Boolean condition2 = fieldChoixIdEtudiant.getText().length() > 0;
				Boolean condition3 = fieldChoixSigleDominante.getSelectedItem() != " ";
				Boolean condition4 = fieldChoixValidation.getSelectedItem() != "";
				
				if(condition1 && condition2 && condition3 && condition4) {
					int numOrdre = Integer.parseInt((String) fieldNumeroChoix.getSelectedItem()) ;
					int idEtu = Integer.parseInt(fieldChoixIdEtudiant.getText()) ;
					String sigle = (String) fieldChoixSigleDominante.getSelectedItem() ;
					String validation = (String) fieldChoixValidation.getSelectedItem() ;
					ajouterChoix(idEtu, sigle, numOrdre, validation);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Entrez les informations dans tous les champs", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(ajoutChoixBtn, gbcPrincipal);
		
		JLabel titlePlace = new JLabel();
		titlePlace.setText("Ajouter une place");
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
		
		JButton ajoutPlaceBtn = new JButton("Ajouter Place");
		ajoutPlaceBtn.setBackground(Color.BLUE);
		ajoutPlaceBtn.setForeground(Color.WHITE);
		ajoutPlaceBtn.setPreferredSize(new Dimension(170,50));
		ajoutPlaceBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 6;
		ajoutPlaceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition1 = fieldPlaceSigleDominante.getSelectedItem() != "";
				Boolean condition2 = fieldPlacePromotion.getSelectedItem() != " ";
				Boolean condition3 = fieldPlaceNombreMax.getText().length() > 0;
				Boolean condition4 = fieldPlaceAlternant.getText().length() > 0;
				
				if(condition1 && condition2 && condition3 && condition4) {
					String sigle = (String) fieldPlaceSigleDominante.getSelectedItem() ;
					String annee = (String) fieldPlacePromotion.getSelectedItem() ;
					int nbreMax = Integer.parseInt(fieldPlaceNombreMax.getText()) ;
					int nbreReserveAlt = Integer.parseInt(fieldPlaceAlternant.getText()) ;
					ajouterPlace(annee, sigle, nbreMax, nbreReserveAlt);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Entrez les informations dans tous les champs", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(ajoutPlaceBtn, gbcPrincipal);
		
		JLabel titleEtape = new JLabel();
		titleEtape.setText("Ajouter une étape");
		titleEtape.setForeground(Color.WHITE);
		titleEtape.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 7;
		panelPrincipal.add(titleEtape, gbcPrincipal);
		
		TransparantPanel addEtape = new TransparantPanel();
		addEtape.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		addEtape.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2)); // Espacement des bords
		gbcPrincipal.gridy = 7;
		panelPrincipal.add(addEtape, gbcPrincipal);
		
		JLabel etapeNiveau = new JLabel();
		etapeNiveau.setText("niveau traitement :");
		etapeNiveau.setForeground(Color.WHITE);
		etapeNiveau.setFont(new Font("Arial", Font.BOLD, 18));
		addEtape.add(etapeNiveau);
		
		JTextField fieldEtapeNiveau = new JTextField();
		fieldEtapeNiveau.setColumns(10);
		addEtape.add(fieldEtapeNiveau);
		
		JButton ajoutEtapeBtn = new JButton("Ajouter Etape");
		ajoutEtapeBtn.setBackground(Color.BLUE);
		ajoutEtapeBtn.setForeground(Color.WHITE);
		ajoutEtapeBtn.setPreferredSize(new Dimension(170,50));
		ajoutEtapeBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 7;
		ajoutEtapeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition = fieldEtapeNiveau.getText().length() > 0;
				
				if(condition) {
					String niveau = (String) fieldEtapeNiveau.getText() ;
					ajouterEtape(niveau);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Entrez le niveau de traitement de l'étape", "Dialog",
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
	
	
	//pour ajouter un étudiant a partir de l'interface dans la table étudiant
	public void ajouterEtudiant(int idEtu, String annee, String nom, String prenom, String date, int rang1A, String filiere) {
		EtudiantDAO etuDAO = new EtudiantDAO();
		Etudiant etudiant = etuDAO.get(idEtu);
		if(etudiant != null) {
			JOptionPane.showMessageDialog(new JFrame(), "Cet étudiant existe déja. Ajout impossible!", "Dialog",
					JOptionPane.ERROR_MESSAGE);
		}else {
			int idPromo = 0;
			String[] anneePromo = getAnneePromo();
			for(int i = 0; i < anneePromo.length ; i ++) {
				if(annee.equals(anneePromo[i]))
					idPromo = i;
			}
			System.out.println("l'id de la promo pour l'anéee " + annee + " est : " + idPromo);
			etudiant = new Etudiant(idEtu, idPromo, nom, prenom, date, rang1A, filiere);
			etudiant.afficher();
			int resultat = etuDAO.add(etudiant);
			System.out.println(resultat + " ligne ajoutée");
			if(resultat != 0)
				JOptionPane.showMessageDialog(new JFrame(), "Ajout de l'étudiant réussi avec succès", "Dialog",
						JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(new JFrame(), "Erreur lors de l'ajout de l'étudiant", "Dialog",
						JOptionPane.ERROR_MESSAGE);
		}
		
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
	
	//Pour ajouter un utilisateur a partir de l'interface dans la table utilisateur
	public void ajouterUtisalateur(int id, String password, String type) {
		UtilisateurDAO userDAO = new UtilisateurDAO();
		Utilisateur user = new Utilisateur(id, password, type);
		int resultat = userDAO.add(user);
		
		if(resultat == 1) {
			JOptionPane.showMessageDialog(new JFrame(), "Ajout de l'utilisateur réussi avec succès", "Dialog",
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(new JFrame(), "Ce utilisateur existe déja", "Dialog",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//Pour ajouter une dominante a partir de l'interface dans la table dominante
	public void ajouterDominante(String nom, String sigle) {
		DominanteDAO domDAO = new DominanteDAO();
		ArrayList<Dominante> list = domDAO.getList();
		int identifiant = list.size() + 1;
		domDAO.add(new Dominante(identifiant, nom, sigle));
		Boolean condition = false ;
		
		for(Dominante d : list ) {
			if(d.getNom() != nom && d.getSigle() != sigle) {
				condition = true ;
			}
		}
		
		if(condition) {
			Dominante newDom = new Dominante(identifiant, nom, sigle);
			newDom.afficher();
			int resultat = domDAO.add(newDom);
			System.out.println(resultat + " ligne ajoutée !");
			JOptionPane.showMessageDialog(new JFrame(), "Ajout de la dominante réussi avec succès", "Dialog",
						JOptionPane.INFORMATION_MESSAGE);
			
		}else {
			JOptionPane.showMessageDialog(new JFrame(), "Cette dominante existe déja", "Dialog",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Permet de recupérer le tableau des niveaux des étapes dans la table etape
	 * pour les différents chexbox
	 * @return String[] un tableau de niveau des étapes
	 */
	public String[] getListNiveauEtape() {
		EtapeDAO etapeDAO = new EtapeDAO();
		ArrayList<EtapeTraitement> list = etapeDAO.getList();
		ArrayList<String> listNiveauEtape = new ArrayList<>();
		String[] tableauNomDominante ;
		listNiveauEtape.add(" ");
		for (EtapeTraitement etape : list) {
			listNiveauEtape.add(etape.getNiveauTraitement());
		}
		tableauNomDominante = listNiveauEtape.toArray(new String[0]);
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
		listAnnee.add(" ");
		for (Promotion promo : list) {
			String annee = Integer.toString(promo.getAnneeDiplomation());
			listAnnee.add(annee);
		}
		tableauAnneePromo = listAnnee.toArray(new String[0]);
		return tableauAnneePromo;
	}
	
	//Pour ajouter une promotion a partir de l'interface dans la table promotion
	public void ajouterPromotion(int annee, String etape) {
		int idetape = 0;
		String[] niveauEtape = getListNiveauEtape();
		for(int i = 0; i < niveauEtape.length ; i ++) {
			if(etape.equals(niveauEtape[i]))
				idetape = i;
		}	
		PromotionDAO promoDAO = new PromotionDAO();
		ArrayList<Promotion> list = promoDAO.getList();
		int idPromo = list.size() + 1;
		Promotion promo = new Promotion(idPromo, idetape, annee);
		promo.afficher();
		int resultat = promoDAO.add(promo);
		System.out.println(resultat + " ligne ajoutée");
		if(resultat != 0) 
			JOptionPane.showMessageDialog(new JFrame(), "Ajout de la promotion réussi avec succès", "Dialog",
					JOptionPane.INFORMATION_MESSAGE);
		else 
			JOptionPane.showMessageDialog(new JFrame(), "Erreur ! cette promotion existe déjà", "Dialog",
					JOptionPane.ERROR_MESSAGE);
		
	}
	
	//Pour ajouter un choix a partir de l'interface dans la table choix
	public void ajouterChoix(int idEtu, String sigle, int numOrdre, String validation) {
		int idDominante = 0;
		String[] sigleDominante = getSigleDominante();
		for(int i = 0; i < sigleDominante.length ; i ++) {
			if(sigle.equals(sigleDominante[i]))
				idDominante = i;
		}
		EtudiantDAO etuDAO = new EtudiantDAO();
		Etudiant etudiant = etuDAO.get(idEtu);
		if(etudiant != null){
			if(etudiant.getFiliere().equals("alternant") && numOrdre > 2) {
				JOptionPane.showMessageDialog(new JFrame(), "Erreur ! ce étudiant ne peut pas avoir plus de deux choix", "Dialog",
						JOptionPane.ERROR_MESSAGE);
			}else {
				ChoixDAO choixDAO = new ChoixDAO();
				Choix choix = choixDAO.get(idEtu, idDominante);
				if(choix != null){
					JOptionPane.showMessageDialog(new JFrame(), "Erreur ! ce choix existe déjà", "Dialog",
						JOptionPane.ERROR_MESSAGE);
				}else {
					choix = new Choix(idEtu, idDominante, numOrdre, validation);
					int resultat = choixDAO.add(choix);
					System.out.println(resultat + "ligne ajoutée");
					if(resultat != 0)
						JOptionPane.showMessageDialog(new JFrame(), "Ajout du choix réussi avec succès", "Dialog",
								JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(new JFrame(), "Erreur lors de l'ajout du choix", "Dialog",
								JOptionPane.ERROR_MESSAGE);
				}
			}	
		}else {
			JOptionPane.showMessageDialog(new JFrame(), "Erreur ! id étudiant introuvable", "Dialog",
					JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	//Pour ajouter un choix a partir de l'interface dans la table choix
	public void ajouterPlace(String annee, String sigle, int nbreMax, int nbreReserveAlt) {
		int idDominante = 0;
		String[] sigleDominante = getSigleDominante();
		for(int i = 0; i < sigleDominante.length ; i ++) {
			if(sigle.equals(sigleDominante[i]))
				idDominante = i;
		}
		int idPromotion = 0;
		String[] anneePromo = getAnneePromo();;
		for(int j = 0; j < anneePromo.length ; j ++) {
			if(annee.equals(anneePromo[j]))
				idPromotion = j;
		}
		PlaceDAO placeDAO = new PlaceDAO();
		Place place = placeDAO.get(idDominante, idPromotion);
		if(place != null){
			JOptionPane.showMessageDialog(new JFrame(), "Erreur ! cette place existe déjà", "Dialog",
							JOptionPane.ERROR_MESSAGE);
		}else {
			place = new Place(idDominante, idPromotion, nbreMax, nbreReserveAlt);
			place.afficher();
			int resultat = placeDAO.add(place);
			System.out.println(resultat + " ligne ajoutée");
			if(resultat != 0)
				JOptionPane.showMessageDialog(new JFrame(), "Ajout de la place réussi avec succès", "Dialog",
						JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(new JFrame(), "Erreur lors de l'ajout de la place", "Dialog",
						JOptionPane.ERROR_MESSAGE);
		}
					
	}
	
	//Pour ajouter une étape a partir de l'interface dans la table choix
	public void ajouterEtape(String niveau) {
		String[] niveauEtape = getListNiveauEtape();
		int idEtape = niveauEtape.length ;
		EtapeTraitement etape = new EtapeTraitement(idEtape, niveau);
		etape.afficher();
		EtapeDAO etapeDAO = new EtapeDAO();
		int resultat = etapeDAO.add(etape);
		if(resultat != 0)
			JOptionPane.showMessageDialog(new JFrame(), "Ajout de l'étape réussi !", "Dialog",
					JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(new JFrame(), "Erreur lors de l'ajout de l'étape", "Dialog",
					JOptionPane.ERROR_MESSAGE);
		
	}

}
