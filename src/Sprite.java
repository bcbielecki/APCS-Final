/**
 * 
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 * 
 */


public abstract class Sprite {

	protected int x, y;
	protected double vx, vy;
	protected boolean isVisible;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public double getVX() {	
		return vx;
	}
	
	public double getVY() {
		return vy;
	}
	
	public boolean isVisible() {
	    return isVisible;
	  }	
}
