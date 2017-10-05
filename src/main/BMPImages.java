package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
	
	public static void load() {
		
		URL i = BMPImages.class.getClassLoader().getResource("images/pickaxe.bmp");
		
		try {
			pickaxe = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/arrow_up.bmp");
		
		try {
			arrow_up = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/arrow_down.bmp");
		
		try {
			arrow_down = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/arrow_left.bmp");
		
		try {
			arrow_left = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/arrow_right.bmp");
		
		try {
			arrow_right = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/arrow_bundle.bmp");
		
		try {
			arrow_bundle = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/chest.bmp");
		
		try {
			chest = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/barrel.bmp");
		
		try {
			barrel = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/crate.bmp");
		
		try {
			crate = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/raw_venison.bmp");
		
		try {
			raw_venison = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		i = BMPImages.class.getClassLoader().getResource("images/bow.bmp");
		
		try {
			bow = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/bow_in_hand.bmp");
		
		try {
			bow_in_hand = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/wheat_seed.bmp");
		
		try {
			wheat_seed = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/wheat.bmp");
		
		try {
			wheat = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/bread.bmp");
		
		try {
			bread = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/wolf.bmp");
		
		try {
			wolf = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/sparker.bmp");
		
		try {
			sparker = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/sparker_in_hand.bmp");
		
		try {
			sparker_in_hand = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/fire_1.bmp");
		
		try {
			fire_1 = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/fire_2.bmp");
		
		try {
			fire_2 = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/fire_3.bmp");
		
		try {
			fire_3 = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/shallow_water_3.bmp");
		
		try {
			shallow_water_3 = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/shallow_water_2.bmp");
		
		try {
			shallow_water_2 = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/shallow_water_1.bmp");
		
		try {
			shallow_water_1 = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/water_3.bmp");
		
		try {
			water_3 = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/water_2.bmp");
		
		try {
			water_2 = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/water_1.bmp");
		
		try {
			water_1 = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/venison.bmp");
		
		try {
			venison = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/iron_trinket.bmp");
		
		try {
			iron_trinket = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/furnace.bmp");
		
		try {
			furnace = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/hammer_in_hand.bmp");
		
		try {
			hammer_in_hand = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/hammer.bmp");
		
		try {
			hammer = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/molten_iron.bmp");
		
		try {
			molten_iron = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/iron.bmp");
		
		try {
			iron = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/iron_ore.bmp");
		
		try {
			iron_ore = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/deer.bmp");
		
		try {
			deer = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/rock.bmp");
		
		try {
			rock = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		i = BMPImages.class.getClassLoader().getResource("images/stone.bmp");
		
		try {
			stone = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/sword_in_hand.bmp");
		
		try {
			sword_in_hand = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/sword.bmp");
		
		try {
			sword = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/pickaxe_in_hand.bmp");
		
		try {
			pickaxe_in_hand = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/background.bmp");
		
		try {
			background = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/person.bmp");
		
		try {
			person = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/wood.bmp");
		
		try {
			wood = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/plant.bmp");
		
		try {
			plant = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/axe.bmp");
		
		try {
			axe = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/axe_in_hand.bmp");
		
		try {
			axe_in_hand = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/shrub.bmp");
		
		try {
			shrub = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		i = BMPImages.class.getClassLoader().getResource("images/stick.bmp");
		
		try {
			stick = ImageIO.read(new File(i.toString().substring(6)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
