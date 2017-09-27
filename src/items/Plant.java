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
	public int id() {
		return 1;
	}
	
	@Override
	public void grow(long time) {
		
		elapsedTime += time;
		
		if(elapsedTime >= cycleTime) {
			
			elapsedTime = 0;
		
			Main.registry.items.remove(this);
			
			Wood c = new Wood();
			c.coords = coords;
		
			Main.registry.addItem(c);
			
		}
	
	}
	
	@Override
	public void place() {
		
		LinkedList<Item> items = Main.registry.items;
		
		for(Item i : items) {
			int xi = i.coords[0];
			int yi = i.coords[1];
			
			if(coords[0] == xi && coords[1] == yi && i instanceof Plant) {
				
				Main.registry.items.remove(i);
				
				Wood w = new Wood();
				w.coords = coords;
				Main.registry.items.add(w);
				
				break;
				
			}
		}
		
		Main.registry.addItem(this);
		
	}
	
	@Override
	public void draw(Graphics2D g2, GamePanel panel) {
		int x = coords[0];
		int y = coords[1];
		
		g2.drawImage(image, x*20, y*20, panel);
	}

}
