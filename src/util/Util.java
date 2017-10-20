package util;

import java.awt.Point;
import java.awt.Shape;
import java.util.Collection;
import java.util.Iterator;

import boundaries.Boundary;
import items.Item;
import main.Main;

public class Util {
	
	public static boolean inArea(Shape a, Point p){
		return a.getBounds().contains(p);
	}
	
	/**
	 * @param origin - Point from which to base direction from
	 * @param tester - Point away from origin to judge direction
	 * @return 1 - up, 2 - down, 3 - left, 4 - right, -1 undefined
	 */
	public static int directionFrom(Point origin, Point tester) {
		int ox = origin.x; int tx = tester.x;
		int oy = origin.y; int ty = tester.y;
		
		if(oy > ty && tx < (oy - ty + ox) && tx > (ty + ox - oy)) {
			return 1;
		} else if(oy < ty && tx < (ty + ox - oy) && tx > (oy - ty + ox)) {
			return 2;
		} else if(ox > tx && ty < (ox - tx + oy) && ty > (oy + tx - ox)) {
			return 3;
		} else if(ox < tx && ty < (oy + tx - ox) && ty > (ox - tx + oy)) {
			return 4;
		}
		
		return -1;
	}
	
	public static int distanceTo(int x, int y, int x1, int y1) {
		return (int) Math.sqrt(Math.pow(Math.abs(x - x1), 2) + Math.pow(Math.abs(y - y1), 2));
	}
	
	public static boolean inBoundary(Coords next) {
		Collection<Item> bucket = Main.realm.hmitems.get(next);
		Iterator<Item> iter = bucket.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			if(!i.canWalkOver) {
				return true;
			}
		}
		return false;
	}
	
	public static int ordinalIndexOf(String str, String substr, int n) {
		int pos = str.indexOf(substr);
		while (--n > 0 && pos != -1)
			pos = str.indexOf(substr, pos + 1);
		return pos;
	}
	
	public static Point mouseSnap(Point point) {
		
		int x = point.x;
		int y = point.y;
		
		int lowXCount = 0;
		int lowYCount = 0;
		int highXCount = 0;
		int highYCount = 0;
		
		for(int i = 0; i < 21; i++){
			if((x+i) % 20 == 0){
				lowXCount = i;
				break;
			}
		}
		for(int i = 0; i < 21; i++){
			if((y+i) % 20 == 0){
				lowYCount = i;
				break;
			}
		}
		for(int i = 0; i < 21; i++){
			if((y-i) % 20 == 0){
				highYCount = i;
				break;
			}
		}
		for(int i = 0; i < 21; i++){
			if((x-i) % 20 == 0){
				highXCount = i;
				break;
			}
		}
		
		if(highXCount < lowXCount || highYCount < lowYCount){
			
			return new Point(x-highXCount, y-highYCount);
			
		}else if(lowXCount < highXCount || lowYCount < highYCount){
			
			return new Point(x+lowXCount, y+lowYCount);
			
		}
		
		return point;
	}

}
