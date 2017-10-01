package items;

import entity.Entity;
import main.BMPImages;

public class Bread extends Food {
	
	public Bread() {
		
		health = 25;
		image = BMPImages.bread;
		
	}
	
	@Override
	public void eat(Entity e) {
		e.health += health;
	}
}
