/**
 * 
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 * 
 */
import javafx.scene.image.Image;

public abstract class Obstacle extends Sprite {
	
	public Obstacle(Image img, int x, int y, int w, int h, int vx, int vy) {
		super(img, x, y, w, h, vx, vy);
	}
	
	public abstract boolean checkCollision(Sprite sprite);
}
