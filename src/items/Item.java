package items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import entity.Entity;
import frame.GamePanel;
import interfaces.IItem;
import main.Main;

public class Item implements IItem {
	
	public int[] coords;
	public BufferedImage image;
	public boolean canPickUp;
	public boolean canWalkOver;
	
	public Item() {
		coords = new int[2];
		canPickUp = true;
		canWalkOver = true;
	}
	
	@Override
	public void draw(Graphics2D g2, JPanel panel) {
		int x = coords[0];
		int y = coords[1];
		
		g2.drawImage(image, x*20, y*20, panel);
	}
	
	/**
	 * returns false if entity should update itself.
	 * returns true if entity does not need to change anything.
	 */
	@Override
	public boolean use(Entity e) {
		return true;
	}
	
	@Override
	public boolean pickUp(Entity e) {
		return true;
	}
	
	@Override
	public String description() {
		return this.getClass().getSimpleName();
	}
	
	@Override
	public boolean place(int[] coords) {
		this.coords = coords;
		Main.realm.items.add(this);
		return true;
	}
	
}
