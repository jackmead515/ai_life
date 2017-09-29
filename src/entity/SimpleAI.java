package entity;

import java.util.concurrent.ThreadLocalRandom;

public class SimpleAI extends Entity {
	
	protected long movementTimeSpeed;
	protected long elapsedTime;
	
	public SimpleAI() {
		elapsedTime = 0L;
		movementTimeSpeed = 1000000000L;
	}
	
	@Override
	public void move(long time) {
		
		elapsedTime += time;
		
		if(elapsedTime >= movementTimeSpeed) {
			
			elapsedTime = 0;
			
			int rand = ThreadLocalRandom.current().nextInt(1, 4 + 1);
			
			int[] now = coords;
			
			switch(rand) {
				case 1:
					coords = new int[] {now[0], now[1]+1};
					return;
				case 2:
					coords = new int[] {now[0], now[1]-1};
					return;
				case 3:
					coords = new int[] {now[0]-1, now[1]};
					return;
				case 4:
					coords = new int[] {now[0]+1, now[1]};
					return;
			}		
		}
	}
	
	

}
