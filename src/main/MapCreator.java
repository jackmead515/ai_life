package main;

import java.awt.EventQueue;
import java.util.concurrent.TimeUnit;

import frame.MapCreatorFrame;
import frame.Palette;
import interfaces.IAnimate;
import items.Boundary;
import items.Item;
import util.SoundEffect;

public class MapCreator {
	
	public static Realm realm;
	public static MapCreatorFrame window;

	public static void main(String[] args) {
		
		init();
		
		startLoop();

	}
	
	private static void init() {
		
		realm = new Realm();
		
		BMPImages.load();
		
		SoundEffect.init();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MapCreatorFrame();
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
		
		window.palette.move(delta);
		
		for(int x = 0; x < realm.items.size(); x++) {
			Item i = realm.items.get(x);
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
		for(int x = 0; x < window.palette.items.size(); x++) {
			Item i = window.palette.items.get(x);
			if(i instanceof IAnimate) {
				((IAnimate) i).animate(time);
			}
		}
		for(int x = 0; x < window.palette.boundaries.size(); x++) {
			Boundary i = window.palette.boundaries.get(x);
			if(i instanceof IAnimate) {
				((IAnimate) i).animate(time);
			}
		}
	}
	
	private static void render() {
		try {
			window.creatorPanel.paintImmediately(0,0,window.creatorPanel.getWidth(),window.creatorPanel.getHeight());
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
