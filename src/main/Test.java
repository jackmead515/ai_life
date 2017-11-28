package main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

import com.google.common.collect.HashMultimap;

import items.Item;
import items.Shrub;
import items.Stick;
import items.Wood;
import util.Coords;
import util.Util;

public class Test {
	
	public static void main(String[] args) {
		
		Coords x = new Coords(1, 1);
		Coords y = new Coords(1, 1);
		
		System.out.println(x.equals(y));
		System.out.println(y == x);
		
			
	}
	
}
