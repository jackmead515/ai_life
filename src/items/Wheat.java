package items;

import java.util.Collection;
import java.util.Iterator;

import entity.Entity;
import floors.Floor;
import food.Bread;
import main.BMPImages;
import main.Main;
import main.SoundEffect;

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
					Bread a = new Bread();
					a.coords = i.coords;
					Main.realm.remove(this);
					Main.realm.add(a);
					return false;
				}
			}
		}
		return true;
	}

}
