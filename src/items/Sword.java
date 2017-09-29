package items;

import main.BMPImages;
import main.Main;

public class Sword extends Weapon {
	
	public Sword() {
		
		image = BMPImages.sword;
		
	}
	
	@Override
	public boolean useComponent(Component item) {
		if(item instanceof Wood) {
			
			Axe a = new Axe();
			a.coords = item.coords;
			Main.registry.items.remove(item);
			Main.registry.items.add(a);
			return false;
			
		} else if(item instanceof Stone) {
			
			Pickaxe a = new Pickaxe();
			a.coords = item.coords;
			Main.registry.items.remove(item);
			Main.registry.items.add(a);
			return false;
			
		}
		
		return true;
	}

}
