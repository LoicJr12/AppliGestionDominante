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

public class DisplayListChoiceDominate {
	
	protected JFrame mainFrame;
	private static JLabel clockLabel;
	private JTable table;
	private DefaultTableModel tableModel;
	
	/**
	 * Create the application.
	 */
	public DisplayListChoiceDominate() {
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
					DisplayListChoiceDominate window = new DisplayListChoiceDominate();
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
		title.setText("LISTE DES CHOIX PAR DOMINANTE");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Arial", Font.BOLD, 25));
		gbcPrincipal.gridy = 2;
		
		JPanel panelSearch = new JPanel();
		panelSearch.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		panelSearch.setOpaque(false);
		gbcPrincipal.gridy = 3;
		panelPrincipal.add(panelSearch, gbcPrincipal);
		
		JLabel labelDominante = new JLabel();
		labelDominante.setText("Sélectionnez la dominante : ");
		labelDominante.setForeground(Color.WHITE);
		labelDominante.setFont(new Font("Arial", Font.BOLD, 20));
		panelSearch.add(labelDominante);
		
		JComboBox<String> fieldChoixSigleDominante = new JComboBox<>(getSigleDominante());
		fieldChoixSigleDominante.setPreferredSize(new Dimension(120, 25));
		panelSearch.add(fieldChoixSigleDominante);
		
		JButton searchButton = new JButton("Consulter choix"); 
		searchButton.setBackground(Color.BLUE);
		searchButton.setForeground(Color.WHITE);
		searchButton.setBorder(new RoundBtn(5));
		searchButton.setFont(new Font("Arial", Font.BOLD, 18));
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dominante = (String)fieldChoixSigleDominante.getSelectedItem();
				if(!dominante.isBlank()) {
					tableModel.setRowCount(0);
					ArrayList<Choix> list = consulterChoix(dominante);
					if(!list.isEmpty() && list != null) {
						for(Choix c : list) {
							Object[] row = {
								c.getChoixIdEtudiant(),
								c.getNumeroOrdre(),
								c.getValidation()
							};
							tableModel.addRow(row);
						}
					}else {
						JOptionPane.showMessageDialog(new JFrame(), "Cette dominante n'a pas encore été choisie ", "Dialog",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		panelSearch.add(searchButton);
		
		TransparantPanel panelDisplayChoice = new TransparantPanel();
		panelDisplayChoice.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		panelDisplayChoice.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espacement des bords
		gbcPrincipal.gridy = 4;
		panelPrincipal.add(panelDisplayChoice, gbcPrincipal);
		
		String[] columnNames = {"ID Étudiant", "Ordre", "Validation"};
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		table.setFillsViewportHeight(true);
		table.setPreferredScrollableViewportSize(new Dimension(400, 150));
		JScrollPane scrollPane = new JScrollPane(table);
		panelDisplayChoice.add(scrollPane);
		
		JButton deconnexionBtn = new JButton("Retour à la page d'accueil");
		deconnexionBtn.setBackground(Color.BLACK);
		deconnexionBtn.setForeground(Color.WHITE);
		deconnexionBtn.setPreferredSize(new Dimension(270,50));
		deconnexionBtn.setBorder(new RoundBtn(5));
		deconnexionBtn.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 5;
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
	public ArrayList<Choix> consulterChoix(String sigle) {
		ArrayList<Choix> listChoixEtudiant = new ArrayList<>();
		int id = 0 ;
		String[] tab = getSigleDominante();
		for(int i=0; i<tab.length ; i++) {
			if(sigle.equals(tab[i]))
				id = i;
		}
		
		ChoixDAO choixDAO = new ChoixDAO();
		ArrayList<Choix> list = choixDAO.getList();
		for(Choix c : list) {
			if(c.getChoixIdDominante() == id)
				listChoixEtudiant.add(c);
		}
		
		return listChoixEtudiant;
	}

}
