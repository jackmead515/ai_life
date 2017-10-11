package food;

import entity.Entity;
import items.Item;

public class Food extends Item {
	
	public int health;
	
	public Food() {}
	
	@Override
	public boolean use(Entity e) {
		e.health += health;
		if(e.health > e.totalHealth) {
			e.health = e.totalHealth;
		}
		return false;
	}

}

