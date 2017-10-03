package util;

import java.awt.Point;
import java.awt.Shape;

import items.Boundary;
import main.Main;

public class Util {
	
	public static boolean inArea(Shape a, Point p){
		return a.getBounds().contains(p);
	}
	
	public static boolean inBoundary(int[] next) {
		int x = next[0];
		int y = next[1];
		
		for(Boundary b : Main.realm.boundaries) {
			int bx = b.coords[0];
			int by = b.coords[1];
			
			if(x == bx && y == by) {
				if(!b.canWalkOver) {
					return true;
				}
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
