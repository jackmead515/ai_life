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

	private boolean selecting;
	private int[][] state;
	private double reward;
	
	public AI() {
		state = new int[40][100];
		initState();
		reward = 0;
		selecting = false;
	}
	
	private void initState() {
		for(int x = 0; x < state.length; x++) {
			for(int i = 0; i < state[x].length; i++) {
				state[x][i] = 0;
			}
		}
		
		LinkedList<Item> items = Main.realm.items;
		for(int x = 0; x < items.size(); x++) {
			Item i = items.get(x);
			state[i.coords[0]][i.coords[1]] = new BigInteger(i.getClass().getSimpleName().getBytes()).intValue();
		}
	}
	
	private  void takeAction(int action) {
		switch(action) {
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
		}
		
		updateMovement();
		updatePointingDirection();
	}
	
	@SuppressWarnings("finally")
	private int fetchAction() {

		HttpURLConnection connection = null;
		int action = 0;

		try {
			String postString = "state=" + Arrays.deepToString(state) + "&reward=" + reward;
			
			URL url = new URL("http://" + RefStrings.AI_IP);
			connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			connection.setDoOutput(true);
			
			connection.getOutputStream().write(postString.getBytes());
			connection.getOutputStream().flush();
			connection.getOutputStream().close();
			
			InputStream input = connection.getInputStream();
			
			action = input.read();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			return action;
		}
	}
	
	public void selectAction() {
		if(!selecting) {
			selecting = true;
			
			initState();
			
			Thread t = new Thread(new Runnable() {
				public void run() {
					int action = fetchAction();
					takeAction(action);
					reward = 0;
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
					reward += AIStats.eat_reward(((Food) inHand).health, totalHealth);
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
			reward += AIStats.starve_reward(health, totalHealth);
			
		}
	}
	
}
