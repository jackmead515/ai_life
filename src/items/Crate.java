package items;

import main.BMPImages;
import main.Main;
import util.Randomizer;
import util.SoundEffect;

public class Crate extends Item {
	
	public Crate() {
		
		image = BMPImages.crate;
		
	}
	
	public static Item generate() {
		int rand = Randomizer.random(1, 5);
		switch(rand) {
			case 1:
				return new Stick();
			case 2:
				return new Rock();
			case 3:
				return new IronOre();
			case 4:
				return new Wheat();
			case 5:
				return new IronTrinket();
		}
		return null;
	}

}
