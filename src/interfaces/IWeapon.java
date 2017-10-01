package interfaces;

import java.awt.Graphics2D;

import entity.Entity;
import frame.GamePanel;

public interface IWeapon {
	
	public void draw(Graphics2D g2, GamePanel panel);
	
	public boolean place(int[] coords);
	
	public boolean use(Entity item);

}
