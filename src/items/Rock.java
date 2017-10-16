package items;

import entity.Entity;
import main.BMPImages;
import main.Main;
import tools.Sparker;

public class Rock extends Item {
	
	public Rock() {
		
		image = BMPImages.rock;
		
	}
	
	@Override
	public boolean use(Entity e) {
		for(Item i : Main.realm.items) {
			if(e.coords[0] == i.coords[0] && e.coords[1] == i.coords[1]) {
				if(i instanceof Stone) {
					
					Furnace a = new Furnace();
					a.coords = i.coords;
					Main.realm.items.remove(i);
					Main.realm.items.remove(this);
					Main.realm.items.add(a);
					return false;
					
				} else if(i instanceof IronTrinket) {
					
					Sparker a = new Sparker();
					a.coords = i.coords;
					Main.realm.items.remove(i);
					Main.realm.items.remove(this);
					Main.realm.items.add(a);
					return false;
					
				}
			}
		}
		return true;
	}

}
