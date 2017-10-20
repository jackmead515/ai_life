package items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import entity.Entity;
import frame.GamePanel;
import interfaces.IItem;
import main.Main;
import util.Coords;

public class Item implements IItem {
	
	public Coords coords;
	public BufferedImage image;
	public boolean canPickUp;
	public boolean canWalkOver;
	
	public Item() {
		coords = new Coords();
		canPickUp = true;
		canWalkOver = true;
	}
	
	@Override
	public void draw(Graphics2D g2, JPanel panel) {
		g2.drawImage(image, coords.x()*20, coords.y()*20, panel);
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
	
	public Item drop() {
		return null;
	}
	
	@Override
	public String description() {
		return this.getClass().getSimpleName();
	}
	
	@Override
	public boolean place(Coords coords) {
		this.coords.set(coords.x(), coords.y());
		Main.realm.add(this);
		return true;
	}
	
}
