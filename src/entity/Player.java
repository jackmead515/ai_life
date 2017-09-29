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
import items.Tool;
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
	private boolean drop;
	
	private Item inHand;
	
	public Player() {
		super();
		
		image = BMPImages.person;
		
		inHand = null;
		
		up = down = left = right = pickUp = use = drop = false;
		
		setName("Player");
	
		coords = new int[]{0, 0};
	}
	
	@Override
	public void move() {
		int[] now = coords;
		int[] next = null;
		
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
	public void drop() {
		if(inHand != null) {
			LinkedList<Item> items = Main.registry.items;
			
			for(Item i : items) {
				int xi = i.coords[0];
				int yi = i.coords[1];
				
				if(coords[0] != xi || coords[1] != yi) {
					if(inHand.place(coords)) {
						Main.registry.items.add(inHand);
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
			LinkedList<Item> items = Main.registry.items;
			
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
	
	@Override
	public void pickUp() {
		LinkedList<Item> items = Main.registry.items;
		
		for(Item i : items) {
			int xi = i.coords[0];
			int yi = i.coords[1];
			
			if(coords[0] == xi && coords[1] == yi) {
				
				if(inHand != null) {
					
					Main.registry.items.remove(i);
					if(inHand.place(coords)) {
						inHand = i;
						
						if(inHand instanceof Tool) {
							image = ((Tool) inHand).imageInHand;
						}
						
						break;
					} else {
						Main.registry.items.add(i);
						break;
					}
		
				} else {
					
					Main.registry.items.remove(i);
					inHand = i;

					if(inHand instanceof Tool) {
						image = ((Tool) inHand).imageInHand;
					}
					
					break;
					
				}
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
		}
		updateMovement();
	}

}
