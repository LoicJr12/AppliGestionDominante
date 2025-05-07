package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.*;

import dao.*;
import model.*;

public class ChoiceGUI {
	
	protected JFrame mainFrame;
	protected int idEtudiant ;
	protected String nomEtudiant ;
	protected String prenomEtudiant ;
	protected String filiere ;
	/*
	 * Accès partagé : La méthode updateClock est appelée par le Timer, qui est une classe interne statique. 
	 * Pour que updateClock puisse accéder à clockLabel sans créer une instance de la classe contenante, clockLabel doit être static.
	 * Simplicité : Dans ce programme simple, il n'y a qu'une seule instance de JLabel pour afficher l'heure. 
	 * En utilisant static, nous pouvons accéder à cette instance directement sans avoir besoin de passer une référence à l'objet.
	 * 
	 * by Mystral AI
	 */
	private static JLabel clockLabel;
	
	
	/**
	 * Create the application.
	 */
	public ChoiceGUI(int idEtudiant, String prenomEtudiant, String nomEtudiant, String filiere) {
		this.idEtudiant = idEtudiant ;
		this.prenomEtudiant = prenomEtudiant ;
		this.nomEtudiant = nomEtudiant ;
		this.filiere = filiere;
		initialize(this.idEtudiant, this.prenomEtudiant, this.nomEtudiant, this.filiere);
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
					ChoiceGUI windowAccueil = new ChoiceGUI(12064, "KAKOU", "Goue", "alternant");
					windowAccueil.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}//*/
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int idEtu, String prenom, String nom, String filiere) {
		mainFrame = new JFrame();
		mainFrame.setTitle("Home Page");
		mainFrame.setBounds(100, 100, 1200, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));
		//mainFrame.getContentPane().setLayout(new GridLayout(1, 2));
		// Color lightBlue = new Color(173, 216, 230);
		
		
		JPanel panelHeader = new JPanel();
		panelHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 180, 0));
		mainFrame.getContentPane().add(panelHeader);
		
		JPanel panelTitle = new JPanel();
		mainFrame.getContentPane().add(panelTitle);
		
		JPanel panelConsigne = new JPanel();
		panelConsigne.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
		mainFrame.getContentPane().add(panelConsigne);
		
		JPanel panelChoice1 = new JPanel();
		panelChoice1.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
		mainFrame.getContentPane().add(panelChoice1);
		
		JPanel panelChoice2 = new JPanel();
		panelChoice2.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
		mainFrame.getContentPane().add(panelChoice2);
		
		JPanel panelChoice3 = new JPanel();
		panelChoice3.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
		mainFrame.getContentPane().add(panelChoice3);
		
		JPanel panelChoice4 = new JPanel();
		panelChoice4.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
		mainFrame.getContentPane().add(panelChoice4);
		
		JPanel panelChoice5 = new JPanel();
		panelChoice5.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
		mainFrame.getContentPane().add(panelChoice5);
		
		JPanel panelButton = new JPanel();
		panelButton .setLayout(new FlowLayout(FlowLayout.CENTER, 150, 0));
		mainFrame.getContentPane().add(panelButton);
		
		JLabel username = new JLabel();
		username.setText(prenom + " " + nom);
		username.setForeground(Color.BLACK);
		username.setFont(new Font("Arial", Font.BOLD, 35));
		panelHeader.add(username);
		
		ImageIcon logoEsigelec = new ImageIcon(LoginGUI.class.getResource("esigelec.png"));
		Image logo = logoEsigelec.getImage();
		Image editLogo = logo.getScaledInstance(250, 55, Image.SCALE_SMOOTH);
		ImageIcon logoEsig = new ImageIcon(editLogo);
		JLabel image = new JLabel();
		image.setIcon(logoEsig);
		panelHeader.add(image);
		
		updateTime();
		clockLabel = new JLabel();
		clockLabel.setForeground(Color.BLACK);
		clockLabel.setFont(new Font("Arial", Font.BOLD, 30));
		panelHeader.add(clockLabel);
		
		JLabel title = new JLabel();
		title.setText("CHOIX DES DOMINANTES");
		title.setForeground(Color.BLUE);
		title.setFont(new Font("Arial", Font.BOLD, 45));
		panelTitle.add(title);
		
		JLabel consigne = new JLabel();
		consigne.setText("Vous avez 5 choix obligatoires à faire. Une fois les choix soumis, ils ne sont plus modifiables!");
		consigne.setFont(new Font("Arial", Font.ITALIC, 20));
		panelConsigne.add(consigne);
		
		JLabel choix1 = new JLabel();
		choix1.setText("CHOIX 1 :");
		choix1.setForeground(Color.BLACK);
		choix1.setFont(new Font("Arial", Font.BOLD, 20));	
		panelChoice1.add(choix1);
		
		JComboBox<String> combobox1 = new JComboBox<>(getNomDominante());
		combobox1.setPreferredSize(new Dimension(600, 30));
		panelChoice1.add(combobox1);
		
		JLabel choix2 = new JLabel();
		choix2.setText("CHOIX 2 :");
		choix2.setForeground(Color.BLACK);
		choix2.setFont(new Font("Arial", Font.BOLD, 20));
		panelChoice2.add(choix2);
		
		JComboBox<String> combobox2 = new JComboBox<>(getNomDominante());
		combobox2.setPreferredSize(new Dimension(600, 30));
		panelChoice2.add(combobox2);
		
		JLabel choix3 = new JLabel();
		choix3.setText("CHOIX 3 :");
		choix3.setForeground(Color.BLACK);
		choix3.setFont(new Font("Arial", Font.BOLD, 20));
		
		JComboBox<String> combobox3 = new JComboBox<>(getNomDominante());
		combobox3.setPreferredSize(new Dimension(600, 30));
		
		JLabel choix4 = new JLabel();
		choix4.setText("CHOIX 4 :");
		choix4.setForeground(Color.BLACK);
		choix4.setFont(new Font("Arial", Font.BOLD, 20));
		
		JComboBox<String> combobox4 = new JComboBox<>(getNomDominante());
		combobox4.setPreferredSize(new Dimension(600, 30));
		
		JLabel choix5 = new JLabel();
		choix5.setText("CHOIX 5 :");
		choix5.setForeground(Color.BLACK);
		choix5.setFont(new Font("Arial", Font.BOLD, 20));
		
		JComboBox<String> combobox5 = new JComboBox<>(getNomDominante());
		combobox5.setPreferredSize(new Dimension(600, 30));
		
		if(isClassique(filiere)) {
			panelChoice3.add(choix3);
			panelChoice3.add(combobox3);
			panelChoice4.add(choix4);
			panelChoice4.add(combobox4);
			panelChoice5.add(choix5);
			panelChoice5.add(combobox5);
		}
			
		JButton resetBtn = new JButton("Renitialiser");
		resetBtn.setBackground(Color.RED);
		resetBtn.setForeground(Color.WHITE);
		resetBtn.setPreferredSize(new Dimension(150,50));
		resetBtn.setFont(new Font("Arial", Font.BOLD, 18));
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click Reset");
				combobox1.setSelectedIndex(0);
				combobox2.setSelectedIndex(0);
				if(isClassique(filiere)) {
					combobox3.setSelectedIndex(0);
					combobox4.setSelectedIndex(0);
					combobox5.setSelectedIndex(0);
				}
			}
		});
		panelButton.add(resetBtn);
		
		/*JButton deconnexionBtn = new JButton("Deconnexion");
		deconnexionBtn.setBackground(Color.BLACK);
		deconnexionBtn.setForeground(Color.WHITE);
		deconnexionBtn.setPreferredSize(new Dimension(150,50));
		deconnexionBtn.setFont(new Font("Arial", Font.BOLD, 18));
		deconnexionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
			}
		});
		panelButton.add(deconnexionBtn);*/
		
		JButton submitBtn = new JButton("Soumettre");
		submitBtn.setBackground(Color.green);
		submitBtn.setForeground(Color.WHITE);
		submitBtn.setPreferredSize(new Dimension(150,50));
		submitBtn.setFont(new Font("Arial", Font.BOLD, 18));
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected1 = (String) combobox1.getSelectedItem();
				String selected2 = (String) combobox2.getSelectedItem();
				Boolean condition1 = !selected1.equals("choisissez une dominante") ;
				Boolean condition2 = !selected2.equals("choisissez une dominante") ;
				
				if(isClassique(filiere)) {
					String selected3 = (String) combobox3.getSelectedItem();
					String selected4 = (String) combobox4.getSelectedItem();
					String selected5 = (String) combobox5.getSelectedItem();
					Boolean condition3 = !selected3.equals("choisissez une dominante");
					Boolean condition4 = !selected4.equals("choisissez une dominante");
					Boolean condition5 = !selected5.equals("choisissez une dominante");
					if(condition1 && condition2 && condition3 && condition4 && condition5) {
						if(detectDoublonFor5Vals(selected1, selected2, selected3, selected4, selected5)) {
							int resultat = save5Choices(idEtu, selected1, selected2, selected3, selected4, selected5);
							if(resultat == 5) {
								JOptionPane.showMessageDialog(new JFrame(), "Choix enregistrees avec succees !", "Dialog",
										JOptionPane.INFORMATION_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(new JFrame(), "Probleme survenu lors de l'enregistrement !", "Dialog",
										JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(new JFrame(), "Faites 5 choix différents !", "Dialog",
									JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(new JFrame(), "Faites 5 choix différents !", "Dialog",
								JOptionPane.ERROR_MESSAGE);
					}
				}else {
					if(condition1 && condition2) {
						if(detectDoublonFor2Vals(selected1, selected2)) {
							int resultat = save2Choices(idEtu, selected1, selected2);
							if(resultat == 2) {
								JOptionPane.showMessageDialog(new JFrame(), "Choix enregistrees avec succees !", "Dialog",
										JOptionPane.INFORMATION_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(new JFrame(), "Probleme survenu lors de l'enregistrement !", "Dialog",
										JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(new JFrame(), "Faites 3 choix différents !", "Dialog",
									JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(new JFrame(), "Faites 2 choix différents !", "Dialog",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		panelButton.add(submitBtn);
	}
	
	/**
	 * Permet de recupérer le tableau des noms de dominante dans la table Dominante
	 * pour les différents chexbox
	 * @param filiere
	 * @return String[] un tableau de nom des dominantes
	 */
	public String[] getNomDominante() {
		DominanteDAO dom = new DominanteDAO();
		ArrayList<Dominante> list = dom.getList();
		ArrayList<String> listNomDominante = new ArrayList<>();
		String[] tableauNomDominante ;
		listNomDominante.add("choisissez une dominante");
		for (Dominante de : list) {
			listNomDominante.add(de.getNom());
		}
		tableauNomDominante = listNomDominante.toArray(new String[0]);
		return tableauNomDominante;
	}
	
	/**
	 * Compare 5 valeurs String a partir d'un HashSet et detecte les doublons
	 * @param val1
	 * @param val2
	 * @param val3
	 * @param val4
	 * @param val5
	 * @return true s'il n'y aucun doublon et false s'il a un doublon
	 */
	public static boolean detectDoublonFor5Vals(String val1, String val2, String val3, String val4, String val5) {
		HashSet<String> uniqueValues = new HashSet<>();
        uniqueValues.add(val1);
        uniqueValues.add(val2);
        uniqueValues.add(val3);
        uniqueValues.add(val4);
        uniqueValues.add(val5);
        
		return uniqueValues.size() == 5;
	}
	
	/**
	 * Compare 2 valeurs String a partir d'un HashSet et detecte les doublons
	 * @param val1
	 * @param val2
	 * @return true s'il n'y aucun doublon et false s'il a un doublon
	 */
	public static boolean detectDoublonFor2Vals(String val1, String val2) {
		HashSet<String> uniqueValues = new HashSet<>();
        uniqueValues.add(val1);
        uniqueValues.add(val2);
        
		return uniqueValues.size() == 2;
	}
	
	/**
	 * Verifie si l'étudiant est classique ou pas
	 * @param filiere
	 * @return true si l'étudiant est classique ou false si c'est pas le cas
	 */
	public boolean isClassique(String filiere) {
		if(filiere.equals("classique"))
				return true ;
		return false;
	}
	
	/**
	 * Enregistre 5 choix fait pas l'étudiant dans la table choix
	 * @param idEtudiant
	 * @param c1
	 * @param c2
	 * @param c3
	 * @param c4
	 * @param c5
	 */
	public int save5Choices(int idEtudiant, String c1, String c2, String c3, String c4, String c5) {
		ChoixDAO choix = new ChoixDAO();
		int nbreLigne = 0 ;
		int numeroOrdre = 0 ;
		String dominante[] = getNomDominante();
		for(int i = 0; i < dominante.length; i++) {
			boolean cond1 = dominante[i].equals(c1);
			boolean cond2 = dominante[i].equals(c2);
			boolean cond3 = dominante[i].equals(c3);
			boolean cond4 = dominante[i].equals(c4);
			boolean cond5 = dominante[i].equals(c5);
			if(cond1 || cond2 || cond3 || cond4 || cond5) {
				if(cond1) {
					numeroOrdre = 1 ;
				}else if(cond2) {
					numeroOrdre = 2 ;
				}else if(cond3) {
					numeroOrdre = 3 ;
				}else if(cond4) {
					numeroOrdre = 4 ;
				}else {
					numeroOrdre = 5 ;
				}
				Choix newChoix = new Choix(idEtudiant, i, numeroOrdre, "en attente");
				int resultat = choix.add(newChoix);
				nbreLigne = nbreLigne + resultat ;
				System.out.println(resultat + " choix ajoute !");
				
			}else {
				System.out.println(0 + "- aucun choix non ajoute !");
			}
		}
		return nbreLigne ;
	}
	
	/**
	 * Enregistre 2 choix fait pas l'étudiant dans la table choix
	 * @param idEtudiant
	 * @param c1
	 * @param c2
	 */
	public int save2Choices(int idEtudiant, String c1, String c2) {
		ChoixDAO choix = new ChoixDAO();
		int nbreLigne = 0 ;
		int numeroOrdre = 0 ;
		String dominate[] = getNomDominante();
		for(int i = 0; i < dominate.length; i++) {
			boolean cond1 = dominate[i].equals(c1);
			boolean cond2 = dominate[i].equals(c2);
			if(cond1 || cond2) {
				if(cond1) {
					numeroOrdre = 1 ;
				}else {
					numeroOrdre = 2 ; 
				}
				Choix newChoix = new Choix(idEtudiant, i, numeroOrdre, "en attente");
				newChoix.afficher();
				int resultat = choix.add(newChoix);
				nbreLigne = nbreLigne + resultat ;
				System.out.print(nbreLigne + " choix ajoute !");
			}
		}
		return nbreLigne ;
	}
	

}






