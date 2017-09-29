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
	
	public static void load() {
		
		URL i = BMPImages.class.getClassLoader().getResource("images/background.bmp");
		
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
