package items;

import main.BMPImages;
import main.Main;

public class Wheat extends Component {
	
	public Wheat() {
		
		image = BMPImages.wheat;
		
	}
	
	@Override
	public boolean useComponent(Component item) {
		if(item instanceof Furnace) {
			
			Bread a = new Bread();
			a.coords = item.coords;
			Main.realm.items.remove(this);
			Main.realm.items.add(a);
			return false;
			
		}
		
		return true;
	}

}
