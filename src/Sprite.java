/**
 * 
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 * 
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite {

	protected double x, y;
	protected double vx, vy;
	protected double initVX, initVY;
	protected double w, h;
	Image img;
	
	
	public Sprite(Image img, double x, double y, double w, double h, double vx, double vy) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.initVX = vx;
		this.initVY = vy;
		this.vx = vx;
		this.vy = vy;
		this.img = img;
	}
	
	
	public abstract void move(GraphicsContext gc);
	
	public void changeVel(double vx, double vy) {
		this.vx = vx;
		this.vy = vy;
	}
	
	public Image getImg() {
		return img;
	}
	
	public void draw(GraphicsContext gc) {
		gc.drawImage(img, x, y, w, h);
	}
	
	public double[][] getCornerCoords() {
		double[][] coords = {{x, y}, {x + w, y}, {x, y + h}, {x + w, y + h}};
		return coords;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getWidth() {
		return w;
	}
	
	public double getHeight() {
		return h;
	}
	
	public double getVX() {	
		return initVX;
	}
	
	public double getVY() {
		return initVY;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
}
