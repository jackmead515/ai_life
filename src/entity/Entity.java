package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

import interfaces.IEntity;
import items.Item;
import weapons.Projectile;

public class Entity extends Item implements IEntity {
	
	public int health;
	public int totalHealth;
	
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	
	public boolean pointingUp;
	public boolean pointingDown;
	public boolean pointingLeft;
	public boolean pointingRight;
	public LinkedList<Projectile> projs;
	
	public Entity() {
		coords = new int[2];
		projs = new LinkedList<Projectile>();
	}
	
	@Override
	public void move(long time) {
		// TODO Auto-generated method stub
		
	}

}
