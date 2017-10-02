package interfaces;

import java.awt.Graphics2D;
import java.io.Serializable;

import javax.swing.JPanel;

import frame.GamePanel;

public interface IBoundary extends Serializable {
	
	public void draw(Graphics2D g2, JPanel panel);

}
