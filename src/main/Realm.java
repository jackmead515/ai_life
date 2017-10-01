package main;

import java.util.LinkedList;

import items.Boundary;
import items.Item;

public class Realm {
	
	public volatile LinkedList<Boundary> boundaries;
	public volatile LinkedList<Item> items;
	
	public Realm() {
		boundaries = new LinkedList<Boundary>();
		items = new LinkedList<Item>();
	}
	
	public void generate() {
		
		
		
	}

}
