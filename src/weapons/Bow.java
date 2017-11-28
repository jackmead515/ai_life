package weapons;

import java.util.ArrayList;
import java.util.LinkedList;

import entity.Entity;
import main.BMPImages;
import main.Main;
import main.SoundEffect;

public class Bow extends Weapon {
	
	public LinkedList<Arrow> ammo;
	
	public Bow() {
		health = 100;
		ammo = new LinkedList<Arrow>();
		image = BMPImages.bow;
		imageInHand = BMPImages.bow_in_hand;
	}
	
	@Override
	public String description() {
		return "Bow (" + health + ")(" + ammo.size() + ")";
	}

	@Override
	public boolean use(Entity e) {
		if(ammo.size() > 0) {
			Arrow p = ammo.pop();
			
			if(e.pointingDown) {
				p.direction = 2;
			} else if (e.pointingUp) {
				p.direction = 1;
			} else if (e.pointingLeft) {
				p.direction = 3;
			} else if(e.pointingRight) {
				p.direction = 4;
			} else {
				ammo.add(p);
				return false;
			}
			SoundEffect.BOW.play();
			health -= 1;
			p.isShot = true;
			p.coords.set(coords);
			e.projectiles.add(p);
		}
		
		if(health <= 0) {
			return false;
		} else {
			return true;
		}
		
	}
	
	

}
