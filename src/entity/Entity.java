package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

import items.Item;

public class Entity extends Item implements IEntity {
	
	private String name;
	public int health;
	
	public Entity() {
		coords = new int[2];
		name = "";
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	@Override
	public void move(long time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2, JPanel panel) {
		int x = coords[0];
		int y = coords[1];
		
		g2.drawImage(image, x*20, y*20, panel);
	}

}
