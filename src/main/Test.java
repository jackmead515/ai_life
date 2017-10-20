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
		Item w = new Item();
		
		Item p = new Item();
		
		Coords one = new Coords(1, 1);
		Coords two = new Coords(2, 2);
		
		items.put(new Coords(1, 1), s);
		items.put(new Coords(1, 1), w);
		items.put(new Coords(2, 2), p);
		
		/*Collection<Item> bucket = items.get(new Coords(1, 1));
		bucket.add(s);
		items.putAll(new Coords(1, 1), bucket);
		
		bucket = items.get(new Coords(1, 1));
		bucket.add(w);
		items.putAll(new Coords(1, 1), bucket);
		
		bucket = items.get(new Coords(2, 2));
		bucket.add(p);
		items.putAll(new Coords(2, 2), bucket);*/
		
		for(Coords key : items.keySet()) {
			System.out.println(key.x() + " " + key.y());
		}
			
	}
	
}
