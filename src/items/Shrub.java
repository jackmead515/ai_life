package items;

import interfaces.IGrow;
import main.BMPImages;
import main.Main;

public class Shrub extends Component implements IGrow {
	
	protected long cycleTime;
	protected long startTime;
	
	public Shrub() {
		
		cycleTime = 120000000000L;
		
		image = BMPImages.shrub;
		
		startTime = System.nanoTime();
		
	}
	
	@Override
	public void grow(long time) {
		if(time - startTime >= cycleTime) {
		
			Main.realm.items.remove(this);
			
			Wood c = new Wood();
			c.coords = coords;
		
			Main.realm.items.add(c);
			
		}
	
	}
	
	@Override
	public boolean useComponent(Component item) {
		if(item instanceof Shrub) {
			
			Stick a = new Stick();
			a.coords = item.coords;
			Main.realm.items.remove(item);
			Main.realm.items.remove(this);
			Main.realm.items.add(a);
			return false;
			
		}
		
		return true;
	}

}
