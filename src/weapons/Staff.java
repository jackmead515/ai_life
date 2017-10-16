package weapons;

import java.util.LinkedList;

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

public class Staff extends Weapon {
	
	public LinkedList<FireSpell> ammo;
	
	public Staff() {
		health = 50;
		ammo = new LinkedList<FireSpell>();
		image = BMPImages.staff;
		imageInHand = BMPImages.staff_in_hand;
		damage = 1;
	}
	
	@Override
	public String description() {
		return "Staff (" + health + ")(" + ammo.size() + ")";
	}
	
	@Override
	public boolean use(Entity e) {
		if(ammo.size() > 0) {
			health -= 1;
			FireSpell p = ammo.pop();
			p.isShot = true;
			if(p instanceof FireSpell) {
				SoundEffect.FIRE_SPELL.play();
			}
			
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
			e.projectiles.add(p);
		}
		
		if(health <= 0) {
			return false;
		} else {
			return true;
		}
	}

}
