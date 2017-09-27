package items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import frame.GamePanel;

public class Boundary {
	
	public int[] coords;
	public BufferedImage image;
	
	public boolean canWalkOver;
	
	public Boundary() {
		coords = new int[2];
	}
	
	public void draw(Graphics2D g2, GamePanel panel) {
		
	}

}
