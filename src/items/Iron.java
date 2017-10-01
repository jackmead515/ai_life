package items;

import main.BMPImages;
import main.Main;

public class Iron extends Component {

	public Iron() {
		
		image = BMPImages.iron;
		
	}
	
	@Override
	public boolean useComponent(Component item) {
		if(item instanceof Furnace) {
			
			MoltenIron a = new MoltenIron();
			a.coords = item.coords;
			//Main.realm.items.remove(item);
			Main.realm.items.remove(this);
			Main.realm.items.add(a);
			return false;
			
		}
		
		return true;
	}
	
}
