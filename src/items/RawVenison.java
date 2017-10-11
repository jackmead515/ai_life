package items;

import entity.Entity;
import food.Venison;
import main.BMPImages;
import main.Main;

public class RawVenison extends Item {
	
	public RawVenison() {
		image = BMPImages.raw_venison;
	}
	
	@Override
	public boolean use(Entity e) {
		for(Item i : Main.realm.items) {
			if(e.coords[0] == i.coords[0] && e.coords[1] == i.coords[1]) {
				if(i instanceof CampFire || i instanceof Furnace) {
					Venison a = new Venison();
					a.coords = e.coords;
					Main.realm.items.remove(this);
					Main.realm.items.add(a);
					return false;
				}
			
			}
		}
		return true;
	}
	
	

}
