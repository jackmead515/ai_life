package items;

import entity.Animal;
import main.BMPImages;
import main.Main;

public class Axe extends Tool {

	public Axe() {
		health = 20;
		image = BMPImages.axe;
		imageInHand = BMPImages.axe_in_hand;
	}

	@Override
	public boolean useComponent(Component c) {
		if(c instanceof Wood) {
			
			health-=1;
			Stick s = new Stick();
			s.coords = c.coords;
			Main.registry.items.remove(c);
			Main.registry.items.add(s);
			
		}
		
		if(health <= 0) {
			return false;
		} else {
			return true;
		}
	}

}
