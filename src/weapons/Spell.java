package weapons;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Iterator;

import entity.Entity;
import items.Item;
import main.BMPImages;
import main.Main;
import util.Coords;

public class Spell extends Projectile {
	
	protected long startTime;
	protected long animationDuration;
	public boolean isShot;
	
	protected BufferedImage[][] animation;
	private int iIndex;
	
	public Spell() {
		startTime = System.nanoTime();
		animationDuration = 50000000L;
		imageInHand = BMPImages.person;
		iIndex = 0;
		
		tileLife = 30;
		damage = 3;
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
				coords.set(coords.x, coords.y-1);
				image = animation[0][iIndex];
				iIndex+=1;
			} else if(direction == 2) {
				coords.set(coords.x, coords.y+1);
				image = animation[1][iIndex];
				iIndex+=1;
			} else if(direction == 3) {
				coords.set(coords.x-1, coords.y);
				image = animation[2][iIndex];
				iIndex+=1;
			} else if(direction == 4) {
				coords.set(coords.x+1, coords.y);
				image = animation[3][iIndex];
				iIndex+=1;
			}
			
			if(iIndex >= 2) {
				iIndex = 0;
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
			if(i instanceof Staff) {
				((Staff) i).ammo.add(this);
				Main.realm.remove(this);
				return false;
			}
		}
		
		return true;
		
	}
	
}
