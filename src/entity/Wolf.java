package entity;

import main.BMPImages;
import main.Main;
import util.Util;

public class Wolf extends Predator {
	
	public Wolf() {
		health = 40;
		maxSpawns = 1;
		multipleStartTime = System.nanoTime();
		multipleTimeSpeed = 120000000000L;
		image = BMPImages.wolf;
	}
	
	@Override
	public void multiple(long time) {
		if(maxSpawns <= 0) {
			return;
		}
		
		if(time - multipleStartTime >= multipleTimeSpeed) {
			
			multipleStartTime = time;
			
			Wolf d = new Wolf();
			d.maxSpawns = 0;
			d.coords.set(coords);
			if(!Main.window.gamePanel.outOfBounds(d.coords) && !Util.inBoundary(d.coords)) {
				Main.realm.add(d);
				maxSpawns -= 1;
			}

		}
	}

}
