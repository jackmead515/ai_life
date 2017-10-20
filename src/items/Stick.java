package items;

import java.util.Collection;
import java.util.Iterator;

import entity.Entity;
import floors.Floor;
import main.BMPImages;
import main.Main;
import tools.Axe;
import tools.Hammer;
import tools.Pickaxe;
import weapons.Bow;
import weapons.Sword;

public class Stick extends Item {
	
	public Stick() {
		
		image = BMPImages.stick;
		
	}
	
	@Override
	public boolean use(Entity e) {
		Collection<Item> bucket = Main.realm.hmitems.get(e.coords);
		Iterator<Item> iter = bucket.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			if(!(i instanceof Floor)) {
				if(i instanceof Wood) {
					Axe a = new Axe();
					a.coords.set(i.coords.x(), i.coords.y());
					Main.realm.remove(i);
					Main.realm.remove(this);
					Main.realm.add(a);
					return false;
				} else if(i instanceof Stone) {
					Pickaxe a = new Pickaxe();
					a.coords.set(i.coords.x(), i.coords.y());
					Main.realm.remove(i);
					Main.realm.remove(this);
					Main.realm.add(a);
					return false;
				} else if(i instanceof IronTrinket) {
					Sword a = new Sword();
					a.coords.set(i.coords.x(), i.coords.y());
					Main.realm.remove(i);
					Main.realm.remove(this);
					Main.realm.add(a);
					return false;
				} else if(i instanceof Rock) {
					Hammer a = new Hammer();
					a.coords.set(i.coords.x(), i.coords.y());
					Main.realm.remove(i);
					Main.realm.remove(this);
					Main.realm.add(a);
					return false;
				}
			}
		}
		return true;
	}

}
