package misc;

/**
 * A Point contains the data associated with 
 * the equivalent of 
 * "touching your marker to the whiteboard".
 * Currently, it just contains the x- and y-coordinates
 * of the mark, but later it may contain the size, color, etc.
 * @author Matt
 */
public class Point {
    //public so we don't have to create a stack frame to access using a getter,
    //faster this way.
    public final int x;
    public final int y;
    
    public Point(int xc, int yc){
        x = xc;
        y = yc;
    }
    
    @Override
    public boolean equals(Object o){
        boolean ret = false;
        if(o instanceof Point){
            Point p = (Point)o;
            ret = p.x == x && p.y == y;
        }
        return ret;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.x;
        hash = 11 * hash + this.y;
        return hash;
    }
    
    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}
