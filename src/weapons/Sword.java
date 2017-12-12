package weapons;

import java.util.Collection;
import java.util.Iterator;

import entity.Deer;
import entity.Entity;
import floors.Floor;
import items.Barrel;
import items.Chest;
import items.Container;
import items.Crate;
import items.Item;
import items.Plant;
import items.RawVenison;
import items.Shrub;
import items.Stick;
import items.WheatSeed;
import items.Wood;
import main.BMPImages;
import main.Main;
import main.SoundEffect;

public class Sword extends Weapon {
	
	public Sword() {
		health = 100;
		image = BMPImages.sword;
		imageInHand = BMPImages.sword_in_hand;
		damage = 3;
	}
	
	@Override
	public String description() {
		return "Sword (" + health + ")";
	}
	
	@Override
	public boolean use(Entity e) {
		Collection<Item> bucket = Main.realm.hmitems.get(e.coords);
		Iterator<Item> iter = bucket.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			if(!(i instanceof Floor)) {
				SoundEffect.SWORD.play();
				if(i instanceof Entity) {
					health -= 1;
					((Entity) i).health-=damage;
					if(((Entity) i).health <= 0) {
						Item a = i.drop();
						if(a != null) {
							a.coords.set(e.coords);
							Main.realm.add(a);
							Main.realm.remove(i);
						}
						Main.realm.remove(i);
					}
					break;
				} else if(i instanceof Container) {
					health -= 1;
					Item p = i.drop();
					p.coords.set(e.coords);
					Main.realm.add(p);
					Main.realm.remove(i);
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
