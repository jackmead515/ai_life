package weapons;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

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
		image = BMPImages.arrow;
		imageInHand = BMPImages.person;
		tileLife = 20;
		damage = 1;
		direction = 1;
	}
	
	@Override
	public void draw(Graphics2D g2, JPanel p) {
		int x = coords[0]*20;
		int y = coords[1]*20;
		
		AffineTransform at = new AffineTransform();
		
		if(direction == 2) {
			at.rotate(Math.PI);
			at.translate(-image.getWidth()/2, -image.getHeight()/2);
		} else if(direction == 3) {
			at.rotate(-Math.PI/2);
			at.translate(-image.getWidth()/2, -image.getHeight()/2);
		} else if(direction == 4) {
			at.rotate(Math.PI/2);
			at.translate(-image.getWidth()/2, -image.getHeight()/2);
		}
		
		
		g2.drawImage(image, at, p);
	}

	@Override
	public void animate(long time) {
		if(tileLife < 0) {
			//Main.realm.items.remove(this);
			isShot = false;
			tileLife = 20;
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
			
			for(Item i : Main.realm.items) {
				if(coords[0] == i.coords[0] && coords[1] == i.coords[1]) {
					if(i instanceof Deer) {
						((Entity) i).health-=damage;
						Main.realm.items.remove(this);
						if(((Entity) i).health <= 0) {
							RawVenison a = new RawVenison();
							a.coords = i.coords;
							Main.realm.items.add(a);
							Main.realm.items.remove(i);
						}
						
					} else if(i instanceof Crate) {
						Item p = Crate.generate();
						p.coords = i.coords;
						Main.realm.items.add(p);
						Main.realm.items.remove(i);
						Main.realm.items.remove(this);
						
					} else if(i instanceof Barrel) {
						Item p = Barrel.generate();
						p.coords = i.coords;
						Main.realm.items.add(p);
						Main.realm.items.remove(i);
						Main.realm.items.remove(this);
						
					} else if(i instanceof Chest) {
						Item p = Chest.generate();
						p.coords = i.coords;
						Main.realm.items.add(p);
						Main.realm.items.remove(i);
						Main.realm.items.remove(this);
						
					}
				}
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
