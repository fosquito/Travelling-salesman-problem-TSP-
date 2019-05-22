
public class Cidade {
	
	private int id;
	private Point p;
	
	//Contrutor
	public Cidade (int d, int x, int y) { setId(d); setP(x, y); }
	public Cidade (Cidade c) { setId(c.getId()); setP(c.getP().getX(), c.getP().getY()); }
	
	
	/**
	 * Set value of id
	 * 
	 * @param d
	 */
	private void setId(int d) { id = d; }
	
	
	/**
	 * Set value of point
	 * 
	 * @param x
	 * @param y
	 */
	private void setP(double x, double y) { p = new Point(x, y); }
	
	
	/**
	 * Get City id
	 * 
	 * @return City id
	 */
	public int getId() { return id; }
	
	
	/**
	 * Get City point
	 * @return City point
	 */
	public Point getP() { return p; }
}
