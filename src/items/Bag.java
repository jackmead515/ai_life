package items;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import entity.Entity;
import floors.Floor;
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
	public String description() {
		return "Bag (" + inventory.size() + "/" + capacity + ")";
	}
	
	@Override
	public boolean use(Entity e) {
		Collection<Item> bucket = Main.realm.hmitems.get(e.coords);
		Iterator<Item> iter = bucket.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			if(inventory.size() < capacity && i.canPickUp) {
				Main.realm.remove(i);
				inventory.add(i);
				return true;
			}
		}
		
		if(inventory.size() > 0) {
			Item p = inventory.pop();
			p.coords.set(e.coords);
			Main.realm.add(p);
		}
		
		return true;
	}

}
