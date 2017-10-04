package items;

import entity.Entity;
import main.BMPImages;
import main.Main;

public class Iron extends Item {

	public Iron() {
		
		image = BMPImages.iron;
		
	}
	
	@Override
	public boolean use(Entity e) {
		for(Item i : Main.realm.items) {
			if(e.coords[0] == i.coords[0] && e.coords[1] == i.coords[1]) {
				
				if(i instanceof Furnace) {
					MoltenIron a = new MoltenIron();
					a.coords = i.coords;
					Main.realm.items.remove(this);
					Main.realm.items.add(a);
					return false;
				}
			
			}
		}
		return true;
	}
	
}
