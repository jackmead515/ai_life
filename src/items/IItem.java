package items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import frame.GamePanel;

public interface IItem {
	
	public void draw(Graphics2D g2, GamePanel panel);
	
	public boolean place(int[] coords);
	
	public boolean use(Item item);

}
