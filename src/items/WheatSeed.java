package items;

import interfaces.IGrow;
import main.BMPImages;
import main.Main;

public class WheatSeed extends Component implements IGrow {
	
	protected long cycleTime;
	protected long startTime;
	public boolean startGrowing;
	
	public WheatSeed() {
		
		cycleTime = 120000000000L;
		
		image = BMPImages.wheat_seed;
		
		startTime = System.nanoTime();
		
		startGrowing = false;
		
	}

	@Override
	public void grow(long time) {
		
		/*if(!startGrowing) {
			return;
		}*/
		
		if(time - startTime >= cycleTime) {
			
			Main.realm.items.remove(this);
			
			Wheat c = new Wheat();
			c.coords = coords;
		
			Main.realm.items.add(c);
			
		}
	
	}
	
}
