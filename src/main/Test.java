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
		
		Point origin = new Point(100, 100);
		
		ArrayList<Point> points = new ArrayList<Point>();
		
		points.add(new Point(50, 140));
		points.add(new Point(50, 100));
		points.add(new Point(50,60));
		points.add(new Point(10,73));
		
		points.add(new Point(150, 140));
		points.add(new Point(150, 100));
		points.add(new Point(150,60));
		points.add(new Point(122,105));
		
		points.add(new Point(90, 140));
		points.add(new Point(70, 200));
		points.add(new Point(120, 340));
		
		points.add(new Point(40, 20));
		points.add(new Point(80, 40));
		points.add(new Point(145, 10));
		
		points.add(new Point(150,150));
	
		
		for(Point test : points) {
			System.out.println(Util.directionFrom(origin, test));
		}
		
			
	}
	
}
