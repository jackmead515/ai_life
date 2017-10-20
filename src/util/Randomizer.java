package util;

import java.util.concurrent.ThreadLocalRandom;

import items.Item;
import main.Main;

public class Randomizer {
	
	public static int random(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

}
