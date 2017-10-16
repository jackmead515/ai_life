package items;

import entity.Entity;
import main.BMPImages;
import main.Main;
import tools.Axe;
import tools.Hammer;
import tools.Pickaxe;
import weapons.Sword;

public class Stick extends Item {
	
	public Stick() {
		
		image = BMPImages.stick;
		
	}
	
	@Override
	public boolean use(Entity e) {
		for(Item i : Main.realm.items) {
			if(e.coords[0] == i.coords[0] && e.coords[1] == i.coords[1]) {
				if(i instanceof Wood) {
					
					Axe a = new Axe();
					a.coords = i.coords;
					Main.realm.items.remove(i);
					Main.realm.items.remove(this);
					Main.realm.items.add(a);
					return false;
					
				} else if(i instanceof Stone) {
					
					Pickaxe a = new Pickaxe();
					a.coords = i.coords;
					Main.realm.items.remove(i);
					Main.realm.items.remove(this);
					Main.realm.items.add(a);
					return false;
					
				} else if(i instanceof IronTrinket) {
					
					Sword a = new Sword();
					a.coords = i.coords;
					Main.realm.items.remove(i);
					Main.realm.items.remove(this);
					Main.realm.items.add(a);
					return false;
					
				} else if(i instanceof Rock) {
					
					Hammer a = new Hammer();
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
