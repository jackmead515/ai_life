package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

import interfaces.IEntity;
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

}
