package items;

import entity.Entity;
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
		for(Item i : Main.realm.items) {
			if(e.coords[0] == i.coords[0] && e.coords[1] == i.coords[1]) {
				if(i instanceof Bow) {
					for(int x = 0; x < amount; x++) {
						((Bow) i).ammo.add(new Arrow());
					}
					Main.realm.items.remove(this);
					return false;
					
				}
			}
		}
		return true;
	}

}
