package items;

import java.util.Collection;
import java.util.Iterator;

import entity.Entity;
import floors.Floor;
import food.Venison;
import main.BMPImages;
import main.Main;

public class Iron extends Item {

	public Iron() {
		
		image = BMPImages.iron;
		
	}
	
	@Override
	public boolean use(Entity e) {
		Collection<Item> bucket = Main.realm.hmitems.get(e.coords);
		Iterator<Item> iter = bucket.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			if(!(i instanceof Floor)) {
				if(i instanceof Furnace) {
					MoltenIron a = new MoltenIron();
					a.coords.set(i.coords.x(), i.coords.y());
					Main.realm.remove(this);
					Main.realm.add(a);
					return false;
				}
			}
		}
		return true;
	}
	
}
