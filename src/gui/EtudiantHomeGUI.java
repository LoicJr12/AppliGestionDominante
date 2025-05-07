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

//import javax.swing.border.Border;

//import dao.*;
//import gui.LoginGUI.RoundBtn;
//import model.*;

public class EtudiantHomeGUI {
	
	protected JFrame mainFrame;
	protected int idEtudiant ;
	protected String nomEtudiant ;
	protected String prenomEtudiant ;
	protected String filiere ;
	protected String nom;
	private static JLabel clockLabel;
	private LoginGUI loginPage;
	
	public EtudiantHomeGUI(LoginGUI loginPage, int idEtudiant, String prenomEtudiant, String nomEtudiant, String filiere) {
		this.idEtudiant = idEtudiant ;
		this.prenomEtudiant = prenomEtudiant ;
		this.nomEtudiant = nomEtudiant ;
		this.filiere = filiere;
		this.nom = prenomEtudiant +" "+nomEtudiant;
		this.loginPage = loginPage;
		initialize(this.loginPage, this.idEtudiant, this.prenomEtudiant, this.nomEtudiant, this.filiere);
	}
	
	/**
	 * Pour afficher le temps
	 */
	private static void clock(String nom) {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeString = now.format(formatter);
        clockLabel.setText(nom + "  -  " + timeString);
    }
	
	/*
	 * Pour mettre a jour le temps chaque 1s
	 */
	private void updateTime() {
		Timer timer = new Timer(1000, e -> clock(nom));
        timer.start();
	}
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EtudiantHomeGUI windowAdmin = new EtudiantHomeGUI(62090354, "KAKOU", "Goue", "classique");
					windowAdmin.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	private void initialize(LoginGUI loginPage, int idEtudiant, String prenom, String nom, String filiere) {
		mainFrame = new JFrame();
		mainFrame.setTitle("Admin Page");
		mainFrame.setBounds(100, 100, 800, 650);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//mainFrame.getContentPane().setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));
		
		GradientBackgroundPanel panelPrincipal = new GradientBackgroundPanel();
		panelPrincipal.setLayout(new GridBagLayout());
		mainFrame.getContentPane().add(panelPrincipal);
		
		GridBagConstraints gbcPrincipal = new GridBagConstraints();
		gbcPrincipal.insets = new Insets(15, 15, 25, 15);
		gbcPrincipal.anchor = GridBagConstraints.CENTER;
		
		updateTime();
		clockLabel = new JLabel();
		clockLabel.setForeground(Color.WHITE);
		clockLabel.setFont(new Font("Arial", Font.BOLD, 25));
		gbcPrincipal.gridy = 0;
		panelPrincipal.add(clockLabel, gbcPrincipal);
		
		ImageIcon logoEsigelec = new ImageIcon(LoginGUI.class.getResource("esigBW.png"));
		Image logo = logoEsigelec.getImage();
		Image editLogo = logo.getScaledInstance(250, 100, Image.SCALE_SMOOTH);
		ImageIcon logoEsig = new ImageIcon(editLogo);
		JLabel image = new JLabel();
		image.setIcon(logoEsig);
		gbcPrincipal.gridy = 1;
		panelPrincipal.add(image, gbcPrincipal);
		
		JLabel title = new JLabel();
		title.setText("ACCUEIL ETUDIANT");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Arial", Font.BOLD, 40));
		gbcPrincipal.gridy = 2;
		panelPrincipal.add(title, gbcPrincipal);
		
		TransparantPanel panelOption = new TransparantPanel();
		panelOption.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		panelOption.setLayout(new GridLayout(3, 1, 10, 10)); // 10 pixels d'espacement entre les boutons
		panelOption.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espacement des bords
		gbcPrincipal.gridy = 3;
		panelPrincipal.add(panelOption, gbcPrincipal);
		
		
		JButton makeChoiceBtn = new JButton("Faire mes choix");
		makeChoiceBtn.setBackground(Color.BLUE);
		makeChoiceBtn.setForeground(Color.WHITE);
		makeChoiceBtn.setBorder(new RoundBtn(5));
		makeChoiceBtn.setFont(new Font("Arial", Font.BOLD, 20));
		makeChoiceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EtudiantDAO etuDAO = new EtudiantDAO();
				Etudiant etudiant = etuDAO.get(idEtudiant);
				PromotionDAO promoDAO = new PromotionDAO();
				Promotion promo = promoDAO.get(etudiant.getEtuIdPromotion());
				boolean condition1 = promo.getPromIdEtapeTraitement() == 5 && filiere.equals("classique") ;
				boolean condition2 = promo.getPromIdEtapeTraitement() == 1 && filiere.equals("classique") ;
				boolean condition3 = promo.getPromIdEtapeTraitement() == 1 && filiere.equals("alternant") ;
				boolean condition4 = promo.getPromIdEtapeTraitement() == 2 && filiere.equals("alternant") ;
				
				if( condition1 || condition2) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								ChoiceGUI windowAccueil = new ChoiceGUI(idEtudiant, prenom, nom, filiere);
								windowAccueil.mainFrame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				} else if (condition3 || condition4) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								ChoiceGUI windowAccueil = new ChoiceGUI(idEtudiant, prenom, nom, filiere);
								windowAccueil.mainFrame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Vous ne pouvez pas faire de choix actuellement", "Dialog",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		panelOption.add(makeChoiceBtn);
		
		JButton getListChoixtBtn = new JButton("Consulter mes choix");
		getListChoixtBtn.setBackground(Color.BLUE);
		getListChoixtBtn.setForeground(Color.WHITE);
		getListChoixtBtn.setBorder(new RoundBtn(5));
		getListChoixtBtn.setFont(new Font("Arial", Font.BOLD, 20));
		getListChoixtBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChoixDAO choixDAO = new ChoixDAO();
				ArrayList<Choix> listChoix = choixDAO.getChoixByIdEtudiant(idEtudiant);
				if(listChoix != null) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								DisplayChoiceEtudiantGUI windowAccueil = new DisplayChoiceEtudiantGUI(listChoix);
								windowAccueil.mainFrame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Impossible de consulter vos choix car vous n'en avez pas. Faites des choix !", "Dialog",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}	
		});
		panelOption.add(getListChoixtBtn);
		
		JButton getChoiceAcceptedBtn = new JButton("Voir mon choix validées");
		getChoiceAcceptedBtn.setBackground(Color.BLUE);
		getChoiceAcceptedBtn.setForeground(Color.WHITE);
		getChoiceAcceptedBtn.setBorder(new RoundBtn(5));
		getChoiceAcceptedBtn.setFont(new Font("Arial", Font.BOLD, 20));
		getChoiceAcceptedBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JFrame(), "Vous n'avez aucun choix valide ! ", "Dialog",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panelOption.add(getChoiceAcceptedBtn);
		
		
		JButton deconnexionBtn = new JButton("Deconnexion");
		deconnexionBtn.setBackground(Color.BLACK);
		deconnexionBtn.setForeground(Color.WHITE);
		deconnexionBtn.setPreferredSize(new Dimension(150,50));
		deconnexionBtn.setBorder(new RoundBtn(5));
		deconnexionBtn.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 4;
		deconnexionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPage.resetField();
				mainFrame.dispose();
			}
		});
		panelPrincipal.add(deconnexionBtn, gbcPrincipal);

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
	 * permet la récupération des choix d'un etudiant par l'id de l'etudiant
	 * @param id
	 * @return la liste des choix d'un etudiant 
	 */
	/*public ArrayList<Choix> getMyChoice(int id) {
		ChoixDAO choixDao = new ChoixDAO();
		ArrayList<Choix> resultat = choixDao.getListChoixEtudiant(id);
		return resultat ;
	}*/

	

}
