package tools;

import entity.Entity;
import items.CampFire;
import items.Item;
import items.Wood;
import main.BMPImages;
import main.Main;

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
		for(Item i : Main.realm.items) {
			if(e.coords[0] == i.coords[0] && e.coords[1] == i.coords[1]) {
				health-=1;
				if(i instanceof Wood) {
					CampFire s = new CampFire();
					s.coords = i.coords;
					Main.realm.items.remove(i);
					Main.realm.items.add(s);
					
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
