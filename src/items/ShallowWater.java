package items;

import java.awt.image.BufferedImage;

import interfaces.IAnimate;
import main.BMPImages;

public class ShallowWater extends Component implements IAnimate {
	
	protected BufferedImage[] animation;
	protected int imageIndex;
	
	protected long startTime;
	protected long animationDuration;
	
	public ShallowWater() {
		
		startTime = System.nanoTime();
		animationDuration = 500000000L;
		
		image = BMPImages.shallow_water_1;
		
		canPickUp = false;
		
		animation = new BufferedImage[] {BMPImages.shallow_water_1, BMPImages.shallow_water_2, BMPImages.shallow_water_3};
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
