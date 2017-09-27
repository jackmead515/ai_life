package entity;

import java.awt.Graphics2D;
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
import items.Item;
import items.Wood;
import main.BMPImages;
import main.Main;
import util.Util;

public class Player extends Entity implements IActions {
	
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	private boolean pickUp;
	private boolean use;
	
	private Item inHand;
	
	public Player() {
		super();
		
		image = BMPImages.person;
		
		inHand = null;
		
		up = down = left = right = pickUp = use = false;
		
		setName("Player");
	
		coords = new int[]{0, 0};
	}
	
	@Override
	public void move() {
		int[] now = coords;
		int[] next = null;
		
		if(pickUp) {
			pickUp();
			pickUp = false;
		}
		
		if(use) {
			use();
			use = false;
		}
		
		if(down) {
			next = new int[]{ now[0], now[1]+1 };
		} else if (up) {
			next = new int[]{ now[0], now[1]-1 };
		} else if (left) {
			next = new int[]{ now[0]-1, now[1] };
		} else if(right) {
			next = new int[]{ now[0]+1, now[1] };
		}
		
		if(next != null) {
			if(!Main.window.gamePanel.outOfBounds(next) && !Util.inBoundary(next)) {
				coords = next;
			}
			resetMovement();
		}
	}
	
	@Override
	public void use() {
		if(inHand != null) {
			
			Class<?> c;
			Constructor<?> cons;
			Object p = null;
			try {
				c = Class.forName(inHand.getClass().getName());
				cons = c.getConstructor();
				p = cons.newInstance();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			((Item) p).coords = coords;
			((Item) p).place();
			select(inHand);
			
		}
	}
	
	public void draw(Graphics2D g2, GamePanel panel) {
		int x = coords[0];
		int y = coords[1];
		
		g2.drawImage(image, x*20, y*20, panel);
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
	
	@Override
	public void select(Item item) {
		ArrayList<Item> inv = getInventory();
		boolean selected = false;
		for(Item i : inv) {
			if(i.getClass().equals(item.getClass())) {
				
				selected = true;
				inHand = item;
				inventory.remove(i);
				break;
				
			}
		}
		
		if(!selected) {
			inHand = null;
		}
	}
	
	@Override
	public void pickUp() {
		int x = coords[0];
		int y = coords[1];
		
		LinkedList<Item> items = Main.registry.items;
		
		for(Item i : items) {
			int xi = i.coords[0];
			int yi = i.coords[1];
			
			if(x == xi && y == yi) {
				
				inventory.add(i);
				Main.registry.items.remove(i);
				break;
				
			}
		}
	}
	
	private void openInventory() {
		Main.window.openInventory();
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
			case KeyEvent.VK_I:
				openInventory();
				return;
		}
		updateMovement();
	}

}
