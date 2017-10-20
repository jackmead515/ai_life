package load;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import items.Item;
import main.MapCreator;
import main.Realm;

public class MapSaver {
	
	public static void save(Realm realm) {
		
		JFileChooser jfc = new JFileChooser();
		
		FileFilter filter = new FileNameExtensionFilter("Map Files", "map");

		jfc.setDialogType(JFileChooser.SAVE_DIALOG);
		jfc.setDialogTitle("Save your map");
		jfc.setFileFilter(filter);
		
		int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			
			try {
				PrintWriter print = new PrintWriter(selectedFile);
				
				print.flush();
				for(Item i : realm.items) {
					print.println(parseItem(i));
				}
				print.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static String parseItem(Item i) {
		String s = i.getClass().getName();
		s += "(" + i.coords.x() + "," + i.coords.y() + ")";
		return s;
	}

}
