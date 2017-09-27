package items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import frame.GamePanel;

public class Item {
	
	public int[] coords;
	public BufferedImage image;
	
	public Item() {
		coords = new int[2];
	}
	
	public void draw(Graphics2D g2, GamePanel panel) {
		
	}
	
	public void place() {
		
	}
	
	public int id() {
		return 0;
	}
	
}
