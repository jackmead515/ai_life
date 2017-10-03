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

import items.Boundary;
import items.Item;
import main.Realm;
import util.Util;

public class MapLoader {

	public static Realm load() {
		
		Realm realm = null;
		
		JFileChooser jfc = new JFileChooser();
		
		FileFilter filter = new FileNameExtensionFilter("Map Files", "map");

		jfc.setDialogType(JFileChooser.OPEN_DIALOG);
		jfc.setDialogTitle("Choose a map");
		jfc.setFileFilter(filter);
		
		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
		
			FileInputStream fin = null;
			ObjectInputStream ois = null;

			try {

				fin = new FileInputStream(selectedFile);
				ois = new ObjectInputStream(fin);
				realm = (Realm) ois.readObject();

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {

				if (fin != null) {
					try {
						fin.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (ois != null) {
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}
		
		return realm;
	}


}
