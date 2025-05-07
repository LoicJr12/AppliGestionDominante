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
 * Interface de suppression des données dans la base de donnée pour un profil admin
 * @author KAKOU
 * @version 1.0
 */
public class DeleteDataAdminGUI {
	
	protected JFrame mainFrame;
	private static JLabel clockLabel;
	
	/**
	 * Constructor Create the application.
	 */
	public DeleteDataAdminGUI() {
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
					DeleteDataAdminGUI windowAdmin = new DeleteDataAdminGUI();
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
		mainFrame.setBounds(100, 30, 1000, 680);
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
		title.setText("SUPPRESSION DES DONNEES");
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
		titleEtudiant.setText("Supprimer un etudiant");
		titleEtudiant.setForeground(Color.WHITE);
		titleEtudiant.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 1;
		panelPrincipal.add(titleEtudiant, gbcPrincipal);
		
		TransparantPanel deleteEtudiant = new TransparantPanel();
		deleteEtudiant.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		deleteEtudiant.setBorder(BorderFactory.createEmptyBorder(6, 3, 6, 3)); // Espacement des bords
		gbcPrincipal.gridy = 1;
		panelPrincipal.add(deleteEtudiant, gbcPrincipal);
		
		JLabel idEtudiant = new JLabel();
		idEtudiant.setText("id étudiant :");
		idEtudiant.setForeground(Color.WHITE);
		idEtudiant.setFont(new Font("Arial", Font.BOLD, 18));
		deleteEtudiant.add(idEtudiant);
		
		JTextField fieldIdEtudiant = new JTextField();
		fieldIdEtudiant.setColumns(10);
		deleteEtudiant.add(fieldIdEtudiant);
		
		JButton deleteEtudiantBtn = new JButton("Supprimer Etudiant");
		deleteEtudiantBtn.setBackground(Color.BLUE);
		deleteEtudiantBtn.setForeground(Color.WHITE);
		deleteEtudiantBtn.setPreferredSize(new Dimension(190,40));
		deleteEtudiantBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 1;
		deleteEtudiantBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition = fieldIdEtudiant.getText().length() > 0;
				
				if(condition) {
					int id = Integer.parseInt(fieldIdEtudiant.getText()) ;
					System.out.println("id étudiant saisie : " + id);
					deleteEtudiant(id);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Entrez l'id de l'étudiant", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(deleteEtudiantBtn, gbcPrincipal);
		
		JLabel titleUtilisateur = new JLabel();
		titleUtilisateur.setText("Supprimer un utilisateur");
		titleUtilisateur.setForeground(Color.WHITE);
		titleUtilisateur.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 2;
		panelPrincipal.add(titleUtilisateur, gbcPrincipal);
		
		TransparantPanel deleteUtilisateur = new TransparantPanel();
		deleteUtilisateur.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		deleteUtilisateur.setBorder(BorderFactory.createEmptyBorder(6, 3, 6, 3)); // Espacement des bords
		gbcPrincipal.gridy = 2;
		panelPrincipal.add(deleteUtilisateur, gbcPrincipal);
		
		JLabel idUtilisateur = new JLabel();
		idUtilisateur.setText("id utilisateur:");
		idUtilisateur.setForeground(Color.WHITE);
		idUtilisateur.setFont(new Font("Arial", Font.BOLD, 18));
		deleteUtilisateur.add(idUtilisateur);
		
		JTextField fieldIdUtilisateur = new JTextField();
		fieldIdUtilisateur.setColumns(10);
		deleteUtilisateur.add(fieldIdUtilisateur);
		
		JButton deleteUserBtn = new JButton("Supprimer Utilisateur");
		deleteUserBtn.setBackground(Color.BLUE);
		deleteUserBtn.setForeground(Color.WHITE);
		deleteUserBtn.setPreferredSize(new Dimension(200,40));
		deleteUserBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 2;
		deleteUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition = fieldIdUtilisateur.getText().length() > 0;
				
				if(condition) {
					int id = Integer.parseInt(fieldIdUtilisateur.getText());
					System.out.println("id utilisateur saisie : " + id);
					deleteUtilisateur(id);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Entrez l'id de l'utilisateur", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(deleteUserBtn, gbcPrincipal);
		
		JLabel titleDominante = new JLabel();
		titleDominante.setText("Supprimer une dominante");
		titleDominante.setForeground(Color.WHITE);
		titleDominante.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 3;
		panelPrincipal.add(titleDominante, gbcPrincipal);
		
		TransparantPanel deleteDominante = new TransparantPanel();
		deleteDominante.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		deleteDominante.setBorder(BorderFactory.createEmptyBorder(6, 3, 6, 3)); // Espacement des bords
		gbcPrincipal.gridy = 3;
		panelPrincipal.add(deleteDominante, gbcPrincipal);
		
		JLabel sigleDominante = new JLabel();
		sigleDominante.setText("sigle :");
		sigleDominante.setForeground(Color.WHITE);
		sigleDominante.setFont(new Font("Arial", Font.BOLD, 18));
		deleteDominante.add(sigleDominante);
		
		JComboBox<String> fieldSigleDominante = new JComboBox<>(getSigleDominante());
		fieldSigleDominante.setPreferredSize(new Dimension(85, 22));
		deleteDominante.add(fieldSigleDominante);
		
		JButton deleteDominanteBtn = new JButton("Supprimer Dominante");
		deleteDominanteBtn.setBackground(Color.BLUE);
		deleteDominanteBtn.setForeground(Color.WHITE);
		deleteDominanteBtn.setPreferredSize(new Dimension(200,40));
		deleteDominanteBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 3;
		deleteDominanteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition = fieldSigleDominante.getSelectedItem() != "";
				
				if(condition) {
					String sigle = (String) fieldSigleDominante.getSelectedItem();
					System.out.println("Le sigle sélectionné est : "+ sigle);
					deleteDominante(sigle);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Entrez le sigle de la dominante", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(deleteDominanteBtn, gbcPrincipal);
		
		JLabel titlePromotion = new JLabel();
		titlePromotion.setText("Supprimer une promotion");
		titlePromotion.setForeground(Color.WHITE);
		titlePromotion.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 4;
		panelPrincipal.add(titlePromotion, gbcPrincipal);
		
		TransparantPanel deletePromotion = new TransparantPanel();
		deletePromotion.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		deletePromotion.setBorder(BorderFactory.createEmptyBorder(6, 3, 6, 3)); // Espacement des bords
		gbcPrincipal.gridy = 4;
		panelPrincipal.add(deletePromotion, gbcPrincipal);
		
		JLabel anneePromotion = new JLabel();
		anneePromotion.setText("annee de diplomation :");
		anneePromotion.setForeground(Color.WHITE);
		anneePromotion.setFont(new Font("Arial", Font.BOLD, 18));
		deletePromotion.add(anneePromotion);
		
		JTextField fieldAnneePromotion = new JTextField();
		fieldAnneePromotion.setColumns(10);
		deletePromotion.add(fieldAnneePromotion);
		
		JButton deletePromoBtn = new JButton("Supprimer Promotion");
		deletePromoBtn.setBackground(Color.BLUE);
		deletePromoBtn.setForeground(Color.WHITE);
		deletePromoBtn.setPreferredSize(new Dimension(200,40));
		deletePromoBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 4;
		deletePromoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition = fieldAnneePromotion.getText().length() > 0;
				
				if(condition) {
					int annee = Integer.parseInt(fieldAnneePromotion.getText()) ;
					System.out.println("l'année saisie est : "+annee);
					//deletePromotion(annee);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Entrez l'annee de la promotion", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(deletePromoBtn, gbcPrincipal);
		
		JLabel titleChoix = new JLabel();
		titleChoix.setText("Supprimer un choix");
		titleChoix.setForeground(Color.WHITE);
		titleChoix.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 5;
		panelPrincipal.add(titleChoix, gbcPrincipal);
		
		TransparantPanel deleteChoix = new TransparantPanel();
		deleteChoix.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		deleteChoix.setBorder(BorderFactory.createEmptyBorder(6, 3, 6, 3)); // Espacement des bords
		gbcPrincipal.gridy = 5;
		panelPrincipal.add(deleteChoix, gbcPrincipal);
		
		JLabel choixIdEtudiant = new JLabel();
		choixIdEtudiant.setText("id etudiant :");
		choixIdEtudiant.setForeground(Color.WHITE);
		choixIdEtudiant.setFont(new Font("Arial", Font.BOLD, 18));
		deleteChoix.add(choixIdEtudiant);
		
		JTextField fieldChoixIdEtudiant = new JTextField();
		fieldChoixIdEtudiant.setColumns(10);
		deleteChoix.add(fieldChoixIdEtudiant);
		
		JLabel choixSigleDominante = new JLabel();
		choixSigleDominante.setText("dominante :");
		choixSigleDominante.setForeground(Color.WHITE);
		choixSigleDominante.setFont(new Font("Arial", Font.BOLD, 18));
		deleteChoix.add(choixSigleDominante);
		
		JComboBox<String> fieldChoixSigleDominante = new JComboBox<>(getSigleDominante());
		fieldChoixSigleDominante.setPreferredSize(new Dimension(85, 22));
		deleteChoix.add(fieldChoixSigleDominante);
		
		JButton deleteChoixBtn = new JButton("Supprimer Choix");
		deleteChoixBtn.setBackground(Color.BLUE);
		deleteChoixBtn.setForeground(Color.WHITE);
		deleteChoixBtn.setPreferredSize(new Dimension(200,40));
		deleteChoixBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 5;
		deleteChoixBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition1 = fieldChoixIdEtudiant.getText().length() > 0;
				Boolean condition2 = fieldChoixSigleDominante.getSelectedItem() != " ";
				
				if(condition1 && condition2) {
					int idEtu = Integer.parseInt(fieldChoixIdEtudiant.getText()) ;
					String sigle = (String) fieldChoixSigleDominante.getSelectedItem() ;
					System.out.println("choix : "+ idEtu+"-"+sigle);
					deleteChoix(idEtu, sigle);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Entrez l'id utilisateur et sélectionner la dominante", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(deleteChoixBtn, gbcPrincipal);
		
		JLabel titlePlace = new JLabel();
		titlePlace.setText("Supprimer une place");
		titlePlace.setForeground(Color.WHITE);
		titlePlace.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 6;
		panelPrincipal.add(titlePlace, gbcPrincipal);
		
		TransparantPanel deletePlace = new TransparantPanel();
		deletePlace.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		deletePlace.setBorder(BorderFactory.createEmptyBorder(6, 3, 6, 3)); // Espacement des bords
		gbcPrincipal.gridy = 6;
		panelPrincipal.add(deletePlace, gbcPrincipal);
		
		JLabel placeSigleDominante = new JLabel();
		placeSigleDominante.setText("dominante :");
		placeSigleDominante.setForeground(Color.WHITE);
		placeSigleDominante.setFont(new Font("Arial", Font.BOLD, 18));
		deletePlace.add(placeSigleDominante);
		
		JComboBox<String> fieldPlaceSigleDominante = new JComboBox<>(getSigleDominante());
		fieldPlaceSigleDominante.setPreferredSize(new Dimension(70, 22));
		deletePlace.add(fieldPlaceSigleDominante);
		
		JLabel placePromotion = new JLabel();
		placePromotion.setText("promotion :");
		placePromotion.setForeground(Color.WHITE);
		placePromotion.setFont(new Font("Arial", Font.BOLD, 18));
		deletePlace.add(placePromotion);
		
		JComboBox<String> fieldPlacePromotion = new JComboBox<>(getAnneePromo());
		fieldPlacePromotion.setPreferredSize(new Dimension(70, 22));
		deletePlace.add(fieldPlacePromotion);
		
		JButton deletePlaceBtn = new JButton("Supprimer Place");
		deletePlaceBtn.setBackground(Color.BLUE);
		deletePlaceBtn.setForeground(Color.WHITE);
		deletePlaceBtn.setPreferredSize(new Dimension(200,40));
		deletePlaceBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 6;
		deletePlaceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition1 = fieldPlaceSigleDominante.getSelectedItem() != "";
				Boolean condition2 = fieldPlacePromotion.getSelectedItem() != " ";
				
				if(condition1 && condition2) {
					String sigle = (String) fieldPlaceSigleDominante.getSelectedItem() ;
					String annee = (String) fieldPlacePromotion.getSelectedItem() ;
					System.out.println("place : "+ sigle+"-"+annee);
					deletePlace(sigle, annee);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Sélectionnez le sigle et l'annee", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(deletePlaceBtn, gbcPrincipal);
		
		JLabel titleEtape = new JLabel();
		titleEtape.setText("Supprimer une etape");
		titleEtape.setForeground(Color.WHITE);
		titleEtape.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 7;
		panelPrincipal.add(titleEtape, gbcPrincipal);
		
		TransparantPanel deleteEtape = new TransparantPanel();
		deleteEtape.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		deleteEtape.setBorder(BorderFactory.createEmptyBorder(6, 3, 6, 3)); // Espacement des bords
		gbcPrincipal.gridy = 7;
		panelPrincipal.add(deleteEtape, gbcPrincipal);
		
		JLabel etapeTraitement = new JLabel();
		etapeTraitement.setText("etape de traitement :");
		etapeTraitement.setForeground(Color.WHITE);
		etapeTraitement.setFont(new Font("Arial", Font.BOLD, 18));
		deleteEtape.add(etapeTraitement);
		
		//recupération des niveaux de traitements qui sont dans la table etape
		JComboBox<String> fieldEtapeTraitement = new JComboBox<>(getNiveauEtape());
		deleteEtape.add(fieldEtapeTraitement);
		
		JButton deleteEtapeBtn = new JButton("Supprimer Etape");
		deleteEtapeBtn.setBackground(Color.BLUE);
		deleteEtapeBtn.setForeground(Color.WHITE);
		deleteEtapeBtn.setPreferredSize(new Dimension(200,40));
		deleteEtapeBtn.setFont(new Font("Arial", Font.BOLD, 15));
		gbcPrincipal.gridy = 7;
		deleteEtapeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition1 = fieldEtapeTraitement.getSelectedItem() != "   ";

				if(condition1) {
					String etape = (String) fieldEtapeTraitement.getSelectedItem() ;
					System.out.println("etape choisie : "+etape);
					deleteEtape(etape);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Selectionnez une étape", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPrincipal.add(deleteEtapeBtn, gbcPrincipal);
		
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
		listAnnee.add(" ");
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
		listNiveauEtape.add(" ");
		for (EtapeTraitement etape : list) {
			listNiveauEtape.add(etape.getNiveauTraitement());
		}
		tableauNomDominante = listNiveauEtape.toArray(new String[0]);
		return tableauNomDominante;
	}
	
	//Cette methode permet de supprimer les utilisateurs dans la table utilisateur
	public void deleteEtudiant(int id) {
		EtudiantDAO etuDAO = new EtudiantDAO();
		Etudiant etudiant = etuDAO.get(id);
		if(etudiant != null) {
			int ligne = etuDAO.delete(id);
			System.out.println(ligne + " ligne supprimée");
			if(ligne != 0)
				JOptionPane.showMessageDialog(new JFrame(), "Suppression de l'étudiant réussie", "Dialog",
							JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(new JFrame(), "Echec lors de la suppression de l'étudiant", "Dialog",
							JOptionPane.ERROR_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(new JFrame(), "Suppression impossible ! cet étudiant n'existe pas", "Dialog",
						JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//Cette methode permet de supprimer les utilisateurs dans la table utilisateur
	public void deleteUtilisateur(int id) {
		UtilisateurDAO userDAO = new UtilisateurDAO();
		Utilisateur user = userDAO.get(id);
		if(user != null) {
			int ligne = userDAO.delete(id);
			System.out.println(ligne + " ligne supprimée");
			if(ligne != 0)
				JOptionPane.showMessageDialog(new JFrame(), "Suppression de l'utilisateur réussie", "Dialog",
						JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(new JFrame(), "Echec lors de la suppression de l'utilisateur", "Dialog",
						JOptionPane.ERROR_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(new JFrame(), "Erreur ! cet utilisateur n'existe pas", "Dialog",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//Cette methode permet de supprimer une dominante dans la table dominante
	public void deleteDominante(String sigle) {
		int idDominante = 0;
		String[] sigleDominante = getSigleDominante();
		for(int i = 0; i < sigleDominante.length ; i ++) {
			if(sigle.equals(sigleDominante[i]))
				idDominante = i;
		}
		DominanteDAO dominanteDAO = new DominanteDAO();
		int resultat = dominanteDAO.delete(idDominante);
		System.out.println(resultat + " ligne supprimée");
		if(resultat != 0)
			JOptionPane.showMessageDialog(new JFrame(), "Suppression de la dominante réussie", "Dialog",
							JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(new JFrame(), "Erreur lors de la suppression de la dominante", "Dialog",
							JOptionPane.ERROR_MESSAGE);
	}
	
	//Cette methode permet de supprimer une promotion dans la table promotion
	public void deletePromotion(String annee) {
		int idPromotion = 0;
		String[] anneePromo = getAnneePromo();;
		for(int j = 0; j < anneePromo.length ; j ++) {
			if(annee.equals(anneePromo[j]))
				idPromotion = j;
		}
		PromotionDAO promoDAO = new PromotionDAO();
		Promotion promo = promoDAO.get(idPromotion);
		if(promo != null){
			int resultat = promoDAO.delete(idPromotion);
			System.out.println(resultat + " ligne supprimée");
			if(resultat != 0)
				JOptionPane.showMessageDialog(new JFrame(), "Suppression du choix réussie", "Dialog",
						JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(new JFrame(), "Erreur lors de la suppression du choix", "Dialog",
						JOptionPane.ERROR_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(new JFrame(), "Suppression impossible ! ce choix n'existe pas", "Dialog",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//Cette methode permet de supprimer un choix dans la table choix
	public void deleteChoix(int idEtudiant, String sigle) {
		int idDominante = 0;
		String[] sigleDominante = getSigleDominante();
		for(int i = 0; i < sigleDominante.length ; i ++) {
			if(sigle.equals(sigleDominante[i]))
				idDominante = i;
		}
		ChoixDAO choixDAO = new ChoixDAO();
		Choix choix = choixDAO.get(idEtudiant, idDominante);
		if(choix != null){
			int resultat = choixDAO.delete(idEtudiant, idDominante);
			System.out.println(resultat + " ligne supprimée");
			if(resultat != 0)
				JOptionPane.showMessageDialog(new JFrame(), "Suppression du choix réussie", "Dialog",
						JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(new JFrame(), "Erreur lors de la suppression du choix", "Dialog",
						JOptionPane.ERROR_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(new JFrame(), "Suppression impossible ! ce choix n'existe pas", "Dialog",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//Cette methode permet de supprimer un choix dans la table choix
	public void deletePlace(String sigle, String annee) {
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
			int resultat = placeDAO.delete(idDominante, idPromotion);
			System.out.println(resultat + " ligne supprimée");
			if(resultat != 0)
				JOptionPane.showMessageDialog(new JFrame(), "Suppression de place réussie", "Dialog",
							JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(new JFrame(), "Erreur lors de la suppression de cette place", "Dialog",
							JOptionPane.ERROR_MESSAGE);
		}else {
				JOptionPane.showMessageDialog(new JFrame(), "Suppression impossible ! cette place n'existe pas", "Dialog",
						JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//Cette methode permet de supprimer un choix dans la table choix
	public void deleteEtape(String niveau) {
		int idEtape = 0;
		String[] niveauEtape = getNiveauEtape();;
		for(int j = 0; j < niveauEtape.length ; j ++) {
			if(niveau.equals(niveauEtape[j]))
				idEtape = j;
		}
		EtapeDAO etapeDAO = new EtapeDAO();
		int resultat = etapeDAO.delete(idEtape);
		System.out.println(resultat + " ligne supprimée");
		if(resultat != 0)
			JOptionPane.showMessageDialog(new JFrame(), "Suppression de l'étape réussie", "Dialog",
								JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(new JFrame(), "Erreur lors de la suppression de l'étape", "Dialog",
								JOptionPane.ERROR_MESSAGE);
	}
	
}
