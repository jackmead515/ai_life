package items;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import frame.GamePanel;
import main.BMPImages;

public class Wood extends Item {
	
	public Wood() {
		
		image = BMPImages.wood;
		
	}
	
	@Override
	public int id() {
		return 2;
	}
	
	@Override
	public void draw(Graphics2D g2, GamePanel panel) {
		int x = coords[0];
		int y = coords[1];
		
		g2.drawImage(image, x*20, y*20, panel);
	}

}
