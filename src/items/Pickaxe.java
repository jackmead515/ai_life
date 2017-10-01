package items;

import main.BMPImages;
import main.Main;
import util.SoundEffect;

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
			SoundEffect.PICKAXE.play();
			Rock s = new Rock();
			s.coords = item.coords;
			Main.realm.items.remove(item);
			Main.realm.items.add(s);
			
		} else if(item instanceof IronOre) {
			
			health-=1;
			SoundEffect.PICKAXE.play();
			Iron s = new Iron();
			s.coords = item.coords;
			Main.realm.items.remove(item);
			Main.realm.items.add(s);
			
		}
		
		if(health <= 0) {
			return false;
		} else {
			return true;
		}
	}

}
