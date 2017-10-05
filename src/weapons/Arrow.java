package weapons;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;

import javax.swing.JPanel;

import entity.Deer;
import entity.Entity;
import items.Barrel;
import items.Chest;
import items.Crate;
import items.Fire;
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
	public void draw(Graphics2D g2, JPanel p) {
		int x = coords[0]*20;
		int y = coords[1]*20;
		
		if(direction == 1) {
			image = BMPImages.arrow_up;
		} else if(direction == 2) {
			image = BMPImages.arrow_down;
		} else if(direction == 3) {
			image = BMPImages.arrow_left;
		} else if(direction == 4) {
			image = BMPImages.arrow_right;
		}
		
		g2.drawImage(image, x, y, p);
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
				coords = new int[] {coords[0], coords[1]-1};
			} else if(direction == 2) {
				coords = new int[] {coords[0], coords[1]+1};
			} else if(direction == 3) {
				coords = new int[] {coords[0]-1, coords[1]};
			} else if(direction == 4) {
				coords = new int[] {coords[0]+1, coords[1]};
			}
			
			tileLife -= 1;
			
		}
	}
	
	@Override
	public boolean use(Entity e) {
		for(Item i : Main.realm.items) {
			if(e.coords[0] == i.coords[0] && e.coords[1] == i.coords[1]) {
				if(i instanceof Bow) {
					((Bow) i).ammo.add(this);
					Main.realm.items.remove(this);
					return false;
					
				}
			}
		}
		return true;
		
	}

}
