package entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import boundaries.Boundary;
import floors.Floor;
import food.Food;
import food.Venison;
import frame.GamePanel;
import interfaces.IActions;
import items.Barrel;
import items.CampFire;
import items.Chest;
import items.Container;
import items.Crate;
import items.Furnace;
import items.Item;
import items.RawVenison;
import items.Wood;
import main.BMPImages;
import main.Main;
import main.RealmController;
import main.SoundEffect;
import tools.Tool;
import util.Coords;
import util.RefStrings;
import util.Util;
import weapons.Bow;
import weapons.Projectile;
import weapons.Weapon;

public class Player extends Entity {
	
	protected volatile boolean pickUp;
	protected volatile boolean use;
	protected volatile boolean dropItem;

	public boolean showHUD;
	public long startTime;
	public long starveRate;
	public int starveAmount;
	
	public Item inHand;
	
	public Player() {		
		image = BMPImages.person;
		
		startTime = System.nanoTime();
		starveRate = 2000000000L;
		starveAmount = 1;
		
		inHand = null;
		
		health = totalHealth = 100;
		
		showHUD = true;
		
		pointingUp = pointingDown = pointingLeft = pointingRight = false;
		up = down = left = right = pickUp = use = dropItem = false;
	
		coords = new Coords(1,1);
	}
	
	@Override
	public void move(long time) {
		
		starve(time);
		
		shoot(time);
		
		if(dropItem) {
			dropItem();
			dropItem = false;
		}
		
		if(pickUp) {
			pickUp();
			pickUp = false;
		}
		
		if(use) {
			use();
			use = false;
		}
		
		calculateNextMovement();
	}
		
	protected void shoot(long time) {
		for(int x = 0; x < projectiles.size(); x++) {
			Projectile p = projectiles.get(x);
			p.animate(time);
			
			Collection<Item> bucket = Main.realm.hmitems.get(p.coords);
			Iterator<Item> iter = bucket.iterator();
			while(iter.hasNext()) {
				Item i = iter.next();
				if(!(i instanceof Floor)) {
					if(i instanceof Boundary) {
						projectiles.remove(p);
						break;
					}
					if(i instanceof Entity) {
						((Entity) i).health-=p.damage;
						projectiles.remove(p);
						if(((Entity) i).health <= 0) {
							Item a = i.drop();
							if(a != null) {
								a.coords.set(i.coords.x(), i.coords.y());
								Main.realm.add(a);
							}
							Main.realm.remove(i);
						}
						break;
					} else if(i instanceof Container) {
						health -= 1;
						Item a = i.drop();
						if(a != null) {
							a.coords.set(i.coords.x(), i.coords.y());
							Main.realm.add(a);
							Main.realm.remove(i);
						}
						break;
					}
				}
				
				if(p.tileLife <= 0) {
					projectiles.remove(p);
					break;
				}
			}
		}
	}

	protected void calculateNextMovement() {
		
		int[] now = new int[] {coords.x(), coords.y()};
		Coords next = new Coords(0,0);
		
		if(down) {
			next.set(now[0], now[1]+1);
		} else if (up) {
			next.set(now[0], now[1]-1);
		} else if (left) {
			next.set(now[0]-1, now[1]);
		} else if(right) {
			next.set(now[0]+1, now[1]);
		} else {
			return;
		}
			
		if(!Main.window.gamePanel.outOfBounds(next)) {
			 if(!Util.inBoundary(next)) {
				 coords.set(next.x(), next.y());
			 }
		} else {
			if(up && Main.realmController.upRealm()) {
				coords.set(next.x(), RefStrings.gameHeight / 20);
			} else if(down && Main.realmController.downRealm()) {
				coords.set(next.x(), 0);
			} else if(left && Main.realmController.leftRealm()) {
				coords.set(RefStrings.gameWidth / 20, next.y());
			} else if(right && Main.realmController.rightRealm()) {
				coords.set(0, next.y());
			}
		}
		
		resetMovement();
	}
	
	public void dropItem() {
		if(inHand != null) {
			SoundEffect.DROP.play();
			if(inHand.place(coords)) {
				image = BMPImages.person;
				inHand = null; 
			}
		}
	}
	
	public void use() {
		if(inHand != null) {
			if(!inHand.use(this)) {
				image = BMPImages.person;
				inHand = null;
			}
		}
	}
	
	public void pickUp() {
		if(inHand == null) {
			Collection<Item> bucket = Main.realm.hmitems.get(coords);
			Iterator<Item> iter = bucket.iterator();
			while(iter.hasNext()) {
				Item i = iter.next();
				if(!(i instanceof Floor) && i.canPickUp) {
						SoundEffect.PICKUP.play();
						Main.realm.remove(i);
						i.pickUp(this);
						inHand = i;
						break;
				}
			}
		}
	}
	
	protected void resetMovement() {
		up = down = left = right = false;
	}
	
	protected void updateMovement() {
		if(down) {
			up = left = right = false;
		} else if (up) {
			down = left = right = false;
		} else if (left) {
			right = up = down = false;
		} else if(right) {
			up = down = left = false;
		}
	}
	
	protected void updatePointingDirection() {
		if(pointingDown) {
			pointingUp = pointingLeft = pointingRight = false;
		} else if (pointingUp) {
			pointingDown = pointingLeft = pointingRight = false;
		} else if (pointingLeft) {
			pointingRight = pointingUp = pointingDown = false;
		} else if(pointingRight) {
			pointingUp = pointingDown = pointingLeft = false;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_S: down = true; break;
			case KeyEvent.VK_W: up = true; break;
			case KeyEvent.VK_A: left = true; break;
			case KeyEvent.VK_D: right = true; break;
			case KeyEvent.VK_SPACE: pickUp = true; return;
			case KeyEvent.VK_ENTER: use = true; return;
			case KeyEvent.VK_SHIFT: dropItem = true; return;
			case KeyEvent.VK_H: showHUD = !showHUD; return;
		}
		updateMovement();
	}
	
	public void mouseMoved(Point p) {
		Point c = new Point((coords.x()*20)+10, (coords.y()*20)+10);
		
		int direction = Util.directionFrom(c, p);
		
		switch(direction) {
			case 1: pointingUp = true; break;
			case 2: pointingDown = true; break;
			case 3: pointingLeft = true; break;
			case 4: pointingRight = true; break;
			default: return;
		}
		
		updatePointingDirection();
	}

	protected void starve(long time) {
		if(time - startTime >= starveRate) {
			startTime = time;
			
			health-=starveAmount;
		}
	}
}