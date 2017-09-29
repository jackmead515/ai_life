package items;

import main.BMPImages;
import main.Main;

public class Plant extends Component implements IGrow {
	
	protected long cycleTime;
	protected long elapsedTime;
	
	public Plant() {
		
		elapsedTime = 0;
		cycleTime = 60000000000L;
		
		image = BMPImages.plant;
		
	}
	
	@Override
	public void grow(long time) {
		
		elapsedTime += time;
		
		if(elapsedTime >= cycleTime) {
			
			elapsedTime = 0;
		
			Main.registry.items.remove(this);
			
			Shrub c = new Shrub();
			c.coords = coords;
		
			Main.registry.items.add(c);
			
		}
	
	}
	
	public boolean use() {
		return true;
	}

}
