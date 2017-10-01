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

}
