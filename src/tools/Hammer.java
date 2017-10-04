package tools;

import entity.Entity;
import items.IronTrinket;
import items.Item;
import items.MoltenIron;
import main.BMPImages;
import main.Main;

public class Hammer extends Tool {
	
	public Hammer() {
		health = 20;
		image = BMPImages.hammer;
		imageInHand = BMPImages.hammer_in_hand;
	}
	
	@Override
	public boolean use(Entity e) {
		for(Item i : Main.realm.items) {
			if(e.coords[0] == i.coords[0] && e.coords[1] == i.coords[1]) {
				health -= 1;
				if(i instanceof MoltenIron) {
					IronTrinket a = new IronTrinket();
					a.coords = i.coords;
					Main.realm.items.remove(i);
					Main.realm.items.add(a);
					
				}
				
				if(health <= 0) {
					return false;
				} else {
					return true;
				}
			
			}
		}
		return true;
	}

}
