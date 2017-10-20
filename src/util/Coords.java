package util;

public class Coords {
	
	private int[] coords;
	
	public Coords() {
		coords = new int[2];
	}
	
	public Coords(int x, int y) {
		coords = new int[] {x, y};
	}
	
	public void set(int x, int y) {
		coords[0] = x; coords[1] = y;
	}
	
	public void set(Coords o) {
		coords[0] = o.x(); coords[1] = o.y();
	}
	
	public int x() {
		return coords[0];
	}
	
	public int y() {
		return coords[1];
	}
	
    @Override
    public int hashCode() {
    		int hash = 1;
        for (int e : coords)
            hash = 31 * hash + e;

        return hash;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Coords o = (Coords) obj;
        
        return x() == o.x() && y() == o.x();
    }

}
