package frame;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import javax.swing.JPanel;

import entity.Deer;
import entity.Wolf;
import items.Axe;
import items.Boundary;
import items.Bread;
import items.Fire;
import items.Furnace;
import items.Hammer;
import items.Iron;
import items.IronOre;
import items.IronTrinket;
import items.Item;
import items.MoltenIron;
import items.Pickaxe;
import items.Plant;
import items.Rock;
import items.ShallowWater;
import items.Shrub;
import items.Sparker;
import items.Stick;
import items.Stone;
import items.Sword;
import items.Venison;
import items.Water;
import items.Wheat;
import items.WheatSeed;
import items.Wood;
import main.MapCreator;
import util.Util;

public class Palette {
	
	private JPanel panel;
	public LinkedList<Item> items;
	public LinkedList<Boundary> boundaries;
	
	public boolean opened;
	public boolean clicked;
	public int toLeft;
	
	public Item item_selection;
	public Boundary boundary_selection;
	public Point selectionPlacement;
	public String item_description;

	public Palette(CreatorPanel creatorPanel) {
		this.panel = creatorPanel;
		toLeft = 50;
		clicked = true;
		opened = true;
		items = new LinkedList<Item>();
		boundaries = new LinkedList<Boundary>();
		item_description = null;
		selectionPlacement = null;
		addItems();
		addBoundaries();
		addIBBounds();
		attachListeners();
	}
	
	private void addIBBounds() {
		int x = panel.getWidth()-440;
		int y = 40;
		int yf = y;
		for(Item i : items) {
			
			i.coords = new int[] {x/20, y/20}; 
			
			if(x >= panel.getWidth()-40) {
				y+=40;
				yf = y;
				x = panel.getWidth()-440;
			} else {
				x+=40;
			}
		}
		
		x = panel.getWidth()-440;
		y = yf + 80;
		for(Boundary i : boundaries) {
			
			i.coords = new int[] {x/20, y/20}; 
			
			if(x >= panel.getWidth()-40) {
				y+=40;
				x = panel.getWidth()-440;
			} else {
				x+=40;
			}
		}
	}
	
	private void addBoundaries() {
		boundaries.add(new Water());
	}
	
	private void addItems() {
		items.add(new Axe());
		items.add(new Bread());
		items.add(new Fire());
		items.add(new Furnace());
		items.add(new Hammer());
		items.add(new Iron());
		items.add(new IronOre());
		items.add(new IronTrinket());
		items.add(new MoltenIron());
		items.add(new Pickaxe());
		items.add(new Plant());
		items.add(new Rock());
		items.add(new ShallowWater());
		items.add(new Shrub());
		items.add(new Sparker());
		items.add(new Stick());
		items.add(new Stone());
		items.add(new Sword());
		items.add(new Venison());
		items.add(new Wheat());
		items.add(new WheatSeed());
		items.add(new Wood());
		items.add(new Deer());
		items.add(new Wolf());
	}

	private void attachListeners() {
		attachMakeSelectionListener();
		attachMouseMotionListener();
	}
	
	private void attachMouseMotionListener() {
		panel.addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent arg0) {}
			
			@Override
			public void mouseMoved(MouseEvent mouse) {
				Point p = Util.mouseSnap(mouse.getPoint());
				
				if(item_selection != null || boundary_selection != null) {
					selectionPlacement = p;
				}
				
				if(!opened) {
					item_description = null;
					return;
				}
				
				for(Item b : items) {
					if(Util.inArea(new Rectangle(b.coords[0]*20, b.coords[1]*20, 20, 20), p)) {
						item_description = b.getClass().getSimpleName();
						return;
					}
				}
				for(Boundary b : boundaries) {
					if(Util.inArea(new Rectangle(b.coords[0]*20, b.coords[1]*20, 20, 20), p)) {
						item_description = b.getClass().getSimpleName();
						return;
					}
				}
				
				item_description = null;
			}
			
		});
	}

	private void attachMakeSelectionListener() {
		panel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent mouse){
				if(Util.inArea(tabBounds(), mouse.getPoint())){
					clicked = true;
					return;
				}
				
				if(opened) {
					makeSelection(mouse);
				}
			}

			private void makeSelection(MouseEvent mouse) {
				
				Point p = Util.mouseSnap(mouse.getPoint());
				p.x = p.x+5;
				p.y = p.y+5;
				
				if(item_selection != null && boundary_selection == null) {
					//place item
					item_selection.coords = new int[] {(p.x-5)/20,(p.y-5)/20};
					MapCreator.realm.items.add(item_selection);
					try {
						item_selection = item_selection.getClass().newInstance();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					
				} else if(item_selection == null && boundary_selection != null) {
					//place boundary
					boundary_selection.coords = new int[] {(p.x-5)/20,(p.y-5)/20};
					MapCreator.realm.boundaries.add(boundary_selection);
					try {
						boundary_selection = boundary_selection.getClass().newInstance();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					
				} else {
					//get selection
					for(Item b : items) {
						if(Util.inArea(new Rectangle(b.coords[0]*20, b.coords[1]*20, 20, 20), p)) {
							item_selection = b;
							return;
						}
					}
					for(Boundary b : boundaries) {
						if(Util.inArea(new Rectangle(b.coords[0]*20, b.coords[1]*20, 20, 20), p)) {
							boundary_selection = b;
							return;
						}
					}
				}
				
			}
		});
	}
	
	public void clearBoundarySelection() {
		boundary_selection = null;
	}
	
	public void clearItemSelection() {
		item_selection = null;
	}
	
	public Rectangle tabBounds() {
		if(opened) {
			return new Rectangle(panel.getWidth()-500, 0, 50, 80);
		} else {
			return new Rectangle(panel.getWidth()-50,0,50, 80);
		}
	}
	
	public Rectangle bounds() {
		if(opened) {
			return new Rectangle(panel.getWidth()-450, 0, 450, panel.getHeight());
		} else {
			return new Rectangle(0,0,0,0);
		}
	}
	
	public void move(double delta){
		
		if(clicked) {
			if(opened) {
				
				toLeft -= 40*delta;
				
				if(toLeft <= 50){
					toLeft = 50;
					opened = false;
					clicked = false;
				}
				
			} else {
				
				toLeft += 40*delta;
				
				if(toLeft >= 500){
					toLeft = 500;
					opened = true;
					clicked = false;
				}
				
			}
		}
	}

	

}
