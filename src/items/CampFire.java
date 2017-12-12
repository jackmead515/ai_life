package items;

import java.awt.image.BufferedImage;

import interfaces.IAnimate;
import interfaces.IGrow;
import main.BMPImages;
import main.Main;

public class CampFire extends Item implements IAnimate, IGrow {
	
	protected BufferedImage[] animation;
	protected int imageIndex;
	
	protected long startTime;
	protected long animationDuration;
	
	protected long growStartTime;
	protected long growTimeSpeed;
	
	public CampFire() {
		
		startTime = growStartTime = System.nanoTime();
		animationDuration = 100000000L;
		growTimeSpeed = 300000000000L;
		
		canPickUp = false;
		
		image = BMPImages.fire_1;
		
		animation = new BufferedImage[] {BMPImages.fire_1, BMPImages.fire_2, BMPImages.fire_3};
		imageIndex = 0;
		
	}

	@Override
	public void animate(long time) {
		if(time - startTime >= animationDuration) {
			
			startTime = time;
			
			image = animation[imageIndex];
			
			if(imageIndex >= animation.length-1) {
				imageIndex = 0;
			} else {
				imageIndex+=1;
			}
			
		}
	}

	@Override
	public void grow(long time) {
		if(time - growStartTime >= growTimeSpeed) {
			
			Main.realm.remove(this);
			Ash c = new Ash();
			c.coords.set(this.coords);
			Main.realm.add(c);
			
		}
	}

}
