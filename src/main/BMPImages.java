package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;

import javax.imageio.ImageIO;

import frame.GamePanel;

public class BMPImages {
	
	public static BufferedImage background;
	public static BufferedImage person;
	public static BufferedImage wood;
	public static BufferedImage plant;
	public static BufferedImage axe;
	public static BufferedImage axe_in_hand;
	public static BufferedImage shrub;
	public static BufferedImage stick;
	public static BufferedImage pickaxe;
	public static BufferedImage pickaxe_in_hand;
	public static BufferedImage sword;
	public static BufferedImage sword_in_hand;
	public static BufferedImage stone;
	public static BufferedImage rock;
	public static BufferedImage deer;
	public static BufferedImage iron_ore;
	public static BufferedImage iron;
	public static BufferedImage molten_iron;
	public static BufferedImage hammer;
	public static BufferedImage hammer_in_hand;
	public static BufferedImage furnace;
	public static BufferedImage iron_trinket;
	public static BufferedImage venison;
	public static BufferedImage water_1;
	public static BufferedImage water_2;
	public static BufferedImage water_3;
	public static BufferedImage shallow_water_1;
	public static BufferedImage shallow_water_2;
	public static BufferedImage shallow_water_3;
	public static BufferedImage sparker;
	public static BufferedImage sparker_in_hand;
	public static BufferedImage fire_1;
	public static BufferedImage fire_2;
	public static BufferedImage fire_3;
	public static BufferedImage wolf;
	public static BufferedImage wheat_seed;
	public static BufferedImage wheat;
	public static BufferedImage bread;
	public static BufferedImage chest;
	public static BufferedImage raw_venison;
	public static BufferedImage crate;
	public static BufferedImage bow;
	public static BufferedImage bow_in_hand;
	public static BufferedImage barrel;
	public static BufferedImage arrow_up;
	public static BufferedImage arrow_down;
	public static BufferedImage arrow_left;
	public static BufferedImage arrow_right;
	public static BufferedImage arrow_bundle;
	
	public static BufferedImage twine;
	public static BufferedImage staff;
	public static BufferedImage staff_in_hand;
	public static BufferedImage fire_spell_up_1;
	public static BufferedImage fire_spell_up_2;
	public static BufferedImage fire_spell_up_3;
	public static BufferedImage fire_spell_down_1;
	public static BufferedImage fire_spell_down_2;
	public static BufferedImage fire_spell_down_3;
	public static BufferedImage fire_spell_left_1;
	public static BufferedImage fire_spell_left_2;
	public static BufferedImage fire_spell_left_3;
	public static BufferedImage fire_spell_right_1;
	public static BufferedImage fire_spell_right_2;
	public static BufferedImage fire_spell_right_3;
	public static BufferedImage fire_spell;
	public static BufferedImage gray_brick;
	public static BufferedImage dirt;
	public static BufferedImage gray_brick_floor;
	public static BufferedImage anvil;
	public static BufferedImage ash;
	public static BufferedImage rune;
	public static BufferedImage spell_table;
	
	public static void load() {
		
		
		Field[] fields = BMPImages.class.getFields();
		
		URL i = null;
		
		for(Field field : fields) {
			i = BMPImages.class.getClassLoader().getResource("images/" + field.getName() + ".bmp");
			
			try {
				field.set(BMPImages.class, ImageIO.read(new File(i.toString().substring(6))));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
