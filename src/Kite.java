
/**
 * 
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 * 
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Kite extends Obstacle {
	public Kite(double x, double y, double w, double h, double vx, double vy) {
		super(setImage(), x, y, w, h, vx, vy);

	}

	private static Image setImage() {
		Image img = null;
		try {
			img = new Image(new FileInputStream("media/images/obstacles/kite.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	@Override
	public boolean fromTop() {
		return false;
	}
	
	@Override
	public boolean checkPlayerCollision(Player sprite) {
        if (x > (sprite.getX() + sprite.getWidth()) || (sprite.getX() > x + w)) { 
            return false; 
        } 
        if (y + 0.5 * h < sprite.getY() || sprite.getY() + sprite.getHeight() < y) { 
            return false; 
        } 
        return true; 
	}
	
	@Override
	public double[][] getCornerCoords() {
		double[][] coords = {{x, y}, {x + w, y}, {x, y + 0.5 * h}, {x + w, y + 0.5 * h}};
		return coords;
	}
	
}
