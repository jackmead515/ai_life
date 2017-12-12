package tools;

import java.util.Collection;
import java.util.Iterator;

import entity.Entity;
import floors.Floor;
import items.CampFire;
import items.Iron;
import items.IronOre;
import items.Item;
import items.Rock;
import items.Stone;
import items.Wood;
import main.BMPImages;
import main.Main;
import main.SoundEffect;

public class Pickaxe extends Tool {
	
	public Pickaxe() {
		health = 20;
		image = BMPImages.pickaxe;
		imageInHand = BMPImages.pickaxe_in_hand;
	}
	
	@Override
	public String description() {
		return "Pickaxe (" + health + ")";
	}
	
	@Override
	public boolean use(Entity e) {
		Collection<Item> bucket = Main.realm.hmitems.get(e.coords);
		Iterator<Item> iter = bucket.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			if(!(i instanceof Floor)) {
				SoundEffect.PICKAXE.play();
				if(i instanceof Stone) {
					health-=1;
					Rock s = new Rock();
					s.coords.set(i.coords);
					Main.realm.remove(i);
					Main.realm.add(s);
					break;
				} else if(i instanceof IronOre) {
					health-=1;
					Iron s = new Iron();
					s.coords.set(i.coords);
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
