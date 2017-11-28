package items;

import java.util.Collection;
import java.util.Iterator;

import entity.Entity;
import floors.Floor;
import food.Venison;
import main.BMPImages;
import main.Main;
import tools.Sparker;

public class RawVenison extends Item {
	
	public RawVenison() {
		image = BMPImages.raw_venison;
	}
	
	@Override
	public boolean use(Entity e) {
		Collection<Item> bucket = Main.realm.hmitems.get(e.coords);
		Iterator<Item> iter = bucket.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			if(!(i instanceof Floor)) {
				if(i instanceof CampFire || i instanceof Furnace) {
					Venison a = new Venison();
					Main.realm.remove(this);
					a.coords.set(i.coords);
					Main.realm.add(a);
					return false;
				}
			}
		}
		return true;
	}
	
	

}
