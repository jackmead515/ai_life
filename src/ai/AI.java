package ai;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import food.Food;
import frame.GamePanel;
import interfaces.IActions;
import items.Item;
import main.BMPImages;
import main.Main;
import util.Util;

public class AI extends Player {
	
	private boolean selecting;
	private String[][] state;
	private double reward;
	
	public AI() {
		state = new String[40][100];
		initState();
		reward = 0;
		selecting = false;
	}
	
	private void initState() {
		for(int x = 0; x < state.length; x++) {
			for(int i = 0; i < state[x].length; i++) {
				state[x][i] = "null";
			}
		}
	}
	
	public void takeAction(int action) {
		switch(action) {
			case 1:
				drop = true;
				break;
			case 2:
				pickUp = true;
				break;
			case 3:
				use = true;
				break;
			case 4:
				left = true;
				break;
			case 5:
				right = true;
				break;
			case 6:
				up = true;
				break;
			case 7:
				down = true;
				break;
			case 8:
				pointingLeft = true;
				break;
			case 9:
				pointingRight = true;
				break;
			case 10:
				pointingDown = true;
				break;
			case 11:
				pointingUp = true;
				break;
		}
		
		updateMovement();
		updatePointingDirection();
	}
	
	public int fetchAction() {
		
	}
	
	public void selectAction() {
		if(!selecting) {
			selecting = true;
			
			LinkedList<Item> items = Main.realm.items;
			initState();
			for(int x = 0; x < items.size(); x++) {
				Item i = items.get(x);
				state[i.coords[0]][i.coords[1]] = i.getClass().getSimpleName();
			}
			
			Thread t = new Thread(new Runnable() {
				public void run() {
					int action = fetchAction();
					takeAction(action);
					selecting = false;
				}
			});
			t.start();
		}
	}
	
	@Override
	public void move(long time) {
		
		selectAction();
		
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
	
	@Override
	public void use() {
		if(inHand != null) {
			if(!inHand.use(this)) {
				if(inHand instanceof Food) {
					//TODO reward somehow;
				}
				
				image = BMPImages.person;
				inHand = null;
			}
		}
	}
	
	@Override
	protected void starve(long time) {
		if(time - startTime >= starveRate) {
			startTime = time;
			
			//TODO punish somehow
			
			health-=starveAmount;
			
		}
	}
	
}
