/**
 * 
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 * 
 */
import javafx.scene.image.Image;

public abstract class Sprite {

	protected int x, y;
	protected double vx, vy;
	Image img;
	
	
	public Sprite(Image img, int x, int y, int vx, int vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.img = img;
	}
	
	public void move() {
		x += vx;
		y += vy;
	}
	
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
	
}
