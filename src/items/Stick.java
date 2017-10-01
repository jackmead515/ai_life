package items;

import main.BMPImages;
import main.Main;

public class Stick extends Component {
	
	public Stick() {
		
		image = BMPImages.stick;
		
	}
	
	@Override
	public boolean useComponent(Component item) {
		if(item instanceof Wood) {
			
			Axe a = new Axe();
			a.coords = item.coords;
			Main.realm.items.remove(item);
			Main.realm.items.remove(this);
			Main.realm.items.add(a);
			return false;
			
		} else if(item instanceof Stone) {
			
			Pickaxe a = new Pickaxe();
			a.coords = item.coords;
			Main.realm.items.remove(item);
			Main.realm.items.remove(this);
			Main.realm.items.add(a);
			return false;
			
		} else if(item instanceof IronTrinket) {
			
			Sword a = new Sword();
			a.coords = item.coords;
			Main.realm.items.remove(item);
			Main.realm.items.remove(this);
			Main.realm.items.add(a);
			return false;
			
		} else if(item instanceof Rock) {
			
			Hammer a = new Hammer();
			a.coords = item.coords;
			Main.realm.items.remove(item);
			Main.realm.items.remove(this);
			Main.realm.items.add(a);
			return false;
			
		}
		
		return true;
	}

}
