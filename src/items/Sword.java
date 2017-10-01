package items;

import entity.Deer;
import entity.Entity;
import main.BMPImages;
import main.Main;
import util.SoundEffect;

public class Sword extends Weapon {
	
	public Sword() {
		health = 20;
		image = BMPImages.sword;
		imageInHand = BMPImages.sword_in_hand;
		
	}
	
	@Override
	public boolean useEntity(Entity e) {
		
		SoundEffect.SWORD.play();
		e.health-=1;
		health-=1;
		
		if(e.health <= 0) {
			if(e instanceof Deer) {
				Venison a = new Venison();
				a.coords = e.coords;
				Main.realm.items.remove(e);
				Main.realm.items.add(a);
			}
		}
		
		if(health <= 0) {
			return false;
		} else {
			return true;
		}
		
	}

}
