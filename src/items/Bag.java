package items;

import java.util.LinkedList;

import entity.Entity;
import main.BMPImages;
import main.Main;

public class Bag extends Item {
	
	protected LinkedList<Item> inventory;
	protected int capacity;
	
	public Bag() {
		
		capacity = 10;
		inventory = new LinkedList<Item>();
		image = BMPImages.bag;
		
	}
	
	@Override
	public boolean use(Entity e) {
		for(Item i : Main.realm.items) {
			if(e.coords[0] != i.coords[0] && e.coords[1] != i.coords[1] && inventory.size() > 0) {
				
				Item p = inventory.pop();
				p.coords = e.coords;
				Main.realm.items.add(p);
				return true;
				
			} else if(e.coords[0] == i.coords[0] && e.coords[1] == i.coords[1] && 
					  inventory.size() < capacity && i.canPickUp) {
				
				Main.realm.items.remove(i);
				inventory.add(i);
				return true;
				
			}
		}
		return true;
	}

}
