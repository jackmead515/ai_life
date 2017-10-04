package tools;

import java.awt.image.BufferedImage;

import entity.Entity;
import items.Item;

public class Tool extends Item {
	
	public BufferedImage imageInHand;
	public int health;
	
	public Tool() {}
	
	@Override
	public boolean pickUp(Entity e) {
		e.image = imageInHand;
		return true;
	}

}
