package frame;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import items.Item;
import items.Plant;
import items.Wood;
import main.Main;

public class InventoryPanel extends JPanel {
	
	public InventoryPanel() {
		this.setBounds(0, 0, 500, 500);
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setFont(new Font("Minion Pro", Font.PLAIN, 15));
		
		ArrayList<Item> inventory = Main.player.getInventory();
		
		int x = 20;
		int y = 20;
		
		int plants = getPlants(inventory);
		int wood = getWood(inventory);
		
		g2.drawString("Plants: " + plants, x, y);
		g2.drawString("Wood: " + wood, x, y+20);
		
	
	}
	
	private int getPlants(ArrayList<Item> inventory) {
		int total = 0;
		for(Item i : inventory) {
			if(i instanceof Plant)
				total += 1;
		}
		return total;
	}
	
	private int getWood(ArrayList<Item> inventory) {
		int total = 0;
		for(Item i : inventory) {
			if(i instanceof Wood)
				total += 1;
		}
		return total;
	}

}
