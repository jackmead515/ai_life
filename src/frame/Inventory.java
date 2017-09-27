package frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import items.Item;
import items.Plant;
import items.Wood;
import main.Main;

public class Inventory {

	public JFrame frame;
	//public InventoryPanel panel;

	public Inventory() {
		initialize();
		createJButtons();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 520, 540);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//panel = new InventoryPanel();
		//frame.getContentPane().add(panel);
	}
	
	private void createJButtons() {
		ArrayList<Item> inventory = Main.player.getInventory();
		
		int x = 20;
		int y = 20;
		
		int plants = getPlants(inventory);
		int wood = getWood(inventory);
		
		if(plants > 0) {
			JButton b = new JButton();
			b.setText("Plants: " + plants);
			b.setBounds(x, y, 100, 20);
			b.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Main.player.select(new Plant());
				}
			});
			frame.getContentPane().add(b);
			
			y+=25;
		}
		
		if(wood > 0) {
			JButton b = new JButton();
			b.setText("Wood: " + wood);
			b.setBounds(x, y, 100, 20);
			b.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Main.player.select(new Wood());
				}
			});
			frame.getContentPane().add(b);
			frame.getContentPane().add(b);
			
			y+=25;
		}
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
