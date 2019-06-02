/**
 * 
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 * 
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Comet extends Obstacle {
	public Comet(double x, double y, double w, double h, double vx, double vy) {
		super(setImage(), x, y, w, h, vx, vy);

	}

	private static Image setImage() {
		Image img = null;
		try {
			img = new Image(new FileInputStream("media/images/obstacles/comet.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	@Override
	public boolean fromTop() {
		return true;
	}
	
	@Override
	public boolean checkPlayerCollision(Player sprite) {
        return checkCollision(sprite);
	}
	
}
