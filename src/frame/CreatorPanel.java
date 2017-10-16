package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import boundaries.Boundary;
import items.Item;
import main.BMPImages;
import main.Main;
import main.MapCreator;

public class CreatorPanel extends JPanel {
	
	public CreatorPanel() {}

	public boolean outOfBounds(int[] pos) {
		int width = this.getWidth();
		int height = this.getHeight();
		int gw = width / 20;
		int gh = height / 20;
		
		int x = pos[0];
		int y = pos[1];
		
		if((y < 0 || x < 0) || (y > gh || x > gw)) {
			return true;
		}
		
		return false;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setFont(new Font("Minion Pro", Font.PLAIN, 15));
		
		drawBackground(g2);
		
		drawGrid(g2);
		
		drawItems(g2);
		
		drawPaletteGUI(g2);
		
		drawSelection(g2);
		
	}
	
	private void drawSelection(Graphics2D g2) {
		
		if(MapCreator.window.palette.selection != null &&
		   MapCreator.window.palette.selectionPlacement != null) {
			
			g2.setColor(new Color(62, 179, 218,150));
			
			int x = MapCreator.window.palette.selectionPlacement.x;
			int y = MapCreator.window.palette.selectionPlacement.y;
			g2.fillRect(x, y, 20, 20);
			
		}
	}

	private void drawPaletteGUI(Graphics2D g2) {
		//g2.drawImage(MapCreator.window.palette.tabImage, (int) (getWidth()-MapCreator.toLeft),0,this);
		g2.setColor(new Color(62, 179, 218,150));
		g2.fillRect( (int) (getWidth()-MapCreator.window.palette.toLeft)+50, 0, 500, getHeight());
		
		g2.setColor(Color.BLACK);
		if(MapCreator.window.palette.opened && !MapCreator.window.palette.clicked) {
			
			for(Item i : MapCreator.window.palette.items) {
				i.draw(g2, this);
			}
			
			if(MapCreator.window.palette.item_description != null) {
				g2.setColor(Color.BLACK);
				
				g2.drawString(MapCreator.window.palette.item_description, this.getWidth()-440, 20);
			}
			
			Point p = MapCreator.window.palette.cancelPlacement;
			Point p1 = MapCreator.window.palette.undoPlacement;
			Point p2 = MapCreator.window.palette.savePlacement;
			Point p3 = MapCreator.window.palette.loadPlacement;
			
			g2.drawString("X", p.x, p.y);
			g2.drawString("<", p1.x, p1.y);
			g2.drawString("Save", p2.x, p2.y);
			g2.drawString("Load", p3.x, p3.y);
			
		}
		
	}

	private void drawItems(Graphics2D g2){ 
		for(Item i : MapCreator.realm.items) {
			i.draw(g2, this);
		}
	}
	
	private void drawBackground(Graphics2D g2) {
		g2.drawImage(BMPImages.background, 0, 0, this);
	}
	
	private void drawGrid(Graphics2D g2) {
		g2.setColor(new Color(0,0,0,20));
		
		for(int i = 0; i < getWidth(); i+=20) {
			g2.drawLine(i, 0, i, getHeight());
		}
		
		for(int i = 0; i < getHeight(); i+=20) {
			g2.drawLine(0, i, getWidth(), i);
		}
	}

}
