package weapons;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JPanel;

import entity.Entity;
import floors.Floor;
import interfaces.IAnimate;
import items.Item;
import items.Plant;
import items.Shrub;
import items.Stick;
import items.WheatSeed;
import items.Wood;
import main.BMPImages;
import main.Main;
import main.SoundEffect;

public class FireSpell extends Spell {
	
	protected long startTime;
	protected long animationDuration;
	public boolean isShot;
	
	private BufferedImage[][] animation;
	private int iIndex;
	
	public FireSpell() {
		startTime = System.nanoTime();
		animationDuration = 50000000L;
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
				image = animation[0][iIndex];
				iIndex+=1;
			} else if(direction == 2) {
				coords.set(coords.x(), coords.y()+1);
				image = animation[1][iIndex];
				iIndex+=1;
			} else if(direction == 3) {
				coords.set(coords.x()-1, coords.y());
				image = animation[2][iIndex];
				iIndex+=1;
			} else if(direction == 4) {
				coords.set(coords.x()+1, coords.y());
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
