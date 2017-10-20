package items;

import interfaces.IGrow;
import main.BMPImages;
import main.Main;
import util.Randomizer;

public class Plant extends Item implements IGrow {
	
	protected long cycleTime;
	protected long startTime;
	protected boolean canGrow;
	
	public Plant() {
		
		if(Randomizer.random(1, 5) == 3) {
			canGrow = true;
		} else {
			canGrow = false;
		}
		
		
		cycleTime = 30000000000L;
		
		image = BMPImages.plant;
		
		startTime = System.nanoTime();
		
	}
	
	@Override
	public void grow(long time) {
		if(!canGrow) {
			return;
		}
		
		if(time - startTime >= cycleTime) {
		
			Main.realm.remove(this);
			
			Shrub c = new Shrub();
			c.coords.set(coords.x(), coords.y());
		
			Main.realm.add(c);
			
		}
	
	}

}
