package main;

import java.util.Collection;

import com.google.common.collect.HashMultimap;

import items.Item;
import items.Shrub;
import items.Stick;
import items.Wood;
import util.Coords;

public class Test {
	
	public static void main(String[] args) {
		
		HashMultimap<Coords, Item> items = HashMultimap.create();
		
		Item s = new Item();
		s.coords.set(0, 0);
		Item w = new Item();
		w.coords.set(0, 0);
		
		Item p = new Item();
		p.coords.set(1, 1);
		
		items.put(s.coords, s);
		items.put(w.coords, w);
		items.put(p.coords, p);
		
		Collection<Item> bucket = items.get(s.coords);
		bucket.add(s);
		items.putAll(s.coords, bucket);
		
		bucket = items.get(w.coords);
		bucket.add(w);
		items.putAll(w.coords, bucket);
		
		bucket = items.get(p.coords);
		bucket.add(p);
		items.putAll(p.coords, bucket);
		
		for(Coords key : items.keySet()) {
			System.out.println(key.x() + " " + key.y());
		}
			
	}
	
}
