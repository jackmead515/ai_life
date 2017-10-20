package ai;

import java.io.InputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import items.Item;
import main.Main;
import util.RefStrings;

public class AISelector {
	
	private boolean selecting;
	public int action;
	
	public AISelector() {
		action = -1;
		selecting = false;
	}
	
	private void initState(int[][] state) {
		Arrays.fill(state, 0);
		
		LinkedList<Item> items = Main.realm.items;
		for(int x = 0; x < items.size(); x++) {
			Item i = items.get(x);
			state[i.coords.x()][i.coords.y()] = new BigInteger(i.getClass().getSimpleName().getBytes()).intValue();
		}
	}
	
	@SuppressWarnings("finally")
	private int fetchAction(int[][] state, double reward) {

		HttpURLConnection connection = null;
		int action = -1;

		try {
			String postString = "state=" + Arrays.deepToString(state) + "&reward=" + reward;
			
			URL url = new URL("http://" + RefStrings.AI_IP);
			connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			connection.setDoOutput(true);
			
			connection.getOutputStream().write(postString.getBytes());
			connection.getOutputStream().flush();
			connection.getOutputStream().close();
			
			Scanner input = new Scanner(connection.getInputStream());
			
			action = input.nextInt();
			
			input.close();
			connection.getInputStream().close();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			return action;
		}
	}
	
	public void select(int[][] state, double reward) {
		if(!selecting) {
			selecting = true;
			
			initState(state);
			
			Thread t = new Thread(new Runnable() {
				public void run() {
					action = fetchAction(state, reward);
					selecting = false;
				}
			});
			t.start();
		}
	}

}
