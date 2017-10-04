package boundaries;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import frame.GamePanel;
import items.Item;

public class Boundary extends Item {
	
	public int[] coords;
	public BufferedImage image;
	
	public Boundary() {
		coords = new int[2];
		canPickUp = false;
		canWalkOver = false;
	}

}
