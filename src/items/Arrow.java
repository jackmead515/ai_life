package items;

import entity.Entity;
import main.BMPImages;
import main.Main;

public class Arrow extends Ammo {
	
	protected long startTime;
	protected long animationDuration;
	public int direction;
	
	public Arrow() {
		startTime = System.nanoTime();
		animationDuration = 10000000L;
		image = BMPImages.arrow;
		imageInHand = BMPImages.person;
		tileLife = 20;
	}

	@Override
	public void animate(long time) {
		
		if(tileLife < 0) {
			Main.realm.items.remove(this);
			return;
		}
		
		if(time - startTime >= animationDuration && isShot) {
			
			startTime = time;
			
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
				if(i instanceof Entity) {
					if(((Entity) i).coords[0] == coords[0] && ((Entity) i).coords[1] == coords[1]) {
						((Entity) i).health -= 1;
						Main.realm.items.remove(this);
						if(((Entity) i).health <= 0) {
							Main.realm.items.remove(i);
						}
						return;
					}
				}
			}
			
			tileLife -= 1;
			
		}
	}
	
	@Override
	public boolean useWeapon(Weapon w) {
		if(w instanceof Bow) {
			Main.realm.items.remove(this);
			((Bow) w).ammo.add(this);
			return false;
		}
		
		return true;
	}

}
