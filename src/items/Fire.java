package items;

import java.awt.image.BufferedImage;

import interfaces.IAnimate;
import main.BMPImages;

public class Fire extends Item implements IAnimate {
	
	protected BufferedImage[] animation;
	protected int imageIndex;
	
	protected long startTime;
	protected long animationDuration;
	
	public Fire() {
		
		startTime = System.nanoTime();
		animationDuration = 300000000L;
		
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

}
