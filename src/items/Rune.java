package items;

import java.util.Collection;
import java.util.Iterator;

import entity.Entity;
import floors.Floor;
import main.BMPImages;
import main.Main;

public class Rune extends Item {
	
	public Rune() {
		
		image = BMPImages.rune;
		
	}
	
	@Override
	public boolean use(Entity e) {
		Collection<Item> bucket = Main.realm.hmitems.get(e.coords);
		Iterator<Item> iter = bucket.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			if(!(i instanceof Floor)) {
				if(i instanceof ShallowWater) {
					WaterRune a = new WaterRune();
					a.coords.set(i.coords.x(), i.coords.y());
					Main.realm.remove(this);
					Main.realm.add(a);
					return false;
				} else if(i instanceof Rock) {
					EarthRune a = new EarthRune();
					a.coords.set(i.coords.x(), i.coords.y());
					Main.realm.remove(this);
					Main.realm.add(a);
					return false;
				} else if(i instanceof CampFire) {
					FireRune a = new FireRune();
					a.coords.set(i.coords.x(), i.coords.y());
					Main.realm.remove(this);
					Main.realm.add(a);
					return false;
				} else if(i instanceof Plant) {
					LifeRune a = new LifeRune();
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
