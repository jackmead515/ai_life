package main;

import java.awt.EventQueue;
import java.util.concurrent.TimeUnit;

import ai.AI;
import boundaries.Boundary;
import boundaries.Water;
import entity.Animal;
import entity.Deer;
import entity.Entity;
import entity.Player;
import entity.Predator;
import entity.Wolf;
import frame.Frame;
import interfaces.IAnimate;
import interfaces.IGrow;
import interfaces.IMultiple;
import items.IronOre;
import items.Item;
import items.Plant;
import items.ShallowWater;
import items.Stone;
import load.MapLoader;
import tools.Axe;
import tools.Pickaxe;
import util.Randomizer;
import weapons.Arrow;
import weapons.Bow;
import weapons.Projectile;
import weapons.Sword;

public class Main {
	
	public static Frame window;
	public static Realm realm;
	public static RealmController realmController;
	
	public static Player player;
	public static AI aiplayer;

	public static void main(String[] args) {
		
		init();
		
		startLoop();
		
	}
	
	private static void init() {
		
		BMPImages.load();
		SoundEffect.load();
		
		realm = MapLoader.load();
		realmController = new RealmController();
		
		player = new Player();
		aiplayer = new AI();
		
		realmController.realms[10][10] = realm;
		
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					window = new Frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void update(double delta, long time) {
		
		player.move(time);
		aiplayer.move(time);
		
		for(int x = 0; x < realm.items.size(); x++) {
			Item i = realm.items.get(x);
			
			if(i instanceof IAnimate) {
				((IAnimate) i).animate(time);
			}
			
			if(i instanceof IGrow) {
				((IGrow) i).grow(time);
			} else if(i instanceof Entity) {
				((Entity) i).move(time);
				if(i instanceof IMultiple) {
					((IMultiple) i).multiple(time);
				}
			}
			
			if(i instanceof Projectile) {
				((Projectile) i).shoot();
			}
		}
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
		long startTime = lastLoopTime;
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
				lastFpsTime = 0;
				fps = 0;
			} 
			
			update(delta, now);

			render();

			try {
				Thread.sleep( Math.abs((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 ));
			} catch(Exception e) {
				e.printStackTrace();
			};
		}
		
	}

}
