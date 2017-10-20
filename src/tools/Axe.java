package tools;

import java.util.Collection;
import java.util.Iterator;

import entity.Entity;
import floors.Floor;
import items.Item;
import items.Plant;
import items.Shrub;
import items.Stick;
import items.WheatSeed;
import items.Wood;
import main.BMPImages;
import main.Main;
import main.SoundEffect;

public class Axe extends Tool {

	public Axe() {
		health = 20;
		image = BMPImages.axe;
		imageInHand = BMPImages.axe_in_hand;
	}
	
	@Override
	public String description() {
		return "Axe (" + health + ")";
	}

	@Override
	public boolean use(Entity e) {
		
		Collection<Item> bucket = Main.realm.hmitems.get(e.coords);
		Iterator<Item> iter = bucket.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			if(!(i instanceof Floor)) {
				SoundEffect.AXE.play();
				if(i instanceof Wood) {
					health-=1;
					Stick s = new Stick();
					s.coords = i.coords;
					Main.realm.remove(i);
					Main.realm.add(s);
					break;
				} else if(i instanceof Plant || i instanceof Shrub) {
					health-=1;
					WheatSeed s = new WheatSeed();
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
