package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import dao.*;
import model.*;

public class DisplayListStudent{
	
	protected JFrame mainFrame;
	private static JLabel clockLabel;
	private JTable table;
	private DefaultTableModel tableModel;
	private ArrayList<Etudiant> list ;
	
	/**
	 * Create the application.
	 */
	public DisplayListStudent() {
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
					DisplayListStudent window = new DisplayListStudent();
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
		gbcPrincipal.insets = new Insets(5, 10, 10, 10);
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
		title.setText("LISTE DES ETUDIANTS");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Arial", Font.BOLD, 25));
		gbcPrincipal.gridy = 2;
		panelPrincipal.add(title, gbcPrincipal);
		
		TransparantPanel panelDisplayChoice = new TransparantPanel();
		panelDisplayChoice.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		panelDisplayChoice.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espacement des bords
		gbcPrincipal.gridy = 3;
		panelPrincipal.add(panelDisplayChoice, gbcPrincipal);
		
		String[] columnNames = {"ID Etudiant", "ID User","Nom", "Prénom", "Date", "Rang", "Filiere", "Promotion"};
		tableModel = new DefaultTableModel(columnNames, 0); // 0 lignes au départ
		list = getListEtudiant();
		tableModel.setRowCount(0);
		for(int i = 0; i < list.size(); i++){
			Etudiant etu = list.get(i);
			int id = etu.getEtuIdPromotion();
			Object[] row = {
				etu.getIdEtudiant(),
				etu.getEtuIdUtisateur(),
				etu.getNom(),
				etu.getPrenom(),
				formatDate(etu.getDateNaissance()),
				etu.getClassement1A(),
				etu.getFiliere(),
				getAnneePromo(id)
			};
			tableModel.addRow(row);
		}
		
		table = new JTable(tableModel);
		table.setFillsViewportHeight(true);
		table.setPreferredScrollableViewportSize(new Dimension(600, 170));
		JScrollPane scrollPane = new JScrollPane(table);
		panelDisplayChoice.add(scrollPane);
		
		JButton deconnexionBtn = new JButton("Retour à la page d'accueil");
		deconnexionBtn.setBackground(Color.BLACK);
		deconnexionBtn.setForeground(Color.WHITE);
		deconnexionBtn.setPreferredSize(new Dimension(280,50));
		deconnexionBtn.setBorder(new RoundBtn(5));
		deconnexionBtn.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 4;
		deconnexionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
	 * Permet de recupérer l'année d'une promotion par son Id
	 * @return int l'année de la promotion
	 */
	public int getAnneePromo(int id) {
		PromotionDAO promoDAO = new PromotionDAO();
		Promotion promo = promoDAO.get(id);
		int annee = promo.getAnneeDiplomation();
		return annee;
	}
	 
	/**
	 * Premet de récupérer la liste des choix dans la table choix
	 * @return ArrayList<Choix> listChoixEtudiant;
	 */
	public ArrayList<Etudiant> getListEtudiant() {
		ArrayList<Etudiant> listChoixEtudiant = new ArrayList<>();
		EtudiantDAO etduiantDAO = new EtudiantDAO();
		listChoixEtudiant = etduiantDAO.getList();
		return listChoixEtudiant;
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
}

