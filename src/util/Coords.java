package util;

public class Coords implements Comparable {
	
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
        
        return Integer.compare(x(), o.x()) == 0 && Integer.compare(y(), o.y()) == 0;
    }

	@Override
	public int compareTo(Object obj) {
		if (this == obj)
            return 1;
        if (obj == null || getClass() != obj.getClass())
            return 1;
        
        Coords o = (Coords) obj;
        if(Integer.compare(x(), o.x()) == 0 && Integer.compare(y(), o.y()) == 0) {
        	return 0;
        }
		return 1;
	}

}
