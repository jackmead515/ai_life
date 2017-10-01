package items;

import main.BMPImages;
import main.Main;

public class Hammer extends Tool {
	
	public Hammer() {
		health = 20;
		image = BMPImages.hammer;
		imageInHand = BMPImages.hammer_in_hand;
	}
	
	@Override
	public boolean useComponent(Component item) {
		if(item instanceof MoltenIron) {
			
			health -= 1;
			IronTrinket a = new IronTrinket();
			a.coords = item.coords;
			Main.realm.items.remove(item);
			Main.realm.items.add(a);
			
		}
		
		if(health <= 0) {
			return false;
		} else {
			return true;
		}
	}

}
