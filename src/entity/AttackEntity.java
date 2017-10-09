package entity;

import main.Main;
import util.Randomizer;
import util.Util;

public class AttackEntity extends Entity {
	
	protected long movementTimeSpeed;
	protected long startTime;
	
	protected long targetSwitchTime;
	protected long idleTime;
	protected long switchStartTime;
	
	protected Entity target;
	
	public AttackEntity() {
		startTime = System.nanoTime();
		movementTimeSpeed = 500000000L;
		target = null;
		//idleTime = 1000000000L;
		//targetSwitchTime = 10000000000L;
	}
	
	@Override
	public void move(long time) {
		if(time - startTime >= movementTimeSpeed) {
			
			startTime = time;
			
			int[] now = coords;
			int[] next = null;
			
			Entity t = chooseTarget(time);
			
			
			//TODO
			
			
			if(next != null) {
				if(!Main.window.gamePanel.outOfBounds(next) && !Util.inBoundary(next)) {
					coords = next;
				}
			}
		}
	}
	
	private Entity chooseTarget(long time) {
		//TODO
		return null;
	}

}
