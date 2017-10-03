package load;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.MapCreator;

public class MapSaver {
	
	public static void save() {
		
		JFileChooser jfc = new JFileChooser();
		
		FileFilter filter = new FileNameExtensionFilter("Map Files", "map");

		jfc.setDialogType(JFileChooser.SAVE_DIALOG);
		jfc.setDialogTitle("Save your map");
		jfc.setFileFilter(filter);
		
		int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
		
			FileOutputStream fout = null;
			ObjectOutputStream oos = null;

			try {

				fout = new FileOutputStream(selectedFile);
				oos = new ObjectOutputStream(fout);
				
				oos.writeObject(MapCreator.realm);

			} catch (Exception ex) {

				ex.printStackTrace();

			} finally {

				if (fout != null) {
					try {
						fout.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (oos != null) {
					try {
						oos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}

		
	}

}
