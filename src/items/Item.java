package items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.Entity;
import frame.GamePanel;
import interfaces.IItem;
import main.Main;

public class Item implements IItem {
	
	public int[] coords;
	public BufferedImage image;
	public boolean canPickUp;
	
	public Item() {
		coords = new int[2];
		canPickUp = true;
	}
	
	public void draw(Graphics2D g2, GamePanel panel) {
		int x = coords[0];
		int y = coords[1];
		
		g2.drawImage(image, x*20, y*20, panel);
	}
	
	@Override
	public boolean use(Object item) {
		if(item instanceof Weapon) {
			return useWeapon((Weapon) item);
		} else if(item instanceof Component) {
			return useComponent((Component) item);
		} else if(item instanceof Tool) {
			return useTool((Tool) item);
		} else if(item instanceof Entity) {
			return useEntity((Entity) item);
		}
		return false;
	}
	
	protected boolean useEntity(Entity e) {
		return true;
	}
	
	protected boolean useWeapon(Weapon w) {
		return true;
	}
	
	protected boolean useComponent(Component c) {
		return true;
	}
	
	protected boolean useTool(Tool t) {
		return true;
	}
	
	public boolean place(int[] coords) {
		this.coords = coords;
		Main.realm.items.add(this);
		return true;
	}
	
}
