package items;

import entity.Entity;
import interfaces.IGrow;
import main.BMPImages;
import main.Main;

public class Shrub extends Item implements IGrow {
	
	protected long cycleTime;
	protected long startTime;
	
	public Shrub() {
		
		cycleTime = 120000000000L;
		
		image = BMPImages.shrub;
		
		startTime = System.nanoTime();
		
	}
	
	@Override
	public void grow(long time) {
		if(time - startTime >= cycleTime) {
		
			Main.realm.items.remove(this);
			
			Wood c = new Wood();
			c.coords = coords;
		
			Main.realm.items.add(c);
			
		}
	
	}
	
	@Override
	public boolean use(Entity e) {
		for(Item i : Main.realm.items) {
			if(e.coords[0] == i.coords[0] && e.coords[1] == i.coords[1]) {
				if(i instanceof Shrub) {
					Stick a = new Stick();
					a.coords = i.coords;
					Main.realm.items.remove(i);
					Main.realm.items.remove(this);
					Main.realm.items.add(a);
					return false;
				}
			
			}
		}
		return true;
	}

}
