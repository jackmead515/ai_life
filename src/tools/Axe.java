package tools;

import entity.Entity;
import items.Item;
import items.Plant;
import items.Stick;
import items.WheatSeed;
import items.Wood;
import main.BMPImages;
import main.Main;

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
		for(Item i : Main.realm.items) {
			if(e.coords[0] == i.coords[0] && e.coords[1] == i.coords[1]) {
				health-=1;
				if(i instanceof Wood) {
					Stick s = new Stick();
					s.coords = i.coords;
					Main.realm.items.remove(i);
					Main.realm.items.add(s);
					
				} else if(i instanceof Plant) {
					WheatSeed s = new WheatSeed();
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
