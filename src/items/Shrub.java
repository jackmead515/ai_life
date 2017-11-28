package items;

import java.util.Collection;
import java.util.Iterator;

import entity.Entity;
import floors.Floor;
import interfaces.IGrow;
import main.BMPImages;
import main.Main;
import weapons.Bow;

public class Shrub extends Item {
	
	protected long cycleTime;
	protected long startTime;
	
	public Shrub() {
		
		cycleTime = 120000000000L;
		
		image = BMPImages.shrub;
		
		startTime = System.nanoTime();
		
	}
	
	@Override
	public boolean use(Entity e) {
		Collection<Item> bucket = Main.realm.hmitems.get(e.coords);
		Iterator<Item> iter = bucket.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			if(!(i instanceof Floor)) {
				if(i instanceof Shrub) {
					Stick a = new Stick();
					a.coords.set(i.coords);
					Main.realm.remove(i);
					Main.realm.remove(this);
					Main.realm.add(a);
					return false;
				}
			}
		}
		return true;
	}

}
