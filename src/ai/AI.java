package ai;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
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
import main.SoundEffect;
import util.Coords;
import util.RefStrings;
import util.Util;
import weapons.Weapon;

public class AI extends Player {
	
	private int[][] state;
	public double reward;
	
	private AISelector selector;
	
	public AI() {
		state = new int[RefStrings.gameWidth/20][RefStrings.gameHeight/20];
		selector = new AISelector(this);
		reward = 0;
		
		coords.set(1, 1);
	}
	
	private void takeAction(int action) {
		switch(action) {
			case -1: return;
			case 0: break;
			case 1: dropItem = true; break;
			case 2: pickUp = true; break;
			case 3: use = true; break;
			case 4: left = true; break;
			case 5: right = true; break;
			case 6: up = true; break;
			case 7: down = true; break;
			case 8: pointingLeft = true; break;
			case 9: pointingRight = true; break;
			case 10: pointingDown = true; break;
			case 11: pointingUp = true; break;
			default: return;
		}
		
		updateMovement();
		updatePointingDirection();
		selector.action = -1;
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
		
		selector.select(state);
		takeAction(selector.action);

	}
	
	@Override
	protected void calculateNextMovement() {
		int[] now = new int[] {coords.x, coords.y};
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
			
		if(!Main.window.gamePanel.outOfBounds(next) && !Util.inBoundary(next)) {
			coords.set(next);
		} else {
			reward += AIStats.outOfBounds_reward;
		}	
		
		resetMovement();
	}
	
	@Override
	public void dropItem() {
		if(inHand != null) {
			SoundEffect.DROP.play();
			Main.realm.drop(inHand);
			reward += AIStats.dropItem_reward;
			image = BMPImages.person;
			inHand = null; 
		}
	}
	
	@Override
	public void pickUp() {
		if(inHand == null) {
			Collection<Item> bucket = Main.realm.hmitems.get(coords);
			System.out.println(bucket.size());
			Iterator<Item> iter = bucket.iterator();
			while(iter.hasNext()) {
				Item i = iter.next();
				if(i.canPickUp) {
					reward += AIStats.pickUpItem_reward;
					if(i instanceof Food || i instanceof Weapon) {
						reward += AIStats.pickUpItem_reward;
					}
					SoundEffect.PICKUP.play();
					Main.realm.remove(i);
					i.pickUp(this);
					inHand = i;
					break;
				}
			}
		}
	}
	
	@Override
	public void use() {
		if(inHand != null) {
			if(!inHand.use(this)) {
				reward += AIStats.useItem_reward;
				
				if(inHand instanceof Food) {
					reward += (((Food) inHand).health*AIStats.eatFoodMultipler_reward);
				} else if(inHand instanceof Weapon) {
					reward += AIStats.attack_reward;
				}
				
				image = BMPImages.person;
				inHand = null;
			} else if(inHand instanceof Weapon) {
				reward += AIStats.attack_reward;
			}
		}
	}
	
	@Override
	protected void starve(long time) {
		if(time - startTime >= starveRate) {
			startTime = time;
			
			health -= starveAmount;
			reward += -starveAmount;
			
		}
	}
	
}
