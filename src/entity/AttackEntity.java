package entity;

import java.awt.Point;

import items.Item;
import items.RawVenison;
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
			if(t == null) {
				int rand = Randomizer.random(0, 4);
				
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
			} else {
				
				int x =	now[0]*20;
				int y = now[1]*20;
				int x1 = t.coords[0]*20;
				int y1 = t.coords[1]*20;
				
				if(Util.distanceTo(x+20, y, x1, y1) < Util.distanceTo(x,y,x1,y1)) {
					next = new int[] {now[0]+1, now[1]};
				} else if(Util.distanceTo(x-20, y, x1, y1) < Util.distanceTo(x,y,x1,y1)) {
					next = new int[] {now[0]-1, now[1]};
				} else if(Util.distanceTo(x, y+20, x1, y1) < Util.distanceTo(x,y,x1,y1)) {
					next = new int[] {now[0], now[1]+1};
				} else if(Util.distanceTo(x, y-20, x1, y1) < Util.distanceTo(x,y,x1,y1)) {
					next = new int[] {now[0], now[1]-1};
				}
				
			}
			
			if(next != null) {
				if(!Main.window.gamePanel.outOfBounds(next) && !Util.inBoundary(next)) {
					coords = next;
					if(t != null) {
						attackTarget(t);
					}
				}
			}
		}
	}
	
	private void attackTarget(Entity t) {
		if(coords[0] == t.coords[0] && coords[1] == t.coords[1]) {
			if(t instanceof Deer) {
				((Entity) t).health-=1;
				if(((Entity) t).health <= 0) {
					RawVenison a = new RawVenison();
					a.coords = t.coords;
					Main.realm.items.add(a);
					Main.realm.items.remove(t);
				}
			} else if(t == Main.player) {
				Main.player.health -= 1;
			}
		}
	}
	
	private Entity chooseTarget(long time) {
		
		int x = coords[0]*20;
		int y = coords[1]*20;
		
		if(Util.distanceTo(x,y,Main.player.coords[0],Main.player.coords[1]) <= 300) {
			return (Entity) Main.player;
		}
		
		for(int p = 0; p < Main.realm.items.size(); p++) {
			Item i = Main.realm.items.get(p);
			
			if(i instanceof Entity && !(i instanceof AttackEntity)) {
			
				int x1 = i.coords[0]*20;
				int y1 = i.coords[1]*20;
				
				if(Util.distanceTo(x,y,x1,y1) <= 300) {
					return (Entity) i;
				}
				
			}
		}
		
		return null;
	}

}
