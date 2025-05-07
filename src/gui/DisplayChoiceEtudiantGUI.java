package gui;

import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;

public class DisplayChoiceEtudiantGUI {
	
	protected JFrame mainFrame;
	private ArrayList<Choix> listChoix;
	
	public DisplayChoiceEtudiantGUI(ArrayList<Choix> listChoix) {
		this.listChoix = listChoix;
		initialize(this.listChoix);
	}

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<Choix> list = getMyChoice(12066);
					DisplayChoiceEtudiantGUI windowAccueil = new DisplayChoiceEtudiantGUI(list);
					windowAccueil.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}*/
	
	public void initialize(ArrayList<Choix> listChoix) {
		mainFrame = new JFrame();
		mainFrame.setTitle("Admin Page");
		mainFrame.setBounds(100, 100, 800, 650);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GradientBackgroundPanel panelPrincipal = new GradientBackgroundPanel();
		panelPrincipal.setLayout(new GridBagLayout());
		mainFrame.getContentPane().add(panelPrincipal);
		
		GridBagConstraints gbcPrincipal = new GridBagConstraints();
		gbcPrincipal.insets = new Insets(15, 15, 25, 15);
		gbcPrincipal.anchor = GridBagConstraints.CENTER;
		
		JLabel title = new JLabel();
		title.setText("Mes Choix");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		gbcPrincipal.gridy = 0;
		panelPrincipal.add(title, gbcPrincipal);
		
		TransparantPanel panelOption = new TransparantPanel();
		panelOption.setOpaque(false);// Rendre le panneau non opaque pour permettre la transparence
		panelOption.setLayout(new GridLayout(3, 2, 10, 10)); // 10 pixels d'espacement entre les boutons
		panelOption.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espacement des bords
		gbcPrincipal.gridy = 1;
		panelPrincipal.add(panelOption, gbcPrincipal);
		
		JLabel numeroOrdre = new JLabel();
		numeroOrdre.setText("N°");
		numeroOrdre.setForeground(Color.BLACK);
		numeroOrdre.setFont(new Font("Arial", Font.BOLD, 20));
		panelOption.add(numeroOrdre);
		
		/*JLabel sigleDominante = new JLabel();
		sigleDominante.setText("SIGLE ");
		sigleDominante.setForeground(Color.BLACK);
		sigleDominante.setFont(new Font("Arial", Font.BOLD, 18));
		panelOption.add(sigleDominante);*/
		
		JLabel nomDominante = new JLabel();
		nomDominante.setText("NOM ");
		nomDominante.setForeground(Color.BLACK);
		nomDominante.setFont(new Font("Arial", Font.BOLD, 20));
		panelOption.add(nomDominante);
		
		/*if(listChoix.size() > 0) {
			for(Choix c : listChoix) {
				System.out.println(c.getIdDominante());
				JLabel numero = new JLabel(c.getIdDominante()); //c.getIdDominante()
				numero.setForeground(Color.WHITE);
				numero.setFont(new Font("Arial", Font.BOLD, 18));
				panelOption.add(numero);
				
				System.out.println(c.getSigleDominante());
				JLabel sigle = new JLabel(c.getSigleDominante());//c.getSigleDominante()
				sigle.setForeground(Color.BLUE);
				sigle.setFont(new Font("Arial", Font.BOLD, 18));
				panelOption.add(sigle);
				
				System.out.println(c.getNomDominante());
				JLabel nom = new JLabel(c.getNomDominante()); //c.getNomDominante()
				nom.setForeground(Color.WHITE);
				nom.setFont(new Font("Arial", Font.BOLD, 18));
				panelOption.add(nom);
			}

		}*/
		
		
		JButton comeBackBtn = new JButton("Revenir a la page d'accueil");
		comeBackBtn.setBackground(Color.BLACK);
		comeBackBtn.setForeground(Color.WHITE);
		comeBackBtn.setPreferredSize(new Dimension(250,50));
		comeBackBtn.setBorder(new RoundBtn(5));
		comeBackBtn.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPrincipal.gridy = 7;
		comeBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			g.drawRoundRect(x, y, width-1, height-1, r, r);
		}
	}
			

}
