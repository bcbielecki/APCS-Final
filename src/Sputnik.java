/**
 * 
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 * 
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Sputnik extends Obstacle {
	public Sputnik(double x, double y, double w, double h, double vx, double vy) {
		super(setImage(), x, y, w, h, vx, vy);

	}

	private static Image setImage() {
		Image img = null;
		try {
			img = new Image(new FileInputStream("media/images/obstacles/sputnik.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	@Override
	public boolean checkPlayerCollision(Player sprite) {
        if (x > (sprite.getX() + sprite.getWidth()) || (sprite.getX() > x + 0.75 * w)) { 
            return false; 
        } 
        if (y + 0.25 * h < sprite.getY() || sprite.getY() + sprite.getHeight() < y) { 
            return false; 
        } 
        return true; 
	}
	
	@Override
	public double[][] getCornerCoords() {
		double[][] coords = {{x + 0.75 * w, y}, {x + w, y}, {x, y + 0.25 * h}, {x + w, y + 0.25 * h}};
		return coords;
	}
	
	@Override
	public boolean fromTop() {
		return false;
	}		
}
