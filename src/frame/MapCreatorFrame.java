package frame;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import main.Main;
import main.MapCreator;
import main.Realm;
import main.Registry;

public class MapCreatorFrame {
	
	public JFrame frame;
	public CreatorPanel creatorPanel;
	public static Palette palette;

	public MapCreatorFrame() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		frame.pack();
		
		creatorPanel = new CreatorPanel(frame);
		frame.getContentPane().add(creatorPanel);
		
		palette = new Palette(creatorPanel);
		
	}
}
