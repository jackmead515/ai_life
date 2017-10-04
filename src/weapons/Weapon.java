package weapons;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import frame.GamePanel;
import items.Item;
import main.Main;

public class Weapon extends Item {
	
	public BufferedImage imageInHand;
	
	public int health;

	public Weapon() {}
	
	@Override
	public boolean pickUp(Entity e) {
		e.image = imageInHand;
		return true;
	}

	

}
