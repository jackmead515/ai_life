package items;

import main.BMPImages;
import main.Main;

public class Sparker extends Tool {
	
	public Sparker() {
		
		health = 20;
		image = BMPImages.sparker;
		imageInHand = BMPImages.sparker_in_hand;
		
	}
	
	@Override
	public boolean useComponent(Component c) {
		if(c instanceof Wood) {
			
			health-=1;
			Fire s = new Fire();
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
