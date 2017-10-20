package items;

import interfaces.IGrow;
import main.BMPImages;
import main.Main;

public class WheatSeed extends Item implements IGrow {
	
	protected long cycleTime;
	protected long startTime;
	
	public WheatSeed() {
		
		cycleTime = 120000000000L;
		
		image = BMPImages.wheat_seed;
		
		startTime = System.nanoTime();
		
	}

	@Override
	public void grow(long time) {
		
		if(time - startTime >= cycleTime) {
			
			Main.realm.items.remove(this);
			
			Wheat c = new Wheat();
			c.coords = coords;
		
			Main.realm.items.add(c);
			
		}
	
	}
	
}
