package tools;

import entity.Entity;
import items.Iron;
import items.IronOre;
import items.Item;
import items.Rock;
import items.Stone;
import main.BMPImages;
import main.Main;
import util.SoundEffect;

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
		for(Item i : Main.realm.items) {
			if(e.coords[0] == i.coords[0] && e.coords[1] == i.coords[1]) {
				SoundEffect.PICKAXE.play();
				health-=1;
				if(i instanceof Stone) {
					Rock s = new Rock();
					s.coords = i.coords;
					Main.realm.items.remove(i);
					Main.realm.items.add(s);
					
				} else if(i instanceof IronOre) {
					Iron s = new Iron();
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
