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

import frame.GamePanel;
import interfaces.IActions;
import items.Food;
import items.Item;
import items.Projectile;
import items.Tool;
import items.Weapon;
import items.Wood;
import main.BMPImages;
import main.Main;
import main.RealmController;
import util.Util;

public class Player extends Entity implements IActions {
	
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	private boolean pointingUp;
	private boolean pointingDown;
	private boolean pointingLeft;
	private boolean pointingRight;
	
	private boolean pickUp;
	private boolean use;
	private boolean drop;

	public boolean showHUD;
	public int totalHealth;
	public long startTime;
	public long starveRate;
	
	public Item inHand;
	
	public Player() {
		super();
		
		image = BMPImages.person;
		
		startTime = System.nanoTime();
		starveRate = 2000000000L;
		
		inHand = null;
		
		health = totalHealth = 100;
		
		showHUD = true;
		
		pointingUp = pointingDown = pointingLeft = pointingRight = false;
		up = down = left = right = pickUp = use = drop = false;
		
		setName("Player");
	
		coords = new int[]{0, 0};
	}
	
	@Override
	public void move(long time) {
		
		starve(time);
		
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
		
		/*if(!animating) {
			if(calculateNextMovement()) {
				animating = true;
			}
		} else {
			if(animatedPath.size() > 0) {
				coords = animatedPath.pop();
			} else {
				animating = false;
			}
		}*/
	}
		
	private void calculateNextMovement() {
		int[] now = coords;
		int[] next = null;
		int cx = coords[0];
		int cy = coords[1];
		
		if(down) {
			next = new int[]{ now[0], now[1]+1 };
			/*for(int i = 0; i < 21; i++) {
				int[] p = new int[2];
				p[1] = cy + i;
				p[0] = cx;
				animatedPath.add(p);
			}*/
		} else if (up) {
			next = new int[]{ now[0], now[1]-1 };
			/*for(int i = 0; i < 21; i++) {
				int[] p = new int[2];
				p[1] = cy - i;
				p[0] = cx;
				animatedPath.add(p);
			}*/
		} else if (left) {
			next = new int[]{ now[0]-1, now[1] };
			/*for(int i = 0; i < 21; i++) {
				int[] p = new int[2];
				p[1] = cy;
				p[0] = cx - i;
				animatedPath.add(p);
			}*/
		} else if(right) {
			next = new int[]{ now[0]+1, now[1] };
			/*for(int i = 0; i < 21; i++) {
				int[] p = new int[2];
				p[1] = cy;
				p[0] = cx + i;
				animatedPath.add(p);
			}*/
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
	
	@Override
	public void drop() {
		if(inHand != null) {
			LinkedList<Item> items = Main.realm.items;
			
			if(items.size() <= 0) {
				if(inHand.place(coords)) {
					image = BMPImages.person;
					inHand = null;
					return;
				}
			}
			
			for(Item i : items) {
				int xi = i.coords[0];
				int yi = i.coords[1];
				
				if(coords[0] != xi || coords[1] != yi) {
					if(inHand.place(coords)) {
						image = BMPImages.person;
						inHand = null;
						break;
					}
				}
			}
		}
	}
	
	@Override
	public void use() {
		if(inHand != null) {
			
			if(inHand instanceof Food) {
				((Food) inHand).eat(this);
				inHand = null;
				if(health > totalHealth) {
					health = totalHealth;
				}
				return;
			}
			
			if(inHand instanceof Projectile) {
				int direction = 0;
				if(pointingUp) {
					direction = 1;
				} else if(pointingDown) {
					direction = 2;
				} else if(pointingLeft) {
					direction = 3;
				} else if(pointingRight) {
					direction = 4;
				}
				if(direction != 0) {
					((Projectile) inHand).shoot(direction);
				}
				
				return;
			}
			
			LinkedList<Item> items = Main.realm.items;
			
			for(Item i : items) { 
				int xi = i.coords[0];
				int yi = i.coords[1];
				
				if(coords[0] == xi && coords[1] == yi) {
					if(!inHand.use(i)) {
						image = BMPImages.person;
						inHand = null;
					}
					break;
				}
			}
		}
	}
	
	private void resetMovement() {
		up = down = left = right = false;
	}
	
	private void updateMovement() {
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
	
	private void updatePointingDirection() {
		if(pointingDown) {
			pointingUp = pointingLeft = pointingRight = false;
		} else if (pointingUp) {
			pointingDown = pointingLeft = pointingRight = false;
		} else if (left) {
			pointingRight = pointingUp = pointingDown = false;
		} else if(pointingRight) {
			pointingUp = pointingDown = pointingLeft = false;
		}
	}
	
	@Override
	public void pickUp() {
		
		if(inHand != null) {
			return;
		}
		
		LinkedList<Item> items = Main.realm.items;
		
		for(Item i : items) {
			int xi = i.coords[0];
			int yi = i.coords[1];
			
			if(coords[0] == xi && coords[1] == yi && i.canPickUp) {
					
					Main.realm.items.remove(i);
					inHand = i;

					if(inHand instanceof Tool) {
						image = ((Tool) inHand).imageInHand;
					} else if(inHand instanceof Weapon) {
						image = ((Weapon) inHand).imageInHand;
					}
					
					break;
					
				}
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
	
	public void mouseMoved(Point p) {
		Point c = new Point(coords[0]+10, coords[1]+10);
		
		if(p.y < c.y && (p.x > (p.y-c.y+c.x)) && (p.x < (c.y+c.x-p.y))) {
			pointingUp = true;
		} else if(p.y > c.y && (p.x > (p.y-c.y+c.x)) && (p.x <  (c.y+c.x+p.y))) {
			pointingDown = true;
		} else if(p.x < c.x && (p.y > (p.x-c.x+c.y)) && (p.y < (c.x+c.y-p.x))) {
			pointingLeft = true;
		} else if(p.x > c.x && (p.y < (p.x-c.x+c.y)) && (p.y > (c.x+c.y-p.x))) {
			pointingRight = true;
		}
		
		updatePointingDirection();
	}

	private void starve(long time) {
		if(time - startTime >= starveRate) {
			
			startTime = time;
			
			health-=1;
		}
	}
}