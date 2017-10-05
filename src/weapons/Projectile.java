package weapons;

import entity.Entity;
import interfaces.IAnimate;

public class Projectile extends Weapon implements IAnimate {
	
	protected long startTime;
	protected long animationDuration;
	public boolean isShot;
	public int direction;
	protected int tileLife;
	
	public Projectile() {
		isShot = false;
		direction = 0;
	}

	@Override
	public void animate(long time) {
		// TODO Auto-generated method stub
		
	}

	public void shoot() {
		// TODO Auto-generated method stub
		
	}

}
