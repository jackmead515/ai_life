package weapons;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;

import entity.Deer;
import entity.Entity;
import items.Barrel;
import items.Chest;
import items.Crate;
import items.CampFire;
import items.Item;
import items.RawVenison;
import items.Wood;
import main.BMPImages;
import main.Main;

public class Arrow extends Projectile {
	
	protected long startTime;
	protected long animationDuration;
	public boolean isShot;
	
	public Arrow() {
		startTime = System.nanoTime();
		animationDuration = 10000000L;
		image = BMPImages.arrow_up;
		imageInHand = BMPImages.person;
		tileLife = 20;
		damage = 1;
		direction = 1;
	}

	@Override
	public void animate(long time) {
		if(tileLife < 0) {
			return;
		}
		
		if(time - startTime >= animationDuration && isShot) {
			
			startTime = time;
			
			/*
			 * Direction: 1 for up, 2 for down, 3 for left, and 4 for right.
			 */
			if(direction == 1) {
				coords.set(coords.x(), coords.y()-1);
				image = BMPImages.arrow_up;
			} else if(direction == 2) {
				coords.set(coords.x(), coords.y()+1);
				image = BMPImages.arrow_down;
			} else if(direction == 3) {
				coords.set(coords.x()-1, coords.y());
				image = BMPImages.arrow_left;
			} else if(direction == 4) {
				coords.set(coords.x()+1, coords.y());
				image = BMPImages.arrow_right;
			}
			
			tileLife -= 1;
			
		}
	}
	
	@Override
	public boolean use(Entity e) {
		Collection<Item> bucket = Main.realm.hmitems.get(e.coords);
		Iterator<Item> iter = bucket.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			if(i instanceof Bow) {
				((Bow) i).ammo.add(this);
				Main.realm.remove(this);
				return false;
			}
		}
		
		return true;
		
	}

}
