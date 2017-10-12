package ai;

public class AIStats {
	
	public static final double outOfBounds_reward = -0.2; 
	
	public static final double living_reward = 0.01;
	
	public static double starve_reward(int health, int totalHealth) {
		return -(((totalHealth - health) / totalHealth) * 2);
	}
	
	public static double eat_reward(int foodHealth, int totalHealth) {
		if(foodHealth > totalHealth) {
			return 2;
		}
		return (((totalHealth - foodHealth) / totalHealth) * 2);
	}

}
