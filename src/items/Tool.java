package items;

import java.awt.image.BufferedImage;

public class Tool extends Item implements ITool {
	
	public int[] coords;
	public BufferedImage image;
	public BufferedImage imageInHand;
	
	public int health;
	
	public Tool() {}

}
