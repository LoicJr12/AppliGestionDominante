package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TransparantPanel extends JPanel {
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Définir la couleur de fond avec transparence
        Color transparentWhite = new Color(255, 255, 255, 128); // 128 est la valeur alpha pour la transparence
        g2d.setColor(transparentWhite);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.dispose();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
