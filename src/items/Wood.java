package items;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import frame.GamePanel;
import main.BMPImages;
import main.Main;

public class Wood extends Item implements IBoundary {

	public Wood() {

		image = BMPImages.wood;

	}

	@Override
	public boolean canWalkOver() {
		return false;
	}

}
