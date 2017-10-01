package util;

import java.util.concurrent.ThreadLocalRandom;

import items.Item;
import main.Main;

public class Randomizer {
	
	public static int random(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	public static void randomize(Item i, int percent) {
		
		int rand = random(1, 100);
		
		for(int x = 1; x < percent; x++) {
			if(x == rand) {
				
				try {
					int h = Main.window.gamePanel.getHeight() / 20;
					int w = Main.window.gamePanel.getWidth() / 20;
					
					int[] pwh = new int[] {random(0, w),  random(0, h)};
					
					if(!Main.window.gamePanel.outOfBounds(pwh) && !Util.inBoundary(pwh)) {
						
						if(Main.realm.items.size() <= 0) {
							Object p = i.getClass().newInstance();
							((Item) p).coords = pwh;
							Main.realm.items.add((Item) p);
							break;
						}
						
						for(Item c : Main.realm.items) {
							if(c.coords[0] != pwh[0] || c.coords[1] != pwh[1]) {
								Object p = i.getClass().newInstance();
								((Item) p).coords = pwh;
								Main.realm.items.add((Item) p);
								return;
							}
						}
						
					}	
					
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		
		
	}

}
