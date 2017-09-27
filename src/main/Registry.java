package main;

import java.util.LinkedList;

import entity.Entity;
import entity.Player;
import items.Boundary;
import items.Item;

public class Registry {
	
	public LinkedList<Entity> entities;
	public LinkedList<Boundary> boundaries;
	public LinkedList<Item> items;
	
	public Registry() {
		entities = new LinkedList<Entity>();
		boundaries = new LinkedList<Boundary>();
		items = new LinkedList<Item>();
	}
	
	public void addBoundary(Boundary b) {
		int x = b.coords[0];
		int y = b.coords[1];
		
		for(Boundary c : boundaries) {
			int cx = c.coords[0];
			int cy = c.coords[1];
			
			if(x == cx && y == cy) {
				return;
			}
		}
		
		boundaries.add(b);
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
	
	public void addEntity(Entity e) {
		
	}

}
