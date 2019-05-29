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
	private int counter;
	private int initialSpawn = 6;
	private final static int STAGE_1 = 60;
	private final static int STAGE_2 = 120;
	private final static int STAGE_3 = 180;
	private final static int STAGE_4 = 240;
	
	public ObstacleGen(GraphicsContext gc) {
		this.gc = gc;
		counter = 0;
		
		obstacles = new ArrayList<Obstacle>();
		for(int i = 0; i < initialSpawn; i++) {
			spawn();
		}
		
	}
	
	public void generate(int counter) {
	}
	
	public void moveAll() {
		for(int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).move(gc);
			if(obstacles.get(i).getY() > gc.getCanvas().getHeight() + 50) { //50 as buffer to ensure off screen
				obstacles.remove(i);
				spawn();
			}
		}
	}
	
	public void spawn() {
		Obstacle obs = null;
		if(counter >= 0 && counter < STAGE_1) {
			obs = new Cloud(Math.random() * gc.getCanvas().getWidth() - 100, Math.random() * -1 * ((gc.getCanvas().getHeight() * 1.25)) - 100, 200.0, 100.0, 0, 5.0);
		}
		else if(counter > STAGE_1 && counter < STAGE_2) {
			
		}
		else if(counter > STAGE_2 && counter < STAGE_3) {
			
		}
		else if(counter > STAGE_3 && counter < STAGE_4) {
			
		}
		else if(counter > STAGE_4) {
			
		}
		
		obstacles.add(obs);
		adjustObstacles();
	}
	
	public boolean checkCollisionAll(Sprite thing) {
		for(Obstacle obs : obstacles) {
			if(obs.checkCollision(thing)) {
				return true;
			}
		}
		return false;
	}
	
	public void adjustObstacles() {
		for(int i = 0; i < obstacles.size(); i++) {
			if(obstacles.get(i).getY() > 0) {
				continue;
			}
			else {
				while(checkCollisionAllObstacles(obstacles.get(i), i)) {
					obstacles.get(i).setX(Math.random() * (gc.getCanvas().getWidth() - obstacles.get(i).getWidth()));
					obstacles.get(i).setY(Math.random() * -1 * ((gc.getCanvas().getHeight() * 1.25)) - obstacles.get(i).getHeight());
				}
			}
		}
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
	
	public void counterAdd() {
		counter++;
	}
	
	public int getCounter() {
		return counter;
	}
}
