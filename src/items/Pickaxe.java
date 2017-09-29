package items;

import main.BMPImages;
import main.Main;

public class Pickaxe extends Tool {
	
	public Pickaxe() {
		health = 20;
		image = BMPImages.pickaxe;
		imageInHand = BMPImages.pickaxe_in_hand;
	}
	
	@Override
	public boolean useComponent(Component item) {
		if(item instanceof Stone) {
			
			health-=1;
			Rock s = new Rock();
			s.coords = item.coords;
			Main.registry.items.remove(item);
			Main.registry.items.add(s);
			
		}
		
		if(health <= 0) {
			return false;
		} else {
			return true;
		}
	}

}
