package items;

import java.util.Collection;
import java.util.Iterator;

import entity.Entity;
import floors.Floor;
import main.BMPImages;
import main.Main;
import tools.Sparker;

public class Rock extends Item {
	
	public Rock() {
		
		image = BMPImages.rock;
		
	}
	
	@Override
	public boolean use(Entity e) {
		Collection<Item> bucket = Main.realm.hmitems.get(e.coords);
		Iterator<Item> iter = bucket.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			if(!(i instanceof Floor)) {
				if(i instanceof Stone) {
					Furnace a = new Furnace();
					a.coords = i.coords;
					Main.realm.remove(i);
					Main.realm.remove(this);
					Main.realm.add(a);
					return false;
				} else if(i instanceof IronTrinket) {
					Sparker a = new Sparker();
					a.coords = i.coords;
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
