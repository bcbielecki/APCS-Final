/**
 * 
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 * 
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

//Main large class
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
	
	//Moves object, abstract to handle type of movement
	public abstract void move(GraphicsContext gc);
	
	public Image getImg() {
		return img;
	}
	
	//Prints image to screen
	public void draw(GraphicsContext gc) {
		gc.drawImage(img, x, y, w, h);
		//gc.setFill(Color.WHITE);
		//gc.strokeRect(getCornerCoords()[0][0], getCornerCoords()[0][1], getCornerCoords()[1][0] - getCornerCoords()[0][0], getCornerCoords()[2][1] - getCornerCoords()[1][1]);
	}
	
	//Returns coordinates of corner of hitbox, may be overriden in some classes
	public double[][] getCornerCoords() {
		double[][] coords = {{x, y}, {x + w, y}, {x, y + h}, {x + w, y + h}};
		return coords;
	}
	
	//Getters
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
	
	//Setters
	public void setVX(double vx) {
		this.vx = vx;
	}
	
	public void setVY(double vy) {
		this.vy = vy;
	}
	
	public void setX(double x) {
		this.x = x; 
	}
	
	public void setY(double y) {
		this.y = y; 
	}
	
}
