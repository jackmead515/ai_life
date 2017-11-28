package entity;

import java.awt.Point;

import items.Item;
import items.RawVenison;
import main.Main;
import util.Coords;
import util.Randomizer;
import util.Util;

public class AttackEntity extends Entity {
	
	protected long movementTimeSpeed;
	protected long movementStartTime;
	
	protected long changeTargetStartTime;
	protected long changeTargetTimeSpeed;
	
	protected long idleStartTime;
	protected long idleTimeSpeed;
	
	protected Entity target;
	
	public AttackEntity() {
		movementStartTime = changeTargetStartTime = idleStartTime = System.nanoTime();
		movementTimeSpeed = 600000000L;
		changeTargetTimeSpeed = 30000000000L;
		idleTimeSpeed = 30000000000L;
		target = null;
	}
	
	@Override
	public void move(long time) {
		/*
		 * If it's time to make a move:
		 * 	If it's time to change the target:
		 * 	 If it's time to pursue:
		 * 		choose a target
		 * 	 else:
		 * 		return
		 * 
		 *  make a move
		 */
		if(time - movementStartTime >= movementTimeSpeed) {
			movementStartTime = time;
			
			if(time - changeTargetStartTime >= changeTargetTimeSpeed) {
				changeTargetStartTime = time;
				
				if(time - idleStartTime >= idleTimeSpeed) {
					idleStartTime = time; 
					target = chooseTarget();
				} else {
					return;
				}
				
				
			}
			
			makeMove();
		}
	}
	
	private void makeMove() {
		int[] now = new int[] {coords.x(), coords.y()};
		Coords next = null;
		
		if(target == null) {
			//Random Movement
			int rand = Randomizer.random(0, 4);
			
			switch(rand) {
				case 0: return;
				case 1: next = new Coords(now[0], now[1]+1); break;
				case 2: next = new Coords(now[0], now[1]-1); break;
				case 3: next = new Coords(now[0]-1, now[1]); break;
				case 4: next = new Coords(now[0]+1, now[1]); break;
			}
		} else {
			//Attacking Movement
			int x =	now[0]*20; int x1 = target.coords.x()*20;
			int y = now[1]*20; int y1 = target.coords.y()*20;
			
			if(Util.distanceTo(x+20, y, x1, y1) < Util.distanceTo(x,y,x1,y1)) {
				next = new Coords(now[0]+1, now[1]);
			} else if(Util.distanceTo(x-20, y, x1, y1) < Util.distanceTo(x,y,x1,y1)) {
				next = new Coords(now[0]-1, now[1]);
			} else if(Util.distanceTo(x, y+20, x1, y1) < Util.distanceTo(x,y,x1,y1)) {
				next = new Coords(now[0], now[1]+1);
			} else if(Util.distanceTo(x, y-20, x1, y1) < Util.distanceTo(x,y,x1,y1)) {
				next = new Coords(now[0], now[1]-1);
			}
		}
		
		if(next != null) {
			if(!Main.window.gamePanel.outOfBounds(next) && !Util.inBoundary(next)) {
				Main.realm.remove(this);
				coords.set(next.x(), next.y());
				Main.realm.add(this);
				if(target != null) {
					attackTarget();
				}
			}
		}
	}
	
	private void attackTarget() {
		if(coords.equals(target.coords)) {
			target.health-=5;
			if(target.health <= 0) {
				Item a = target.drop();
				if(a != null) {
					a.coords.set(target.coords);
					Main.realm.add(a);
				}
				Main.realm.remove(target);
				target = null;
			}
		}
	}
	
	private Entity chooseTarget() {
		for(int p = 0; p < Main.realm.items.size(); p++) {
			Item i = Main.realm.items.get(p);
			if(i instanceof Entity && !(i instanceof AttackEntity)) {
				int px = i.coords.x()*20;
				int py = i.coords.y()*20;
				
				if(Util.distanceTo(coords.x()*20,coords.y()*20,px,py) <= 300) {
					return (Entity) i;
				}
			}
		}
		return null;
	}

}
