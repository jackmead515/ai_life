package weapons;

import entity.Deer;
import entity.Entity;
import items.Barrel;
import items.Chest;
import items.Crate;
import items.Item;
import items.RawVenison;
import main.BMPImages;
import main.Main;
import main.SoundEffect;

public class Sword extends Weapon {
	
	public Sword() {
		health = 20;
		image = BMPImages.sword;
		imageInHand = BMPImages.sword_in_hand;
		damage = 1;
	}
	
	@Override
	public String description() {
		return "Sword (" + health + ")";
	}
	
	@Override
	public boolean use(Entity e) {
		for(Item i : Main.realm.items) {
			if(e.coords[0] == i.coords[0] && e.coords[1] == i.coords[1]) {
				SoundEffect.SWORD.play();
				health -= 1;
				if(i instanceof Deer) {
					((Entity) i).health-=damage;
					if(((Entity) i).health <= 0) {
						RawVenison a = new RawVenison();
						a.coords = i.coords;
						Main.realm.items.add(a);
						Main.realm.items.remove(i);
					}
				} else if(i instanceof Crate) {
					Item p = Crate.generate();
					p.coords = e.coords;
					Main.realm.items.add(p);
					Main.realm.items.remove(i);
				} else if(i instanceof Barrel) {
					Item p = Barrel.generate();
					p.coords = e.coords;
					Main.realm.items.add(p);
					Main.realm.items.remove(i);
				} else if(i instanceof Chest) {
					Item p = Chest.generate();
					p.coords = e.coords;
					Main.realm.items.add(p);
					Main.realm.items.remove(i);
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
