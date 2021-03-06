package entity;

import items.Item;
import items.RawVenison;
import main.BMPImages;
import main.Main;
import util.Util;

public class Deer extends Animal {
	
	public Deer() {
		health = 20;
		totalHealth = 20;
		maxSpawns = 2;
		multipleStartTime = System.nanoTime();;
		multipleTimeSpeed = 60000000000L;
		image = BMPImages.deer;
	}
	
	@Override
	public void multiple(long time) {
		if(maxSpawns <= 0) {
			return;
		}
		
		if(time - multipleStartTime >= multipleTimeSpeed) {
			
			multipleStartTime = time;
			
			Deer d = new Deer();
			d.maxSpawns = 0;
			d.coords.set(coords);
			if(!Main.window.gamePanel.outOfBounds(d.coords) && !Util.inBoundary(d.coords)) {
				Main.realm.add(d);
				maxSpawns -= 1;
			}

		}
	}
	
	@Override
	public Item drop() {
		return new RawVenison();
	}

}
