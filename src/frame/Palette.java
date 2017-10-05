package frame;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import javax.swing.JPanel;

import boundaries.Boundary;
import boundaries.Water;
import entity.Deer;
import entity.Wolf;
import food.Bread;
import food.Venison;
import items.ArrowBundle;
import items.Barrel;
import items.Chest;
import items.Crate;
import items.Fire;
import items.Furnace;
import items.Iron;
import items.IronOre;
import items.IronTrinket;
import items.Item;
import items.MoltenIron;
import items.Plant;
import items.Rock;
import items.ShallowWater;
import items.Shrub;
import items.Stick;
import items.Stone;
import items.RawVenison;
import items.Wheat;
import items.WheatSeed;
import items.Wood;
import load.MapLoader;
import load.MapSaver;
import main.MapCreator;
import tools.Axe;
import tools.Hammer;
import tools.Pickaxe;
import tools.Sparker;
import util.Util;
import weapons.Arrow;
import weapons.Bow;
import weapons.Sword;

public class Palette {
	
	private JPanel panel;
	public LinkedList<Item> items;
	
	public boolean opened;
	public boolean clicked;
	public int toLeft;
	
	public Item selection;
	public Point selectionPlacement;
	public Point undoPlacement;
	public Point cancelPlacement;
	public Point savePlacement;
	public Point loadPlacement;
	public String item_description;

	public Palette(CreatorPanel creatorPanel) {
		this.panel = creatorPanel;
		toLeft = 50;
		clicked = true;
		opened = true;
		items = new LinkedList<Item>();
		item_description = null;
		selectionPlacement = null;
		addItems();
		addBounds();
		attachListeners();
	}

	private void addBounds() {
		int x = panel.getWidth()-440;
		int y = 40;
		for(Item i : items) {
			
			i.coords = new int[] {x/20, y/20}; 
			
			if(x >= panel.getWidth()-40) {
				y+=40;
				x = panel.getWidth()-440;
			} else {
				x+=40;
			}
		}
		
		cancelPlacement = new Point(panel.getWidth()-300, 20);
		undoPlacement = new Point(panel.getWidth()-280, 20);
		savePlacement = new Point(panel.getWidth()-200, 20);
		loadPlacement = new Point(panel.getWidth()-120, 20);
		
	}
	
	private void addItems() {
		items.add(new Axe());
		items.add(new Hammer());
		items.add(new Pickaxe());
		items.add(new Sparker());
		items.add(new Sword());
		items.add(new Bow());
		items.add(new Arrow());
		
		items.add(new Fire());
		items.add(new Furnace());
		items.add(new Iron());
		items.add(new IronOre());
		items.add(new IronTrinket());
		items.add(new MoltenIron());
		items.add(new Plant());
		items.add(new Rock());
		items.add(new ShallowWater());
		items.add(new Shrub());
		items.add(new Stick());
		items.add(new Stone());
		items.add(new RawVenison());
		items.add(new Wheat());
		items.add(new WheatSeed());
		items.add(new Wood());
		items.add(new Barrel());
		items.add(new Chest());
		items.add(new Crate());
		items.add(new ArrowBundle());
		
		items.add(new Bread());
		items.add(new Venison());
		
		items.add(new Deer());
		items.add(new Wolf());
		
		items.add(new Water());
	}

	private void attachListeners() {
		attachMakeSelectionListener();
		attachMouseMotionListener();
		attachKeyListener();
	}
	
	private void attachKeyListener() {
		panel.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
					
		        }
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_P) {
					clicked = true;
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {}
		});
	}

	private void attachMouseMotionListener() {
		panel.addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent arg0) {}
			
			@Override
			public void mouseMoved(MouseEvent mouse) {
				Point p = Util.mouseSnap(mouse.getPoint());
				
				if(selection != null) {
					selectionPlacement = p;
				}
				
				for(Item b : items) {
					if(Util.inArea(new Rectangle(b.coords[0]*20, b.coords[1]*20, 20, 20), p)) {
						item_description = b.description();
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
				
				if(Util.inArea(new Rectangle(undoPlacement.x, undoPlacement.y-20, 20, 20), p)) {
					if(MapCreator.realm.items.size() > 0) {
						MapCreator.realm.items.removeLast();
					}
					return;
				}
				
				if(Util.inArea(new Rectangle(cancelPlacement.x, cancelPlacement.y-20, 20, 20), p)) {
					selection = null;
					return;
				}
				
				if(Util.inArea(new Rectangle(savePlacement.x, savePlacement.y-20, 80, 20), p)) {
					MapSaver.save(MapCreator.realm);
					return;
				}
				
				if(Util.inArea(new Rectangle(loadPlacement.x, loadPlacement.y-20, 80, 20), p)) {
					MapCreator.realm = MapLoader.load();
					return;
				}
				
				p.x = p.x+5;
				p.y = p.y+5;
				
				if(selection != null) {
					//place item
					if(Util.inArea(tabBounds(), p) || Util.inArea(bounds(), p)) {
						return;
					}
					try {
						Object o = selection.getClass().newInstance();
						((Item) o).coords = new int[] {(p.x-5)/20,(p.y-5)/20};
						MapCreator.item = (Item) o;
					} catch (InstantiationException e1) {
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						e1.printStackTrace();
					}
				} else {
					//get selection
					for(Item b : items) {
						if(Util.inArea(new Rectangle(b.coords[0]*20, b.coords[1]*20, 20, 20), p)) {
							selection = b;
							return;
						}
					}
				}
				
			}
		});
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
