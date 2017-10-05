package weapons;

import java.util.ArrayList;
import java.util.LinkedList;

import entity.Entity;
import main.BMPImages;
import main.Main;

public class Bow extends Weapon {
	
	public LinkedList<Arrow> ammo;
	
	public Bow() {
		
		ammo = new LinkedList<Arrow>();
		image = BMPImages.bow;
		imageInHand = BMPImages.bow_in_hand;
		
	}
	
	@Override
	public String description() {
		return "Bow (" + ammo.size() + ")";
	}

	@Override
	public boolean use(Entity e) {
		if(ammo.size() > 0) {
			Arrow p = ammo.pop();
			p.isShot = true;
			if(e.pointingDown) {
				p.direction = 2;
			} else if (e.pointingUp) {
				p.direction = 1;
			} else if (e.pointingLeft) {
				p.direction = 3;
			} else if(e.pointingRight) {
				p.direction = 4;
			}
			p.coords = e.coords;
			e.projs.add(p);
		}
		
		return true;
	}
	
	

}
