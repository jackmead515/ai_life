package interfaces;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.swing.JPanel;

import entity.Entity;
import frame.GamePanel;
import items.Item;
import util.Coords;

public interface IItem extends Serializable {
	
	public void draw(Graphics2D g2, JPanel panel);

	public boolean use(Entity e);

	public boolean pickUp(Entity e);

	public String description();

	public Item drop();

}
