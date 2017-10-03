package items;

import interfaces.IAnimate;

public class Ammo extends Weapon implements IAnimate {
	
	protected long startTime;
	protected long animationDuration;
	public boolean isShot;
	public int direction;
	protected int tileLife;
	
	public Ammo() {
		isShot = false;
		direction = 0;
	}

	@Override
	public void animate(long time) {
		// TODO Auto-generated method stub
		
	}

}
