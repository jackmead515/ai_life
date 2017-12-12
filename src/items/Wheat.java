package items;

import java.util.Collection;
import java.util.Iterator;

import entity.Entity;
import floors.Floor;
import food.Bread;
import main.BMPImages;
import main.Main;
import main.SoundEffect;
import util.Coords;

public class Wheat extends Item {
	
	public Wheat() {
		
		image = BMPImages.wheat;
		
	}
	
	@Override
	public boolean use(Entity e) {
		Collection<Item> bucket = Main.realm.hmitems.get(e.coords);
		Iterator<Item> iter = bucket.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			if(!(i instanceof Floor)) {
				if(i instanceof Furnace) {
					Main.realm.remove(this);
					Bread a = new Bread();
					i.coords.set(e.coords);
					a.coords.set(e.coords);
					Main.realm.add(a);
					return false;
				}
			}
		}
		return true;
	}

}
