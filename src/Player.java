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

public class Player extends Sprite {
	public Player(double x, double y, double w, double h, double vx, double vy) {
		super(getImage(), x, y, w, h, vx, vy);
		changeVel(0.0, 0.0);

	}
	
	public Player(double x, double y, double vx, double vy) {
		super(getImage(), x, y, getImage().getWidth(), getImage().getHeight(), vx, vy);

	}
	
	public static Image getImage() {
		Image img = null;
		try {
			img = new Image(new FileInputStream("media/images/playerObjects/saturnV.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return img;
	}


}
