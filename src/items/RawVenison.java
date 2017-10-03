package items;

import entity.Entity;
import main.BMPImages;
import main.Main;

public class RawVenison extends Food {
	
	public RawVenison() {
		
		health = 5;
		image = BMPImages.venison;
		
	}
	
	@Override
	public boolean useComponent(Component e) {
		if(e instanceof Fire || e instanceof Furnace) {
			
			Venison a = new Venison();
			a.coords = e.coords;
			Main.realm.items.remove(this);
			Main.realm.items.add(a);
			return false;
			
		}
		
		return false;
	}
	
	

}
