package weapons;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import entity.Entity;
import interfaces.IAnimate;
import items.Item;
import main.BMPImages;
import main.Main;

public class FireSpell extends Spell {
	
	protected long startTime;
	protected long animationDuration;
	public boolean isShot;
	
	private BufferedImage[][] animation;
	private int iIndex;
	
	public FireSpell() {
		startTime = System.nanoTime();
		animationDuration = 10000000L;
		image = BMPImages.fire_spell;
		imageInHand = BMPImages.person;
		iIndex = 0;
		
		animation = new BufferedImage[][] {
			{
				BMPImages.fire_spell_up_1, BMPImages.fire_spell_up_2, BMPImages.fire_spell_up_3
			},
			{
				BMPImages.fire_spell_down_1, BMPImages.fire_spell_down_2, BMPImages.fire_spell_down_3
			},
			{
				BMPImages.fire_spell_left_1, BMPImages.fire_spell_left_2, BMPImages.fire_spell_left_3
			},
			{
				BMPImages.fire_spell_right_1, BMPImages.fire_spell_right_2, BMPImages.fire_spell_right_3
			}
		};
		
		tileLife = 30;
		damage = 3;
		direction = 1;
	}
	
	@Override
	public void draw(Graphics2D g2, JPanel p) {
		int x = coords[0]*20;
		int y = coords[1]*20;
		
		if(direction == 1) {
			image = animation[0][iIndex];
			iIndex+=1;
		} else if(direction == 2) {
			image = animation[1][iIndex];
			iIndex+=1;
		} else if(direction == 3) {
			image = animation[2][iIndex];
			iIndex+=1;
		} else if(direction == 4) {
			image = animation[3][iIndex];
			iIndex+=1;
		}
		
		if(iIndex >= 2) {
			iIndex = 0;
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
				if(i instanceof Staff) {
					((Staff) i).ammo.add(this);
					Main.realm.items.remove(this);
					return false;
					
				}
			}
		}
		return true;
		
	}

}
