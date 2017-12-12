package ai;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	public AI ai;
	
	public AISelector(AI ai) {
		this.ai = ai;
		action = -1;
		selecting = false;
	}
	
	private void initState(int[][] state) {
		int rows = state.length;
		int cols = state[0].length;
		for(int i = 0; i < rows-1; i++) {
			for(int o = 0; o < cols-1; o++) {
				state[i][o] = 0;
			}
		}
		
		//Arrays.fill(state, 0);
		
		LinkedList<Item> items = Main.realm.items;
		for(int x = 0; x < items.size(); x++) {
			Item i = items.get(x);
			//System.out.println(i.coords.x() + " " + i.coords.y() + " " + i.getClass().getName());
			if(i.coords.x <= state.length-1 && i.coords.y <= state[0].length-1) {
				state[i.coords.x][i.coords.y] = new BigInteger(i.getClass().getSimpleName().getBytes()).intValue();
			}
			
		}
	}
	
	@SuppressWarnings("finally")
	private int fetchAction(int[][] state, double reward) {

		HttpURLConnection connection = null;
		int action = -1;

		try {
			String postString = "{\"state\":\"" + Arrays.deepToString(state) + "\",\"reward\":\"" + reward + "\"}";
			
			URL url = new URL("http://" + RefStrings.AI_IP + "/");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(postString);
			wr.flush();
			wr.close();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			action = Integer.parseInt(response.toString());
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			return action;
		}
	}
	
	public void select(int[][] state) {
		if(!selecting) {
			selecting = true;
			
			initState(state);
			 
			AI ai = this.ai;
			
			Thread t = new Thread(new Runnable() {
				public void run() {
					double reward = ai.reward;
					ai.reward = 0;
					action = fetchAction(state, reward);
					selecting = false;
				}
			});
			t.start();
		}
	}

}
