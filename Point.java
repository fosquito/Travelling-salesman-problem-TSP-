
public class Point {
	
    private double x, y;
    
    //construtor
    public Point(double a, double b) { setX(a); setY(b); }
    
    
    /**
     * Get x coordinate
     * 
     * @return x coordinate
     */
    public double getX() { return x; }
    
    
    /**
     * Get y coordinate
     * 
     * @return Y coordinate
     */
    public double getY() { return y; }
    
    
    /**
     * Set x coordinate of point
     * 
     * @param a
     */
    public void setX(double a) { x = a; }
    
    
    /**
     * Set y coordinate of point
     * 
     * @param b
     */
    public void setY(double b)  {y = b; }
    
    
    /**
     * Function that calculate the distance of two points
     * 
     * @param p
     * @returnm return double distance
     */
    public double dist (Point p) {
        double dx = getX() - p.getX();
        double dy = getY() - p.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }
}