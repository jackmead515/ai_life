package items;

import entity.Entity;
import main.BMPImages;
import main.Main;
import weapons.Bow;

public class Twine extends Item {
	
	public Twine() {
		
		image = BMPImages.twine;
		
	}
	
	@Override
	public boolean use(Entity e) {
		for(Item i : Main.realm.items) {
			if(e.coords[0] == i.coords[0] && e.coords[1] == i.coords[1]) {
				if(i instanceof Stick) {
					Bow s = new Bow();
					s.coords = i.coords;
					Main.realm.items.remove(this);
					Main.realm.items.remove(i);
					Main.realm.items.add(s);
					return false;
				}
			}
		}
		return true;
	}

}
