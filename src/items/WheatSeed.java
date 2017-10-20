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
		
		if(Randomizer.random(1, 5) == 3) {
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
			
			Main.realm.items.remove(this);
			
			Wheat c = new Wheat();
			c.coords.set(coords.x(), coords.y());
		
			Main.realm.items.add(c);
			
		}
	
	}
	
}
