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
	
	public abstract boolean checkCollision(Sprite sprite);
	
	public void move(GraphicsContext gc) {
		x += vx;
		y += vy;
		if(y > gc.getCanvas().getHeight() + 10) {
			x = (Math.random() * gc.getCanvas().getWidth());
			y = (Math.random() * 20 - 20);
		}
	}
	/*public boolean checkSegmentOverlap(double x, double y, double i, double j, double a, double b, double c, double d) {
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
		double[][] coords = getCornerCoords();
		double[][] spriteCoords = sprite.getCornerCoords();
		double x, y, i, j, a, b, c, d;
		for(int k = 0; k < coords.length; k++) { 
			x = coords[k][0];
			y = coords[k][1];
			if(k == getCornerCoords().length - 1) {
				i = coords[0][0];
				j = coords[0][1]; 
			}
			else {
				i = coords[k + 1][0];
				j = coords[k + 1][1];
			}
			for(int l = 0; l < spriteCoords.length; l++) {
				a = spriteCoords[l][0];
				b = spriteCoords[l][1];
				
				if(l == getCornerCoords().length - 1) {
					c = spriteCoords[0][0];
					d = spriteCoords[0][1]; 
				}
				else {
					c = spriteCoords[l + 1][0];
					d = spriteCoords[l + 1][1];
				}
				if(checkSegmentOverlap(x, y, i, j, a, b, c, d)) {
					return true;
				}
			}
		}
		return false;
	}*/
}
