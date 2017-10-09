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
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import food.Food;
import frame.GamePanel;
import interfaces.IActions;
import items.Barrel;
import items.Chest;
import items.Crate;
import items.Item;
import items.RawVenison;
import items.Wood;
import main.BMPImages;
import main.Main;
import main.RealmController;
import main.SoundEffect;
import tools.Tool;
import util.Util;
import weapons.Projectile;
import weapons.Weapon;

public class Player extends Entity {
	
	protected boolean pickUp;
	protected boolean use;
	protected boolean drop;

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
		up = down = left = right = pickUp = use = drop = false;
	
		coords = new int[]{0, 0};
	}
	
	@Override
	public void move(long time) {
		
		starve(time);
		
		shoot(time);
		
		if(drop) {
			drop();
			drop = false;
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
			for(int x1 = 0; x1 < Main.realm.items.size(); x1++) {
				Item i = Main.realm.items.get(x1);
				if(p.coords[0] == i.coords[0] && p.coords[1] == i.coords[1]) {
					if(i instanceof Deer) {
						((Entity) i).health-=p.damage;
						projectiles.remove(p);
						if(((Entity) i).health <= 0) {
							RawVenison a = new RawVenison();
							a.coords = i.coords;
							Main.realm.items.add(a);
							Main.realm.items.remove(i);
						}
						
					} else if(i instanceof Crate) {
						Item p1 = Crate.generate();
						p1.coords = i.coords;
						Main.realm.items.add(p1);
						Main.realm.items.remove(i);
						projectiles.remove(p);
						
					} else if(i instanceof Barrel) {
						Item p1 = Barrel.generate();
						p1.coords = i.coords;
						Main.realm.items.add(p1);
						Main.realm.items.remove(i);
						projectiles.remove(p);
						
					} else if(i instanceof Chest) {
						Item p1 = Chest.generate();
						p1.coords = i.coords;
						Main.realm.items.add(p1);
						Main.realm.items.remove(i);
						projectiles.remove(p);
						
					}
				}
			}
			if(p.tileLife <= 0) {
				projectiles.remove(p);
			}
		}
	}

	protected void calculateNextMovement() {
		int[] now = coords;
		int[] next = null;
		
		if(down) {
			next = new int[]{ now[0], now[1]+1 };
		} else if (up) {
			next = new int[]{ now[0], now[1]-1 };
		} else if (left) {
			next = new int[]{ now[0]-1, now[1] };
		} else if(right) {
			next = new int[]{ now[0]+1, now[1] };
		} else {
			return;
		}
			
		if(!Main.window.gamePanel.outOfBounds(next)) {
			 if(!Util.inBoundary(next)) {
				 resetMovement();
				 coords = next;
			 }
		} else {
			if(up) {
				coords = new int[]{next[0], Main.window.gamePanel.getHeight() / 20};
				Main.realmController.upRealm();
			} else if(down) {
				coords = new int[]{next[0], 0};
				Main.realmController.downRealm();
			} else if(left) {
				coords = new int[]{Main.window.gamePanel.getWidth() / 20, next[1]};
				Main.realmController.leftRealm();
			} else if(right) {
				coords = new int[]{0, next[1]};
				Main.realmController.rightRealm();
			}
		}
		
		resetMovement();
	}
	
	public void drop() {
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
			LinkedList<Item> items = Main.realm.items;
			
			for(Item i : items) {
				int xi = i.coords[0];
				int yi = i.coords[1];
				
				if(coords[0] == xi && coords[1] == yi && i.canPickUp) {	
					SoundEffect.PICKUP.play();
					Main.realm.items.remove(i);
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
			case KeyEvent.VK_S:
				down = true;
				break;
			case KeyEvent.VK_W:
				up = true;
				break;
			case KeyEvent.VK_A:
				left = true;
				break;
			case KeyEvent.VK_D:
				right = true;
				break;
			case KeyEvent.VK_SPACE:
				pickUp = true;
				return;
			case KeyEvent.VK_ENTER:
				use = true;
				return;
			case KeyEvent.VK_SHIFT:
				drop = true;
				return;
			case KeyEvent.VK_H:
				showHUD = !showHUD;
				return;
		}
		updateMovement();
	}
	
	public  void mouseMoved(Point p) {
		Point c = new Point((coords[0]*20)+10, (coords[1]*20)+10);
		
		if(p.y < c.y && (p.x > (p.y-c.y+c.x)) && (p.x < (c.y+c.x-p.y))) {
			pointingUp = true;
			updatePointingDirection();
			return;
		} else if(p.y > c.y && (p.x > (p.y-c.y+c.x)) && (p.x < (c.y+c.x+p.y))) {
			pointingDown = true;
			updatePointingDirection();
			return;
		} else if(p.x < c.x && (p.y > (p.x-c.x+c.y)) && (p.y < (c.x+c.y-p.x))) {
			pointingLeft = true;
			updatePointingDirection();
			return;
		} else if(p.x > c.x && (p.y < (p.x-c.x+c.y)) && (p.y > (c.x+c.y-p.x))) {
			pointingRight = true;
			updatePointingDirection();
			return;
		}
	}

	protected void starve(long time) {
		if(time - startTime >= starveRate) {
			startTime = time;
			
			health-=starveAmount;
		}
	}
}