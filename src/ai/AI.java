package ai;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
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
import util.RefStrings;
import util.Util;

public class AI extends Player {
	
	private int[][] state;
	private double reward;
	
	private AISelector selector;
	
	public AI() {
		state = new int[RefStrings.gameWidth/20][RefStrings.gameHeight/20];
		selector = new AISelector();
		reward = 0;
	}
	
	private void takeAction(int action) {
		switch(action) {
			case -1:
				return;
			case 0:
				break;
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
			default:
				return;
		}
		
		updateMovement();
		updatePointingDirection();
		selector.action = -1;
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
		
		selector.select(state, reward);
		takeAction(selector.action);

	}
	
	@Override
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
			reward += AIStats.outOfBounds_reward;
		}
		
		resetMovement();
	}
	
	@Override
	public void use() {
		if(inHand != null) {
			if(!inHand.use(this)) {
				if(inHand instanceof Food) {
					reward += ((Food) inHand).health;
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
			
			health-=starveAmount;
			
			reward += AIStats.living_reward;
			reward += -starveAmount;
			
		}
	}
	
}
