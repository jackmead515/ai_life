package items;

import main.BMPImages;
import main.Main;

public class Shrub extends Plant implements IGrow {
	
	public Shrub() {
		
		elapsedTime = 0;
		cycleTime = 120000000000L;
		
		image = BMPImages.shrub;
		
	}
	
	@Override
	public void grow(long time) {
		
		elapsedTime += time;
		
		if(elapsedTime >= cycleTime) {
			
			elapsedTime = 0;
		
			Main.registry.items.remove(this);
			
			Wood c = new Wood();
			c.coords = coords;
		
			Main.registry.items.add(c);
			
		}
	
	}
	
	@Override
	public boolean useComponent(Component item) {
		if(item instanceof Shrub) {
			
			Stick a = new Stick();
			a.coords = item.coords;
			Main.registry.items.remove(item);
			Main.registry.items.add(a);
			return false;
			
		}
		
		return true;
	}

}
