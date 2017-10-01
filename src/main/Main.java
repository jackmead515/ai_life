package main;

import java.awt.EventQueue;
import java.util.concurrent.TimeUnit;

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
import items.Axe;
import items.Boundary;
import items.IronOre;
import items.Item;
import items.Pickaxe;
import items.Plant;
import items.ShallowWater;
import items.Stone;
import items.Sword;
import items.Water;
import util.Randomizer;
import util.SoundEffect;

public class Main {
	
	public static Frame window;
	public static Registry registry;
	public static Realm realm;
	public static RealmController realmController;
	
	public static Player player;

	public static void main(String[] args) {
		
		realm = new Realm();
		realmController = new RealmController();
		
		init();
		
		startLoop();
		
	}
	
	private static void init() {
		
		BMPImages.load();
		
		player = new Player();
		player.setName("Jack");
		
		loadMap();
		
		realmController.realms[10][10] = realm;
		
		SoundEffect.init();
		
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
	
	private static void loadMap() {
	
		Stone s = new Stone();
		s.coords = new int[] {20, 31};
		Stone s1 = new Stone();
		s1.coords = new int[] {21, 31};
		Stone s2 = new Stone();
		s2.coords = new int[] {22, 34};
		Stone s3 = new Stone();
		s3.coords = new int[] {18, 35};
		Stone s4 = new Stone();
		s4.coords = new int[] {19, 31};
		
		Plant p = new Plant();
		p.coords = new int[] {19, 32};
		Plant p1 = new Plant();
		p1.coords = new int[] {19, 34};
		Plant p2 = new Plant();
		p2.coords = new int[] {21, 36};
		Plant p3 = new Plant();
		p3.coords = new int[] {16, 32};
		Plant p4 = new Plant();
		p4.coords = new int[] {14, 52};
		
		Pickaxe e = new Pickaxe();
		e.coords = new int[] {13, 13};
		Axe e1 = new Axe();
		e1.coords = new int[] {13, 14};
		Sword e2 = new Sword();
		e2.coords = new int[] {13, 15};
		
		IronOre i = new IronOre();
		i.coords = new int[] {15, 23};
		IronOre i1 = new IronOre();
		i1.coords = new int[] {14, 23};
		IronOre i2 = new IronOre();
		i2.coords = new int[] {13, 23};
		IronOre i3 = new IronOre();
		i3.coords = new int[] {15, 25};
		IronOre i4 = new IronOre();
		i4.coords = new int[] {15, 28};		
		
		Deer d = new Deer();
		d.coords = new int[] {20, 32};
		Deer d1 = new Deer();
		d1.coords = new int[] {20, 33};
		
		for(int x = 0; x < 10; x++){
			Water w = new Water();
			w.coords = new int[] {40, 20+x};
			realm.boundaries.add(w);
		}
		
		for(int x = 0; x < 10; x++){
			Water w = new Water();
			w.coords = new int[] {41, 20+x};
			realm.boundaries.add(w);
		}
		
		for(int x = 0; x < 10; x++){
			Water w = new Water();
			w.coords = new int[] {42, 20+x};
			realm.boundaries.add(w);
		}
		
		for(int x = 0; x < 10; x++){
			ShallowWater w = new ShallowWater();
			w.coords = new int[] {43, 20+x};
			realm.items.add(w);
		}
		
		for(int x = 0; x < 10; x++){
			ShallowWater w = new ShallowWater();
			w.coords = new int[] {39, 20+x};
			realm.items.add(w);
		}
		
		for(int x = 0; x < 5; x++){
			ShallowWater w = new ShallowWater();
			w.coords = new int[] {39+x, 19};
			realm.items.add(w);
		}
		
		for(int x = 0; x < 5; x++){
			ShallowWater w = new ShallowWater();
			w.coords = new int[] {39+x, 30};
			realm.items.add(w);
		}
		
		
		realm.items.add(e);
		realm.items.add(e1);
		realm.items.add(e2);
		realm.items.add(i);
		realm.items.add(i1);
		realm.items.add(i2);
		realm.items.add(i3);
		realm.items.add(i4);
		realm.items.add(s);
		realm.items.add(s1);
		realm.items.add(s2);
		realm.items.add(s3);
		realm.items.add(s4);
		realm.items.add(d);
		realm.items.add(d1);
		realm.items.add(p);
		realm.items.add(p1);
		realm.items.add(p2);
		realm.items.add(p3);
		realm.items.add(p4);
	}

	private static void update(double delta, long time) {
		
		player.move(time);
		
		for(int x = 0; x < realm.items.size(); x++) {
			Item i = realm.items.get(x);
			
			if(i instanceof IGrow) {
				((IGrow) i).grow(time);
			} else if(i instanceof Entity) {
				((Entity) i).move(time);
				if(i instanceof IMultiple) {
					((IMultiple) i).multiple(time);
				}
			}
			
			if(i instanceof IAnimate) {
				((IAnimate) i).animate(time);
			}
		}
		
		for(int x = 0; x < realm.boundaries.size(); x++) {
			Boundary i = realm.boundaries.get(x);
			
			if(i instanceof IAnimate) {
				((IAnimate) i).animate(time);
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
				
				Randomizer.randomize(new Plant(), 5);
				//Randomizer.randomize(new Deer(), 2);
				//Randomizer.randomize(new Wolf(), 2);
				
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
