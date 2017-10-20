package items;

import java.util.Collection;
import java.util.Iterator;

import entity.Entity;
import entity.Player;
import floors.Floor;
import main.Main;
import weapons.WaterSpell;

public class WaterRune extends Rune {
	
	public WaterRune() {
		
		//image = BMPImages.water_rune;
		
	}
	
	@Override
	public boolean use(Entity e) {
		Collection<Item> bucket = Main.realm.hmitems.get(e.coords);
		Iterator<Item> iter = bucket.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			if(!(i instanceof Floor)) {
				if(i instanceof SpellTable) {
					WaterSpell a = new WaterSpell();
					Main.realm.remove(this);
					if(e instanceof Player) {
						((Player) e).inHand = a;
						return true;
					} else {
						a.coords.set(e.coords);
						Main.realm.add(a);
						return false;
					}
				}
			}
		}
		return true;
	}

}
