package boundaries;

import java.awt.image.BufferedImage;

import interfaces.IAnimate;
import main.BMPImages;

public class Water extends Boundary implements IAnimate {
	
	protected BufferedImage[] animation;
	protected int imageIndex;
	
	protected long startTime;
	protected long animationDuration;
	
	public Water() {
		
		startTime = System.nanoTime();
		animationDuration = 500000000L;
		image = BMPImages.water_1;
		animation = new BufferedImage[] {BMPImages.water_1, BMPImages.water_2, BMPImages.water_3};
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
