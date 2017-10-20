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
	
	public FireSpell() {
		startTime = System.nanoTime();
		image = BMPImages.fire_spell;
		
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
	}

}
