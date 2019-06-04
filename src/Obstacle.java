/**
 * 
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 * 
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Obstacle extends Sprite {
	
	public Obstacle(Image img, double x, double y, double w, double h, double vx, double vy) {
		super(img, x, y, w, h, vx, vy);
	}
	
	public boolean checkCollision(Sprite sprite) {
        if (x > (sprite.getX() + sprite.getWidth()) || (sprite.getX() > x + w)) { 
            return false; 
        } 
        if (y + h < sprite.getY() || sprite.getY() + sprite.getHeight() < y) { 
            return false; 
        } 
        return true; 
	}
	
	public abstract boolean checkPlayerCollision(Player sprite);
	
	public abstract boolean fromTop();
	
	public void move(GraphicsContext gc) {
		x += vx;
		y += vy;
	}
}
