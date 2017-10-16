package food;

import main.BMPImages;

public class Venison extends Food {
	
	public Venison() {
		health = 25;
		image = BMPImages.venison;
	}
	
	@Override
	public String description() {
		return "Venison (" + health + ")";
	}

}
