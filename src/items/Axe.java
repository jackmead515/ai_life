package items;

import main.BMPImages;
import main.Main;

public class Axe extends Tool {

	public Axe() {
		health = 20;
		image = BMPImages.axe;
		imageInHand = BMPImages.axe_in_hand;
	}
	
	@Override
	public boolean use(Item item) {
		if(item instanceof Wood) {
			
			health-=1;
			Stick s = new Stick();
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
