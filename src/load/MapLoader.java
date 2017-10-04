package load;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import boundaries.Boundary;
import items.Item;
import main.Realm;
import util.Util;

public class MapLoader {

	public static Realm load() {
		
		Realm realm = new Realm();
		
		JFileChooser jfc = new JFileChooser();
		
		FileFilter filter = new FileNameExtensionFilter("Map Files", "map");

		jfc.setDialogType(JFileChooser.OPEN_DIALOG);
		jfc.setDialogTitle("Choose a map");
		jfc.setFileFilter(filter);
		
		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
		
			try {
				Scanner scan = new Scanner(selectedFile);
				
				while(scan.hasNextLine()) {
					realm.items.add(parseItem(scan.nextLine()));
				}
				
				scan.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return realm;
	}
	
	private static Item parseItem(String line) {
		String objName = line.substring(0, line.indexOf('('));
		int x = Integer.parseInt(line.substring(line.indexOf('(')+1, line.indexOf(',')));
		int y = Integer.parseInt(line.substring(line.indexOf(',')+1, line.lastIndexOf(')')));
		
		Class<?> cls;
		Item i = null;
		try {
			cls = Class.forName(objName);
			i = (Item) cls.newInstance();
			i.coords = new int[] {x, y};
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
		
		return i;
	}


}
