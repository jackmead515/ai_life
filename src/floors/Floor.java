package floors;

import java.awt.image.BufferedImage;

import items.Item;

public class Floor extends Item {
		
		public int[] coords;
		public BufferedImage image;
		
		public Floor() {
			coords = new int[2];
			canPickUp = false;
		}

}
