/**
 *
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 * 
 * Dennis Perepech: Implemented logic for obstacleGen & TakeOff, Implemented sound
 * Benjamen Bielecki: Developed all images, handled gradient, Implemented key events
 *
 * Both: Handled class design & inheritance of objects (ie Asteroid, Cosmonaut, etc.)
 */
import javafx.scene.text.*;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;


public class TakeOff extends Application {
	private int screenWidth, screenHeight;
	private Player player;
	private boolean gameOver;
	private ObstacleGen obstacleGen;
	private Timeline tme;
	private int endTime;
	private boolean easyMode;

	int[] rVals = {240, 0, 0, 0};
	int[] gVals = {245, 10, 0, 0};
	int[] bVals = {250, 96, 0, 0};
	int[] r2Vals = {120, 240, 0, 0};
	int[] g2Vals = {190, 245, 10, 0};
	int[] b2Vals = {250, 250, 96, 0};

	public TakeOff() {
		gameOver = false;
		easyMode = false;
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = gd.getDisplayMode().getWidth()/2;
		screenHeight = gd.getDisplayMode().getHeight();

		player = new Player(screenWidth / 2 + (0.5 * 25), screenHeight - 250, 30, 300);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		//create the canvas
		Canvas canvas = new Canvas(screenWidth, screenHeight);
		canvas.setFocusTraversable(true);

		//Create GraphicContext object
		GraphicsContext gc = canvas.getGraphicsContext2D();

		obstacleGen = new ObstacleGen(gc);

		//Loop frequency
		Timeline tme = new Timeline(new KeyFrame(Duration.millis(17), e -> run(gc)));
		Timeline clock = new Timeline(new KeyFrame(Duration.millis(10), e -> obstacleGen.counterAdd()));
		tme.setCycleCount(Timeline.INDEFINITE);
		clock.setCycleCount(Timeline.INDEFINITE);

		primaryStage.setTitle("TakeOff");
		primaryStage.setScene(new Scene(new StackPane(canvas)));
		primaryStage.show();
		primaryStage.setFullScreen(true);
		tme.play();
		clock.play();
		
		//Establish Sounds/SOundtrack
		String bip = "media/sounds/soundtrack.mp3";
        Media hit = new Media(Paths.get(bip).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(hit.getSource());
        mediaPlayer.setVolume(0.3);
        mediaPlayer.setCycleCount(AudioClip.INDEFINITE);
        mediaPlayer.play();

	     // handle key events
        canvas.setOnKeyPressed(e -> {
        	if(!gameOver) {
		    	if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP && player.getY() > 0) {
		            player.setVY(-5f);
		        }if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT && player.getX() > 0) {
		            player.setVX(-5f);
		        }if (e.getCode() == KeyCode.S  || e.getCode() == KeyCode.DOWN && player.getY() < screenHeight) {
		            player.setVY(5f);
		        }if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT && player.getX() < screenWidth) {
		            player.setVX(5f);
		        }if (e.getCode() == KeyCode.P) {
		        	if(!easyMode) {
		        		//easyMode = true;
		        	}
		        	else {
		        		easyMode = false;
		        	}
		        }

        	}
        	else {//Closes game when gameOver & enter is pressed
        		if(e.getCode() == KeyCode.ENTER) {
        		primaryStage.close();
        		}
        	}
        	});

        canvas.setOnKeyReleased(e -> {
        	if(!gameOver) {
	            if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) {
	            	player.setVY(0f);
	            } else if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) {
	            	player.setVX(0f);
	            } else if (e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) {
	            	player.setVY(0f);
	            } else if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) {
	            	player.setVX(0f);
	            }
        	}
        });
	}



	//Loop method
	private void run(GraphicsContext gc) {
		//Handles gradient in background
		int r = 0;
		int g = 0;
		int b = 0;
		int r2 = 0;
		int g2 = 0;
		int b2 = 0;

		if(obstacleGen.getCounter() >= 0 && obstacleGen.getCounter() < obstacleGen.STAGE_1) {
			r = rVals[0];
			g = gVals[0];
			b = bVals[0];
			r2 = r2Vals[0] + (int) ((r2Vals[1] - r2Vals[0]) * (double) obstacleGen.getCounter()/obstacleGen.STAGE_1);
			g2 = g2Vals[0] + (int) ((g2Vals[1] - g2Vals[0]) * (double) obstacleGen.getCounter()/obstacleGen.STAGE_1);
			b2 = b2Vals[0];
		}
		else if(obstacleGen.getCounter() >= obstacleGen.STAGE_1 && obstacleGen.getCounter() < obstacleGen.STAGE_2) {
			r = rVals[0] + (int) ((rVals[1] - rVals[0]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_1)/(obstacleGen.STAGE_2  - obstacleGen.STAGE_1));
			g = gVals[0] + (int) ((gVals[1] - gVals[0]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_1)/(obstacleGen.STAGE_2  - obstacleGen.STAGE_1));
			b = bVals[0] + (int) ((bVals[1] - bVals[0]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_1)/(obstacleGen.STAGE_2  - obstacleGen.STAGE_1));
			r2 = r2Vals[1];
			g2 = g2Vals[1];
			b2 = b2Vals[1];
		}
		else if(obstacleGen.getCounter() >= obstacleGen.STAGE_2 && obstacleGen.getCounter() < obstacleGen.STAGE_3) {
			r = rVals[1] + (int) ((rVals[2] - rVals[1]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_2)/(obstacleGen.STAGE_3  - obstacleGen.STAGE_2));
			g = gVals[1] + (int) ((gVals[2] - gVals[1]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_2)/(obstacleGen.STAGE_3  - obstacleGen.STAGE_2));
			b = bVals[1] + (int) ((bVals[2] - bVals[1]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_2)/(obstacleGen.STAGE_3  - obstacleGen.STAGE_2));
			r2 = r2Vals[1] + (int) ((r2Vals[2] - r2Vals[1]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_2)/(obstacleGen.STAGE_3  - obstacleGen.STAGE_2));
			g2 = g2Vals[1] + (int) ((g2Vals[2] - g2Vals[1]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_2)/(obstacleGen.STAGE_3  - obstacleGen.STAGE_2));
			b2 = b2Vals[1] + (int) ((b2Vals[2] - b2Vals[1]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_2)/(obstacleGen.STAGE_3  - obstacleGen.STAGE_2));
		}
		else if(obstacleGen.getCounter() >= obstacleGen.STAGE_3 && obstacleGen.getCounter() < obstacleGen.STAGE_4) {
			r = rVals[2] + (int) ((rVals[3] - rVals[2]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_3)/(obstacleGen.STAGE_4  - obstacleGen.STAGE_3));
			g = gVals[2] + (int) ((gVals[3] - gVals[2]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_3)/(obstacleGen.STAGE_4  - obstacleGen.STAGE_3));
			b = bVals[2] + (int) ((bVals[3] - bVals[2]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_3)/(obstacleGen.STAGE_4  - obstacleGen.STAGE_3));
			r2 = r2Vals[2] + (int) ((r2Vals[3] - r2Vals[2]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_3)/(obstacleGen.STAGE_4  - obstacleGen.STAGE_3));
			g2 = g2Vals[2] + (int) ((g2Vals[3] - g2Vals[2]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_3)/(obstacleGen.STAGE_4  - obstacleGen.STAGE_3));
			b2 = b2Vals[2] + (int) ((b2Vals[3] - b2Vals[2]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_3)/(obstacleGen.STAGE_4  - obstacleGen.STAGE_3));		
		}
		else if(obstacleGen.getCounter() >= obstacleGen.STAGE_4) {

		}

        Stop[] stops = new Stop[] {new Stop(1, Color.rgb(r, g, b)), new Stop(0, Color.rgb(r2, g2, b2))};
	    LinearGradient lg1 = new LinearGradient(0, 1, 0, 0, true, CycleMethod.NO_CYCLE, stops);

	    //Loads gameOver screen if it is game over
		if(obstacleGen.checkCollisionPlayerAll(player) && !easyMode) {
			if(!gameOver) {
				endTime = obstacleGen.getCounter() / 1000;
			}
			gameOver = true;
			Image img = null;
			try {
				img = new Image(new FileInputStream("media/images/title.png"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			gc.setFill(Color.WHITE);
			gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
			gc.setFill(Color.BLACK);
			gc.fillText("GAME OVER! YOU LASTED " + endTime + " SECONDS!\nPRESS ENTER TO CLOSE THE GAME", gc.getCanvas().getWidth() / 4, gc.getCanvas().getHeight() / 2);
			gc.drawImage(img, gc.getCanvas().getWidth() / 4, gc.getCanvas().getWidth() * 0.75, 500, 250);
			tme.stop();
		}
		
		//Increases difficulty every so often
		if(obstacleGen.getCounter() > 0 && obstacleGen.getCounter() < 120000 && obstacleGen.getCounter() % 5100 < 20) {
			obstacleGen.makeHarder();
		}
		if (obstacleGen.getCounter() % 5200 < 20) {
			obstacleGen.makeReadyToUpdate();
		}
		
        gc.setFill(lg1);
        gc.fillRect(0, 0, screenWidth, screenHeight);
        
        //gc.setStroke(Color.BLACK); //Manages player hitbox display
		//gc.strokeRect(player.getCornerCoords()[0][0], player.getCornerCoords()[0][1], player.getWidth(), player.getHeight());

        //Updates position of objects & draws them
		player.move(gc);
		player.draw(gc);

		obstacleGen.drawAll();
		obstacleGen.moveAll();

		//Manages T-Plus time in bottom left
		gc.setFill(Color.BLACK);
		if(obstacleGen.getCounter() > obstacleGen.STAGE_3) {
			gc.setFill(Color.WHITE);
		}
		gc.setFont(new Font("Arial", 25));
		gc.fillText("T-Plus: " + obstacleGen.getCounter() / 1000 + " Seconds", 0, gc.getCanvas().getHeight() - 5);
	}

	//Run application
	public static void main(String[] args) {
        Application.launch(args);
    }
}
