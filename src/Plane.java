
/**
 * 
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 * 
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Plane extends Obstacle {
	public Plane(int x, int y, int w, int h, int vx, int vy) {
		super(setImage(), x, y, w, h, vx, vy);

	}

	private static Image setImage() {
		Image img = null;
		try {
			img = new Image(new FileInputStream("media/plane.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return img;
	}
}
