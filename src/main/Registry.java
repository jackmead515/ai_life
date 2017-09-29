package main;

import java.util.LinkedList;

import entity.Entity;
import entity.Player;
import items.Boundary;
import items.Item;

public class Registry {
	
	public volatile LinkedList<Entity> entities;
	public volatile LinkedList<Boundary> boundaries;
	public volatile LinkedList<Item> items;
	
	public Registry() {
		entities = new LinkedList<Entity>();
		boundaries = new LinkedList<Boundary>();
		items = new LinkedList<Item>();
	}
	
	public void addItem(Item i) {
		int x = i.coords[0];
		int y = i.coords[1];
		
		for(Item c : items) {
			int cx = c.coords[0];
			int cy = c.coords[1];
			
			if(x == cx && y == cy) {
				return;
			}
		}
		
		items.add(i);
	}
}
