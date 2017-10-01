package items;

import entity.Entity;
import main.BMPImages;
import main.Main;

public class Venison extends Food {
	
	public Venison() {
		
		health = 25;
		image = BMPImages.venison;
		
	}
	
	@Override
	public void eat(Entity e) {
		e.health += health;
	}
	
	

}
