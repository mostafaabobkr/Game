package engine;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class MyContent extends JPanel {
	public Image backgroundImage;

	public MyContent(Image img) {
		this.backgroundImage = img;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
	}
}