package items;

import java.util.Collection;
import java.util.Iterator;

import entity.Entity;
import floors.Floor;
import food.Venison;
import main.BMPImages;
import main.Main;
import weapons.Arrow;
import weapons.Bow;

public class ArrowBundle extends Item {
	
	public int amount;
	
	public ArrowBundle() {
		
		amount = 5;
		image = BMPImages.arrow_bundle;
		
	}
	
	@Override
	public String description() {
		return "Arrows (" + amount + ")";
	}
	
	@Override
	public boolean use(Entity e) {
		Collection<Item> bucket = Main.realm.hmitems.get(e.coords);
		Iterator<Item> iter = bucket.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			if(!(i instanceof Floor)) {
				if(i instanceof Bow) {
					Main.realm.remove(this);
					for(int x = 0; x < amount; x++) {
						((Bow) i).ammo.add(new Arrow());
					}
					return false;
				}
			}
		}
		return true;
	}

}
