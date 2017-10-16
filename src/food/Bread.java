package food;

import entity.Entity;
import main.BMPImages;

public class Bread extends Food {
	
	public Bread() {
		health = 10;
		image = BMPImages.bread;
	}
	
	@Override
	public String description() {
		return "Bread (" + health + ")";
	}
}
