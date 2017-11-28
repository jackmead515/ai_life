package main;

import java.util.Collection;
import java.util.LinkedList;

import com.google.common.collect.HashMultimap;

import entity.Entity;
import items.Item;
import util.Coords;

public class Realm {
	
	public volatile LinkedList<Item> items;
	//public volatile LinkedList<Entity> entities;
	public volatile HashMultimap<Coords, Item> hmitems;
	
	public Realm() {
		items = new LinkedList<Item>();
		hmitems = HashMultimap.create();
		//entities = new LinkedList<Entity>();
	}
	
	public void add(Item i) {
		items.add(i);
		if(i instanceof Entity) {
			//entities.add((Entity) i);
		}
		hmitems.put(i.coords, i);
		/*Collection<Item> bucket = hmitems.get(i.coords);
		bucket.add(i);
		hmitems.putAll(i.coords, bucket);*/
	}
	
	public void remove(Item i) {
		items.remove(i);
		if(i instanceof Entity) {
			//entities.remove((Entity) i);
		}
		hmitems.remove(i.coords, i);
		/*Collection<Item> bucket = hmitems.get(i.coords);
		bucket.remove(i);
		hmitems.putAll(i.coords, bucket);*/
	}

}
