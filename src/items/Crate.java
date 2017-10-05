package items;

import main.BMPImages;
import main.Main;
import main.SoundEffect;
import util.Randomizer;

public class Crate extends Item {
	
	public Crate() {
		
		image = BMPImages.crate;
		
	}
	
	public static Item generate() {
		int rand = Randomizer.random(1, 6);
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
			case 6:
				return new Twine();
		}
		return null;
	}

}
