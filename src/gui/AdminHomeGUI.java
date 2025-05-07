package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.border.Border;

//import dao.*;
//import model.*;

public class AdminHomeGUI {
	
	protected JFrame mainFrame;
	private static JLabel clockLabel;
	private LoginGUI loginPage ;
	
	/**
	 * Create the application.
	 */
	public AdminHomeGUI(LoginGUI loginPage) {
		this.loginPage = loginPage ;
		initialize(this.loginPage);
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
					AdminHomeGUI windowAdmin = new AdminHomeGUI(null);
					windowAdmin.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	private void initialize(LoginGUI loginPage) {
		mainFrame = new JFrame();
		mainFrame.setTitle("Admin Page");
		mainFrame.setBounds(100, 100, 1000, 800);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//mainFrame.getContentPane().setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));
		
		GradientBackgroundPanel panelPrincipal = new GradientBackgroundPanel();
		panelPrincipal.setLayout(new GridBagLayout());
		mainFrame.getContentPane().add(panelPrincipal);
		
		GridBagConstraints gbcPrincipal = new GridBagConstraints();
		gbcPrincipal.insets = new Insets(15, 15, 30, 15);
		gbcPrincipal.anchor = GridBagConstraints.CENTER;
		
		updateTime();
		clockLabel = new JLabel();
		clockLabel.setForeground(Color.WHITE);
		clockLabel.setFont(new Font("Arial", Font.BOLD, 35));
		gbcPrincipal.gridy = 0;
		panelPrincipal.add(clockLabel, gbcPrincipal);
		
		ImageIcon logoEsigelec = new ImageIcon(LoginGUI.class.getResource("esigBW.png"));
		Image logo = logoEsigelec.getImage();
		Image editLogo = logo.getScaledInstance(315, 130, Image.SCALE_SMOOTH);
		ImageIcon logoEsig = new ImageIcon(editLogo);
		JLabel image = new JLabel();
		image.setIcon(logoEsig);
		gbcPrincipal.gridy = 1;
		panelPrincipal.add(image, gbcPrincipal);
		
		JLabel title = new JLabel();
		title.setText("ACCUEIL ADMINISTRATEUR");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Arial", Font.BOLD, 45));
		gbcPrincipal.gridy = 2;
		panelPrincipal.add(title, gbcPrincipal);
		
		TransparantPanel panelOption = new TransparantPanel();
		panelOption.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		panelOption.setLayout(new GridLayout(4, 2, 10, 10)); // 10 pixels d'espacement entre les boutons
		panelOption.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espacement des bords
		gbcPrincipal.gridy = 3;
		panelPrincipal.add(panelOption, gbcPrincipal);
		
		
		JButton addDataBtn = new JButton("Ajouter des données");
		addDataBtn.setBackground(Color.BLUE);
		addDataBtn.setForeground(Color.WHITE);
		addDataBtn.setBorder(new RoundBtn(5));
		addDataBtn.setFont(new Font("Arial", Font.BOLD, 20));
		addDataBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		panelOption.add(addDataBtn);
		
		JButton updateDataBtn = new JButton("Modifier des données");
		updateDataBtn.setBackground(Color.BLUE);
		updateDataBtn.setForeground(Color.WHITE);
		updateDataBtn.setBorder(new RoundBtn(5));
		updateDataBtn.setFont(new Font("Arial", Font.BOLD, 20));
		updateDataBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		panelOption.add(updateDataBtn);
		
		JButton deleteDataBtn = new JButton("Supprimer des données");
		deleteDataBtn.setBackground(Color.BLUE);
		deleteDataBtn.setForeground(Color.WHITE);
		//deleteDataBtn.setPreferredSize(new Dimension(150,50));
		deleteDataBtn.setBorder(new RoundBtn(5));
		deleteDataBtn.setFont(new Font("Arial", Font.BOLD, 20));
		deleteDataBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		panelOption.add(updateDataBtn);
		panelOption.add(deleteDataBtn);
		
		JButton getListChoixGBtn = new JButton("Consulter les choix effectues");
		getListChoixGBtn.setBackground(Color.BLUE);
		getListChoixGBtn.setForeground(Color.WHITE);
		getListChoixGBtn.setBorder(new RoundBtn(5));
		getListChoixGBtn.setFont(new Font("Arial", Font.BOLD, 20));
		panelOption.add(getListChoixGBtn);
		
		JButton getListChoixDomBtn = new JButton("Consulter les choix par dominantes");
		getListChoixDomBtn.setBackground(Color.BLUE);
		getListChoixDomBtn.setForeground(Color.WHITE);
		getListChoixDomBtn.setBorder(new RoundBtn(5));
		getListChoixDomBtn.setFont(new Font("Arial", Font.BOLD, 20));
		panelOption.add(getListChoixDomBtn);
		
		JButton getListChoixEtuBtn = new JButton("Consulter les Choix d'un etudiant");
		getListChoixEtuBtn.setBackground(Color.BLUE);
		getListChoixEtuBtn.setForeground(Color.WHITE);
		getListChoixEtuBtn.setBorder(new RoundBtn(5));
		getListChoixEtuBtn.setFont(new Font("Arial", Font.BOLD, 20));
		getListChoixEtuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		panelOption.add(getListChoixEtuBtn);
		
		JButton lancerValidationBtn = new JButton("Lancer le traitement et la validation des choix");
		lancerValidationBtn.setBackground(Color.BLUE);
		lancerValidationBtn.setForeground(Color.WHITE);
		lancerValidationBtn.setBorder(new RoundBtn(5));
		lancerValidationBtn.setFont(new Font("Arial", Font.BOLD, 20));
		panelOption.add(lancerValidationBtn);
		
		JButton lancerChoixBtn = new JButton("Lancer la procédure de choix des dominantes");
		lancerChoixBtn.setBackground(Color.BLUE); 
		lancerChoixBtn.setForeground(Color.WHITE);
		lancerChoixBtn.setBorder(new RoundBtn(5));
		lancerChoixBtn.setFont(new Font("Arial", Font.BOLD, 20));
		panelOption.add(lancerChoixBtn);
		
		
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

}


