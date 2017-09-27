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
		
	}

}
