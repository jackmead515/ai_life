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
import util.RefStrings;

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
		
		creatorPanel = new CreatorPanel();
		creatorPanel.setBounds(0, 0, RefStrings.gameWidth, RefStrings.gameHeight);
		frame.getContentPane().add(creatorPanel);
		
		palette = new Palette(creatorPanel);
		
	}
}
