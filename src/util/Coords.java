package util;

import java.util.Arrays;

import items.Item;

public class Coords {
	
	public int x;
	public int y;
	
	public Coords() {}
	
	public Coords(int x, int y) {
		this.x = x; this.y = y;
	}
	
	public void set(int x, int y) {
		this.x = x; this.y = y;
	}
	
	public void set(Coords o) {
		this.x = o.x; this.y = o.y;
	}
	
	@Override
	public String toString() {
		return "x:" + x + "y:" + y;
	}
	
    @Override
    public int hashCode() {
    	return this.toString().hashCode();
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Coords o = (Coords) obj;
        
        System.out.println(o.toString() + " " + this.toString());
        return Integer.compare(o.x, this.x) == 0 && Integer.compare(o.y, this.y) == 0;
    }

}
