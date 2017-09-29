package main;

import java.awt.EventQueue;
import java.util.concurrent.TimeUnit;

import entity.Player;
import frame.Frame;
import items.Boundary;
import items.Item;
import items.Plant;
import items.Wood;
import util.Randomizer;

public class Main {
	
	public static Frame window;
	public static Registry registry;
	public static boolean random;
	
	public static Player player;

	public static void main(String[] args) {
		
		registry = new Registry();
		
		init();
		
		startLoop();
		
	}
	
	private static void init() {
		
		BMPImages.load();
		
		player = new Player();
		player.setName("Jack");
		
		random = false;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void update(double delta, long time) {
		player.move();
	
		for(Item i : registry.items) {
			if(i instanceof Plant) {
				((Plant) i).grow(time);
			}
		}
	}
	
	private static void setRandom(int chance) {
		int gx = window.gamePanel.getWidth() / 20;
		int gy = window.gamePanel.getHeight() / 20;
		
		int rx = Randomizer.random(1, gx);
		int ry = Randomizer.random(1, gy);
		
		Plant p = new Plant();
		p.coords = new int[]{rx, ry};
	
		Main.registry.addItem(p);
	}
	
	private static boolean getRandom() {
		return random;
	}
	
	private static void render() {
		try {
			window.gamePanel.paintImmediately(0,0,window.gamePanel.getWidth(),window.gamePanel.getHeight());
			//Frame.gamePanel.repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void startLoop() {
		long lastLoopTime = System.nanoTime();
		int lastFpsTime = 0;
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;   
		int fps = 0;

		while (true) {

			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double)OPTIMAL_TIME);

			lastFpsTime += updateLength;
			fps++;

			if (lastFpsTime >= 1000000000) {
				setRandom(20);
	
				lastFpsTime = 0;
				fps = 0;
			}
			
			update(delta, lastFpsTime);

			render();

			try {
				Thread.sleep( Math.abs((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 ));
			} catch(Exception e) {
				e.printStackTrace();
			};
		}
		
	}

}
