package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.border.Border;

import dao.*;
import model.*;

public class LoginGUI {
	
	private JFrame mainFrame;
	private JTextField loginIdField;
	private JTextField passwordField ;
	
	/**
	 * Create the application.
	 */
	public LoginGUI() {
		initialize();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI windowLogin = new LoginGUI();
					windowLogin.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("Login Page");
		mainFrame.setBounds(100, 100, 1200, 750);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new GridLayout(1, 2));
		// mainFrame.getContentPane().setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.X_AXIS));
		// Color lightBlue = new Color(173, 216, 230);
		
		GradientBackgroundPanel panelImage = new GradientBackgroundPanel(); //new GridBagLayout() GridBagLayout a comprendre
		panelImage.setLayout(new GridBagLayout()); // Important pour centrer
		//panelImage.setBackground(Color.lightGray);
		GridBagConstraints gbcImage = new GridBagConstraints();
        gbcImage.weightx = 1.0;
        gbcImage.weighty = 1.0;
        gbcImage.insets = new Insets(10, 10, 10, 10);
        gbcImage.anchor = GridBagConstraints.CENTER;
		mainFrame.getContentPane().add(panelImage, gbcImage);
		
		ImageIcon logoEsigelec = new ImageIcon(LoginGUI.class.getResource("esigBW.png"));
		Image logo = logoEsigelec.getImage();
		Image editLogo = logo.getScaledInstance(330, 140, Image.SCALE_SMOOTH);
		ImageIcon logoEsig = new ImageIcon(editLogo);
		JLabel image = new JLabel();
		image.setIcon(logoEsig);
		image.setBounds(300, 0, 330, 140);
		panelImage.add(image, gbcImage);
		
		JPanel panelLogin = new JPanel(new GridBagLayout());
		panelLogin.setBackground(Color.WHITE);
		mainFrame.getContentPane().add(panelLogin);
		
		GridBagConstraints gbcLogin = new GridBagConstraints();
	    gbcLogin.insets = new Insets(15, 15, 15, 15);
	    gbcLogin.anchor = GridBagConstraints.CENTER;
	    
	    ImageIcon logoAuth = new ImageIcon(LoginGUI.class.getResource("authmicrosoft-blue.png"));
	    Image logo2 = logoAuth.getImage();
		Image editLogo2 = logo2.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon logoFinal = new ImageIcon(editLogo2);
	    JLabel imageAuth = new JLabel();
	    imageAuth.setIcon(logoFinal);
	    gbcLogin.gridy = 0; // Mystral
	    panelLogin.add(imageAuth, gbcLogin); //, gbcLogin
	    
	    
		JLabel title = new JLabel();
		title.setText("Authentification");
		title.setForeground(Color.BLUE);
		title.setFont(new Font("TimesRoman", Font.BOLD, 50));
		gbcLogin.gridy = 1; // Mystral 
		panelLogin.add(title, gbcLogin);
		
		JPanel panelLoginID = new JPanel();
		panelLoginID.setBackground(Color.WHITE);
		gbcLogin.gridy = 2; // Mystral
		panelLogin.add(panelLoginID, gbcLogin);
		
		JLabel loginId = new JLabel();
		loginId.setText("Id Utilisateur :");
		loginId.setForeground(Color.BLACK);
		loginId.setFont(new Font("TimesRoman", Font.BOLD, 20));
		panelLoginID.add(loginId);
		
		loginIdField = new JTextField();
		loginIdField.setColumns(27);
		panelLoginID.add(loginIdField);
		
		JPanel panelPassword = new JPanel();
		panelPassword.setBackground(Color.WHITE);
		gbcLogin.gridy = 3; // Mystral
		panelLogin.add(panelPassword, gbcLogin);
		
		JLabel passwordText = new JLabel();
		passwordText.setText("Mot De Passe :");
		passwordText.setForeground(Color.BLACK);
		passwordText.setFont(new Font("TimesRoman", Font.BOLD, 20));
		panelPassword.add(passwordText);
		
		passwordField = new JTextField();
		passwordField.setColumns(25);
		panelPassword.add(passwordField);
		
		JButton connexionBtn = new JButton("Connexion");
		connexionBtn.setBackground(Color.BLUE);
		connexionBtn.setForeground(Color.WHITE);
		connexionBtn.setPreferredSize(new Dimension(150,50));
		connexionBtn.setBorder(new RoundBtn(5));
		connexionBtn.setFont(new Font("TimesRoman", Font.BOLD, 18));
		connexionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean condition1 = loginIdField.getText().length() > 0 ;
				Boolean condition2 = passwordField.getText().length() > 0 ;
				
				if (condition1 && condition2 ) {
					int id = Integer.parseInt(loginIdField.getText());
					String password = passwordField.getText();
					authentification(id, password);
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Vous devez entrer l'id et le mod de passe", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		gbcLogin.gridy = 4; // Mystral
		panelLogin.add(connexionBtn, gbcLogin);
		
	}
	
	public void authentification(int id, String password) {
		// On r�cup�re l'utilisateur ou dans la base de donnée
		UtilisateurDAO userDAO = new UtilisateurDAO();
		Utilisateur user = userDAO.get(id);
		String mdpUser = user.getPassword();
		String typeUser = user.getType();
		
		if (user != null && mdpUser.equals(password)) {
			JOptionPane.showMessageDialog(new JFrame(), "Connexion Reussie", "Dialog",
					JOptionPane.INFORMATION_MESSAGE);
			
			if(typeUser.equals("etudiant")) {
				EtudiantDAO etuDAO = new EtudiantDAO();
				Etudiant etudiant = etuDAO.getByIdUser(id);
				if (etudiant != null) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								int id = etudiant.getIdEtudiant() ;
								String prenom = etudiant.getPrenom();
								String nom = etudiant.getNom();
								String filiere = etudiant.getFiliere();
								EtudiantHomeGUI window = new EtudiantHomeGUI(LoginGUI.this, id, prenom, nom, filiere);
								//ChoiceGUI window = new ChoiceGUI(id, prenom, nom, filiere);
								window.mainFrame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}else {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AdminHomeGUI windowAccueil = new AdminHomeGUI(LoginGUI.this);
							windowAccueil.mainFrame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Erreur ! Id ou Password incorrect", "Dialog",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void resetField() {
		loginIdField.setText("");
		passwordField.setText("");
	}
	
	//Pour arrondir les bordures de mon bouton code trouvé sure une plateforme de 
	// formation java
	class RoundBtn implements Border 
	{
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

