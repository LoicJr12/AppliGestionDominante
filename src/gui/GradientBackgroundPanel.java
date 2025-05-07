package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class GradientBackgroundPanel extends JPanel {
	
	 @Override
	 protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D) g;

	    // Création du dégradé (haut = bleu foncé, bas = bleu clair)
	    Color topColor = new Color(0, 0, 139);  // Bleu foncé
	    Color bottomColor = new Color(0, 191, 255);  // Bleu clair
	    GradientPaint gradient = new GradientPaint(0, 0, topColor, 0, getHeight(), bottomColor);

	    // Appliquer le dégradé au fond du JPanel
	    g2d.setPaint(gradient);
	    g2d.fillRect(0, 0, getWidth(), getHeight());
	}
	
	// Test dans un JFrame
	public static void main(String[] args) {
	        JFrame frame = new JFrame("Dégradé en Background");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(400, 600);

	        // Création du JPanel avec le fond dégradé
	        GradientBackgroundPanel panel = new GradientBackgroundPanel();
	        panel.setLayout(new BorderLayout()); // Permet d'ajouter d'autres composants

	        // Ajouter un label pour voir l'effet
	        JLabel label = new JLabel("Texte sur le JPanel", SwingConstants.CENTER);
	        label.setFont(new Font("Arial", Font.BOLD, 18));
	        label.setForeground(Color.WHITE); // Texte blanc pour contraster

	        panel.add(label, BorderLayout.CENTER); // Ajoute le label au centre

	        frame.setContentPane(panel); // Définir le JPanel avec le background dégradé
	        frame.setVisible(true);
	 }

}
