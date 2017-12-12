package main;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

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
		if(!items.contains(i)) items.add(i);
		if(i instanceof Entity) {
			//entities.add((Entity) i);
		}
		hmitems.put(i.coords, i);
	}
	
	public void remove(Item i) {
		if(items.contains(i)) items.remove(i);
		if(i instanceof Entity) {
			//entities.remove((Entity) i);
		}
		hmitems.remove(i.coords, i);
	}
	
	public void drop(Item i) {
		SoundEffect.DROP.play();
		Main.realm.add(i);
	}
	
	public Item pickUp(Coords c) {
		Collection<Item> bucket = Main.realm.hmitems.get(c);
		Iterator<Item> iter = bucket.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			if(i.canPickUp) {
				SoundEffect.PICKUP.play();
				Main.realm.remove(i);
				return i;
			}
		}
		return null;
	}

}
