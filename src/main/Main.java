package main;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import ai.AI;
import entity.Entity;
import entity.Player;
import frame.Frame;
import interfaces.IAnimate;
import interfaces.IGrow;
import interfaces.IMultiple;
import items.Item;
import load.MapLoader;
import util.Coords;
import weapons.Projectile;

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
		
		realmController.realms[10][10] = realm;
		
		realm.items.add(player);
		
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
		//aiplayer.move(time);
		
		for(int x = 0; x < realm.items.size(); x++) {
			Item i = realm.items.get(x);
			
			if(i instanceof IAnimate) {
				((IAnimate) i).animate(time);
			}
			
			if(i instanceof IGrow) {
				((IGrow) i).grow(time);
			}
			
			if(i instanceof Entity) {
				((Entity) i).move(time);
				if(i instanceof IMultiple) {
					((IMultiple) i).multiple(time);
				}
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
