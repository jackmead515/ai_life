package items;

import interfaces.IGrow;
import main.BMPImages;
import main.Main;
import util.Randomizer;

public class WheatSeed extends Item implements IGrow {
	
	protected long cycleTime;
	protected long startTime;
	protected boolean canGrow;
	
	public WheatSeed() {
		
		if(Randomizer.random(1, 100) <= 80) {
			canGrow = true;
		} else {
			canGrow = false;
		}
		
		cycleTime = 120000000000L;
		
		image = BMPImages.wheat_seed;
		
		startTime = System.nanoTime();
		
	}

	@Override
	public void grow(long time) {
		if(!canGrow) {
			return;
		}
		
		if(time - startTime >= cycleTime) {
			
			Main.realm.remove(this);
			
			Wheat c = new Wheat();
			c.coords.set(coords);
		
			Main.realm.add(c);
			
		}
	
	}
	
}
