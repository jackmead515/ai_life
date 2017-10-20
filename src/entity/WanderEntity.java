package entity;

import main.Main;
import util.Coords;
import util.Randomizer;
import util.Util;

public class WanderEntity extends Entity {
	
	protected long movementTimeSpeed;
	protected long movementStartTime;
	
	public WanderEntity() {
		movementStartTime = System.nanoTime();
		movementTimeSpeed = 500000000L;
	}
	
	@Override
	public void move(long time) {
		if(time - movementStartTime >= movementTimeSpeed) {
			
			movementStartTime = time;
			
			int rand = Randomizer.random(0, 4);
			
			int[] now = new int[] {coords.x(), coords.y()};
			Coords next = null;
			
			switch(rand) {
				case 0: return;
				case 1: next = new Coords(now[0], now[1]+1); break;
				case 2: next = new Coords(now[0], now[1]-1); break;
				case 3: next = new Coords(now[0]-1, now[1]); break;
				case 4: next = new Coords(now[0]+1, now[1]); break;
			}
			
			if(!Main.window.gamePanel.outOfBounds(next) && !Util.inBoundary(next)) {
					coords.set(next.x(), next.y());
			}
		}
	}

}
