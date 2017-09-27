package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import items.Item;
import main.BMPImages;
import main.Main;
import util.Randomizer;

public class GamePanel extends JPanel {
	
	public GamePanel(JFrame frame) {
		setBoundaries(frame);
	}

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
	
	private void setBoundaries(JFrame frame) {
			
			int width = frame.getWidth();
			int height = frame.getHeight();
			
			while(width % 20 != 0) {
				width-=1;
			}
			while(height % 20 != 0) {
				height-=1;
			}
			setBounds(0, 0, width, height);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setFont(new Font("Minion Pro", Font.PLAIN, 15));
		
		drawBackground(g2);
		
		//drawGrid(g2);
		
		drawItems(g2);
		
		drawPlayer(g2);
	
	}
	
	private void drawItems(Graphics2D g2){ 
		for(Item i : Main.registry.items) {
			i.draw(g2, this);
		}
	}
	
	private void drawBackground(Graphics2D g2) {
		g2.drawImage(BMPImages.background, 0, 0, this);
	}
	
	private void drawPlayer(Graphics2D g2) {
		Main.player.draw(g2, this);
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
