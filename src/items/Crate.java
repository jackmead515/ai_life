package items;

import main.BMPImages;
import main.Main;
import util.SoundEffect;

public class Crate extends Component {
	
	public Crate() {
		
		image = BMPImages.crate;
		
	}
	
	public static Item generate() {
		return new Item();
	}

}
