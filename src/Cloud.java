
/**
 * 
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 * 
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Cloud extends Obstacle {
	public Cloud(int x, int y, int w, int h, int vx, int vy) {
		super(setImage(), x, y, w, h, vx, vy);

	}

	private static Image setImage() {
		Image img = null;
		try {
			img = new Image(new FileInputStream("media/images/obstacles/cloud.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	@Override
	public boolean checkCollision(Sprite sprite) {
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
		
		/*for(double[] c : sprite.getCornerCoords()) {
				if((x < c[0] && c[0] < x + w) && (y < c[1] && c[1] < y + h)) {
					return true;
				}
		}
		return false;*/
	}
	
}
