package main;

import java.io.Serializable;
import java.util.LinkedList;

import boundaries.Boundary;
import items.Item;

public class Realm implements Serializable {
	
	public volatile LinkedList<Item> items;
	
	public Realm() {
		items = new LinkedList<Item>();
	}
	
	public void generate() {
		
		
		
	}

}
