/**
 * 
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 * 
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

//Handles obstacles (Asteroids, Cosmonauts. etc extends sprite)
public abstract class Obstacle extends Sprite {
	
	public Obstacle(Image img, double x, double y, double w, double h, double vx, double vy) {
		super(img, x, y, w, h, vx, vy);
	}
	
	//Returns boolean value depending if 1 image overlaps another
	public boolean checkCollision(Sprite sprite) {
        if (x > (sprite.getX() + sprite.getWidth()) || (sprite.getX() > x + w)) { 
            return false; 
        } 
        if (y + h < sprite.getY() || sprite.getY() + sprite.getHeight() < y) { 
            return false; 
        } 
        return true; 
	}
	
	//Returns boolean value depending if 1 hitbox overlaps another (Note hitbox may be smaller than image size)
	public abstract boolean checkPlayerCollision(Player sprite);
	
	//Returns value if image comes from the top of the screen
	public abstract boolean fromTop();
	
	//Adjusts coordinates of image
	public void move(GraphicsContext gc) {
		x += vx;
		y += vy;
	}
}
