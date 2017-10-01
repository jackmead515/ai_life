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
	public boolean useComponent(Component c) {
		if(c instanceof Wood) {
			
			health-=1;
			Stick s = new Stick();
			s.coords = c.coords;
			Main.realm.items.remove(c);
			Main.realm.items.add(s);
			
		} else if(c instanceof Plant) {
			
			health-=1;
			WheatSeed s = new WheatSeed();
			s.coords = c.coords;
			Main.realm.items.remove(c);
			Main.realm.items.add(s);
			
		}
		
		if(health <= 0) {
			return false;
		} else {
			return true;
		}
	}

}
