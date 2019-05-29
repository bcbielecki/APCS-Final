/**
 * 
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 * 
 */
import java.util.List;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
		
		obstacles.add(new Cloud(Math.random() * gc.getCanvas().getWidth(), Math.random() * 20 - 20, 200.0, 100.0, 0, 5.0));
		//obstacles.add(new Cloud(Math.random() * gc.getCanvas().getWidth(), Math.random() * 20 - 20, 200.0, 100.0, 0, 5.0));
		obstacles.add(new Cloud(Math.random() * gc.getCanvas().getWidth(), Math.random() * 20 - 20, 200.0, 100.0, 0, 5.0));
	}
	
	public void generate(int counter) {
	}
	
	public void moveAll() {
		for(int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).move(gc);
			if(obstacles.get(i).getY() > gc.getCanvas().getHeight() + 50) {
				do {
					obstacles.get(i).setX(Math.random() * gc.getCanvas().getWidth());
					obstacles.get(i).setY((0 - obstacles.get(i).getHeight() - 20) * Math.random());
			} while(checkCollisionAllObstacles(obstacles.get(i), i));
			}
		}
	}
	
	public boolean checkCollisionAll(Sprite thing) {
		for(Obstacle obs : obstacles) {
			if(obs.checkCollision(thing)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkCollisionAllObstacles(Sprite thing, int index) {
		for(int i = 0; i < obstacles.size(); i++) {
			if(i == index) {
				continue;
			}
			else if(obstacles.get(i).checkCollision(obstacles.get(index))) {
				return true;
			}
		}
		return false;
	}
	
	
	public void drawAll() {
		for(Obstacle obs : obstacles) {
			obs.draw(gc);
			gc.setFill(Color.WHITE);
			gc.strokeRect(obs.getX(), obs.getY(), obs.getWidth(), obs.getHeight());
		}
	}
}
