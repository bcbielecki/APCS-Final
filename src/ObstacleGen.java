/**
 * 
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 * 
 */
import java.util.List;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class ObstacleGen {
	private GraphicsContext gc;
	private List<Obstacle> obstacles;
	private final static int STAGE_1 = 60;
	private final static int STAGE_2 = 120;
	private final static int STAGE_3 = 180;
	private final static int STAGE_4 = 240;
	
	public ObstacleGen(GraphicsContext gc) {
		this.gc = gc;
		obstacles = new ArrayList<Obstacle>();
	}
	
	public void generate(int counter) {
		if(counter >= 60) {
			
		}
	}
	
	public void remove() {
		for(Obstacle obs : obstacles) {
			if()
		}
	}
	
	public void moveAll() {
		for(Obstacle obs : obstacles) {
			obs.move(gc);
		}
	}
	
	public boolean checkCollisionAll(Player player) {
		for(Obstacle obs : obstacles) {
			if(obs.checkCollision(player)) {
				return true;
			}
		}
		return false;
	}
	
	
	public void drawAll() {
		for(Obstacle obs : obstacles) {
			obs.draw(gc);
		}
	}
}
