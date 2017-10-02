package frame;

import java.util.LinkedList;

import javax.swing.JPanel;

import items.Axe;
import items.Boundary;
import items.Item;

public class Palette {
	
	private JPanel panel;
	public LinkedList<Item> items;
	public LinkedList<Boundary> boundaries;

	public Palette(CreatorPanel creatorPanel) {
		this.panel = creatorPanel;
		addItems();
		addBoundaries();
		attachListeners();
	}
	
	private void addBoundaries() {
		
	}
	
	private void addItems() {
		items.add(new Axe());
		items.add(new Bread());
		items.add(new Fire());
		items.add(new Furnace());
		items.add(new Hammer());
		items.add(new Iron());
		items.add(new IronOre());
		items.add(new Axe());
		items.add(new Axe());
		items.add(new Axe());
		items.add(new Axe());
		items.add(new Axe());
		items.add(new Axe());
		items.add(new Axe());
		items.add(new Axe());
		items.add(new Axe());
		items.add(new Axe());
		items.add(new Axe());
		items.add(new Axe());
		items.add(new Axe());
		items.add(new Axe());
		items.add(new Axe());
	}

	private void attachListeners() {	
		
	}
	public void move(double delta) {
		
	}

	

}
