package tools;

import java.util.Collection;
import java.util.Iterator;

import entity.Entity;
import floors.Floor;
import items.CampFire;
import items.Item;
import items.Wood;
import main.BMPImages;
import main.Main;
import weapons.Bow;

public class Sparker extends Tool {
	
	public Sparker() {
		
		health = 20;
		image = BMPImages.sparker;
		imageInHand = BMPImages.sparker_in_hand;
		
	}
	
	@Override
	public String description() {
		return "Sparker (" + health + ")";
	}
	
	@Override
	public boolean use(Entity e) {
		Collection<Item> bucket = Main.realm.hmitems.get(e.coords);
		Iterator<Item> iter = bucket.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			if(!(i instanceof Floor)) {
				if(i instanceof Wood) {
					health-=1;
					CampFire s = new CampFire();
					s.coords = i.coords;
					Main.realm.remove(i);
					Main.realm.add(s);
					break;
				}
			}
		}
		
		if(health <= 0) {
			return false;
		} else {
			return true;
		}
	}

}
