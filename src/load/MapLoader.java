package load;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import items.Boundary;
import items.Item;
import main.Realm;
import util.Util;

public class MapLoader {

	public static Realm load(Path path) {
		LinkedList<Boundary> boundaries = new LinkedList<Boundary>();
		LinkedList<Item> items = new LinkedList<Item>();
		
		File file = new File(path.toString());
		try {
			Scanner scan = new Scanner(file);
			
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				if(!line.startsWith("#")) {
					readLine(line, boundaries, items);	
				}
				
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Realm realm = new Realm();
		realm.boundaries = boundaries;
		realm.items = items;
		return realm;
	}
	
	private static void readLine(String line, LinkedList<Boundary> boundaries, LinkedList<Item> items){
		
			String objName = getCode(line);
			int[] pos = getPosition(line);
			
			try {
				Class<?> cls = Class.forName(objName);
				Object obj = cls.newInstance();
				
				if(obj instanceof Boundary) {
					
					((Boundary) obj).coords = pos;
					boundaries.add((Boundary) obj);
					
				} else if(obj instanceof Item) {
					
					((Item) obj).coords = pos;
					items.add((Item) obj);
					
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	private static int[] getPosition(String line) {
		
		int x = Integer.parseInt(line.substring(line.indexOf("(")+1, Util.ordinalIndexOf(line, ",", 1)));
		int y = Integer.parseInt(line.substring(Util.ordinalIndexOf(line, ",", 1)+1, line.indexOf(")")));
		
		return new int[] {x ,  y};
	}
	
	private static int getSpecialAmount(String line) {
		try {
			return Integer.parseInt(line.substring(line.indexOf("[")+1, line.indexOf("]")));
		} catch (Exception e) {
			return -1;
		}
	}
	
	private static String getSpecialStringAmount(String line) {
		return line.substring(line.indexOf("[")+1, line.indexOf("]"));
	}
	
	private static String getCode(String line){
		return line.substring(0, line.indexOf("("));
	}


}
