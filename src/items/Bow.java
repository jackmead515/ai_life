package items;

import java.util.ArrayList;
import java.util.LinkedList;

import main.BMPImages;
import main.Main;

public class Bow extends Projectile {
	
	public LinkedList<Arrow> ammo;
	
	public Bow() {
		
		ammo = new LinkedList<Arrow>();
		image = BMPImages.bow;
		imageInHand = BMPImages.bow_in_hand;
		
	}

	@Override
	public void shoot(int direction) {
		if(ammo.size() > 0) {
			Arrow p = ammo.pop();
			p.isShot = true;
			p.direction = direction;
			p.coords = Main.player.coords;
			Main.realm.items.add(p);
		}
	}
	
	

}
