package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

import items.Item;

public class Entity implements IEntity {
	
	private String name;
	public int[] coords;
	public BufferedImage image;
	
	protected ArrayList<Item> inventory;
	
	public Entity() {
		inventory = new ArrayList<Item>();
		coords = new int[2];
		name = "";
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2, JPanel panel) {
		// TODO Auto-generated method stub
		
	}

}
