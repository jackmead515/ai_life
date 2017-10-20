package items;

import main.BMPImages;
import tools.Axe;
import tools.Hammer;
import tools.Pickaxe;
import tools.Sparker;
import util.Randomizer;
import weapons.Arrow;
import weapons.Bow;
import weapons.Sword;

public class Chest extends Container {
	
	public Chest() {
		
		image = BMPImages.chest;
		
	}
	
	@Override
	public Item drop() {
		int rand = Randomizer.random(1, 5);
		switch(rand) {
			case 1:
				return new Bow();
			case 2:
				return new Sword();
			case 3:
				ArrowBundle b = new ArrowBundle();
				b.amount = 10;
				return b;
			case 4:
				return new Sparker();
			case 5:
				return new Stick();
		}
		return null;
	}

}
