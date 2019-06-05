import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.image.Image;

/**
 * 
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0s
 * 
 */
//player class
public class Player extends Sprite {
	public Player(double x, double y, double w, double h) {
		super(getImage(), x, y, w, h, 0, 0);

	}
	
	public Player(double x, double y) {
		super(getImage(), x, y, getImage().getWidth(), getImage().getHeight(), 0, 0);

	}
	
	//Sets image
	public static Image getImage() {
		Image img = null;
		try {
			img = new Image(new FileInputStream("media/images/playerObjects/saturnV.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	//Applies special move method to limit movement to just onscreen
	@Override
	public void move(GraphicsContext gc) {
		if((y < 0 && vy < 0) || (y > gc.getCanvas().getHeight() - h && vy > 0) || (x < 0 && vx < 0) || (x > gc.getCanvas().getWidth() - w && vx > 0)) {
			return;
		}
		x += vx;
		y += vy;
	}
	
	//returns special height of rocket (to avoid hitbox being on thrust of rocket)
	@Override
	public double getHeight() {
		return 0.8 * h;
	}
}
