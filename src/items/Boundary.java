package items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import frame.GamePanel;
import interfaces.IBoundary;

public class Boundary implements IBoundary {
	
	public int[] coords;
	public BufferedImage image;
	
	public boolean canWalkOver;
	
	public Boundary() {
		coords = new int[2];
	}
	
	@Override
	public void draw(Graphics2D g2, GamePanel panel) {
		int x = coords[0];
		int y = coords[1];
		
		g2.drawImage(image, x*20, y*20, panel);
	}

}
