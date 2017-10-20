package entity;

import java.util.LinkedList;

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
		projectiles = new LinkedList<Projectile>();
		health = 1;
		totalHealth = 1;
	}
	
	@Override
	public void move(long time) {
		// TODO Auto-generated method stub
		
	}

}
