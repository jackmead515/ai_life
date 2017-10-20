package items;

import java.util.Collection;
import java.util.Iterator;

import entity.Entity;
import floors.Floor;
import food.Bread;
import main.BMPImages;
import main.Main;
import weapons.Bow;

public class Twine extends Item {
	
	public Twine() {
		
		image = BMPImages.twine;
		
	}
	
	@Override
	public boolean use(Entity e) {
		Collection<Item> bucket = Main.realm.hmitems.get(e.coords);
		Iterator<Item> iter = bucket.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			if(!(i instanceof Floor)) {
				if(i instanceof Stick) {
					Bow s = new Bow();
					s.coords.set(i.coords.x(), i.coords.y());
					Main.realm.remove(this);
					Main.realm.remove(i);
					Main.realm.add(s);
					return false;
				}
			}
		}
		return true;
	}

}
