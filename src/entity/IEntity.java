package entity;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public interface IEntity {
	
	public void move();
	
	public void draw(Graphics2D g2, JPanel panel);
	
}
