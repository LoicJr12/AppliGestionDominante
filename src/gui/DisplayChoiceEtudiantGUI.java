package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import dao.DominanteDAO;
import model.*;

public class DisplayChoiceEtudiantGUI {
	protected JFrame mainFrame;
	private ArrayList<Choix> listChoix;
	private static JLabel clockLabel;
	private JTable table;
	private DefaultTableModel tableModel;
	protected String nom;
	
	/**
	 * Constructor
	 * @param listChoix
	 * @param nom
	 */
	public DisplayChoiceEtudiantGUI(ArrayList<Choix> listChoix, String nom) {
		this.nom = nom ;
		this.listChoix = listChoix;
		initialize(this.listChoix, nom);
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ArrayList<Choix> list = getMyChoice(12066);
					DisplayChoiceEtudiantGUI windowAccueil = new DisplayChoiceEtudiantGUI(null, "Loïc KAKOU");
					windowAccueil.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	/**
	 * fonction qui crée la fenêtre
	 * @param listChoix
	 * @param nom
	 */
	public void initialize(ArrayList<Choix> listChoix, String nom) {
		mainFrame = new JFrame();
		mainFrame.setTitle("Admin Page");
		mainFrame.setBounds(100, 100, 700, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GradientBackgroundPanel panelPrincipal = new GradientBackgroundPanel();
		panelPrincipal.setLayout(new GridBagLayout());
		mainFrame.getContentPane().add(panelPrincipal);
		
		GridBagConstraints gbcPrincipal = new GridBagConstraints();
		gbcPrincipal.insets = new Insets(5, 10, 20, 10);
		gbcPrincipal.anchor = GridBagConstraints.CENTER;
		
		updateTime();
		clockLabel = new JLabel();
		clockLabel.setForeground(Color.WHITE);
		clockLabel.setFont(new Font("Arial", Font.BOLD, 28));
		gbcPrincipal.gridy = 0;
		panelPrincipal.add(clockLabel, gbcPrincipal);
		
		ImageIcon logoEsigelec = new ImageIcon(LoginGUI.class.getResource("esigBW.png"));
		Image logo = logoEsigelec.getImage();
		Image editLogo = logo.getScaledInstance(220, 90, Image.SCALE_SMOOTH);
		ImageIcon logoEsig = new ImageIcon(editLogo);
		JLabel image = new JLabel();
		image.setIcon(logoEsig);
		gbcPrincipal.gridy = 1;
		panelPrincipal.add(image, gbcPrincipal);
		
		JLabel title = new JLabel();
		title.setText("MES CHOIX");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Arial", Font.BOLD, 25));
		gbcPrincipal.gridy = 2;
		panelPrincipal.add(title, gbcPrincipal);
		
		TransparantPanel panelDisplayChoice = new TransparantPanel();
		panelDisplayChoice.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		panelDisplayChoice.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espacement des bords
		gbcPrincipal.gridy = 3;
		panelPrincipal.add(panelDisplayChoice, gbcPrincipal);
		
		String[] columnNames = {"N°Ordre", "Dominante", "Validation"};
		tableModel = new DefaultTableModel(columnNames, 0); // 0 lignes au départ
		tableModel.setRowCount(0);
		String[] sigleDominante = getSigleDominante();
		if(listChoix != null) { //Juste pour voir l'affichage
			for(int i = 0; i < listChoix.size(); i++) {
	        	Choix c = listChoix.get(i);
	        	Object[] row = {
	            		c.getNumeroOrdre(),
	            		sigleDominante[i+1],
	                    c.getValidation()
	        	};
	        	tableModel.addRow(row);
	        }
		}
		
		table = new JTable(tableModel);
		table.setFillsViewportHeight(true);
		table.setPreferredScrollableViewportSize(new Dimension(400, 100));
		
		// Personnaliser le rendu des cellules pour ajouter un padding
        	table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
            		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajouter un padding
                		return this;
            		}
        	});
        
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
	
	//Pour arrondir les bordures de mon bouton code trouvé sure une plateforme de formation java
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
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			g.drawRoundRect(x, y, width-1, height-1, r, r);
		}
	}
	
	/**
	 * Permet de recupérer le tableau des sigles de dominante dans la table Dominante pour les différents chexbox
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
}
