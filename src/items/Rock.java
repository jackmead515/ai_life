package items;

import main.BMPImages;
import main.Main;

public class Rock extends Component {
	
	public Rock() {
		
		image = BMPImages.rock;
		
	}
	
	@Override
	public boolean useComponent(Component item) {
		if(item instanceof Stone) {
			
			Furnace a = new Furnace();
			a.coords = item.coords;
			Main.realm.items.remove(item);
			Main.realm.items.remove(this);
			Main.realm.items.add(a);
			return false;
			
		} else if(item instanceof IronTrinket) {
			
			Sparker a = new Sparker();
			a.coords = item.coords;
			Main.realm.items.remove(item);
			Main.realm.items.remove(this);
			Main.realm.items.add(a);
			return false;
			
		}
		
		return true;
	}

}
