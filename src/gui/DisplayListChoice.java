package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import dao.*;
import model.*;

public class DisplayListChoice {
	
	protected JFrame mainFrame;
	private static JLabel clockLabel;
	private JTable table;
	private DefaultTableModel tableModel;
	private ArrayList<Choix> list ;
	
	/**
	 * Create the application.
	 */
	public DisplayListChoice() {
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
					DisplayListChoice window = new DisplayListChoice();
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
		title.setText("LISTE DES CHOIX");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Arial", Font.BOLD, 25));
		gbcPrincipal.gridy = 2;
		panelPrincipal.add(title, gbcPrincipal);
		
		TransparantPanel panelDisplayChoice = new TransparantPanel();
		panelDisplayChoice.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		panelDisplayChoice.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espacement des bords
		gbcPrincipal.gridy = 3;
		panelPrincipal.add(panelDisplayChoice, gbcPrincipal);
		
		String[] columnNames = {"ID Étudiant", "Dominante", "Ordre", "Validation"};
		tableModel = new DefaultTableModel(columnNames, 0); // 0 lignes au départ
		list = getListChoix();
		tableModel.setRowCount(0);
		for(Choix c : list) {
			Object[] row = {
				c.getChoixIdEtudiant(),
				c.getChoixIdDominante(),
				c.getNumeroOrdre(),
				c.getValidation()
			};
			tableModel.addRow(row);
		}
		
		table = new JTable(tableModel);
		table.setFillsViewportHeight(true);
		table.setPreferredScrollableViewportSize(new Dimension(600, 150));
		JScrollPane scrollPane = new JScrollPane(table);
		panelDisplayChoice.add(scrollPane);
		
		JButton deconnexionBtn = new JButton("Retour à la page d'accueil");
		deconnexionBtn.setBackground(Color.BLACK);
		deconnexionBtn.setForeground(Color.WHITE);
		deconnexionBtn.setPreferredSize(new Dimension(150,50));
		//deconnexionBtn.setBorder(new RoundBtn(5));
		deconnexionBtn.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 4;
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
	 * Premet de récupérer la liste des choix dans la table choix
	 * @return ArrayList<Choix> listChoixEtudiant;
	 */
	public ArrayList<Choix> getListChoix() {
		ArrayList<Choix> listChoixEtudiant = new ArrayList<>();
		ChoixDAO choixDAO = new ChoixDAO();
		listChoixEtudiant = choixDAO.getList();
		return listChoixEtudiant;
	}

}
