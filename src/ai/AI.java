package ai;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entity.Entity;
import entity.IActions;
import frame.GamePanel;
import items.Item;
import main.BMPImages;
import main.Main;
import util.Util;

public class AI extends Entity implements IActions {
	
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	public AI() {
		super();
		
		image = BMPImages.person;
		
		up = down = left = right = false;
		
		setName("AI");
	
		coords = new int[]{5, 5};
	}
	
	public void reward(double r) {
		// TODO Auto-generated method stub
		
	}

	public void punish(double p) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void move() {
		int[] now = coords;
		int[] next = null;
		
		//TODO
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
				punish(AIStats.p_living);	//living penalty 
			} else {
				punish(AIStats.p_outOfBounds); 	// moving into boundary/out of bounds penalty
			}
			resetMovement();
		}
	}
	
	private void resetMovement() {
		up = down = left = right = false;
	}
	
	private void updateMovement() {
		
		//TODO
		
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

	@Override
	public void use() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(Graphics2D g2, JPanel panel) {
		int x = coords[0];
		int y = coords[1];
		
		g2.drawImage(image, x*20, y*20, panel);
	}

	@Override
	public void drop() {
		// TODO Auto-generated method stub
		
	}
	
}
