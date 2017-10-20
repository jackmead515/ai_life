package items;

import main.BMPImages;
import tools.Axe;
import tools.Hammer;
import tools.Pickaxe;
import tools.Sparker;
import util.Randomizer;

public class Barrel extends Container {
	
	public Barrel() {
		
		image = BMPImages.barrel;
		
	}
	
	@Override
	public Item drop() {
		int rand = Randomizer.random(1, 5);
		switch(rand) {
			case 1:
				return new Axe();
			case 2:
				return new Hammer();
			case 3:
				return new Pickaxe();
			case 4:
				return new Sparker();
			case 5:
				return new Stick();
		}
		return null;
	}

}
