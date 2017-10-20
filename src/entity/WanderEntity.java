package entity;

import main.Main;
import util.Coords;
import util.Randomizer;
import util.Util;

public class WanderEntity extends Entity {
	
	protected long movementTimeSpeed;
	protected long startTime;
	
	public WanderEntity() {
		startTime = System.nanoTime();
		movementTimeSpeed = 500000000L;
	}
	
	@Override
	public void move(long time) {
		if(time - startTime >= movementTimeSpeed) {
			
			startTime = time;
			
			int rand = Randomizer.random(0, 4);
			
			int[] now = new int[] {coords.x(), coords.y()};
			Coords next = new Coords(0,0);
			
			switch(rand) {
				case 0:
					break;
				case 1:
					next.set(now[0], now[1]+1);
					break;
				case 2:
					next.set(now[0], now[1]-1);
					break;
				case 3:
					next.set(now[0]-1, now[1]);
					break;
				case 4:
					next.set(now[0]+1, now[1]);
					break;
			}
			
			if(next != null) {
				if(!Main.window.gamePanel.outOfBounds(next) && !Util.inBoundary(next)) {
					coords = next;
				}
			}
		}
	}

}
