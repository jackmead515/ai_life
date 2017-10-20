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
	
	public LinkedList<Spell> ammo;
	
	public Staff() {
		health = 50;
		ammo = new LinkedList<Spell>();
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
			
			Spell p = ammo.pop();
			
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
			health -= 1;
			p.isShot = true;
			if(p instanceof FireSpell) {
				SoundEffect.FIRE_SPELL.play();
			}
			p.coords.set(e.coords.x(), e.coords.y());
			e.projectiles.add(p);
		}
		
		if(health <= 0) {
			return false;
		} else {
			return true;
		}
	}

}
