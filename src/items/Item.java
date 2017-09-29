package items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import frame.GamePanel;
import main.Main;

public class Item implements IItem {
	
	public int[] coords;
	public BufferedImage image;
	
	public Item() {
		coords = new int[2];
	}
	
	public void draw(Graphics2D g2, GamePanel panel) {
		int x = coords[0];
		int y = coords[1];
		
		g2.drawImage(image, x*20, y*20, panel);
	}
	
	public boolean use(Item item) {
		return true;
	}
	
	public boolean place(int[] coords) {
		this.coords = coords;
		Main.registry.items.add(this);
		return true;
	}
	
}
