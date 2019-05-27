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
	
	public boolean checkSegmentOverlap(double x, double y, double i, double j, double a, double b, double c, double d) {
		double t;
		double u; 
		double rs = ((i - x) * (d - b) - (j - y) * (c - a));
		if (rs != 0) {
			t = ( (a-x) * (d - b) - (b - y) * (c - a) ) / rs;
			u = ((a - x) * (j - y) - (b - y) * (i - x)) / rs;
			
			if((0 <= t && t <= 1) && (0 <= u && u <= 1)) {
				return true;
			}
		}
		else if (rs == 0.0 && (a-x) * (d - b) - (b - y) == 0.0 && (x <= a && a <= i || x <= c && c <= i) && (y <= b && b <= j || y <= d && d <= j)) {
			return true;
		}
		return false;
	}
}
