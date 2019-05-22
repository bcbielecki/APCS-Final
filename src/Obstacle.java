import javafx.scene.image.Image;

/**
 * 
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 * 
 */

public abstract class Obstacle extends Sprite {
	
	public Obstacle(Image img, int x, int y, int w, int h, int vx, int vy) {
		super(img, x, y, w, h, vx, vy);
	}

}
