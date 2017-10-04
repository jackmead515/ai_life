package items;

import interfaces.IGrow;
import main.BMPImages;
import main.Main;

public class Plant extends Item implements IGrow {
	
	protected long cycleTime;
	protected long startTime;
	
	public Plant() {
		
		cycleTime = 30000000000L;
		
		image = BMPImages.plant;
		
		startTime = System.nanoTime();
		
	}
	
	@Override
	public void grow(long time) {
		if(time - startTime >= cycleTime) {
		
			Main.realm.items.remove(this);
			
			Shrub c = new Shrub();
			c.coords = coords;
		
			Main.realm.items.add(c);
			
		}
	
	}

}
