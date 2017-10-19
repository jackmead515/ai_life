package entity;

import main.BMPImages;
import main.Main;
import util.Util;

public class Deer extends Animal {
	
	public Deer() {
		health = 5;
		maxSpawns = 2;
		multipleRate = 60000000000L;
		image = BMPImages.deer;
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
			
			Deer d = new Deer();
			d.maxSpawns = 0;
			d.coords = new int[] {coords[0], coords[1]+1};
			if(!Main.window.gamePanel.outOfBounds(d.coords) && !Util.inBoundary(d.coords)) {
				Main.realm.items.add(d);
				maxSpawns -= 1;
			}

		}
	}

}
