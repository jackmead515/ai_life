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
	
	public volatile boolean up;
	public volatile boolean down;
	public volatile boolean left;
	public volatile boolean right;
	
	public volatile boolean pointingUp;
	public volatile boolean pointingDown;
	public volatile boolean pointingLeft;
	public volatile boolean pointingRight;
	
	public LinkedList<Projectile> projectiles;
	
	public Entity() {
		coords = new int[2];
		projectiles = new LinkedList<Projectile>();
		health = 1;
		totalHealth = 1;
	}
	
	@Override
	public void move(long time) {
		// TODO Auto-generated method stub
		
	}

}
