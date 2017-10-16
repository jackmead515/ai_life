package boundaries;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import frame.GamePanel;
import items.Item;

public class Boundary extends Item {
	
	public Boundary() {
		canPickUp = false;
		canWalkOver = false;
	}

}
