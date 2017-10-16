package entity;

import main.BMPImages;
import main.Main;
import util.Util;

public class Wolf extends Predator {
	
	public Wolf() {
		health = 5;
		maxSpawns = 1;
		multipleRate = 120000000000L;
		image = BMPImages.wolf;
		canPickUp = false;
		startTime = System.nanoTime();
	}
	
	@Override
	public void multiple(long time) {
		if(maxSpawns <= 0) {
			return;
		}
		
		if(time - startTime >= multipleRate) {
			
			startTime = time;
			
			Wolf d = new Wolf();
			d.coords = new int[] {coords[0], coords[1]+1};
			if(!Main.window.gamePanel.outOfBounds(d.coords) && !Util.inBoundary(d.coords)) {
				Main.realm.items.add(d);
				maxSpawns -= 1;
			}

		}
	}

}
