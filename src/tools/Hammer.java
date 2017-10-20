package tools;

import java.util.Collection;
import java.util.Iterator;

import entity.Entity;
import floors.Floor;
import items.Iron;
import items.IronOre;
import items.IronTrinket;
import items.Item;
import items.MoltenIron;
import items.Rock;
import items.Stone;
import main.BMPImages;
import main.Main;
import main.SoundEffect;

public class Hammer extends Tool {
	
	public Hammer() {
		health = 20;
		image = BMPImages.hammer;
		imageInHand = BMPImages.hammer_in_hand;
	}
	
	@Override
	public boolean use(Entity e) {
		Collection<Item> bucket = Main.realm.hmitems.get(e.coords);
		Iterator<Item> iter = bucket.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			if(!(i instanceof Floor)) {
				SoundEffect.HAMMER.play();
				if(i instanceof MoltenIron) {
					health -= 1;
					IronTrinket a = new IronTrinket();
					a.coords.set(i.coords.x(), i.coords.y());
					Main.realm.remove(i);
					Main.realm.add(a);
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
