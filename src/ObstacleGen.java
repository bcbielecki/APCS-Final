/**
 *
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 *
 */
import java.util.List;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

//Handles generation of obstacles
public class ObstacleGen {
	private GraphicsContext gc;
	private List<Obstacle> obstacles; //Array list of all obstacles
	private int counter;
	private boolean readyToUpdate;
	private int initialSpawn = 6;
	public final int STAGE_1 = 30000;
	public final int STAGE_2 = 60000;
	public final int STAGE_3 = 90000;
	public final int STAGE_4 = 120000;
	
	private int obstacleLimit = 6;
	private double speedIncrease;
	
	public ObstacleGen(GraphicsContext gc) {
		this.gc = gc;
		counter = 0;
		speedIncrease = 0;
		readyToUpdate = true;

		obstacles = new ArrayList<Obstacle>();
		for(int i = 0; i < initialSpawn; i++) {
			spawn();
		}

	}
	
	//Moves all obstacles, removes obstacle if it is offscreen
	public void moveAll() {
		for(int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).move(gc);
			if(obstacles.get(i).getY() > gc.getCanvas().getHeight() + 50 || 
					obstacles.get(i).getX() < 0 - obstacles.get(i).getWidth() - 50 && obstacles.get(i).getVX() < 0 || 
					obstacles.get(i).getX() > gc.getCanvas().getWidth() + 50 && obstacles.get(i).getVX() > 0 ) { //50 as buffer to ensure off screen
				obstacles.remove(i);
				spawn();
				
			}
		}
		//Also handles obstacleLimit depending on stages
		if(counter > STAGE_2) {
			obstacleLimit = 2;
		}
		else if(counter > STAGE_4) {
			obstacleLimit = 10;
			spawn();
			spawn();
		}
		if(counter > STAGE_2 && obstacles.size() == 0) {
			spawn();
		}
	}

	//Spawns correct object depending on stage
	public void spawn() {
		Obstacle obs = null;
		if(counter >= 0 && counter < STAGE_1) {
			if(Math.random() < 0.5) {
				obs = new Balloon(Math.random() * gc.getCanvas().getWidth() - 55, Math.random() * -1 * gc.getCanvas().getHeight() - 180.0,
						55, 180, 0, Math.random() + 1 + speedIncrease);
			}
			else {
				obs = new Kite(0 - 55 - Math.random() * 55 * 2, Math.random() * gc.getCanvas().getHeight(),
						55, 180, 1.0 + speedIncrease, 1.0 + speedIncrease);
			}
		}
		else if(counter >= STAGE_1 && counter < STAGE_2) {
			if(Math.random() < 0.7) {
				obs = new Cloud(Math.random() * gc.getCanvas().getWidth() - 100, Math.random() * -1 * (gc.getCanvas().getHeight()) - 200,
						200.0, 100.0, 0, 2 * Math.random() + 1.0 + speedIncrease);
			}
			else {
				obs = new Plane(gc.getCanvas().getWidth() + Math.random() * 200, Math.random() * gc.getCanvas().getHeight(),
						200, 58, -4.0 - speedIncrease, 1.0 + speedIncrease);
			}
		}
		else if(counter >= STAGE_2 && counter < STAGE_3) {
			double chance = Math.random();
			if(chance < 0.5) {
			obs = new Sputnik(0 - 240 - Math.random() * 240, Math.random() * gc.getCanvas().getHeight(),
					240.0, 240.0, 7.0 + speedIncrease, 0.5 + speedIncrease);
			}
			else if(chance < 0.55) {
				if(Math.random() < 0.5) {
					obs = new Cosmonaut(0 - 86 - Math.random() * 86, Math.random() * gc.getCanvas().getHeight(), 
							86, 120, 5.0 + speedIncrease, 0.0 + speedIncrease);
				}
				else {
					obs = new Cosmonaut(gc.getCanvas().getWidth() + Math.random() * 86, Math.random() * gc.getCanvas().getHeight(),
							86, 120, -5.0 - speedIncrease, 1.0 + speedIncrease);
				}
			}
			else {
				double scaleFactor = Math.random();
				if(Math.random() > 0.5) {
					obs = new Asteroid(gc.getCanvas().getWidth() + Math.random() * 150, Math.random() * gc.getCanvas().getHeight(),
							scaleFactor * 100, scaleFactor * 100, Math.random() * -8.0 - speedIncrease, 2.0 + speedIncrease);
				}
				else {
					obs = new Asteroid(0 - 150 - Math.random() * 150, Math.random() * gc.getCanvas().getHeight(),
							scaleFactor * 100, scaleFactor * 100, Math.random() * 8.0 + speedIncrease, 2.0 + speedIncrease);
				}
			}
		}
		else if(counter >= STAGE_3 && counter < STAGE_4) {
			if(Math.random() < 0.9) {
				double scaleFactor = Math.random();
				if(Math.random() > 0.5) {
					obs = new Asteroid(gc.getCanvas().getWidth() + Math.random() * 150, Math.random() * gc.getCanvas().getHeight(),
							scaleFactor * 125, scaleFactor * 125, Math.random() * -8.0 - speedIncrease, 2.0 + speedIncrease);
				}
				else {
					obs = new Asteroid(0 - 150 - Math.random() * 150, Math.random() * gc.getCanvas().getHeight(),
							scaleFactor * 125, scaleFactor * 125, Math.random() * 8.0 + speedIncrease, 2.0 + speedIncrease);
				}
			}
			else {
				obs = new Sputnik(0 - 240 - Math.random() * 240, Math.random() * gc.getCanvas().getHeight(),
						240.0, 240.0, 7.0 + speedIncrease, 1.0 + speedIncrease);
			}
		}
		else if(counter >= STAGE_4) {
			double chance = Math.random();
			if(chance < 0.7) {
				double scaleFactor = Math.random();
				if(Math.random() > 0.5) {
					obs = new Asteroid(gc.getCanvas().getWidth() + Math.random() * 150, Math.random() * gc.getCanvas().getHeight(),
							scaleFactor * 200, scaleFactor * 200, Math.random() * -8.0 - speedIncrease, 2.0 + speedIncrease);
				}
				else {
					obs = new Asteroid(0 - 150 - Math.random() * 150, Math.random() * gc.getCanvas().getHeight(),
							scaleFactor * 200, scaleFactor * 200, Math.random() * 8.0 + speedIncrease, 2.0 + speedIncrease);
				}
			}
			else if (chance < 0.95) {
				obs = new Comet(Math.random() * gc.getCanvas().getWidth() - 25, Math.random() * -1 * (gc.getCanvas().getHeight() * 0.5) - 100,
						25.0, 100.0, 0, 2 * Math.random() + 2.0 + speedIncrease);
			}
			else {
				obs = new Enterprise(Math.random() * gc.getCanvas().getWidth() - 100, Math.random() * -1 * (gc.getCanvas().getHeight() * 0.5) - 190,
						100, 190, 0, 2 * Math.random() + 2.0 + speedIncrease);
			}
		}
		
		if(counter >= (STAGE_2 - 9000) && counter < STAGE_2) {	
		}
		else {
			obstacles.add(obs);
		}
		adjustObstacles();
	}

	//Returns true if 1 sprite overlaps another
	public boolean checkCollisionAll(Sprite thing) {
		for(Obstacle obs : obstacles) {
			if(obs.checkCollision(thing)) {
				return true;
			}
		}
		return false;
	}
	
	//Returns true if 1 hitbox overlaps the player
	public boolean checkCollisionPlayerAll(Player thing) {
		for(Obstacle obs : obstacles) {
			if(obs.checkPlayerCollision(thing)) {
				return true;
			}
		}
		return false;
	}

	//Moves obstacles to ensure they do not spawn on top of one another
	public void adjustObstacles() {
		for(int i = 0; i < obstacles.size(); i++) {
			if(obstacles.get(i).getY() > 0 || obstacles.get(i).getX() > 0 && obstacles.get(i).getX() < gc.getCanvas().getWidth()) {
				continue;
			}
			else {
				if(obstacles.get(i).fromTop()) {
					while(checkCollisionAllObstacles(obstacles.get(i), i)) {
						obstacles.get(i).setX(Math.random() * gc.getCanvas().getWidth() - obstacles.get(i).getWidth());
						obstacles.get(i).setY(Math.random() * -1 * gc.getCanvas().getHeight() - obstacles.get(i).getHeight() * 0.5);
					}
				}
				else {
					if(obstacles.get(i).getVX() < 0) {
						while(checkCollisionAllObstacles(obstacles.get(i), i)) {
							obstacles.get(i).setX(gc.getCanvas().getWidth() + Math.random() * obstacles.get(i).getWidth());
							obstacles.get(i).setY(Math.random() * gc.getCanvas().getHeight());
						}
					}
					else {
						while(checkCollisionAllObstacles(obstacles.get(i), i)) {
							obstacles.get(i).setX(0 - obstacles.get(i).getWidth() - Math.random() * 4 * obstacles.get(i).getWidth());
							obstacles.get(i).setY(Math.random() * gc.getCanvas().getHeight());
						}
					}
				}
			}
		}
	}

	//Sees if 1 obstacle from arraylist is overlapping another in the array list & is NOT currently visible
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

	//Updates difficulty of game
	public void makeHarder() {
		if(readyToUpdate) {
			readyToUpdate = false;
			if(obstacles.size() < obstacleLimit) {
				spawn();
			}
			speedIncrease += 0.3;
			for(int i = 0; i < obstacles.size(); i++) {
				obstacles.get(i).setVY(obstacles.get(i).getVY() + 0.3);
			}
		}
	}
	
	//Assists with method above to prevent going off multiple times in 1 second
	public void makeReadyToUpdate() {
		readyToUpdate = true;
	}

	//Draws all obstacles
	public void drawAll() {
		for(Obstacle obs : obstacles) {
			obs.draw(gc);
		}
	}
	
	//Updates counter
	public void counterAdd() {
		counter += 10;
	}
	
	//Returns counter
	public int getCounter() {
		return counter;
	}
	
	//Getter for array list size
	public int getSize() {
		return obstacles.size();
	}
}
