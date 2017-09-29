package items;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import frame.GamePanel;
import main.BMPImages;
import main.Main;

public class Plant extends Item implements IGrow {
	
	protected long cycleTime;
	protected long elapsedTime;
	
	public Plant() {
		
		elapsedTime = 0;
		cycleTime = 60000000000L;
		
		image = BMPImages.plant;
		
	}
	
	@Override
	public void grow(long time) {
		
		elapsedTime += time;
		
		if(elapsedTime >= cycleTime) {
			
			elapsedTime = 0;
		
			Main.registry.items.remove(this);
			
			Shrub c = new Shrub();
			c.coords = coords;
		
			Main.registry.addItem(c);
			
		}
	
	}
	
	public boolean use() {
		return true;
	}

}
