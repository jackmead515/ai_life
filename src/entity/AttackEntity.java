package entity;

import main.Main;
import util.Randomizer;
import util.Util;

public class AttackEntity extends Entity {
	
	protected long movementTimeSpeed;
	protected long startTime;
	
	public AttackEntity() {
		startTime = System.nanoTime();
		movementTimeSpeed = 500000000L;
	}
	
	@Override
	public void move(long time) {
		if(time - startTime >= movementTimeSpeed) {
			
			startTime = time;
			
			int rand = Randomizer.random(0, 4);
			
			int[] now = coords;
			int[] next = null;
			
			switch(rand) {
				case 0:
					break;
				case 1:
					next = new int[] {now[0], now[1]+1};
					break;
				case 2:
					next = new int[] {now[0], now[1]-1};
					break;
				case 3:
					next = new int[] {now[0]-1, now[1]};
					break;
				case 4:
					next = new int[] {now[0]+1, now[1]};
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
