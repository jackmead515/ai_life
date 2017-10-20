package main;

import java.util.Collection;
import java.util.LinkedList;

import com.google.common.collect.HashMultimap;

import items.Item;
import util.Coords;

public class Realm {
	
	public volatile LinkedList<Item> items;
	public volatile HashMultimap<Coords, Item> hmitems;
	
	public Realm() {
		items = new LinkedList<Item>();
		hmitems = HashMultimap.create();
	}
	
	public void add(Item i) {
		items.add(i);
		Collection<Item> bucket = hmitems.get(i.coords);
		bucket.add(i);
		hmitems.putAll(i.coords, bucket);
	}
	
	public void remove(Item i) {
		items.remove(i);
		Collection<Item> bucket = hmitems.get(i.coords);
		bucket.remove(i);
		hmitems.putAll(i.coords, bucket);
	}

}
